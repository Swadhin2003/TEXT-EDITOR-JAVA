import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
public class TextEditor extends JFrame implements ActionListener 
{
    private JTextArea textArea;
    private JFileChooser fileChooser;
    public TextEditor() 
    {
        setTitle("Text Editor");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        newMenuItem.addActionListener(this);
        openMenuItem.addActionListener(this);
        saveMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(this);
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);
        fileChooser = new JFileChooser();
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) 
    {
        String command = e.getActionCommand();
        if (command.equals("New")) 
        {
            textArea.setText("");
        } 
        else if (command.equals("Open")) 
        {
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) 
            {
                try 
                {
                    File file = fileChooser.getSelectedFile();
                    FileReader reader = new FileReader(file);
                    BufferedReader br = new BufferedReader(reader);
                    textArea.read(br, null);
                    br.close();
                    textArea.requestFocus();
                } 
                catch (IOException ex) 
                {
                    ex.printStackTrace();
                }
            }
        } 
        else if (command.equals("Save")) 
        {
            int returnValue = fileChooser.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) 
            {
                try 
                {
                    File file = fileChooser.getSelectedFile();
                    FileWriter writer = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(writer);
                    textArea.write(bw);
                    bw.close();
                    textArea.requestFocus();
                } 
                catch (IOException ex) 
                {
                    ex.printStackTrace();
                }
            }
        } 
        else if (command.equals("Exit")) 
        {
            System.exit(0);
        }
    }
    public static void main(String[] args) 
    {
        new TextEditor();
    }
}