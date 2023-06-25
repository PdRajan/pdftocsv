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
            "<li><b>Input File:</b> Path to the .pdf file containing the " + 
            "result data. You can select the file using the Browse button.</li>" + 
            "<li><b> Output File: </b> Path to the output file where the " + 
            "data will be stored in form of a .csv file. </li>" + 
            "<li><b>Institute Code: </b> Must be the three digit institute " +
            "code. The application will try to find results of students " +
            "from this college code only. </li>" + 
            "<li><b>Semester: </b> The software will collect results of " +
            "this semester to put into the.csv file </li>" +
            "<li><b> Format: </b> The format of the input file. Differences " +
            "between the 'New' and 'Old' format styles is explained in the" +
            "next section. </li></ul></body></html>");
        contentpane.add(textLabel);

        textLabel = new JLabel("<html><body><h2>Formats: New vs Old</h2></body></html>");
        contentpane.add(textLabel);

        textLabel = new JLabel("<html><body><p style=\"width: 720\"> The formats can be easily " +
            "distinguished by looking at the Scheme Of Examinations page " + 
            "in the results pdf. Here are some differences between the " + 
            "two: </p>" +
            "<h3> Old Format </h3>\n" + 
            "<ul style=\"width: 720\"><li> The text is in a monospace font similar to Courier. </li>" +
            "<li> There is no blank space between the edges of the page and " +
            "the edges of the Scheme Of Examination table. </li>" + 
            "<li> The title, Scheme Of Examinations, is enclosed within " + 
            "brackets.</li></ul>\n" +
            "<h3>New Format </h3>\n" +
            "<ul style=\"width: 720\"><li> The text is in a serif font similar to Times  New " + 
            "Roman. </li>" +
            "<li> There is a blank space between the edges of the page and " +
            "the edges of the Scheme Of Examination table on either side. </li>" + 
            "<li> The title, Scheme Of Examinations, is not enclosed within " + 
            "brackets.</li></ul>\n" +
            "</body></html>");
        contentpane.add(textLabel);

        textLabel = new JLabel("<html><body><h3> Example of old format: </h3>" + 
        "</body></html>");
        contentpane.add(textLabel);

        imageLabel = getImageAsLabel("/images/old_example.png");
        contentpane.add(imageLabel);

        textLabel = new JLabel("<html><body><h3> Example of new format: </h3>" + 
        "</body></html>");
        contentpane.add(textLabel);

        imageLabel = getImageAsLabel("/images/new_example.png");
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
