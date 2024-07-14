package ExtraCredit;


/**
 * SuperHeroTTask4.java - A graphical user interface application designed specifically for children to practice and reinforce their knowledge of multiplication tables. The application features a superhero-themed interface to make learning more engaging. Users can select a multiplicand from a dropdown menu, and the application generates random multiplication problems involving the selected number. Users input their answers, and the application provides immediate feedback, helping children learn through repetition and correction.
 *
 * This class extends JFrame and sets up the GUI components including buttons, labels, text fields, and an image label to display a superhero image. The interface is interactive, responding to user actions with ActionListener implementations. It keeps track of the number of correct answers and gives a rating at the end of a session. It aims to create a fun and educational experience for young learners.
 *
 * @author Zecheng Zuo
 * @version 1.0
 * @since 2024-05-16
 */



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class SuperHeroTTask4 extends JFrame {
    private JComboBox<Integer> numberSelector;
    private JLabel productLabel;
    private JTextField answerField;
    private JButton controlButton;
    private JLabel timeLabel;
    private JLabel ratingLabel;
    private JLabel superheroImageLabel;
    private ImageIcon imageIcon;
    private int correctAnswers = 0;
    private int currentQuestion = 0;
    private Timer timer;
    private int elapsedTime = 0;
    private Random random = new Random();

    /**
     * Constructor for SuperHeroTTask4 which initializes the user interface and components.
     */
    public SuperHeroTTask4() {
        setTitle("SuperHero Times Tables");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        initializeComponents();
        layoutComponents(gbc);

        pack();
        setSize(getWidth() + 70, getHeight() + 30);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Initializes all the GUI components used in the JFrame.
     */
    private void initializeComponents() {
        numberSelector = new JComboBox<>();
        for (int i = 1; i <= 12; i++) {
            numberSelector.addItem(i);
        }
        productLabel = new JLabel("Product");
        answerField = new JTextField(10);
        controlButton = new JButton("Start");
        controlButton.addActionListener(new ButtonListener());
        timeLabel = new JLabel("Time: 0 seconds");
        ratingLabel = new JLabel("Rating");
        imageIcon = new ImageIcon("D:\\1大学\\0000大二下\\Java\\Project\\ExtraCredit\\superhero.jpg");
        superheroImageLabel = new JLabel(new ImageIcon(imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));

        // Timer setup
        timer = new Timer(1000, e -> {
            elapsedTime++;
            timeLabel.setText("Time: " + elapsedTime + " seconds");
        });
    }

    /**
     * Configures the layout of components in the frame using GridBagConstraints.
     * @param gbc the GridBagConstraints object used to specify constraints for this layout
     */
    private void layoutComponents(GridBagConstraints gbc) {
        gbc.insets = new Insets(4, 4, 4, 4); // margins around components

        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 1;
        add(numberSelector, gbc);

        gbc.gridy = 1; gbc.gridx = 0;
        add(productLabel, gbc);
        gbc.gridx = 1;
        add(answerField, gbc);
        gbc.gridx = 2;
        add(controlButton, gbc);

        gbc.gridy = 2; gbc.gridx = 0;
        add(timeLabel, gbc); // Timer label replaces the answer label
        gbc.gridx = 1;
        add(superheroImageLabel, gbc);
        gbc.gridx = 2;
        add(ratingLabel, gbc);
    }

    /**
     * Defines the actions to be performed when the start/next button is clicked.
     */
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if ("Start".equals(controlButton.getText())) {
                startNewRound();
            } else {
                processAnswer();
            }
        }

        /**
         * Starts a new round of questions and resets the timer.
         */
        private void startNewRound() {
            elapsedTime = 0;
            timer.start();
            correctAnswers = 0;
            currentQuestion = 0;
            controlButton.setText("Next");
            nextQuestion();
        }

        /**
         * Processes the answer provided by the user.
         */
        private void processAnswer() {
            if (!answerField.getText().isEmpty()) {
                checkAnswer();
                if (currentQuestion < 5) {
                    nextQuestion();
                } else {
                    finishRound();
                }
            }
        }

        /**
         * Generates the next multiplication question and sets focus to the answer field.
         */
        private void nextQuestion() {
            int x = (Integer) numberSelector.getSelectedItem();
            int n = random.nextInt(12) + 1;
            productLabel.setText(x + " * " + n);
            answerField.setText("");
            answerField.requestFocusInWindow();  // Request focus each time a new question is presented
            currentQuestion++;
        }

        /**
         * Checks the user's answer and updates the result label.
         */
        private void checkAnswer() {
            String[] parts = productLabel.getText().split(" \\* ");
            int x = Integer.parseInt(parts[0]);
            int n = Integer.parseInt(parts[1]);
            int correctAnswer = x * n;
            int userAnswer = Integer.parseInt(answerField.getText());
            if (userAnswer == correctAnswer) {
                correctAnswers++;
            }
        }

        /**
         * Finishes the current round, stops the timer, and displays the total number of correct answers.
         */
        private void finishRound() {
            timer.stop();
            ratingLabel.setText("You got " + correctAnswers + " correct!");
            controlButton.setText("Start");
            timeLabel.setText("You took " + elapsedTime + " seconds");
        }
    }

    public static void main(String[] args) {
        new SuperHeroTTask4();
    }
}
