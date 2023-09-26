/**
 * Implements the bar at the bottom having social media links
 */

import javax.swing.*;
import java.awt.*;

public class SocialLinkBar extends JPanel {
    SocialLinkBar(LayoutManager layout) {
        super(layout);

        Color hyperlinkColor = Color.WHITE;
        Font font = new Font("Arial", Font.PLAIN, 14);
        JHyperlink facebook = new JHyperlink("MSI@Facebook", "https://www.facebook.com/maharaja.surajmal.169/", hyperlinkColor, font);
        JHyperlink instagram = new JHyperlink("MSI@Instagram", "https://www.instagram.com/maharajasurajmalofficial/", hyperlinkColor, font);
        JHyperlink linkedin = new JHyperlink("MSI@Linkedin", "https://www.linkedin.com/in/maharaja-surajmal-institute-9a7b931b2/", hyperlinkColor, font);
        JHyperlink twitter = new JHyperlink("MSI@Twitter", "https://twitter.com/MSI_JanakPuri58", hyperlinkColor, font);

        setBackground(new Color(34,54,107));
        // setBackground(Color.decode("0xDCDCDC"));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 20, 0, 20);
        add(facebook, gbc);

        gbc.gridx = 1;
        add(instagram, gbc);

        gbc.gridx = 2;
        add(twitter, gbc);

        gbc.gridx = 3;
        add(linkedin, gbc);
    }
}