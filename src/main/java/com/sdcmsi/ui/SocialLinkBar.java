package com.sdcmsi.ui;

/**
 * Implements the bar at the bottom having social media links
 */

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.IOException;

public class SocialLinkBar extends JPanel {
    ImageIcon facebookIcon;
    ImageIcon instagramIcon;
    ImageIcon twitterIcon;
    ImageIcon linkedinIcon;

    SocialLinkBar() {
        super();
        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 3));
        setBackground(new Color(34,54,107));
        
        Color hyperlinkColor = Color.WHITE;
        Font font = new Font("Arial", Font.PLAIN, 14);

        try {
            facebookIcon = new ImageIcon(ImageIO.read(getClass().getResource("images/fb.png")));
            instagramIcon = new ImageIcon(ImageIO.read(getClass().getResource("images/insta.png")));
            twitterIcon = new ImageIcon(ImageIO.read(getClass().getResource("images/x.png")));
            linkedinIcon = new ImageIcon(ImageIO.read(getClass().getResource("images/linkedin.png")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        JHyperlink facebook = new JHyperlink("MSI@Facebook", "https://www.facebook.com/maharaja.surajmal.169/", hyperlinkColor, font, facebookIcon);
        JHyperlink instagram = new JHyperlink("MSI@Instagram", "https://www.instagram.com/maharajasurajmalofficial/", hyperlinkColor, font, instagramIcon);
        JHyperlink linkedin = new JHyperlink("MSI@Linkedin", "https://www.linkedin.com/in/maharaja-surajmal-institute-9a7b931b2/", hyperlinkColor, font, linkedinIcon);
        JHyperlink twitter = new JHyperlink("MSI@Twitter", "https://twitter.com/MSI_JanakPuri58", hyperlinkColor, font, twitterIcon);

        add(facebook);
        add(instagram);
        add(twitter);
        add(linkedin);
    }
}
