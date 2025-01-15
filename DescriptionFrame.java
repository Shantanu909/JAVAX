import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;

public class DescriptionFrame extends JFrame {
    JTextArea descriptionTextArea;
    JTextArea syntaxTextArea;
    JButton exampleCodeButton;
    JButton javadocButton;
    JButton videoLinkButton ;
    JButton customCloseButton;
    public int buttonsClickedCount=0;


    String[] videoURLs = {
                "https://youtu.be/BuNkybyjG7s?feature=shared",
                "https://youtu.be/_Ylzm140wwY?feature=shared",
                "https://youtu.be/QOES7VIl0Rc?feature=shared",
                "https://youtu.be/oxX2zCsWgiE?feature=shared",
                "https://youtu.be/RHxIZAsa_MA?feature=shared",
                "https://youtu.be/qWhECkQ_SiE?feature=shared",
                "https://youtu.be/EVHzs6rN8go?feature=shared",
                "https://youtu.be/Ckbw00GtC04?feature=shared",
                "https://youtu.be/vgDZu3JrhYI?feature=shared",
                "https://youtu.be/ukz1Qrxx2tg?feature=shared",
                "https://youtu.be/5o3fMLPY7qY?feature=shared",
                "https://youtu.be/Q4uhiF6HYmw?feature=shared",
                "https://youtu.be/paElXOzWcvI?feature=shared",
                "https://youtu.be/0dDPQJOtjUg?feature=shared",
                "https://youtu.be/3PwmWaHdojA?feature=shared",
                "https://youtu.be/LdhW9WqcT2c?feature=shared",
                "https://youtu.be/YmHojdZu4tw?feature=shared",
                "https://youtu.be/GnyB2hCeXeM?feature=shared",
                "https://youtu.be/8P6tOnO0DYg?feature=shared",
              };
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

    String[] javadocURLs = {
            //"https://docs.oracle.com/javase/tutorial/java/annotations/index.html",0 
            "https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html",
            "https://docs.oracle.com/javase/tutorial/java/javaOO/classes.html",
            "https://docs.oracle.com/javase/tutorial/collections/intro/index.html",
            "https://docs.oracle.com/javase/tutorial/reflect/member/ctor.html",
            "https://docs.oracle.com/javase/tutorial/java/IandI/abstract.html",
            "https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html",
            "https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html",
            "https://docs.oracle.com/javase/tutorial/essential/exceptions/definition.html",
            "https://docs.oracle.com/javase/tutorial/essential/io/index.html",
            "https://docs.oracle.com/javase/tutorial/java/nutsandbolts/flow.html",
            "https://docs.oracle.com/javase/tutorial/uiswing/start/index.html",
            "https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html",//INHERITANCE
            "https://docs.oracle.com/javase/tutorial/java/concepts/interface.html",
            "https://docs.oracle.com/javase/tutorial/java/javaOO/objects.html",
            "https://docs.oracle.com/javase/tutorial/java/nutsandbolts/operators.html",
            "https://docs.oracle.com/javase/tutorial/java/package/index.html",
            "https://docs.oracle.com/javase/tutorial/java/IandI/polymorphism.html",
            "https://docs.oracle.com/javase/tutorial/java/data/strings.html",
            "https://docs.oracle.com/javase/tutorial/java/nutsandbolts/variables.html"
        };
    String[] exampleURLs = {   
            "ArraysExample.java",
            "Car.java",
            "CollectionFrameworkExample.java",
            "Constructor1.java",
            "DataAbstractionExample.java",
            "Person.java",
            "EnumExample.java",
            "ExceptionExample.java",
            "FileHandlingExample.java",
            "FlowControlExample.java",
            "Traffic_lights.java",
            "InheritanceDemo.java",
            "InterfaceExample.java",
            "ObjectExample.java",
            "calculator.java",
            "MyClass.java",
            "Message.java",
            "String_funcs.java",
            "VariablesExample.java"

        };

