package anything;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CapitalGuessGame extends JFrame {
    private JLabel countryLabel;
    private JTextField answerField;
    private JButton submitButton;

    private Map<String, String> capitals;

    public CapitalGuessGame() {
        setTitle("Capital Guess Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        countryLabel = new JLabel();
        answerField = new JTextField();
        submitButton = new JButton("Submit");

        add(countryLabel, BorderLayout.NORTH);
        add(answerField, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);

        capitals = new HashMap<>();
        capitals.put("France", "Paris");
        capitals.put("Germany", "Berlin");
        capitals.put("Italy", "Rome");
        capitals.put("Japan", "Tokyo");
        capitals.put("Canada", "Ottawa");
        // Ajoutez d'autres pays et capitales au besoin

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
        Object[] countries = capitals.keySet().toArray();
        String randomCountry = (String) countries[(int) (Math.random() * countries.length)];

        countryLabel.setText("What is the capital of " + randomCountry + "?");
    }

    // Remplacez la méthode checkAnswer() dans la classe CapitalGuessGame par la
    // suivante :
    // Remplacez la méthode checkAnswer() dans la classe CapitalGuessGame par la suivante :
    private void checkAnswer() {
        String currentCountry = countryLabel.getText().replaceAll("What is the capital of (.+)\\?", "$1");
        String correctAnswer = capitals.get(currentCountry);
        String userAnswer = answerField.getText().trim();
    
        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            JOptionPane.showMessageDialog(this, "Correct! The capital of " + currentCountry + " is " + correctAnswer);
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect. The correct answer is " + correctAnswer);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CapitalGuessGame();
            }
        });
    }
}
