package com.PDFtoCSV;

public final class Coordinates {
    String PDFType = "new";

    // top header
    public final float DLLY;
    public final float DLLX;
    public final float DURY;
    public final float DURX;

    // Scheme of exam
    public final float SOELLY;
    public final float SOELLX;
    public final float SOEURY;
    public final float SOEURX;

    // header
    public final float PHLLY;
    public final float PHLLX;
    public final float PHURY;
    public final float PHURX;

    // header of Scheme of exam
    public final float PDLLY;
    public final float PDLLX;
    public final float PDURY;
    public final float PDURX;

    // Enrollment number from result data
    public final float SRNLLY;
    public final float SDLLX;
    public final float SRNURY;
    public final float SDURX;

    // student name
    public final float SNLLY;
    public final float SNURY;

    // student id or SID on result page
    public final float SIDLLY;
    public final float SIDURY;

    // student Scheme id or SSID on result page
    public final float SSIDLLY;
    public final float SSIDURY;

    // Student detail interval in Y
    public final float SDIY;

    // Subject code/marks (confirm using PDFBox coords)
    public final float MLLY;
    public final float MLLX;
    public final float MURY;
    public final float MURX;

    // Subject code/marks (confirm using PDFBox coords)
    public final float MRI;

    // width of each marks column
    public final float MRSI;

    
    public final float MIEI;

    // CS / Remark
    public final float CLLY;
    public final float CLLX;
    public final float CURY;
    public final float CURX;
    public final float LAST;

    Coordinates(String PDFtype) {
        this.PDFType = PDFtype.toLowerCase();
        if (PDFtype.toLowerCase().equals("new")) {
            System.out.println("Setting new coords.");
            DLLY = 0.0f;
            DURY = 100.0f;
            DLLX = 600.0f;
            DURX = 1172.0f;
            SOELLY = 110.0f;
            SOELLX = 455.0f;
            SOEURY = 130.0f;
            SOEURX = 690.0f;
            PHLLY = 100.0f;
            PHLLX = 30.0f;
            PHURY = 125.0f;
            PHURX = 1100.0f;
            PDLLY = 140.0f;
            PDLLX = 60.0f;
            PDURY = 175.0f;
            PDURX = 900.0f;
            SRNLLY = 125.0f;
            SDLLX = 86.0f;
            SRNURY = 135.0f;
            SDURX = 245.0f;
            SNLLY = 134.0f;
            SNURY = 143.0f;
            SIDLLY = 143.0f;
            SIDURY = 153.0f;
            SSIDLLY = 153.0f;
            SSIDURY = 162.0f;
            SDIY = 67.0f;
            MLLY = 125.0f;
            MLLX = 246.0f;
            MURY = 145.0f;
            MURX = 292.0f;
            MRI = 22.3f;
            MRSI = 46.00f;
            MIEI = 23.00f;
            CLLY = 125.0f;
            CLLX = 1028.0f;
            CURY = 191.0f;
            CURX = 1152.0f;
            LAST = 40.0f;
        } else {
            System.out.println("Setting old coords.");
            LAST = 36.0f;
            DURY = 90.0f;
            DLLX = 750.0f;
            DURX = 1180.0f;
            SOELLY = 100.0f;
            SOELLX = 450.0f;
            SOEURY = 124.0f;
            SOEURX = 740.0f;
            PHLLY = 167.0f;
            PHLLX = 26.0f;
            PHURY = 197.0f;
            PHURX = 1100.0f;
            PDLLY = 155.0f;
            PDLLX = 0.0f;
            PDURY = 180.0f;
            PDURX = 1200.0f;
            SDLLX = 85.0f;
            SDURX = 226.0f;
            SRNLLY = 200.0f;
            SRNURY = 207.0f;
            SNLLY = 208.0f;
            SNURY = 217.0f;
            SIDLLY = 218.0f;
            SIDURY = 226.0f;
            SSIDLLY = 227.0f;
            SSIDURY = 235.0f;
            SDIY = 54.0f;
            MLLY = 197.0f;
            MURY = 216.0f;
            MLLX = 226.8f;
            MURX = 270.72f;
            MRI = 18.0f;
            MRSI = 43.92f;
            MIEI = 22.32f;
            CLLY = 198.0f;
            CURY = 252.0f;
            CLLX = 1102.0f;
            CURX = 1185.0f;
            DLLY = 0.0f;
        }
    }      
}