    String[] descriptionTexts = {
            "Arrays are a collection of variabes joined together by the reference of index numbers",
            "Class is an abstract concept that has no real data holding capacity but is considered to be a bunch of mehtods, objects etc.",
            "Collection Framework is a group of special data holding members that can help ease data handling functions.",
            "Constructor is the method that runs by itself and at the very beginning of the code, but after the main method.",
            "Data Abstraction is the concept of hiding the unwanted or complex snippet of code to maintain ease of low and make it user-friendly.",
            "Encapsulation is the concept of hiding sensitive data from unauthorized users by the use of access modifiers.",
            "Enum is the collection of variables that cannot be further modified by any other class or method.",
            "Exceptions are the special cases that are generated when a syntatical or logical error is made in the program.",
            "File Handling is the concept of creatibg, modifying or deleting: xlsx, docx, txt or pdf files, using Java.",
            "Flow control is the logical part of any code that uses conditions and cases to link the user's decision to the outcomes.",
            "GUI is the tool that helps to make any program interactive and graphically enriched.",
            "Inheritance is the concept where ,a sub-class if you will, derives methods(and variables too) from say a parent class and either uses it or modifies it. ",
            "Interface is the tool that can help to connect the user with the methods in the program.",
            "Object is something that has state and behaviour, for eg: a basket of 4 bananas. The basket being object, has state=4 and behaviour = banana.",
            "Operators are the tools that can be used to modify and compare the values of literals or any other data holders. ",
            "Package is a closed library that helps to enclose a bunch of programs and provides increased data protection and accessibility.",
            "Polymorphism is the concept of re-iterating a method or re-defining it but with the same name so as to decrease code-complexity.",
            "String is the literal that contains an array of characters or the language the we speak.",
            "Variables are the memebrs of OOP that hold data and can be differentiated by their types for eg: int, doube, boolean, etc.",
            "Now we will see some interactive bits of programs."
        };

    String [] Syntex ={
        "dataType[] arrayName = new dataType[size];",
    "class ClassName { /* class members and methods */ }",
    "import java.util.Collection;\nimport java.util.ArrayList;\n\nCollection<dataType> collectionName = new ArrayList<>();",
    "class ClassName {\n    ClassName(parameters) { /* constructor body */ }\n}",
    "abstract class AbstractClassName {\n    abstract dataType methodName(parameters);\n}",
    "class ClassName {\n    private dataType variableName;\n    public dataType getVariable() { /* return the variable */ }\n    public void setVariable(dataType value) { /* set the variable */ }\n}",
    "enum EnumName { VALUE1, VALUE2, ... }",
    "try {\n    // code that might throw an exception\n} catch (ExceptionType e) {\n    // handle the exception\n}",
    "try {\n    // code to open and handle the file\n} catch (IOException e) {\n    // handle file-related exception\n} finally {\n    // code to clean up resources\n}",
    "if (condition) {\n    // code to execute if condition is true\n} else {\n    // code to execute if condition is false\n}",
    "// GUI code using a library or framework",
    "class ChildClass extends ParentClass { /* additional members and methods */ }",
    "interface InterfaceName {\n    // method declarations\n}",
    "class ClassName {\n    // class members and methods\n}",
    "int sum = a + b;\nint quotient = x / y;\nint isEqual = (value1 == value2);",
    "package packageName;",
    "class ParentClass {\n    void commonMethod() { /* code */ }\n}\nclass ChildClass extends ParentClass {\n    void commonMethod() { /* overridden code */ }\n}",
    "String str = \"Hello, world!\";",
    "dataType variableName = value;", 
    };

