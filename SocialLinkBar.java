/**
 * Implements the bar at the bottom having social media links
 */

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SocialLinkBar extends JPanel {
    SocialLinkBar() {
        super();
        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 3));
        setBackground(new Color(34,54,107));
        
        Color hyperlinkColor = Color.WHITE;
        Font font = new Font("Arial", Font.PLAIN, 14);

        ImageIcon facebookIcon = new ImageIcon("images/fb.png");
        ImageIcon instagramIcon = new ImageIcon("images/insta.png");
        ImageIcon twitterIcon = new ImageIcon("images/x.png");
        ImageIcon linkedinIcon = new ImageIcon("images/linkedin.png");

        JLabel facebookLogoLabel = new JLabel(facebookIcon);
        JLabel instagramLogoLabel = new JLabel(instagramIcon);
        JLabel twitterLogoLabel = new JLabel(twitterIcon);
        JLabel linkedinLogoLabel = new JLabel(linkedinIcon);

        JHyperlink facebook = new JHyperlink("MSI@Facebook", "https://www.facebook.com/maharaja.surajmal.169/", hyperlinkColor, font);
        JHyperlink instagram = new JHyperlink("MSI@Instagram", "https://www.instagram.com/maharajasurajmalofficial/", hyperlinkColor, font);
        JHyperlink linkedin = new JHyperlink("MSI@Linkedin", "https://www.linkedin.com/in/maharaja-surajmal-institute-9a7b931b2/", hyperlinkColor, font);
        JHyperlink twitter = new JHyperlink("MSI@Twitter", "https://twitter.com/MSI_JanakPuri58", hyperlinkColor, font);

        add(facebook);
        add(instagram);
        add(twitter);
        add(linkedin);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Social Link Bar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new SocialLinkBar());
        frame.pack();
        frame.setVisible(true);
    }
}

