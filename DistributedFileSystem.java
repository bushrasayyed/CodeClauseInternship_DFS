package My_Projects;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class DistributedFileSystem extends JFrame {
    private JTextArea textArea;

    public DistributedFileSystem() {
        super("Distributed File System");

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY); // Set background color
        

        textArea = new JTextArea(15, 40);
        JScrollPane scrollPane = new JScrollPane(textArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        JButton storeButton = new JButton("Store File");
        storeButton.setBackground(Color.GREEN); // Set button background color
        storeButton.setForeground(Color.WHITE); // Set button text color
        storeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeFile();
            }
        });
        buttonPanel.add(storeButton);

        JButton retrieveButton = new JButton("Retrieve File");
        retrieveButton.setBackground(Color.BLUE); // Set button background color
        retrieveButton.setForeground(Color.WHITE); // Set button text color
        retrieveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retrieveFile();
            }
        });
        buttonPanel.add(retrieveButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private void storeFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (PrintWriter writer = new PrintWriter(selectedFile)) {
                writer.print(textArea.getText());
                writer.flush();
                JOptionPane.showMessageDialog(this, "File saved successfully.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void retrieveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                Desktop.getDesktop().open(selectedFile.getParentFile());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error opening file location: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DistributedFileSystem::new);
    }
}
