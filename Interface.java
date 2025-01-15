import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Interface extends JFrame {
    private JButton startButton;
    private JLabel imageLabel;
    private JButton exitButton;
    public String BatchCollector;
    
   // private JTextField name;
 private String getBatchFromExcel(String name, String rollNo) {
    try {
        FileInputStream file = new FileInputStream(new File("Class_list.xlsx"));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0); // Assuming batch information is in the first sheet

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Cell nameCell = row.getCell(1); // Column B
            Cell rollNoCell = row.getCell(0); // Column A
            Cell batchCell = row.getCell(2); // Column C

            // Handle different cell types
            String excelName = "";
            if (nameCell != null) {
                if (nameCell.getCellType() == CellType.STRING) {
                    excelName = nameCell.getStringCellValue().trim();
                } else if (nameCell.getCellType() == CellType.NUMERIC) {
                    excelName = String.valueOf(nameCell.getNumericCellValue()).trim();
                }
            }

            String excelRollNo = "";
            if (rollNoCell != null) {
                if (rollNoCell.getCellType() == CellType.STRING) {
                    excelRollNo = rollNoCell.getStringCellValue().trim();
                } else if (rollNoCell.getCellType() == CellType.NUMERIC) {
                    excelRollNo = String.valueOf((int) rollNoCell.getNumericCellValue()).trim();
                }
            }

            String batch = "";
            if (batchCell != null && batchCell.getCellType() == CellType.STRING) {
                batch = batchCell.getStringCellValue().trim();
            }

            if (excelName.equalsIgnoreCase(name) && excelRollNo.equals(rollNo)) {
                workbook.close();
                return batch;
            }
        }

        workbook.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    return null; // Return null if not found
}

  


    public Interface() {
        JFrame frame = new JFrame("Frame");
        frame.setUndecorated(true); // Remove title bar and borders

        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize()); // Set full screen size
        frame.setLocation(0, 0); // Set frame position at (0, 0)

        frame.setSize(1366, 768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set full screen

        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize()); // Set full screen size
        frame.setLocation(0, 0); // Set frame position at (0, 0)

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set full screen

        ImageIcon imageIcon = new ImageIcon("Interface_bck_img.png");
        Image originalImage = imageIcon.getImage();
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        Image resizedImage = originalImage.getScaledInstance(screenWidth, -1, Image.SCALE_SMOOTH);
        ImageIcon resizedImageIcon = new ImageIcon(resizedImage);
        imageLabel = new JLabel(resizedImageIcon);
        imageLabel.setBounds(0, 0, screenWidth, screenHeight);
        JTextField name = new JTextField();
        name.setBackground(java.awt.Color.WHITE);
        name.setFont(new java.awt.Font("Helvetica", java.awt.Font.PLAIN, 20));
        JTextField pswrd = new JTextField();
        pswrd.setBackground(java.awt.Color.WHITE);
        pswrd.setFont(new java.awt.Font("Helvetica", java.awt.Font.PLAIN, 20));

        JLabel Name = new JLabel("Name:");
        JLabel Pswrd = new JLabel("Roll No:");

        Name.setFont(new java.awt.Font("Helvetica", java.awt.Font.BOLD, 22));
        Name.setBackground(java.awt.Color.BLACK);

        Pswrd.setFont(new java.awt.Font("Helvetica", java.awt.Font.BOLD, 22));        
        Pswrd.setForeground(java.awt.Color.BLACK);

        startButton = new JButton("START");
        startButton.setForeground( new java.awt.Color(218, 165, 32) ); // Set GOLD color for the text
        startButton.setBackground(java.awt.Color.RED);//Backgorund Color RED
        startButton.setFont(new java.awt.Font("Helvetica", java.awt.Font.BOLD, 24));
        startButton.setFocusPainted(false);
        imageLabel.setBounds(0, 0, getWidth(), getHeight());
        startButton.setBounds(1050, 600, 120, 40);
        startButton.setEnabled(true); // Set the button initially disabled

        exitButton = new JButton("Exit");
        exitButton.setForeground(java.awt.Color.WHITE); // Set text color to red
        exitButton.setBackground(new java.awt.Color(128, 128, 128));
        exitButton.setBackground(java.awt.Color.RED);
        exitButton.setFont(new java.awt.Font("Helvetica", java.awt.Font.BOLD, 24));
        exitButton.setFocusPainted(false);
        exitButton.setBounds(10, 10, 100, 35);
        frame.add(exitButton);

        frame.add(startButton);
        frame.add(name);
        frame.add(pswrd);
        frame.add(Name);
        frame.add(Pswrd);
        frame.add(imageLabel);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        
        frame.setLayout(null);

        name.setBounds(1000, 450, 200, 30);
        pswrd.setBounds(1000, 550, 200, 30);
        Name.setBounds(790, 450, 200, 30);
        Pswrd.setBounds(790, 550, 200, 30);
      
        startButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Perform the desired action when the button is clicked
                    // For example, you can close the image display and continue with the main code
                    String inputText1 = name.getText().trim(); // Remove leading/trailing whitespace
                    String inputText2 = pswrd.getText().trim(); // Remove leading/trailing whitespace
                    String matchedBatch = getBatchFromExcel(inputText1, inputText2);
                   
       
                    // Hide the frame to simulate closing the image display
                    frame.setVisible(false);
                    if (matchedBatch != null) {
                         // Valid name and roll number, perform desired actions
                        frame.setVisible(false);
                        System.out.println("The name of the student is: " + inputText1);
                        System.out.println("The roll number of the student is: " + inputText2);
                        System.out.println("Matched Batch: " + matchedBatch);
                        DateTimeExample();
                        writeDataToCSV(inputText1, inputText2);
                        new  Mannual(inputText1,inputText2,matchedBatch);
                        frame.dispose();
                        // Additional actions
                    }else {
                        // Invalid name and roll number, display a message or take appropriate action
                        JOptionPane.showMessageDialog(frame, "Invalid name and roll number.", "Error", JOptionPane.ERROR_MESSAGE);
                        name.setText(""); // Clear the name text field
                        pswrd.setText(""); // Clear the roll number text field
                         frame.setVisible(true); // Show the frame again
                }

                }
            });

            exitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0); // Exit the application
                }
            });
    }
    // private boolean isNameValid(String text) {
    //     return text.matches("[a-zA-Z ]+"); // Only letters and spaces allowed
    // }
    // private boolean isRollNoValid(String text) {
    //     return text.matches("\\d{11}"); // Only 11 digits allowed
    // }

    private void writeDataToCSV(String name, String rollNo) {
        try {
            String filename = "Activity_log.csv";
            File csvFile = new File(filename);
            boolean fileExists = csvFile.exists();
            int index = -1;
            // Create the FileWriter and BufferedWriter
            FileWriter writer = new FileWriter(filename, true);
            BufferedWriter bw = new BufferedWriter(writer);

            // If the file does not exist, write the header
            if (!fileExists) {
                bw.write("Name,Roll No.,Total Time Spent,Start Time,End Time,Date\n");
            }

            if (index != -1) {
               // BatchCollector = Batch[index]; // Get the batch using the index
               // System.out.println("Batch: " + BatchCollector);
                // Now you can use the 'batch' value as needed
            } else {
                System.out.println("Roll number not found in the validRollNumbers array.");
            }
            // Get the current date and time
            LocalDateTime startTime = LocalDateTime.now();
            LocalDateTime endTime = LocalDateTime.now();

            // Calculate the total time spent on the app
            Duration totalTime = Duration.between(startTime, endTime);

            // Define the date and time formatters
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            // Format the date and time
            String formattedStartTime = startTime.format(timeFormatter);
            String formattedEndTime = endTime.format(timeFormatter);
            String formattedDate = startTime.format(dateFormatter);

            // Write the data to the file
            bw.write(name + "," + rollNo + "," + totalTime.toMillis() + ","
                    + formattedStartTime + "," + formattedEndTime + "," + formattedDate + "\n");

            // Close the BufferedWriter
            bw.close();


            System.out.println("Data written to CSV successfully.");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
        public static void DateTimeExample() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Define the date format
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Format the date and time
        String formattedDate = now.format(dateFormatter);
        String formattedTime = now.format(timeFormatter);

        System.out.println("Current date (DD/MM/YYYY): " + formattedDate);
        System.out.println("Local system time (HH:mm:ss): " + formattedTime);
    }
    public static void main(String[] args) {
      System.setProperty("log4j2.disable.jmx", "true");
        SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    try {
                         new Interface();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
    }
}