import javax.swing.*;
import java.awt.event.*;
public class AnotherSimpleGui implements ActionListener {
    JButton myButton;
    public static void main(String[] args) {
        AnotherSimpleGui myGui = new AnotherSimpleGui();
        myGui.go();
    }
    public void go() {
        JFrame myFrame = new JFrame();
        myButton = new JButton("Click me");
        myButton.addActionListener(this);
        myFrame.add(myButton);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(400, 400);
        myFrame.setVisible(true);
    }
    public void actionPerformed(ActionEvent event) {
        myButton.setText("I've been clicked");
    }
}
