import java.io.OutputStream;
import java.io.IOException;
import javax.swing.JTextArea;

public class TextAreaOutputStream extends OutputStream {
    private JTextArea outputTextArea;
    
    TextAreaOutputStream (JTextArea textArea) {
        this.outputTextArea = textArea;

    }

    public void write (int c) throws IOException {
        outputTextArea.append(String.valueOf((char) c));
    }
}
