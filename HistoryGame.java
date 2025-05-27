import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String text;
    private ArrayList<String> choices;
    private int correctChoice;

    public Question(String text, String choice1, String choice2, int correctChoice) {
        this.text = text;
        this.choices = new ArrayList<>();
        this.choices.add(choice1);
        this.choices.add(choice2);
        this.correctChoice = correctChoice;
    }

    public String getText() {
        return text;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public int getCorrectChoice() {
        return correctChoice;
    }
}

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

public class HistoryGame extends JFrame {
    private static final int MAX_QUESTIONS = 10;
    private static final int MAX_CHANCES = 3;

    private ArrayList<Question> questions;
    private int currentQuestionIndex;
    private int score;
    private int chances;
    private Timer timer;

    private JLabel questionLabel;
    private JRadioButton choice1;
    private JRadioButton choice2;
    private ButtonGroup choicesGroup;
    private JButton submitButton;
    private JLabel chancesLabel;
    private JLabel feedbackLabel;
    private JLabel timerLabel;
    private JPanel mainPanel;
    private JPanel startPanel;
    private JButton easyButton;
    private JButton hardButton;

    public HistoryGame() {
        super("History Quiz Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400); 
        setLocationRelativeTo(null);

        questions = initializeQuestions();
        Image backgroundImage = new ImageIcon("ecole.png").getImage();
        BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
        setContentPane(backgroundPanel); 

        createStartPanel();
        add(startPanel);
        initializeGameComponents(false); 
    }

    private ArrayList<Question> initializeQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("When was Algeria liberated :", "1954", "1962", 2));
        questions.add(new Question("Is Eid Al-Fitr a :", "Religious event", "National event", 1));
        questions.add(new Question("What is 1st May :", "Independence Day", "Labor Day", 2));
        questions.add(new Question("When was the birth of the Prophet :", "12 Rabi' al-Awwal", "12 Rabi' althaani", 1));
        questions.add(new Question("if Samy was born in 2005 and Ali was born in 2009, who was born first ?", "Samy", "Ali", 1));
        questions.add(new Question("Which one happened before th other :", "Your first Birthday", "Your First day in school", 1));
        questions.add(new Question("Which ons is a social event :", "Revolution Day", "A wedding", 2));
        questions.add(new Question("What happened on November first 1954 :", "The outbreak of The revolution", "Independence of Algeria", 1));
        questions.add(new Question("Which one is a Personal event :", "Election of The President", "My birthday", 2));
        questions.add(new Question("A National event concerns :", "The whole world", "My country", 2));
        
        return questions;
    }


    private void createStartPanel() {
        startPanel = new ImagePanel("ecole.png");
        startPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipadx = 70;
        gbc.ipady = 70;
    
        JLabel titleLabel = new JLabel("History Quiz Game", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setBackground(Color.WHITE);
        titleLabel.setOpaque(true);
    
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(-30, 0, 0, 0); 
        startPanel.add(titleLabel, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        easyButton = createButton("Easy", "puzzle.jpg");
        gbc.insets = new Insets(40, 0, 0, 20); // Added insets to lower the buttons
        startPanel.add(easyButton, gbc);
    
        gbc.gridx = 1;
        hardButton = createButton("Hard", "dif.png");
        startPanel.add(hardButton, gbc);
    
        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(false);
            }
        });
    
        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(true);
            }
        });
        setLocationRelativeTo(null);
    }
    
    
    
    private JButton createButton(String text, String imagePath) {
        JButton button = new JButton(text);
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(image));

        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.BLUE);
        button.setBackground(Color.WHITE);

        return button;
    }

    private static class ImagePanel extends JPanel {
        private Image backgroundImage;

        public ImagePanel(String imagePath) {
            backgroundImage = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }


    private void startGame(boolean isHardMode) {
        chances = MAX_CHANCES;
        remove(startPanel);
        initializeGameComponents(isHardMode);
        add(mainPanel);
        displayQuestion();
    }
    
    private void initializeGameComponents(boolean isHardMode) {
        mainPanel = new JPanel(new GridLayout(8, 1));
    
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
        chancesLabel = new JLabel("Chances left: " + MAX_CHANCES);
        timerLabel = new JLabel();
        chancesLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        timerLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
    
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(timerLabel);
        topPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        topPanel.add(chancesLabel);
    
        questionLabel = new JLabel();
        choice1 = new JRadioButton();
        choice2 = new JRadioButton();
        choicesGroup = new ButtonGroup();
        submitButton = new JButton("Submit");
        feedbackLabel = new JLabel();
        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        choice1.setHorizontalAlignment(SwingConstants.CENTER);
        choice2.setHorizontalAlignment(SwingConstants.CENTER);
    
        choicesGroup.add(choice1);
        choicesGroup.add(choice2);
    
        questionLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        choice1.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        choice2.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        chancesLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        timerLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
    
        submitButton.setPreferredSize(new Dimension(100, 30));
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setBackground(Color.PINK);
    
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.add(submitButton);
    
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });
    
        mainPanel.add(topPanel);
        mainPanel.add(questionLabel);
        mainPanel.add(choice1);
        mainPanel.add(choice2);
        mainPanel.add(btnPanel);
        mainPanel.add(feedbackLabel);
    
        if (isHardMode) {
            startTimer();
        }
        mainPanel.setPreferredSize(new Dimension(600, 400));
    }
    
    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int secondsLeft = 60;

            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (secondsLeft >= 0) {
                            timerLabel.setText("Time left: " + secondsLeft + " seconds");
                            secondsLeft--;
                        } else {
                            showFinalScore();
                            timer.cancel();
                        }
                    }
                });
            }
        }, 0, 1000);
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            questionLabel.setText(currentQuestion.getText());
            choice1.setText(currentQuestion.getChoices().get(0));
            choice2.setText(currentQuestion.getChoices().get(1));
            choicesGroup.clearSelection();
            feedbackLabel.setText("");
        } else {
            showFinalScore();
        }
    }

    private void checkAnswer() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        if (choice1.isSelected() && currentQuestion.getCorrectChoice() == 1 ||
                choice2.isSelected() && currentQuestion.getCorrectChoice() == 2) {
            score++;
            feedbackLabel.setText("Correct!");
        } else {
            chances--;
            feedbackLabel.setText("Wrong!");
        }

        chancesLabel.setText("Chances left: " + chances);

        Timer feedbackTimer = new Timer();
        feedbackTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        feedbackLabel.setText("");
                        currentQuestionIndex++;
                        if (currentQuestionIndex < questions.size() && chances > 0) {
                            displayQuestion();
                        } else {
                            showFinalScore();
                        }
                    }
                });
            }
        }, 1000); 
    }

    private void showFinalScore() {
        if (timer != null) {
            timer.cancel();
        }

        JOptionPane.showMessageDialog(this, "Your final score: " + score + " out of " + MAX_QUESTIONS);
        System.exit(0);
    }

  

    public static void main(String[] args) {
       
        creerEtAfficherUI();
    }
    static void creerEtAfficherUI()    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HistoryGame().setVisible(true);
            }
        });
     }
}
