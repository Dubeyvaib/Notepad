import javax.swing.*;

public class About extends JFrame {

        About()
        {
            setBounds(100,100,400,400);
            setTitle("About");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ImageIcon img = new ImageIcon("src/notepad.png");
            setIconImage(img.getImage());
            setLayout(null);

            JLabel label1 = new JLabel(new ImageIcon("src/notepad.png"));
            label1.setBounds(100,50,80,80);


            add(label1);

            JLabel textLabel = new JLabel("<html>Hello</html>");
            textLabel.setBounds(100,50,350,350);

            add(textLabel);

        }


        public static void main (String args[])
        {
            new About().setVisible(true);
        }

}
