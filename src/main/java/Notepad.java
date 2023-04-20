import javafx.stage.FileChooser;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class Notepad extends JFrame implements ActionListener
{
    JMenuBar menuBar = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenu edit = new JMenu("Edit");
    JMenu help = new JMenu("Help");


    JMenuItem newFile = new JMenuItem("New");
    JMenuItem openFile = new JMenuItem("Open");
    JMenuItem saveFile = new JMenuItem("Save");
    JMenuItem print = new JMenuItem("Print");
    JMenuItem exit = new JMenuItem("Exit");

    JMenuItem cut = new JMenuItem("Cut");
    JMenuItem copy = new JMenuItem("Copy");
    JMenuItem paste = new JMenuItem("Paste");


    JMenuItem about = new JMenuItem("About");


    JTextArea textArea = new JTextArea();




    Notepad()
    {
        setTitle("Notepad");
        setBounds(100,100,800,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon img = new ImageIcon("src/notepad.png");

        setIconImage(img.getImage());

        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(help);

        setJMenuBar(menuBar);

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(print);
        file.add(exit);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);

        help.add(about);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane);
        textArea.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        textArea.setLineWrap(true);


        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        print.addActionListener(this);
        exit.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        about.addActionListener(this);
    }



    public static void main(String[] args)
    {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        new Notepad().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
          if(e.getActionCommand().equalsIgnoreCase("New"))
          {
                textArea.setText(null);
          }
          else if(e.getActionCommand().equalsIgnoreCase("Open"))
          {

              JFileChooser fileChooser1 = new JFileChooser();
              FileNameExtensionFilter extensionFilter1 = new FileNameExtensionFilter("Only text files(.txt",".txt");
              fileChooser1.setAcceptAllFileFilterUsed(false);
              fileChooser1.addChoosableFileFilter(extensionFilter1);

              int action = fileChooser1.showOpenDialog(null);

              if(action!=fileChooser1.APPROVE_OPTION) {
                  return;
              }
              else {
                  try {
                      BufferedReader reader;
                      reader = new BufferedReader(new FileReader(fileChooser1.getSelectedFile()));
                      textArea.read(reader,null);
                  } catch (IOException ex) {
                      ex.printStackTrace();
                  }
              }

          }
          else if(e.getActionCommand().equalsIgnoreCase("Save"))
          {
              JFileChooser fileChooser = new JFileChooser();
              FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Only text files(.txt",".txt");
              fileChooser.setAcceptAllFileFilterUsed(false);
              fileChooser.addChoosableFileFilter(extensionFilter);

              int action = fileChooser.showSaveDialog(null);

              if(action!=fileChooser.APPROVE_OPTION) {
                  return;
              }
              else {
                  String fileName=fileChooser.getSelectedFile().getAbsolutePath().toString();
                  if(fileName.contains(".txt"))
                      fileName=fileName+".txt";
                  try
                  {
                      BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                      textArea.write(writer);
                  }
                  catch(IOException ex)
                  {
                      ex.printStackTrace();
                  }


              }

          }
          else if(e.getActionCommand().equalsIgnoreCase("Print"))
          {
              try {
                  textArea.print();
              } catch (PrinterException ex) {
                  throw new RuntimeException(ex);
              }
          }
          else if(e.getActionCommand().equalsIgnoreCase("Exit"))
          {
                System.exit(0);
          }
          else if(e.getActionCommand().equalsIgnoreCase("Cut"))
          {
                textArea.cut();
          }
          else if(e.getActionCommand().equalsIgnoreCase("Copy"))
          {
                textArea.copy();
          }
          else if(e.getActionCommand().equalsIgnoreCase("Paste"))
          {
                textArea.paste();
          }
          else if(e.getActionCommand().equalsIgnoreCase("About"))
          {
                new About().setVisible(true);
          }
    }
}
