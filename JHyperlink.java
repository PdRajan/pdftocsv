/**
 * Class to create a clickable JLabel whuch acts as a hyperlink.
 */

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JHyperlink extends JLabel {
    JHyperlink(String text, String website) {
        this(text, website, Color.BLUE.darker());
    }

    JHyperlink(String text, String website, Color color) {
        this(text, website, color, new Font("Arial", Font.PLAIN, 14));
    }

    JHyperlink(String text, String website, Font font) {
        this(text, website, Color.BLUE.darker(), font);
    }

    JHyperlink(String text, String website, Color color, Font font) {
        super(text);

        setForeground(color);
        setFont(font);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(website));
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setText("<html><a href=''>" + text + "</a></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setText(text);
            }
        });
    }
}
