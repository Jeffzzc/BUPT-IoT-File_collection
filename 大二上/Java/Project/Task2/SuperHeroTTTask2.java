package Task2;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * SuperHeroTTTask2.java - A Java Swing application that extends JFrame and allows children to practice multiplication tables with a superhero-themed interface. This version of the application allows users to select multiple multiplicands using checkboxes, making the application more interactive and challenging. It generates random multiplication questions based on the user-selected numbers, displays them, and evaluates the answers provided by the user. The application is designed to make learning math fun and engaging by incorporating a superhero image and providing immediate feedback on the user's answers.
 *
 * The GUI includes checkboxes for number selection, a product label for displaying questions, a text field for entering answers, and buttons to control the flow of the application. It also shows a superhero image and displays a cumulative rating based on the user's performance. This class manages the state and logic for initiating rounds, processing answers, and updating the UI accordingly.
 *
 * @author Zecheng Zuo
 * @version 1.0
 * @since 2024-05-16
 */
public class SuperHeroTTTask2 extends JFrame {
    private List<JCheckBox> checkboxes;
    private JLabel productLabel;
    private JTextField answerField;
    private JButton controlButton;
    private JLabel resultLabel;
    private JLabel ratingLabel;
    private JLabel superheroImageLabel;
    private ImageIcon imageIcon;
    private int correctAnswers = 0;
    private int currentQuestion = 0;
    private Random random = new Random();


    /**
     * Constructor initializes the JFrame, setting up the GUI components and layout.
     * It configures the JFrame size, title, default close operation, layout manager,
     * and adds all GUI elements including panels, labels, text fields, and buttons.
     */
    public SuperHeroTTTask2() {
        setSize(800, 600);
        setTitle("SuperHero Times Tables");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Create checkboxes for selecting multiple numbers
        checkboxes = new ArrayList<>();
        JPanel numberPanel = new JPanel(new FlowLayout());
        for (int i = 1; i <= 12; i++) {
            JCheckBox checkBox = new JCheckBox(String.valueOf(i));
            checkboxes.add(checkBox);
            numberPanel.add(checkBox);
        }
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        add(numberPanel, gbc);

        // Set up the rest of the UI
        productLabel = new JLabel("Product", SwingConstants.CENTER);
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(productLabel, gbc);

        answerField = new JTextField(10);
        gbc.gridx = 1;
        add(answerField, gbc);

        controlButton = new JButton("Start");
        controlButton.addActionListener(new ButtonListener());
        gbc.gridx = 2;
        add(controlButton, gbc);

        resultLabel = new JLabel("Answer", SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(resultLabel, gbc);

        // Load and display the superhero image
        imageIcon = new ImageIcon("D:\\1大学\\0000大二下\\Java\\Project\\Task2\\superhero.jpg"); // Ensure the path is correct
        superheroImageLabel = new JLabel(imageIcon);
        gbc.gridx = 1;
        add(superheroImageLabel, gbc);

        ratingLabel = new JLabel("Rating", SwingConstants.CENTER);
        gbc.gridx = 2;
        add(ratingLabel, gbc);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Initializes the ActionListener for the control button which determines the flow of the application.
     * This inner class listens for button clicks and handles the logic to start new rounds, process answers,
     * generate new questions, and finish the quiz round based on user interactions.
     */
    private class ButtonListener implements ActionListener {
        /**
         * Responds to button clicks to either start a new round or process a provided answer.
         * @param e the ActionEvent triggered by button clicks
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (controlButton.getText().equals("Start")) {
                startNewRound();
            } else {
                processAnswer();
            }
        }

        /**
         * Starts a new round of multiplication questions by resetting counters and updating button text.
         * It also calls nextQuestion() to generate the first question of the round.
         */
        private void startNewRound() {
            correctAnswers = 0;
            currentQuestion = 0;
            controlButton.setText("Next");
            nextQuestion();
        }

        /**
         * Processes the user's answer, checks if it is correct, and either moves to the next question or ends the round.
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
         * Generates the next multiplication question from selected numbers and updates the UI to reflect it.
         * If no numbers are selected, it shows an error dialog.
         */
        private void nextQuestion() {
            List<Integer> selectedNumbers = new ArrayList<>();
            for (JCheckBox checkbox : checkboxes) {
                if (checkbox.isSelected()) {
                    selectedNumbers.add(Integer.parseInt(checkbox.getText()));
                }
            }
            if (selectedNumbers.isEmpty()) {
                JOptionPane.showMessageDialog(SuperHeroTTTask2.this, "Please select at least one number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int x = selectedNumbers.get(random.nextInt(selectedNumbers.size()));
            int n = random.nextInt(12) + 1;
            productLabel.setText(x + " * " + n);
            answerField.setText("");
            answerField.requestFocus();
            currentQuestion++;
        }

        /**
         * Checks the user's answer against the correct answer and updates the result label accordingly.
         */
        private void checkAnswer() {
            String[] parts = productLabel.getText().split(" \\* ");
            int x = Integer.parseInt(parts[0]);
            int n = Integer.parseInt(parts[1]);
            int correctAnswer = x * n;
            int userAnswer = Integer.parseInt(answerField.getText());
            if (userAnswer == correctAnswer) {
                resultLabel.setText("Correct");
                correctAnswers++;
            } else {
                resultLabel.setText("Wrong! " + x + " * " + n + " = " + correctAnswer);
            }
        }

        /**
         * Finishes the current round by displaying the total number of correct answers and resets the control button.
         */
        private void finishRound() {
            ratingLabel.setText("You got " + correctAnswers + " correct!");
            controlButton.setText("Start");
        }
    }

    /**
     * The main method to create and display the application window.
     */
    public static void main(String[] args) {
        new SuperHeroTTTask2(); // Create and display the application window
    }
}
