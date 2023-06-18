package com.PDFtoCSV;

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.FilteredTextRenderListener;
import com.itextpdf.text.pdf.parser.RenderFilter;
import com.itextpdf.text.pdf.parser.LocationTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.RegionTextRenderFilter;
import com.itextpdf.text.Rectangle;
import java.util.Iterator;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.itextpdf.text.pdf.PdfReader;
import java.util.ArrayList;

public class PDFReader
{
    private ArrayList<soe_repeat> done;
    private String[] parts;
    private String last_word;
    String outputFilePath;
    String instituteCode;
    private String filePath;
    private short pageStart;
    private short pageEnd;
    private short totalPages;
    private PdfReader file;
    private TextExtractionStrategy strategy;
    private DataBlock data;
    private boolean isFirstTime;
    private StringBuilder csvData;
    
    public PDFReader(final String fp) {
        this.data = new DataBlock();
        this.done = new ArrayList<soe_repeat>();
        this.filePath = fp;
        this.pageStart = 1;
        this.isFirstTime = true;
        this.csvData = new StringBuilder();
        try {
            this.file = new PdfReader(this.filePath);
            final short n = (short)this.file.getNumberOfPages();
            this.totalPages = n;
            this.pageEnd = n;
        }
        catch (Exception e) {
            System.out.println("Could not open PDF Input File. Check the Input Path");
            System.exit(0);
        }
    }
    
    public boolean isAlreadyAdded(final String prg, final String scheme) {
        for (final soe_repeat temp : this.done) {
            if (temp.prg.equals(this.data.progCode) && temp.scheme.equals(this.data.soeSchemeId)) {
                return true;
            }
        }
        return false;
    }
    
    public TextExtractionStrategy newArea(final float lly, final float llx, final float ury, final float urx) {
        final Rectangle rect = new Rectangle(lly, llx, ury, urx);
        final RenderFilter filter = (RenderFilter)new RegionTextRenderFilter(rect);
        final TextExtractionStrategy strategy = (TextExtractionStrategy)new FilteredTextRenderListener((TextExtractionStrategy)new LocationTextExtractionStrategy(), new RenderFilter[] { filter });
        return strategy;
    }
    
