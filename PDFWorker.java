import javax.swing.JTextArea;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PDFWorker extends Thread {
    public ProcessBuilder pb;
    public Process process;
    public JTextArea outputTextArea;

    PDFWorker(String inputFile, String outputFile, String instituteCode, String semester, String format,
            JTextArea outputTextArea) {
        this.outputTextArea = outputTextArea;

        pb = new ProcessBuilder("java", "com.PDFtoCSV.PDFReader", inputFile, outputFile, instituteCode,
                semester, format);
    }

    public void run() {
        try {
            Process process = pb.start();

            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                outputTextArea.setText(line);
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }
    }
}