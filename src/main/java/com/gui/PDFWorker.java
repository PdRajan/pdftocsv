package com.gui;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.awt.Color;
import com.PDFtoCSV.PDFReader;
import java.io.PrintStream;

public class PDFWorker extends Thread {
    public PDFReader pdfreader;
    public Process process;
    public JTextArea outputTextArea;
    public String inputFile;
    public String outputFile;
    public String instituteCode;
    public String format;
    public JButton submitButton;

    public PDFWorker(String inputFile, String outputFile, String instituteCode, String semester, String format,
            JTextArea outputTextArea, JButton submitButton) {
        this.outputTextArea = outputTextArea;
        this.outputFile = outputFile;
        this.inputFile = inputFile;
        this.instituteCode = instituteCode;
        this.format = format;
        this.submitButton = submitButton;
        
        
        pdfreader = new PDFReader(inputFile, outputFile, instituteCode,
                semester, format);
        outputTextArea.setForeground(Color.WHITE);
    }

    private boolean validateInput() {
        boolean valid = true;
        if (inputFile.equals("") || outputFile.equals("") || instituteCode.equals("")) {
            outputTextArea.append("\nError: Please make sure no fields are empty.");
            valid = false;
        }
        if (inputFile == null || !inputFile.toLowerCase().endsWith(".pdf")) {
            outputTextArea.append("\nInvalid input file. Please select a PDF file.");
            valid = false;
        }

        // Validate output file extension
        if (outputFile == null || !outputFile.toLowerCase().endsWith(".csv")) {
            outputTextArea.append("\nInvalid output file. Please select a CSV file.");
            valid = false;
        }

        // Validate input file size
        File inputFileObj = new File(inputFile);
        long fileSizeInBytes = inputFileObj.length();
        long fileSizeInKB = fileSizeInBytes / 1024;
        if (fileSizeInKB == 0) {
            outputTextArea.append("\nInput file size cannot be 0.");
            valid = false;
        }

        // Validate institute code
        if (instituteCode == null || !instituteCode.matches("\\d\\d\\d")) {
            outputTextArea.append("\nInvalid institute code. It must be a three digit number.");
            valid = false;
        }

        if (!valid) {
            outputTextArea.setForeground(Color.RED);
        }
        return valid;
    }

    public void run() {
        try {
            submitButton.setEnabled(false);
            outputTextArea.setForeground(Color.BLACK);
            outputTextArea.setText("");
            if (validateInput() && pdfreader != null) {
                PrintStream output = new PrintStream(new TextAreaOutputStream(outputTextArea));
                System.setOut(output);
                pdfreader.runPDFReader();
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        } finally {
            submitButton.setEnabled(true);
            try {
                this.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
