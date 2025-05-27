package anything;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Question {
    private String questionText;
    private String correctAnswer;

    public Question(String questionText, String correctAnswer) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public boolean isCorrect(String userAnswer) {
        return correctAnswer.equalsIgnoreCase(userAnswer);
    }
}

class QuizPanel extends JPanel {
    private Quiz quiz;
    private JTextArea questionArea;
    private JTextField answerField;
    private JButton submitButton;
    private int currentQuestionIndex;

    public QuizPanel(Quiz quiz) {
        this.quiz = quiz;
        this.currentQuestionIndex = 0;

        setLayout(new BorderLayout());
        setOpaque(false); // Rendre le panneau transparent pour afficher l'image de fond

        questionArea = new JTextArea();
        questionArea.setEditable(false);
        questionArea.setOpaque(false);
        questionArea.setFont(new Font("Comic Sans MS", Font.BOLD, 24)); // Utilisation d'une police amusante
        questionArea.setForeground(Color.BLUE); // Couleur du texte

        add(questionArea, BorderLayout.NORTH);

        answerField = new JTextField();
        answerField.setOpaque(false);
        answerField.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        add(answerField, BorderLayout.CENTER);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitAnswer();
            }
        });
        submitButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(new Color(255, 69, 0)); // Orange vif
        add(submitButton, BorderLayout.SOUTH);

        showNextQuestion();
    }

    private void showNextQuestion() {
        if (currentQuestionIndex < quiz.getQuestions().size()) {
            Question question = quiz.getQuestions().get(currentQuestionIndex);
            questionArea.setText(question.getQuestionText());
            answerField.setText("");
            answerField.requestFocus();
        } else {
            showQuizResult();
        }
    }

    private void submitAnswer() {
        String userAnswer = answerField.getText();
        Question currentQuestion = quiz.getQuestions().get(currentQuestionIndex);

        if (currentQuestion.isCorrect(userAnswer)) {
            JOptionPane.showMessageDialog(this, "Bravo! C'est correct!");
        } else {
            JOptionPane.showMessageDialog(this, "Oh non! La réponse correcte est : " + currentQuestion.getCorrectAnswer(), "Incorrect", JOptionPane.ERROR_MESSAGE);
        }

        currentQuestionIndex++;
        showNextQuestion();
    }

    private void showQuizResult() {
        JOptionPane.showMessageDialog(this, "Félicitations! Quiz terminé!", "Fin du quiz", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}

class Quiz {
    private List<Question> questions;

    public Quiz() {
        questions = new ArrayList<>();
        // Ajoutez vos questions ici
        questions.add(new Question("Quelle est la somme de 5 + 6 ?", "11"));
        questions.add(new Question("Quelle est la somme de 9 + 9 ?", "18"));
        questions.add(new Question("Quelle est la somme de 18 + 6 ?", "24"));
        questions.add(new Question("Quelle est le produit de 4 * 2 ?", "8"));
        questions.add(new Question("Quelle est le produit de 3 * 10 ?", "30"));
        questions.add(new Question("Quelle est le produit de 14 * 2 ?", "28"));
    }

    public List<Question> getQuestions() {
        return questions;
    }
}

public class EducationalQuizGame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Quiz quiz = new Quiz();
                JFrame frame = new JFrame("Educational Quiz");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);

                // Image de fond pour la fenêtre principale (ajoutez une image enfant-friendly)
                ImageIcon frameBackgroundIcon = new ImageIcon("child_background.jpg");
                JLabel frameBackgroundLabel = new JLabel(frameBackgroundIcon);
                frame.getContentPane().add(frameBackgroundLabel);

                frame.getContentPane().add(new QuizPanel(quiz));
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
