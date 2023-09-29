
package com.PDFtoCSV;

public final class DataBlock
{
    public String programmeName;
    public String programmeId;
    public String datePrepared;
    public String dateDeclared;
    public String semester;
    public String instCode;
    public String progCode;
    public String progName;
    public String examType;
    public short batch;
    public String soeSchemeId;
    public String[] rollNo;
    public String[] schemeId;
    public String[] studentId;
    public String[] name;
    public byte[] credits;
    public String[][] subjectCodes;
    public String[][] internalMarks;
    public String[][] externalMarks;
    public String[][] totalMarks;
    public String[][] grades;
    public static final short NUM_OF_STUDENTS = 10;
    public static final short NUM_OF_SUBJECTS = 30;
    
    public DataBlock() {
        this.rollNo = new String[10];
        this.schemeId = new String[10];
        this.studentId = new String[10];
        this.name = new String[10];
        this.subjectCodes = new String[10][30];
        this.credits = new byte[10];
        this.internalMarks = new String[10][30];
        this.externalMarks = new String[10][30];
        this.totalMarks = new String[10][30];
        this.grades = new String[10][30];
    }
}