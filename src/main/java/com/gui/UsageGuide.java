package com.gui;

import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Frame;

public class UsageGuide extends JDialog {

    JPanel contentpane;

    UsageGuide(Frame owner, String title, boolean modality) {
        super(owner, title, modality);

        contentpane = new JPanel();
        contentpane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentpane.setLayout(new BoxLayout(contentpane, BoxLayout.Y_AXIS));

        JLabel textLabel;
        JLabel imageLabel;

        textLabel = new JLabel("<html><body><h1>User Guide</h1></body></html>");
        contentpane.add(textLabel);

        textLabel = new JLabel("<html><body><h2>Fields</h2></body></html>");
        contentpane.add(textLabel);

        textLabel = new JLabel("<html><body><ul style=\"width: 720\">\n" + 
        "<li><b>Input File:</b> Provide the file path of the input PDF. If the filename or folder includes spaces, enclose it in quotes.</li>" +
        "<li><b> Output File: </b> Specify the file path for the resulting CSV output. If there are spaces in the filename or folder name, be sure to enclose it in quotes. </li>" +
        "<li><b>Institute Code: </b> Provide your college's unique institution code.</li>" +
        "<li><b>Semester: </b> Choose the relevant semester from the options. </li>" +
        "<li><b> PDF Format: </b> Select the appropriate PDF format from the choices: \"New (52),\" \"New (46),\" or \"Old.\" </li></ul><hr></body></html>");
        contentpane.add(textLabel);

        textLabel = new JLabel("<html><body><h2>Formats: New vs Old</h2></body></html>");
        contentpane.add(textLabel);

        textLabel = new JLabel("<html><body><p style=\"width: 720\"> The formats can be easily " +
            "distinguished by looking at the Scheme Of Examinations page " + 
            "in the results pdf. Here are some differences between the " + 
            "two: </p>" +
            

            "<h3>New Format </h3>\n" +
            "<ul style=\"width: 720\"><li> The text is in a serif font similar to Times  New " + 
            "Roman. </li>" +
            "<li> There is a blank space between the edges of the page and " +
            "the edges of the Scheme Of Examination table on either side. </li>" + 
            "<li> The title, Scheme Of Examinations, is not enclosed within " + 
            "brackets.</li><br>"+
            
            "<li><b>New (46) :</b> New format with each marks column width 46 </li>" + 
            "<li><b>New (52) :</b> New format with each marks column width 52 </li>" + 
            "</ul>\n" +


            "<h3> Old Format </h3>\n" + 
            "<ul style=\"width: 720\"><li> The text is in a monospace font similar to Courier. </li>" +
            "<li> There is no blank space between the edges of the page and " +
            "the edges of the Scheme Of Examination table. </li>" + 
            "<li> The title, Scheme Of Examinations, is enclosed within " + 
            "brackets.</li></ul>\n" +
            "<hr></body></html>");
        contentpane.add(textLabel);

        

        textLabel = new JLabel("<html><body><h3> Example of new format: </h3>" + 
        "</body></html>");
        contentpane.add(textLabel);

        imageLabel = getImageAsLabel("/images/new_example.png");
        contentpane.add(imageLabel);

        textLabel = new JLabel("<html><body><h3> Example of old format: </h3>" + 
        "</body></html>");
        contentpane.add(textLabel);

        imageLabel = getImageAsLabel("/images/old_example.png");
        contentpane.add(imageLabel);

        JScrollPane scrollPane = new JScrollPane(contentpane);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane);

        this.setSize(800, 600);
    }
    

    JLabel getImageAsLabel(String image_path) {
        try {
            Image image = ImageIO.read(getClass().getResource(image_path));
            Image image_scaled = image.getScaledInstance(640, 300, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(image_scaled);
            return new JLabel(icon);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error opening image.", "Error", JOptionPane.ERROR_MESSAGE);
            return new JLabel("Error: image not found");
        }
    }
}
