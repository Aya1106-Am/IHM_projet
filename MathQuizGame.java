package anything;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MathQuizGame extends JFrame {
    private int score = 0;
    private int questionIndex = 0;

    private JLabel questionLabel;
    private JTextField answerField;
    private JButton submitButton;

    private String[][] questions = {
            {"2 + 2 =", "4"},
            {"5 * 3 =", "15"},
            {"8 / 2 =", "4"},
            // Ajoutez d'autres questions au besoin
    };

    public MathQuizGame() {
        setTitle("Math Quiz Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        questionLabel = new JLabel();
        answerField = new JTextField();
        submitButton = new JButton("Submit");

        add(questionLabel, BorderLayout.NORTH);
        add(answerField, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);

        displayNextQuestion();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                answerField.setText(""); // Efface le champ de réponse après chaque soumission
                displayNextQuestion();
            }
        });

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void displayNextQuestion() {
        if (questionIndex < questions.length) {
            String[] currentQuestion = questions[questionIndex];
            questionLabel.setText(currentQuestion[0]);
            questionIndex++;
        } else {
            endGame();
        }
    }

    private void checkAnswer() {
        String[] currentQuestion = questions[questionIndex - 1];
        String userAnswer = answerField.getText().trim();

        if (userAnswer.equals(currentQuestion[1])) {
            score++;
            JOptionPane.showMessageDialog(this, "Correct! Your current score is: " + score);
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect. The correct answer is: " + currentQuestion[1]);
        }
    }

    private void endGame() {
        JOptionPane.showMessageDialog(this, "Game Over! Your final score is: " + score);
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MathQuizGame();
            }
        });
    }
}
