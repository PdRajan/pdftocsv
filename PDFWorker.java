import javax.swing.JButton;
import javax.swing.JTextArea;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class PDFWorker extends Thread {
    public ProcessBuilder pb;
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

        pb = new ProcessBuilder("java", "com.PDFtoCSV.PDFReader", inputFile, outputFile, instituteCode,
                semester, format);

    }

    private boolean validateInput() {
        // Validate input file extension
        if (inputFile.equals("") || outputFile.equals("") || instituteCode.equals("")) {
            outputTextArea.setText("Error: Please make sure no fields are empty.");
            return false;
        }
        if (inputFile == null || !inputFile.toLowerCase().endsWith(".pdf")) {
            outputTextArea.setText("Invalid input file. Please select a PDF file.");
            return false;
        }

        // Validate output file extension
        if (outputFile == null || !outputFile.toLowerCase().endsWith(".csv")) {
            outputTextArea.setText("Invalid output file. Please select a CSV file.");
            return false;
        }

        // Validate input file size
        File inputFileObj = new File(inputFile);
        long fileSizeInBytes = inputFileObj.length();
        long fileSizeInKB = fileSizeInBytes / 1024;
        if (fileSizeInKB == 0) {
            outputTextArea.setText("Input file size cannot be 0.");
            return false;
        }

        // Validate institute code
        if (instituteCode == null || !instituteCode.matches("\\d\\d\\d")) {
            outputTextArea.setText("Invalid institute code. It must be a three digit number.");
            return false;
        }

        return true;
    }

    public void run() {
        try {
            if (validateInput() && pb != null) {
                submitButton.setEnabled(false);
                process = pb.start();

                InputStream inputStream = process.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    outputTextArea.setText(line);
                }
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
        submitButton.setEnabled(true);

    }
}
