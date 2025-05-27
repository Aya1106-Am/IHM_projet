import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Englishquizdef extends JFrame {
    private JLabel questionLabel;
    private JButton[] choiceButtons;
    private Map<String, String[]> anChoices;
    private String correctAnswer;
    private int currentQuestionIndex;
    private String[] questions;

    public Englishquizdef() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        questionLabel = new JLabel();
        questionLabel.setForeground(Color.white);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 30));
        questionLabel.setBounds(130, 60, 500, 30);

        add(questionLabel);

        setupChoiceButtons();

        anChoices = new HashMap<>();
        anChoices.put("I've got a ____", new String[]{"train red", "red train ", "red, train"});
        anChoices.put("We didn't have a ____", new String[]{"big lunch", "lunch big", "a big lunch"});
        anChoices.put("I'm going to _____ TV tonight", new String[]{"watching", "watches", "watch"});
       

        questions = anChoices.keySet().toArray(new String[0]);
        currentQuestionIndex = 0;

        displayNextQuestion();
    }

    private void setupChoiceButtons() {
        JPanel choicesPanel = new JPanel(new FlowLayout());
        choicesPanel.setOpaque(false);
        choiceButtons = new JButton[3];

        for (int i = 0; i < 3; i++) {
            choiceButtons[i] = new JButton();
            choiceButtons[i].setPreferredSize(new Dimension(120, 90));
            choiceButtons[i].setBackground(Color.white);

            choiceButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton sourceButton = (JButton) e.getSource();
                    String userAnswer = sourceButton.getText();
                    checkAnswer(userAnswer);
                    displayNextQuestion();
                }
            });
            choicesPanel.add(choiceButtons[i]);
        }

        choicesPanel.setBounds(50, 130, 500, 100);
        add(choicesPanel);
    }

    private void displayNextQuestion() {
        if (currentQuestionIndex < questions.length) {
            String currentQuestion = questions[currentQuestionIndex];
            questionLabel.setText(currentQuestion);

            String[] choices = anChoices.get(currentQuestion);

            for (int i = 0; i < 3; i++) {
                choiceButtons[i].setText(choices[i]);
            }

            setCorrectAnswer(currentQuestion); // Set the correct answer for the current question

            currentQuestionIndex++;
        } else {
            JOptionPane.showMessageDialog(this, "Game Over!");
           
        }
    }

    private void setCorrectAnswer(String currentQuestion) {
        // Set the correct answer based on the current question
        switch (currentQuestion) {
            case "I've got a ____":
                correctAnswer = anChoices.get(currentQuestion)[1];
                break;
            case "We didn't have a ____":
                correctAnswer = anChoices.get(currentQuestion)[0];
                break;
                 case "I'm going to _____ TV tonight":
                correctAnswer = anChoices.get(currentQuestion)[2];
                break;
            case "He's going to _____ to the park":
               
        }
    }

    private void checkAnswer(String userAnswer) {
        if (userAnswer.equals(correctAnswer)) {
            JOptionPane.showMessageDialog(this, "Correct! The answer is " + correctAnswer);
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect. The correct answer is " + correctAnswer);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                creerEtAfficherUI();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }
        
        static void creerEtAfficherUI() throws IOException{
                Englishquizdef game = new Englishquizdef();
                game.setTitle("choose the right answer ");
                game.setSize(600, 400);
                BufferedImage backgroundImage = ImageIO.read(new File("photo.png"));
                JPanel imagePanel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(backgroundImage, 0, 0, null);
                    }
                };
                imagePanel.setSize(backgroundImage.getWidth(), backgroundImage.getHeight());
                imagePanel.setOpaque(false); // Rendre le panel transparent
                game.add(imagePanel);

                game.setResizable(false);
                game.setLocationRelativeTo(null);// rendre dans le centre

                game.setVisible(true);
           
    }
}