    String[] Hardproblems={
       "Implement a parallel sorting algorithm using multi-threading to improve the performance of sorting large arrays.",
    "Design a comprehensive framework for generating and managing reports in a business application, using dynamic class loading.",
    "Build a distributed cache system using Java's collection classes, ensuring data consistency and efficient storage across nodes.",
    "Develop a dependency injection container that dynamically manages object creation and initialization based on class annotations.",
    "Create an advanced database abstraction layer that supports multiple database systems and optimizes query performance.",
    "Design a secure cryptographic library in Java, encapsulating complex encryption and decryption algorithms for various use cases.",
    "Build a configuration management system using enums, allowing users to define and manage application configurations easily.",
    "Develop a distributed fault-tolerant system using exceptions to handle failures and ensure continuous operation across nodes.",
    "Create a distributed file synchronization system using Java's file handling capabilities, ensuring data consistency across nodes.",
    "Implement a complex workflow management system using flow control mechanisms, allowing users to define intricate processes.",
    "Design an advanced 3D graphics application using Java's GUI libraries, incorporating realistic rendering and user interactions.",
    "Build a simulation framework for a complex ecosystem using inheritance, modeling diverse species and their ecological interactions.",
    "Develop a pluggable architecture for a game engine using interfaces, allowing third-party developers to create custom game components.",
    "Create a distributed object-oriented simulation environment where objects can interact and communicate across networked nodes.",
    "Design a custom expression evaluation engine that handles complex mathematical expressions, including user-defined operators.",
    "Implement a modular microservices architecture using Java's package system, with each package representing an independent service.",
    "Build an AI-powered virtual pet simulation with polymorphic behaviors, allowing virtual pets to learn from user interactions.",
    "Develop a natural language processing application that can generate coherent and contextually relevant paragraphs of text.",
    "Design a self-modifying program that dynamically adjusts variable values based on environmental conditions and user inputs."};

    String[] MediumProblems={ 
    "Write a Java program to find the maximum and minimum elements in an array.",
    "Create a simple 'Person' class with attributes for name and age. Add methods to set and display these attributes.",
    "Implement a program to store a list of names in an ArrayList and display them.",
    "Design a class 'Rectangle' with attributes for length and width. Write a constructor to initialize these attributes.",
    "Develop a program to manage a library with a 'Book' class that contains attributes like title and author.",
    "Build a 'BankAccount' class with private balance and methods to deposit, withdraw, and display balance.",
    "Define an enum 'Days' for the days of the week. Write a program to print all the days using a loop.",
    "Create a program that asks the user for their age. Handle cases where the input is not a valid integer.",
    "Write a program that reads text from a file and copies it to another file.",
    "Implement a simple calculator program that takes two numbers and an operator (+, -, *, /) and displays the result.",
    "Design a basic calculator GUI application that allows users to perform arithmetic operations.",
    "Create a 'Vehicle' class and subclasses 'Car' and 'Bike'. Add methods like 'startEngine' specific to each subclass.",
    "Define an interface 'Shape' with a method 'calculateArea'. Implement it in classes like 'Circle' and 'Rectangle'.",
    "Write a Java program to create objects of a 'Student' class with attributes like name, age, and grade.",
    "Develop a program that calculates the area of a triangle using the base and height provided by the user.",
    "Organize a set of utility classes related to math operations into a package called 'math.utils'.",
    "Create a 'Shape' class with a method 'draw'. Implement subclasses 'Circle' and 'Square' to override the method.",
    "Write a program that takes a sentence and counts the occurrences of a specific word in it.",
    "Design a program that swaps the values of two variables without using a temporary variable."

};

