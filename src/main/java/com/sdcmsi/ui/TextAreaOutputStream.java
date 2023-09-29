import java.io.OutputStream;
import java.io.IOException;
import javax.swing.JTextArea;

public class TextAreaOutputStream extends OutputStream {
    private JTextArea outputTextArea;
    String outputStr;
    
    TextAreaOutputStream (JTextArea textArea) {
        this.outputTextArea = textArea;
        outputStr = "";
    }

    public void write (int c) throws IOException {
        if (((char) c) != '\n') {
            outputStr += String.valueOf((char) c);
        } else {
            outputTextArea.setText(outputStr);
            outputStr = "";
        }
    }
}