    public boolean isSoePage(final short pageNo) {
        this.strategy = this.newArea(Coordinates.SOELLY , Coordinates.SOELLX , Coordinates.SOEURY , Coordinates.SOEURX);
        try {
            if (PdfTextExtractor.getTextFromPage(this.file, (int)pageNo, this.strategy).toLowerCase().contains("scheme of examinations")) {
                return true;
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    public void getSoeSchemeId(final short pageNo) {
        this.strategy = this.newArea(Coordinates.PDLLY, Coordinates.PDLLX, Coordinates.PDURY, Coordinates.PDURX);
        try {
            final String pageDetails = PdfTextExtractor.getTextFromPage(this.file, (int)pageNo, this.strategy).toLowerCase();
            this.data.soeSchemeId = pageDetails.substring(pageDetails.indexOf("schemeid") + 10, pageDetails.indexOf("schemeid") + 22);
        }
        catch (Exception ex) {}
    }
    
    public void readPageDetails(final short pageNo) {
        this.strategy = this.newArea(Coordinates.PHLLY, Coordinates.PHLLX, Coordinates.PHURY, Coordinates.PHURX);
        try {
            final String pageheader = PdfTextExtractor.getTextFromPage(this.file, (int)pageNo, this.strategy);
            // System.out.println(pageheader);
            if (!pageheader.toLowerCase().contains("result")) {
                return;
            }
            if (pageheader.toLowerCase().contains("regular")) {
                this.data.examType = "Regular";
            }
            else if (pageheader.toLowerCase().contains("reappear")) {
                this.data.examType = "Reappear";
                return;
            }
            this.data.progCode = pageheader.substring(pageheader.toLowerCase().indexOf("programme code") + 16, pageheader.toLowerCase().indexOf("programme code") + 19);
            this.data.progName = pageheader.substring(pageheader.toLowerCase().indexOf("programme name") + 16, pageheader.toLowerCase().indexOf("sem.")).trim();
            this.data.instCode = pageheader.substring(pageheader.toLowerCase().indexOf("institution code") + 18, pageheader.toLowerCase().indexOf("institution code") + 21);
            this.data.semester = pageheader.substring(pageheader.toLowerCase().indexOf("sem.") + 11, pageheader.toLowerCase().indexOf("sem.") + 13);
            this.data.batch = Short.parseShort(pageheader.substring(pageheader.toLowerCase().indexOf("batch") + 7, pageheader.toLowerCase().indexOf("batch") + 11).trim());
        }
        catch (Exception e) {
            System.out.println("Error occured in reading file");
        }
    }
    
    public void readResultsFromPage(final short pageNo) {
        float lly = Coordinates.SRNLLY;
        float ury = Coordinates.SRNURY;
        float llx = Coordinates.SDLLX;
        float urx = Coordinates.SDURX;
        for (byte i = 0; i < 10; ++i) {
            try {
                this.strategy = this.newArea(lly, llx, ury, urx);
                this.data.rollNo[i] = PdfTextExtractor.getTextFromPage(this.file, (int)pageNo, this.strategy);
                if (this.data.rollNo[i].trim().isEmpty()) {
                    this.data.rollNo[i] = null;
                    break;
                }
                lly = Coordinates.SNLLY + i * Coordinates.SDIY;
                ury = Coordinates.SNURY + i * Coordinates.SDIY;
                this.strategy = this.newArea(lly, llx, ury, urx);
                this.data.name[i] = PdfTextExtractor.getTextFromPage(this.file, (int)pageNo, this.strategy);
                lly = Coordinates.SIDLLY + i * Coordinates.SDIY;
                ury = Coordinates.SIDURY + i * Coordinates.SDIY;
                this.strategy = this.newArea(lly, llx, ury, urx);
                this.data.studentId[i] = PdfTextExtractor.getTextFromPage(this.file, (int)pageNo, this.strategy);
                lly = Coordinates.SSIDLLY + i * Coordinates.SDIY;
                ury = Coordinates.SSIDURY + i * Coordinates.SDIY;
                this.strategy = this.newArea(lly, llx, ury, urx);
                this.data.schemeId[i] = PdfTextExtractor.getTextFromPage(this.file, (int)pageNo, this.strategy);
                this.data.studentId[i] = this.data.studentId[i].substring(this.data.studentId[i].indexOf(":") + 2);
                this.data.schemeId[i] = this.data.schemeId[i].substring(this.data.schemeId[i].indexOf(":") + 2);
                llx = Coordinates.MLLX;
                urx = Coordinates.MURX;
                lly = Coordinates.MLLY + i * Coordinates.SDIY;
                ury = Coordinates.MURY + i * Coordinates.SDIY;
                for (byte j = 0; j < 30; ++j) {
                    this.strategy = this.newArea(lly, llx, ury, urx);
                    this.data.subjectCodes[i][j] = PdfTextExtractor.getTextFromPage(this.file, (int)pageNo, this.strategy);
                    if (this.data.subjectCodes[i][j].trim().isEmpty()) {
                        this.data.subjectCodes[i][j] = null;
                        break;
                    }
                    // this.data.subjectCodes[i][j] = this.data.subjectCodes[i][j].substring(0, this.data.subjectCodes[i][j].indexOf("("));
                    // this.data.subjectCodes[i][j] = this.data.subjectCodes[i][j].replace(/\n/g, "");
                    this.data.subjectCodes[i][j] = this.data.subjectCodes[i][j].replace('\n', ' ');
                    System.out.println(this.data.subjectCodes[i][j]);


                    lly = Coordinates.MLLY + i * Coordinates.SDIY + Coordinates.MRI;
                    ury = Coordinates.MURY + i * Coordinates.SDIY + Coordinates.MRI;
                    urx = urx - Coordinates.MIEI + 0.01f;
                    this.strategy = this.newArea(lly, llx, ury, urx);
                    this.data.internalMarks[i][j] = PdfTextExtractor.getTextFromPage(this.file, (int)pageNo, this.strategy);
                    llx += Coordinates.MIEI;
                    urx += Coordinates.MIEI;
                    this.strategy = this.newArea(lly, llx, ury, urx);
                    this.data.externalMarks[i][j] = PdfTextExtractor.getTextFromPage(this.file, (int)pageNo, this.strategy);
                    llx = Coordinates.MLLX + j * Coordinates.MRSI;
                    urx = Coordinates.MURX + j * Coordinates.MRSI;
                    lly = Coordinates.MLLY + i * Coordinates.SDIY + 40.0f;
                    ury = Coordinates.MURY + i * Coordinates.SDIY + 40.0f;
                    this.strategy = this.newArea(lly, llx, ury, urx);
                    this.data.totalMarks[i][j] = PdfTextExtractor.getTextFromPage(this.file, (int)pageNo, this.strategy);
                    if (this.data.totalMarks[i][j].contains("(")) {
                        this.data.grades[i][j] = this.data.totalMarks[i][j].substring(this.data.totalMarks[i][j].indexOf("(") + 1, this.data.totalMarks[i][j].indexOf(")"));
                        this.data.totalMarks[i][j] = this.data.totalMarks[i][j].substring(0, this.data.totalMarks[i][j].indexOf("("));
                    }
                    if (this.data.totalMarks[i][j].contains("*")) {
                        this.data.totalMarks[i][j] = this.data.totalMarks[i][j].replace("*", "");
                    }
                    if (this.data.internalMarks[i][j].contains("A") || this.data.internalMarks[i][j].contains("C") || this.data.internalMarks[i][j].contains("D") || this.data.internalMarks[i][j].contains("-")) {
                        this.data.internalMarks[i][j] = "0";
                    }
                    if (this.data.externalMarks[i][j].contains("A") || this.data.externalMarks[i][j].contains("C") || this.data.externalMarks[i][j].contains("D") || this.data.externalMarks[i][j].contains("-")) {
                        this.data.externalMarks[i][j] = "0";
                    }
                    if (this.data.totalMarks[i][j].contains("A") || this.data.totalMarks[i][j].contains("C") || this.data.totalMarks[i][j].contains("D") || this.data.totalMarks[i][j].contains("-")) {
                        this.data.totalMarks[i][j] = "0";
                    }
                    if (this.data.internalMarks[i][j].replaceAll("[^A-Z]", "").length() > 1) {
                        final String[] name = this.data.name;
                        final byte b = i;
                        name[b] = String.valueOf(name[b]) + this.data.internalMarks[i][j].replaceAll("[^A-Z\\s]", "").substring(1);
                        this.parts = this.data.name[i].split("  ");
                        this.last_word = this.parts[this.parts.length - 1];
                        this.data.name[i] = this.last_word;
                        this.data.name[i] = this.data.name[i].replaceAll("[^\\x20-\\x7E]", "");
                        this.data.internalMarks[i][j] = this.data.internalMarks[i][j].replaceAll("[^0-9]", "");
                        if (this.data.externalMarks[i][j].replaceAll("[^A-Z]", "").length() > 1) {
                            this.last_word = this.data.externalMarks[i][j].replaceAll("[^A-Z\\s]", "").substring(1);
                            this.parts = this.last_word.split("  ");
                            this.last_word = "";
                            for (int k = 0; k < this.parts.length; ++k) {
                                if (this.parts[k] != " ") {
                                    this.last_word = String.valueOf(this.last_word) + this.parts[k];
                                }
                            }
                            this.data.name[i] = String.valueOf(this.data.name[i]) + this.last_word;
                            this.data.name[i] = this.data.name[i].replaceAll("[^\\x20-\\x7E]", "");
                            this.data.externalMarks[i][j] = this.data.externalMarks[i][j].replaceAll("[^0-9]", "");
                        }
                    }
                    llx = Coordinates.MLLX + (j + 1) * Coordinates.MRSI;
                    urx = Coordinates.MURX + (j + 1) * Coordinates.MRSI;
                    lly -= 40.0f;
                    ury -= 40.0f;
                }
                lly = Coordinates.CLLY + i * Coordinates.SDIY;
                ury = Coordinates.CURY + i * Coordinates.SDIY;
                llx = Coordinates.CLLX;
                urx = Coordinates.CURX;
                this.strategy = this.newArea(lly, llx, ury, urx);
                this.data.credits[i] = Byte.parseByte(PdfTextExtractor.getTextFromPage(this.file, (int)pageNo, this.strategy).trim());
                lly = Coordinates.SRNLLY + (i + 1) * Coordinates.SDIY;
                ury = Coordinates.SRNURY + (i + 1) * Coordinates.SDIY;
                llx = Coordinates.SDLLX;
                urx = Coordinates.SDURX;
            }
            catch (Exception ex) {}
        }
    }
    
    public void processFile() {
        int recordCount = 0;
        for (short i = this.pageStart; i <= this.pageEnd; ++i) {
            final int percent = i * 100 / this.totalPages;
            System.out.println("Processing  : " + percent + " %");
            if (this.isSoePage(i)) {
                this.readPageDetails((short)(i + 1));
            }
            else {
                if (this.data.examType.equals("Regular") && this.data.instCode.equals(this.instituteCode)) {
                    this.readResultsFromPage(i);
                    
                    if (this.isFirstTime) {
                        this.csvData.append("S.No,Enrollment Number,Name,");
                        // this.csvData.append(",");
                        // this.csvData.append("Name");
                        for (int j = 0; j < 30; ++j) {
                            
                            if (this.data.subjectCodes[0][j] != null) {
                                this.csvData.append(this.data.subjectCodes[0][j]+ "," 
                                + this.data.subjectCodes[0][j] + ","
                                + this.data.subjectCodes[0][j] + ",");
                            }
                        }
                        this.csvData.append("\n");
                        this.csvData.append(", ,");
                        for (int j = 0; j < 30; ++j) {
                            if (this.data.subjectCodes[0][j] != null) {
                                this.csvData.append(",");
                                this.csvData.append("Internal");
                                this.csvData.append(",");
                                this.csvData.append("External");
                                this.csvData.append(",");
                                this.csvData.append("Total");
                            }
                        }
                        this.csvData.append("\n");
                        this.isFirstTime = false;
                    }
                    for (int j = 0; j < 10 && this.data.rollNo[j] != null && !this.data.rollNo[j].equals(""); ++j) {
                        recordCount += 1;
                        csvData.append(recordCount + ",");
                        this.csvData.append(this.data.rollNo[j]);
                        this.csvData.append(",");
                        this.csvData.append(this.data.name[j]);
                        this.csvData.append(",");
                        for (int k = 0; k < 30 && this.data.internalMarks[j][k] != null && !this.data.internalMarks[j][k].equals(""); ++k) {
                            this.csvData.append(this.data.internalMarks[j][k]);
                            this.csvData.append(",");
                            this.csvData.append(this.data.externalMarks[j][k]);
                            this.csvData.append(",");
                            this.csvData.append(this.data.totalMarks[j][k]);
                            this.csvData.append(",");
                        }
                        this.csvData.append("\n");
                    }
                }
            }
        }
    }
    
    public void saveCSV() {
        try {
            final PrintWriter pw = new PrintWriter(new File(this.outputFilePath));
            pw.write(this.csvData.toString());
            pw.close();
            System.out.println("\nSuccessfully generated CSV File at " + this.outputFilePath);
            // System.out.println("\nReport any bugs at : raman.batra1709@gmail.com");
        }
        catch (Exception e) {
            System.out.println("File could not be saved!");
            System.out.println("1. Make sure that the CSV file is NOT opened by another application!");
            System.out.println("2. Check the command line arguments entered");
            System.out.println("Error : Supply command line arguments properly!");
            System.out.println("[Input PDF File Path] [Output CSV File Path] [Institute Code]");
            System.out.println("Specify file paths IN double quotes and institute code WITHOUT double quotes");
        }
    }
    
    public static void main(final String[] args) {
        if (args.length != 3) {
            System.out.println("Error : Supply command line arguments properly!");
            System.out.println("[Input PDF File Path] [Output CSV File Path] [Institute Code]");
            System.out.println("Specify file paths IN double quotes and institute code WITHOUT double quotes");
            System.exit(0);
        }
        
            final String inputFilePath = args[0];
            final PDFReader pdfreader = new PDFReader(inputFilePath);
            pdfreader.outputFilePath = args[1];
            pdfreader.instituteCode = args[2];
            pdfreader.processFile();
            pdfreader.file.close();
            pdfreader.saveCSV();
        
    }
}