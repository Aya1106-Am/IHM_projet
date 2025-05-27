package anything;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppleGame {

    private static final String APPLE_IMAGE_PATH = "C:\\Users\\lenovo\\OneDrive\\Bureau\\tp IHM\\fruit-8072565_1280.jpg";

    private int appleCount;
    private JLabel appleLabel;

    public AppleGame() {
        appleCount = 1; // Commence avec une pomme

        JFrame frame = new JFrame("Jeu des Pommes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Label pour afficher les images de pommes
        appleLabel = new JLabel();
        updateAppleLabel();
        mainPanel.add(appleLabel, BorderLayout.CENTER);

        // Bouton pour ajouter une pomme
        JButton addButton = new JButton("Ajouter une pomme");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addApple();
            }
        });
        mainPanel.add(addButton, BorderLayout.SOUTH);

        frame.getContentPane().add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void addApple() {
        appleCount++;
        updateAppleLabel();
        JOptionPane.showMessageDialog(null, "Pomme ajoutée! Total de pommes : " + appleCount);
    }

    private void updateAppleLabel() {
        ImageIcon appleIcon = new ImageIcon("C:\\Users\\lenovo\\OneDrive\\Bureau\\tp IHM\\fruit-8072565_1280.jpg");
        Image scaledImage = appleIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        appleIcon = new ImageIcon(scaledImage);

        StringBuilder htmlText = new StringBuilder("<html><center>");
        for (int i = 0; i < appleCount; i++) {
            htmlText.append("<img src='").append("C:\\Users\\lenovo\\OneDrive\\Bureau\\tp IHM\\fruit-8072565_1280.jpg").append("' width='50' height='50'/>");
        }
        htmlText.append("<br>").append("Total de pommes : ").append(appleCount).append("</center></html>");

        appleLabel.setText(htmlText.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AppleGame();
            }
        });
    }
}