    String[] EasyProblems={
    "Write a Java program that declares an array of integers and calculates their sum.",
    "Create a class called 'Person' with attributes for name and age. Display their information using methods.",
     "Implement an ArrayList to store a list of names. Display the names using a loop.",
    "Design a 'Car' class with a constructor to set its make and model. Display the car's information.",
    "Develop a 'BankAccount' class with balance and methods for deposit and withdrawal. Display the balance.",
    "Build a 'Student' class with private attributes for name and grade. Provide methods to access and display them.",
    "Create an enum 'Color' with values representing basic colors. Display all the colors using a loop.",
    "Write a program that asks the user for their age. Handle cases where the input is not a valid integer.",
    "Implement a program to read a text file and display its content on the console.",
    "Create a program that checks if a given number is positive, negative, or zero.",
    "Design a basic calculator GUI application that allows users to perform addition and subtraction.",
    "Develop a 'Vehicle' class and a subclass 'Car'. Display information about a car using inherited methods.",
    "Define an interface 'Shape' with a method 'calculateArea'. Implement it in a 'Circle' class.",
    "Write a Java program to create an object of a 'Book' class with attributes like title and author.",
    "Write a program that takes two numbers from the user and displays their sum.",
    "Organize a set of utility classes related to geometry into a package called 'geometry.utils'.",
    "Create a 'Shape' class with a method 'draw'. Implement subclasses 'Circle' and 'Rectangle' to override the method.",
    "Design a program that takes a name as input and displays a welcome message using string concatenation.",
    "Declare an integer variable to store your age and display it with a message."
};
    
    public DescriptionFrame(String iconName) {
    setTitle(iconName + " Description");
    setSize(700, 550);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null); // Center the frame on the screen
    setUndecorated(false); // Set the frame as undecorated (no title bar)

    // Create the components
    descriptionTextArea = new JTextArea();
    syntaxTextArea = new JTextArea();
    exampleCodeButton = new JButton("Example Code");
    javadocButton = new JButton("Web Link");
    videoLinkButton = new JButton("Video Link");
    JButton createFileButton = new JButton("Create .java File");

    // Set the layout
    setLayout(new BorderLayout());

    // Create the top pane for the description
    JPanel descriptionPanel = new JPanel(new BorderLayout());
    JEditorPane descriptionEditorPane = new JEditorPane();
    descriptionEditorPane.setContentType("text/html"); // Set the content type to HTML
    descriptionEditorPane.setEditable(false); // Make it non-editable
    descriptionPanel.add(new JScrollPane(descriptionEditorPane), BorderLayout.CENTER);
    
    // Get the index of the current iconName in iconNames array
    int index = Arrays.asList(iconNames).indexOf(iconName);
    
    // Set the formatted HTML text to the JEditorPane
    String formattedText = "<html><p style='font-size: 20px; font-family: Arial, sans-serif; color: Black;'>"
    + descriptionTexts[index] + "</p><br><p style='font-size: 16px; font-family: Arial, sans-serif; font-style: Bold; color : Black'>"
    + "Syntax: <br>" +"</p><br><p style='font-size: 14px; font-family: Arial, sans-serif; font-style: italic,Bold; color : red'>"+ Syntex[index] +
    "</p><br><p style='font-size: 16px; font-family: Arial, sans-serif; font-style: Bold; color : red'>"+"Problems: <br>"+
    "</p><p style='font-size: 11px; font-family: Arial, sans-serif; font-style: Bold; color : black'>"+"Easy : "+ EasyProblems[index]+
    "</p><p style='font-size: 11px; font-family: Arial, sans-serif; font-style: Bold; color : green'>"+"<br>Medium : "+ MediumProblems[index]+
    "</p><p style='font-size: 11px; font-family: Arial, sans-serif; font-style: Bold; color : red'>"+"<br>Hard : "+ Hardproblems[index]+ "</p></html>";

// Set the formatted HTML text to the JEditorPane
descriptionEditorPane.setText(formattedText);

// Add the descriptionPanel to the frame
add(descriptionPanel, BorderLayout.CENTER);
    
    // Add the descriptionPanel to the frame
    add(descriptionPanel, BorderLayout.CENTER);

// Set the combined formatted text to the JTextArea
//descriptionTextArea.setText(formattedText);
descriptionTextArea.setEditable(false);
descriptionTextArea.setLineWrap(true); // Enable line wrap
descriptionTextArea.setWrapStyleWord(true);
    // Set the description text and make it read-only
    //String descriptionText = getDescriptionText(iconName);
   // descriptionTextArea.setText(descriptionText);
 //   descriptionTextArea.setEditable(false);
    //descriptionTextArea.setLineWrap(true); // Enable line wrap
   // descriptionTextArea.setWrapStyleWord(true);

