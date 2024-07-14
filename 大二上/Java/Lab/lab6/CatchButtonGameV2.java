import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * A simple Java GUI application where a button with the text "Click me" moves
 * to a random location when the mouse enters the button.
 * The number of buttons is specified by a command line argument.
 */
public class CatchButtonGameV2 extends JFrame {
    private JButton[] buttons;
    private Random random = new Random();
    private int clickMeButtonIndex;
    private int gridSize;

    /**
     * Constructs the CatchButtonGameV2 GUI.
     *
     * @param numButtons The number of buttons to display.
     */
    public CatchButtonGameV2(int numButtons) {
        gridSize = (int) Math.sqrt(numButtons);
        setTitle("Catch the Button Game");
        setLayout(new GridLayout(gridSize, gridSize));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttons = new JButton[numButtons];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("");
            buttons[i].addMouseListener(new ButtonMouseListener());
            add(buttons[i]);
        }

        placeClickMeButton();

        setSize(300, 300);
        setVisible(true);
    }

    /**
     * Places the "Click me" button at a random location.
     */
    private void placeClickMeButton() {
        if (clickMeButtonIndex >= 0) {
            buttons[clickMeButtonIndex].setText("");
        }
        clickMeButtonIndex = random.nextInt(buttons.length);
        buttons[clickMeButtonIndex].setText("Click me");
    }

    /**
     * Mouse listener that relocates the "Click me" button when the mouse enters.
     */
    private class ButtonMouseListener extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            JButton source = (JButton) e.getSource();
            if (source == buttons[clickMeButtonIndex]) {
                placeClickMeButton();
            }
        }
    }

    /**
     * The main method to start the game.
     *
     * @param args Command line arguments, expects one argument specifying the
     *             number of buttons.
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java CatchButtonGameV2 <number_of_buttons>");
            System.exit(1);
        }
        int numButtons = Integer.parseInt(args[0]);
        if (Math.sqrt(numButtons) % 1 != 0) {
            System.out.println("Number of buttons must be a perfect square.");
            System.exit(1);
        }
        new CatchButtonGameV2(numButtons);
    }
}

/*
修改您在第1节中编写的Java应用程序，使游戏显示的按钮数量成为命令行上的参数。例如，使用java CatchButtonGame 16命令运行应用程序将导致一个窗口显示控件中的按钮
4 * 4 grid3
 */
