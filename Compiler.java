
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import javax.swing.text.DefaultHighlighter;
import java.awt.*;
import java.io.*;
import java.net.*;
//public class Compiler{
public class Compiler extends JFrame {
    private JTextPane codePane;
    private JButton IManual;
    private JList<String> keywordList;
    private DefaultListModel<String> keywordListModel;
    private Map<String, Set<Integer>> keywordMap;
    private List<Highlight> highlights;
    private String URL1 = "";

    public Compiler() {

        IManual = new JButton("Instruction Manual");
        IManual.setForeground(new Color(152, 255, 204)); // Set minty green color to text
        IManual.setBackground(Color.BLACK); // Set black color to background
        Font buttonFont = new Font("Helvetica", Font.BOLD, 30); // Increase the font size to 24
        IManual.setFont(buttonFont);

        IManual.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Close the current Compiler window
                    dispose();

                    // Launch the Mannual class
                    //Mannual.main(new String[0]);
                }
            });

        setTitle("The Compiler");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1366, 768));

        codePane = new JTextPane();

        codePane.setBackground(new Color(152, 255, 204, 50)); // Neon Blue with 20% opacity
        codePane.setOpaque(false);
        codePane.setForeground(new Color(48, 0, 48)); // Very dark purple
        Font font = new Font("Calibri", Font.PLAIN, 20);
        codePane.setFont(font);

        JScrollPane codeScrollPane = new JScrollPane(codePane);
        codeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        codeScrollPane.setPreferredSize(new Dimension(500, 600));

        keywordListModel = new DefaultListModel<>();
        keywordList = new JList<>(keywordListModel);
        keywordList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane keywordScrollPane = new JScrollPane(keywordList);
        keywordScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        keywordScrollPane.setPreferredSize(new Dimension(200, 600));

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(keywordScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(IManual, BorderLayout.CENTER);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, codeScrollPane, rightPanel);
        splitPane.setResizeWeight(0.65);
        splitPane.setDividerLocation(0.65);

        add(splitPane);
        keywordMap = new HashMap<>();
        highlights = new ArrayList<>();

        codePane.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    updateKeywords();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    updateKeywords();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    updateKeywords();
                }
            });

        keywordList.addListSelectionListener(e -> {
                    int selectedIndex = keywordList.getSelectedIndex();
                    highlightSelectedKeyword(selectedIndex);
                    scrollToSelectedLine(selectedIndex);
            });

        pack();
        setLocationRelativeTo(null);

    }

    private void updateKeywords() {
        codePane.getHighlighter().removeAllHighlights();
        highlights.clear();
        keywordMap.clear();

        String code = codePane.getText();
        StringTokenizer tokenizer = new StringTokenizer(code, "\n");

        while (tokenizer.hasMoreTokens()) {
            String line = tokenizer.nextToken();
            String[] words = line.split("\\s+"); // Split by any whitespace character
            for (String word : words) {
                if (isKeyword(word)) {
                    Set<Integer> lineNumbers = keywordMap.computeIfAbsent(word, k -> new HashSet<>());
                    int lineNumber = getLineNumber(code, line);
                    lineNumbers.add(lineNumber);
                }
            }
        }

        updateKeywordList();
        //highlightKeywordsInCodePane();
    }

    private boolean isKeyword(String token) {
        // Add Java keywords here
        String[] keywords = {
                "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const",
                "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float",
                "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long",
                "native", "new", "package", "private", "protected", "public", "return", "short", "static",
                "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient",
                "try", "void", "volatile", "while","main"
            };

        return Arrays.asList(keywords).contains(token);
    }

    private int getLineNumber(String code, String line) {
        String[] lines = code.split("\n");
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].equals(line)) {
                return i + 1;
            }
        }
        return -1;
    }

    private void updateKeywordList() {
        keywordListModel.clear();

        // Sort the keywords based on the first page number
        List<Map.Entry<String, Set<Integer>>> sortedKeywords = new ArrayList<>(keywordMap.entrySet());
        sortedKeywords.sort(Comparator.comparingInt(entry -> entry.getValue().stream().findFirst().orElse(Integer.MAX_VALUE)));

        for (Map.Entry<String, Set<Integer>> entry : sortedKeywords) {
            String keyword = entry.getKey();
            Set<Integer> lineNumbers = entry.getValue();
            StringBuilder sb = new StringBuilder();
            sb.append(keyword).append(": ");
            List<Integer> sortedLineNumbers = new ArrayList<>(lineNumbers);
            Collections.sort(sortedLineNumbers);
            for (int i = 0; i < sortedLineNumbers.size(); i++) {
                sb.append("[").append(sortedLineNumbers.get(i)).append("]");
                if (i < sortedLineNumbers.size() - 1) {
                    sb.append(", ");
                }
            }
            keywordListModel.addElement(sb.toString());
        }
    }

    private Map.Entry<String, Set<Integer>> findKeywordEntry(List<Map.Entry<String, Set<Integer>>> sortedKeywords, String keyword) {
        for (Map.Entry<String, Set<Integer>> entry : sortedKeywords) {
            if (entry.getKey().equals(keyword)) {
                sortedKeywords.remove(entry);
                return entry;
            }
        }
        return null;
    }

    private int getFirstPageNumber(Set<Integer> lineNumbers) {
        for (int lineNumber : lineNumbers) {
            String line = codePane.getDocument().getDefaultRootElement().getElement(lineNumber - 1).toString();
            int startIndex = line.indexOf('[');
            int endIndex = line.indexOf(']');
            if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
                String pageNumbers = line.substring(startIndex + 1, endIndex);
                String[] pageNumberTokens = pageNumbers.split(",");
                if (pageNumberTokens.length > 0) {
                    try {
                        return Integer.parseInt(pageNumberTokens[0]);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private void highlightSelectedKeyword(int selectedIndex) {
        codePane.getHighlighter().removeAllHighlights();
        highlights.clear();

        if (selectedIndex >= 0 && selectedIndex < keywordListModel.getSize()) {
            String selectedItem = keywordListModel.getElementAt(selectedIndex);
            String keyword = selectedItem.substring(0, selectedItem.indexOf(":"));
            Set<Integer> lineNumbers = keywordMap.get(keyword);

            if (lineNumbers != null) {
                for (int lineNumber : lineNumbers) {
                    try {
                        Element root = codePane.getDocument().getDefaultRootElement();
                        Element lineElement = root.getElement(lineNumber - 1);
                        int startOffset = lineElement.getStartOffset();
                        int endOffset = lineElement.getEndOffset() - 1; // Exclude the newline character

                        String line = codePane.getDocument().getText(startOffset, endOffset - startOffset);
                        int keywordIndex = line.indexOf(keyword);
                        while (keywordIndex >= 0) {
                            int highlightStart = startOffset + keywordIndex;
                            int highlightEnd = highlightStart + keyword.length();
                            highlights.add(new Highlight(highlightStart, highlightEnd));
                            keywordIndex = line.indexOf(keyword, keywordIndex + 1);
                        }
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }
                }
                int arr1[]={255,128,0,192,64};
                int arr2[]={255,128,192,64,200,0,175};
                int arr3[]={255,128,0,192,64,175};

                Random random = new Random();
                int randomNumber = random.nextInt(5);
                int randomNumber2 = random.nextInt(7);
                int randomNumber3 = random.nextInt(6);
                int f1 = arr1[randomNumber-1];
                int f2 = arr2[randomNumber2-1];
                int f3 =arr3[randomNumber3-1];

                for (Highlight highlight : highlights) {
                    try {

                        codePane.getHighlighter().addHighlight(highlight.startOffset, highlight.endOffset, new CustomHighlightPainter(new Color(f1, f2, f3)));
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }
                }

                // Repaint the code pane to ensure the highlights remain bright yellow when scrolling
                codePane.repaint();

            }
        }
    }

    private void scrollToSelectedLine(int selectedIndex) {
        if (selectedIndex >= 0 && selectedIndex < keywordListModel.getSize()) {
            String selectedItem = keywordListModel.getElementAt(selectedIndex);
            String keyword = selectedItem.substring(0, selectedItem.indexOf(":"));
            Set<Integer> lineNumbers = keywordMap.get(keyword);

            if (lineNumbers != null && !lineNumbers.isEmpty()) {
                Integer firstLineNumber = lineNumbers.iterator().next();
                Element root = codePane.getDocument().getDefaultRootElement();
                Element lineElement = root.getElement(firstLineNumber - 1);
                Rectangle rect;
                try {
                    rect = codePane.modelToView(lineElement.getStartOffset());
                    codePane.scrollRectToVisible(rect);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class CustomHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {
        private final Stroke dashedStroke;

        public CustomHighlightPainter(Color color) {
            super(color);
            dashedStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{2}, 0);
        }
/**
        @Override
        public Shape paintLayer(Graphics g, int offs0, int offs1, Shape bounds, JTextComponent c, View view) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(dashedStroke);
            return super.paintLayer(g2d, offs0, offs1, bounds, c, view);
        }*/
    }

    private class Highlight {
        private final int startOffset;
        private final int endOffset;

        public Highlight(int startOffset, int endOffset) {
            this.startOffset = startOffset;
            this.endOffset = endOffset;
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
                    Compiler demo = new Compiler();

                    demo.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    demo.setVisible(true);
                    // Call the method to display the full-screen image
            });
    }

}
