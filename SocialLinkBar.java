/**
 * Implements the bar at the bottom having social media links
 */

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SocialLinkBar extends JPanel {
    SocialLinkBar(LayoutManager layout) {
        super(layout);

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

        setBackground(new Color(34,54,107));
        // setBackground(Color.decode("0x54626F"));
        GridBagConstraints gbc = new GridBagConstraints();

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 0); 
        add(facebookLogoLabel, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(0, 5, 0, 0); 
        add(facebook, gbc);

        gbc.gridx = 2;
        gbc.insets = new Insets(0, 40, 0, 0); 
        add(instagramLogoLabel, gbc);

        gbc.gridx = 3;
        gbc.insets = new Insets(0, 5, 0, 0); 
        add(instagram, gbc);

        gbc.gridx = 4;
        gbc.insets = new Insets(0, 40, 0, 0); 
        add(twitterLogoLabel, gbc);

        gbc.gridx = 5;
        gbc.insets = new Insets(0, 5, 0, 0); 
        add(twitter, gbc);
       
        gbc.gridx = 6;
        gbc.insets = new Insets(0, 40, 0, 0); 
        add(linkedinLogoLabel, gbc);

        gbc.gridx = 7;
        gbc.insets = new Insets(0, 5, 0, 0); 
        add(linkedin, gbc);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Social Link Bar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new SocialLinkBar(new FlowLayout()));
        frame.pack();
        frame.setVisible(true);
    }
}





