import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;

public class ProgramInputGUI extends JFrame {
    private JTextField inputFileTextField;
    private JTextField outputFileTextField;
    private JTextField instituteCodeTextField;
    private JComboBox<String> semesterComboBox;
    private JComboBox<String> formatComboBox;
    private JTextArea outputTextArea;
    private JMenuBar menubar;
    private JMenu menu;
    private JMenuItem menuitems;
    PDFWorker pdfworker;

    public ProgramInputGUI() {
        super();
        setTitle("PDFtoCSV by SDC-MSI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 450);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(Color.WHITE);

        // Create labels and text fields

        JLabel inputFileLabel = new JLabel("Input File:");
        JLabel outputFileLabel = new JLabel("Output File:");
        JLabel instituteCodeLabel = new JLabel("Institute Code:");
        JLabel semesterLabel = new JLabel("Semester:");
        JLabel formatLabel = new JLabel("Format:");
        JLabel outputLabel = new JLabel("Output:");

        // Set font styles
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        inputFileLabel.setFont(labelFont);
        outputFileLabel.setFont(labelFont);
        instituteCodeLabel.setFont(labelFont);
        semesterLabel.setFont(labelFont);
        formatLabel.setFont(labelFont);
        outputLabel.setFont(labelFont);

        inputFileTextField = new JTextField(20);
        outputFileTextField = new JTextField(20);
        instituteCodeTextField = new JTextField(10);

        // Create the top toolbar
        menubar = new JMenuBar();

        // Create menu objects for Menubar
        menu = new JMenu("Window");

        menuitems = new JMenuItem("Quit");
        menuitems.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menu.add(menuitems);
        menubar.add(menu);

        menu = new JMenu("Help");

        // Create items for the menu
        menuitems = new JMenuItem("Report a bug");
        menuitems.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] aboutMessage = { "To report bugs, please file an issue on the github page:",
                        "https://github.com/sdc-msi/pdftocsv",
                };

                JOptionPane.showMessageDialog(null, aboutMessage, "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        menu.add(menuitems);

        menuitems = new JMenuItem("Usage guide");
        menuitems.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UsageGuide guide = new UsageGuide(null, "Usage Guide", true);
                guide.setVisible(true);
            }
        });

        menu.add(menuitems);
        menubar.add(menu);

        menu = new JMenu("About");

        menuitems = new JMenuItem("About PDFtoCSV");
        menuitems.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] aboutMessage = { "PDFtoCSV",
                        "\n",
                        "Version 1 authors:",
                        "Raman Batra",
                        "Neeraj Aggarwal",
                        "\n",
                        "Version 2 authors:",
                        "Prakhar Gupta",
                        "\n",
                        "Version 3 authors:",
                        "Priyadarshani Rajan",
                        "Shivodit Gill",
                };
                JOptionPane.showMessageDialog(null, aboutMessage, "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        menu.add(menuitems);
        menubar.add(menu);

        menu = new JMenu("SDC-MSI");
        menuitems = new JMenuItem("Project page");
        menuitems.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                    try {
                        desktop.browse(new URL("https://github.com/sdc-msi/pdftocsv/").toURI());
                    } catch (Exception except) {
                        except.printStackTrace();
                    }
                }
            }
        });

        menu.add(menuitems);

        menuitems = new JMenuItem("Organization page");
        menuitems.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                    try {
                        desktop.browse(new URL("https://github.com/sdc-msi/").toURI());
                    } catch (Exception except) {
                        except.printStackTrace();
                    }
                }
            }
        });

        menu.add(menuitems);
        menubar.add(menu);

        // Create the semester combo box
        String[] semesterOptions = { "1", "2", "3", "4", "5", "6", "7", "8" };
        semesterComboBox = new JComboBox<>(semesterOptions);
        // semesterComboBox.

        // Create the format combo box
        String[] formatOptions = { "New (52)", "New (46)", "Old" };
        formatComboBox = new JComboBox<>(formatOptions);

        // Create the file chooser button for input file
        JButton inputFileButton = new JButton("Browse");
        inputFileButton.setBackground(new Color(34, 54, 107));
        inputFileButton.setForeground(Color.WHITE);
        inputFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(".");
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    inputFileTextField.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        // Create the file chooser button for output file location
        JButton outputFileButton = new JButton("Browse");
        outputFileButton.setBackground(new Color(34, 54, 107)); // Set button color
        outputFileButton.setForeground(Color.WHITE);
        outputFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(".");
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    outputFileTextField.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        // Create the output text area
        outputTextArea = new JTextArea(7, 60);
        outputTextArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);

        // Create the submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(60, 179, 113)); // Set button color
        submitButton.setForeground(Color.WHITE); // Set text color
        submitButton.setFont(new Font("Arial", Font.BOLD, 16)); // Set font style
        
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputFile = inputFileTextField.getText();
                String outputFile = outputFileTextField.getText();
                String instituteCode = instituteCodeTextField.getText();
                String semester = (String) semesterComboBox.getSelectedItem();
                String format = (String) formatComboBox.getSelectedItem();               

                try {
                    if (pdfworker != null && pdfworker.isAlive()) {
                        pdfworker.interrupt();
                    }

                    pdfworker = new PDFWorker(inputFile, outputFile, instituteCode, semester, format, outputTextArea,submitButton);
                    pdfworker.start();
                } catch (Exception ex) {
                    System.out.println("Exception: " + ex);
                }

                try {
                    if (pdfworker != null && pdfworker.isAlive()) {
                        pdfworker.interrupt();
                    }

                    pdfworker = new PDFWorker(inputFile, outputFile, instituteCode, semester, format,
                            outputTextArea, submitButton);
                    pdfworker.start();
                } catch (Exception ex) {
                    System.out.println("Exception: " + ex);
                    // submitButton.setEnabled(true);
                }
            }
        });

        // Create a panel and add components
        setJMenuBar(menubar);
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE); // Set panel background color

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(inputFileLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(inputFileTextField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(inputFileButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(outputFileLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(outputFileTextField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(outputFileButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(instituteCodeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(instituteCodeTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(semesterLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(semesterComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(formatLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(formatComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(outputLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(outputScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(submitButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // panel.add(new SocialLinkBar(new GridBagLayout()), gbc);

        // Add the panel to the frame
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        contentPane.add(panel);
        contentPane.add(new SocialLinkBar());
    }

    public static void main(String[] args) {
        // System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                System.out.println(info);
                if ("Metal".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ProgramInputGUI().setVisible(true);
            }
        });
    }
}