    // Create the bottom pane for syntax and buttons
    JPanel bottomPanel = new JPanel(new BorderLayout());

    // Syntax pane
 //   JScrollPane syntaxScrollPane = new JScrollPane(syntaxTextArea);
  //  bottomPanel.add(syntaxScrollPane, BorderLayout.CENTER);

    // Buttons pane
    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 0)); // Set spacing between buttons
    buttonsPanel.add(javadocButton);
    buttonsPanel.add(exampleCodeButton);
    buttonsPanel.add(videoLinkButton);
    bottomPanel.add(buttonsPanel, BorderLayout.SOUTH);
    //buttonsPanel.add(createFileButton);
    add(bottomPanel, BorderLayout.SOUTH);

    // Set background colors
    exampleCodeButton.setBackground(new Color(144, 238, 144)); // Light green
    javadocButton.setBackground(Color.CYAN);//greenish blue 
    videoLinkButton.setBackground(new Color(135, 206, 235)); // Sky blue for video link
    createFileButton.setBackground(new Color(64, 224, 208));
 
        // Add action listener to the Example Code button
        exampleCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String exampleFilePath = getExampleURL(iconName);
                if (!exampleFilePath.isEmpty()) {
                    File exampleFile = new File(exampleFilePath);
        
                    try {
                        Desktop.getDesktop().open(exampleFile);
                        buttonsClickedCount++; // Increment the counter for this frame
                        // checkButtonsClicked(); // Check if the counter reached 3 for this frame
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(
                            DescriptionFrame.this, "An error occurred while opening the file.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        videoLinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String videoURL = getVideoURL(iconName);
                if (!videoURL.isEmpty()) {
                    openURL(videoURL);
                    buttonsClickedCount++; // Increment the counter for this frame
                  //  checkButtonsClicked(); // Check if the counter reached 3 for this frame
                
                }
            }
        });

        // Add action listener to the Javadoc button
        javadocButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String javadocURL = getJavadocURL(iconName);
                if (!javadocURL.isEmpty()) {
                    openURL(javadocURL);
                      buttonsClickedCount++; // Increment the counter for this frame
                    //checkButtonsClicked(); 
                }
            }
        });

        setVisible(true);
          // Add action listener to the "Create .C File" button
          createFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 JPanel codePanel = new JPanel(new BorderLayout());
                 RSyntaxTextArea codeTextArea = new RSyntaxTextArea();
                codeTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
                codeTextArea.setCodeFoldingEnabled(true);

                   // Create the "Compile and Run" button
                  JButton compileAndRunButton = new JButton("Compile and Run");
                  compileAndRunButton.setPreferredSize(new Dimension(150, 30)); // Set the button size
                   compileAndRunButton.addActionListener(new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                           compileAndRunCode(codeTextArea.getText());
                       }
                   });
                // Create a new JFrame for the code editor
                JFrame codeEditorFrame = new JFrame("C Code Editor");
                codeEditorFrame.setSize(600, 400);
                codeEditorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                codeEditorFrame.setLocationRelativeTo(null);

                // Create the code editor
                
                 // Create the "Save .C File" button
                 JButton saveFileButton = new JButton("Save .C File");
                 saveFileButton.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                         JFileChooser fileChooser = new JFileChooser();
                         int option = fileChooser.showSaveDialog(codeEditorFrame);
 
                         if (option == JFileChooser.APPROVE_OPTION) {
                             File selectedFile = fileChooser.getSelectedFile();
                             if (!selectedFile.getName().endsWith(".c")) {
                                 selectedFile = new File(selectedFile.getAbsolutePath() + ".c");
                             }
 
                             String selectedCode = codeTextArea.getText();
 
                             try (FileWriter writer = new FileWriter(selectedFile)) {
                                 writer.write(selectedCode);
 
                                 JOptionPane.showMessageDialog(
                                         codeEditorFrame, "File saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                             } catch (IOException ex) {
                                 ex.printStackTrace();
                                 JOptionPane.showMessageDialog(
                                         codeEditorFrame, "An error occurred while saving the file.", "Error", JOptionPane.ERROR_MESSAGE);
                             }
                         }
                     }
                 });
                // Set the theme for syntax highlighting (optional)
        try {
            Theme theme = Theme.load(getClass().getResourceAsStream("/org/fife/ui/rsyntaxtextarea/themes/dark.xml"));
            theme.apply(codeTextArea);
        } catch (IOException ex) {
            ex.printStackTrace();
        }     // Create a panel to hold the code editor and save button
        codePanel.add(new RTextScrollPane(codeTextArea), BorderLayout.CENTER);
        codePanel.add(saveFileButton, BorderLayout.SOUTH);
        codePanel.add(compileAndRunButton);
        // Add the code panel to the code editor frame
        codeEditorFrame.getContentPane().add(codePanel);
        codePanel.add(new RTextScrollPane(codeTextArea), BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5)); // Adjust gap and alignment
        buttonPanel.add(saveFileButton);
        buttonPanel.add(compileAndRunButton);
        codePanel.add(buttonPanel, BorderLayout.SOUTH); // Add the button panel
        
        // Set the content of the RSyntaxTextArea based on the selected array
        if (index >= 0 && index < Syntex.length) {
            codeTextArea.setText("//"+Hardproblems[index]+"\n //"+MediumProblems[index]+"\n //"+EasyProblems[index]);
        }
        // Display the code editor frame
        codeEditorFrame.setVisible(true);
            }
        });

    }
   
    private void compileAndRunCode(String code) {
        try {
            File tempFile = File.createTempFile("temp", ".c");
            tempFile.deleteOnExit();
    
            try (FileWriter writer = new FileWriter(tempFile)) {
                writer.write(code);
            }
    
            // Compile the C code using "gcc" command
            ProcessBuilder compileProcessBuilder = new ProcessBuilder("gcc", tempFile.getAbsolutePath(), "-o", "output");
            compileProcessBuilder.redirectErrorStream(true);
    
            Process compileProcess = compileProcessBuilder.start();
            int compileExitCode = compileProcess.waitFor();
    
            if (compileExitCode == 0) {
                // Execute the compiled program using Windows Command Prompt
                ProcessBuilder runProcessBuilder = new ProcessBuilder("cmd.exe", "/c", "start", "cmd.exe", "/K", "output");
                runProcessBuilder.redirectErrorStream(true);
    
                Process runProcess = runProcessBuilder.start();
    
                // Wait for the process to complete
                runProcess.waitFor();
            } else {
                // Handle compilation error
                JOptionPane.showMessageDialog(
                        null, "Compilation failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null, "An error occurred while compiling and running the code.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    


    private String getVideoURL(String iconName) {
        int index = Arrays.asList(iconNames).indexOf(iconName);
        if (index >= 0 && index < videoURLs.length) {
            return videoURLs[index];
        }
        return "";
    }

    private void openURL(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
    }

    // private String getDescriptionText(String iconName) {
    //     int index = Arrays.asList(iconNames).indexOf(iconName);
    //     if (index >= 0 && index < descriptionTexts.length) {
    //         return descriptionTexts[index];
    //     }
    //     return "";
    // }

    private String getJavadocURL(String iconName) {
        int index = Arrays.asList(iconNames).indexOf(iconName);
        if (index >= 0 && index < javadocURLs.length) {
            return javadocURLs[index];
        }
        return "";
    }

    private String getExampleURL(String iconName) {
        int index = Arrays.asList(iconNames).indexOf(iconName);
        if (index >= 0 && index < exampleURLs.length) {
            return exampleURLs[index];
        }
        return "";
    }
   
}
