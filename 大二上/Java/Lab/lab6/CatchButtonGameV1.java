import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class CatchButtonGameV1 extends JFrame {
    private JButton[] buttons;
    private Random random = new Random();
    private int clickMeButtonIndex;

    public CatchButtonGameV1() {
        setTitle("Catch the Button Game");
        setLayout(new GridLayout(3, 3));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttons = new JButton[9];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("");
            buttons[i].addMouseListener(new ButtonMouseListener());
            add(buttons[i]);
        }

        placeClickMeButton();

        setSize(300, 300);
        setVisible(true);
    }

    private void placeClickMeButton() {
        if (clickMeButtonIndex >= 0) {
            buttons[clickMeButtonIndex].setText("");
        }
        clickMeButtonIndex = random.nextInt(buttons.length);
        buttons[clickMeButtonIndex].setText("Click me");
    }

    private class ButtonMouseListener extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            JButton source = (JButton) e.getSource();
            if (source == buttons[clickMeButtonIndex]) {
                placeClickMeButton();
            }
        }
    }

    public static void main(String[] args) {
        new CatchButtonGameV1();
    }
}
