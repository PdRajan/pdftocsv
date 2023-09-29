package com.gui;
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
import javax.swing.Icon;

public class JHyperlink extends JLabel {
    JHyperlink(String text, String website) {
        this(text, website, Color.BLUE.darker(), new Font("Arial", Font.PLAIN, 14), null);
    }

    JHyperlink(String text, String website, Color color) {
        this(text, website, color, new Font("Arial", Font.PLAIN, 14), null);
    }

    JHyperlink(String text, String website, Font font) {
        this(text, website, Color.BLUE.darker(), font, null);
    }

    JHyperlink(String text, String website, Color color, Font font) {
        this(text, website, color, font, null);
    }

    JHyperlink(String text, String website, Icon icon) {
        this(text, website, Color.BLUE.darker(), new Font("Arial", Font.PLAIN, 14), icon);
    }

    JHyperlink(String text, String website, Color color, Icon icon) {
        this(text, website, color, new Font("Arial", Font.PLAIN, 14), icon);
    }

    JHyperlink(String text, String website, Font font, Icon icon) {
        this(text, website, Color.BLUE.darker(), font, icon);
    }

    JHyperlink(String text, String website, Color color, Font font, Icon icon) {
        super(text);

        setForeground(color);
        setFont(font);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setIcon(icon);

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
