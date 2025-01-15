import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mannual  {
    public static boolean b = false;
    JFrame frame;
    JLabel backgroundLabel;
  //  private int currentSetIndex = 0; 
    JLabel nameLabel;
    JLabel rollNoLabel;
    JLabel batchLabel;
    
    String[] iconNames = {
            //"Annotation",
             "ARRAYS",
            "CLASS",
            "COLLECTION FRAMEWORK",
            "CONSTRUCTOR",
            "DATA ABSTRACTION",
            "ENCAPSULATION",
            "ENUM",
            "EXCEPTIONS",
            "FILE HANDLING",
            "FLOW CONTROL",
            "GUI",
            "INHERITANCE",
            "INTERFACE",
            "OBJECT",
            "OPERATORS",
            "PACKAGE",
            "POLYMORPHISM",
            "STRING",
            "VARIABLES",
        };
        JLabel imageLabel;
        public Mannual(String name, String rollNo, String batch) {
        frame = new JFrame("Mannual");
        frame.setUndecorated(true);
        JButton exitButton = new JButton("Exit");
        ImageIcon imageIcon = new ImageIcon("shantanu.jpg"); // Replace with the actual image path
        Image scaledImage2 = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaledImage2);
        imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(50, 50, 150, 200);

       
        // Create JLabels for name, roll number, and batch using the provided arguments
        nameLabel = new JLabel("        Name:         " + name);
        nameLabel.setBounds(50, 260, 200, 20);
        nameLabel.setForeground(Color.WHITE); // Change font color to white
        nameLabel.setFont(new Font("Helvetica", Font.BOLD, 15)); // Change font size and style

        rollNoLabel = new JLabel("       Roll No:      " + rollNo);
        rollNoLabel.setBounds(50, 280, 200, 20);
        rollNoLabel.setForeground(Color.WHITE); // Change font color to white
        rollNoLabel.setFont(new Font("Helvetica", Font.BOLD, 15)); // Change font size and style

        batchLabel = new JLabel("       Batch:         " + batch);
        batchLabel.setBounds(50, 300, 200, 20);
        batchLabel.setForeground(Color.WHITE); // Change font color to white
        batchLabel.setFont(new Font("Helvetica", Font.BOLD, 15)); // Change font size and style

     //   currentSetIndex = loadCurrentSetIndex();
    

        imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(50, 50, 200, 200);
        frame.add(imageLabel);
        exitButton = new JButton("Exit");
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(Color.RED); // Set background color to red
        exitButton.setFont(new Font("Helvetica", Font.BOLD, 24));
        exitButton.setFocusPainted(false);
        exitButton.setBounds(10, 10, 100, 35);

    
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             //   saveCurrentSetIndex(currentSetIndex); // Save the currentSetIndex before exiting
                System.exit(0);
            }
        });

        // Background image
          // Get the screen size and set the frame size accordingly
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);

        // Background images
        ImageIcon backgroundImage = new ImageIcon("Old_Mannual_bck_img.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, screenSize.width, screenSize.height);
// Scroll icons
        int totalIcons = iconNames.length; // Total number of icons based on the array size
        int gridSize = (int) Math.ceil(Math.sqrt(totalIcons)); // Size of the square grid
        int iconSize = 112; // Size of the scroll icon (50% smaller)
     
       JButton[] scrollButtons = new JButton[totalIcons];
        for (int i = 0; i < totalIcons; i++) {
            int index = i; // Create a final variable for index

            ImageIcon scrollIcon = new ImageIcon("Old_Icn_img.png");
        //   ImageIcon scrollIcon1 = new ImageIcon("Sp_icn_img.png");
            Image scaledImage;
         
                scaledImage = scrollIcon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);
            
            scrollButtons[index] = new JButton(new ImageIcon(scaledImage));
            int col = i / gridSize;
            int row = i % gridSize;
            int x = 320 + col * (iconSize + 90);
            int y = 50 + row * (iconSize + 30);
            scrollButtons[index].setBounds(x, y, iconSize, iconSize);

            // Set the name of the button using the corresponding icon name
            scrollButtons[index].setText(iconNames[index]);
            scrollButtons[index].setForeground(Color.BLUE);
            // Set the vertical alignment and center the text
            scrollButtons[index].setVerticalTextPosition(SwingConstants.CENTER);
            scrollButtons[index].setHorizontalTextPosition(SwingConstants.CENTER);
            if(i==totalIcons-1)
            {
               scrollButtons[index].setText(iconNames[index].toUpperCase());
              
            }
             scrollButtons[index].setText("<html><center>" + iconNames[index] + "</center></html>");
           //  scrollButtons[index].setText("<html><center><font color='gold'>" + iconNames[index] + "</font></center></html>");
            // Set the vertical alignment and center the text
            scrollButtons[index].setVerticalTextPosition(SwingConstants.CENTER);
            scrollButtons[index].setHorizontalTextPosition(SwingConstants.CENTER);
            // Add action listener to open DescriptionFrame for the clicked icon
            scrollButtons[index].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                         {
                            String iconName = iconNames[index];
                          new DescriptionFrame(iconName);
                        }
                    }
                });

            frame.add(scrollButtons[index]); 
        }
       
        frame.add(nameLabel);
        frame.add(rollNoLabel);
        frame.add(batchLabel);
        frame.add(exitButton);
        frame.add(backgroundLabel);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setFocusable(true); // Ensure that the frame is focused to receive key events
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    
                     new Mannual( "12","At","A");
                    System.out.println("");
                }
            });
    }
    

}
