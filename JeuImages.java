package anything;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class JeuImages {

    private static List<String> images = Arrays.asList("fleur.jpg", "arbre.jpg", "maison.jpg", "voiture.jpg");
    private static List<String> reponses = Arrays.asList("Fleur", "Arbre", "Maison", "Voiture");

    private static String imageCourante;
    private static String reponseCorrecte;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Jeu des Images");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(new BorderLayout());
            JLabel labelImage = new JLabel();
            JLabel labelQuestion = new JLabel("Qu'est-ce que cette image représente ?");
            JButton boutonChanger = new JButton("Changer d'image");

            JPanel propositionsPanel = new JPanel();

            boutonChanger.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    changerImageEtReponse();
                    afficherImage(labelImage);
                    genererPropositions(propositionsPanel);
                }
            });

            panel.add(labelImage, BorderLayout.CENTER);
            panel.add(labelQuestion, BorderLayout.NORTH);
            panel.add(propositionsPanel, BorderLayout.SOUTH);
            panel.add(boutonChanger, BorderLayout.EAST);

            frame.getContentPane().add(panel);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // Initialiser la première image et la première question
            changerImageEtReponse();
            afficherImage(labelImage);
            genererPropositions(propositionsPanel);

            // Exemple d'appel de la méthode verifierReponse
            String proposition = "votre proposition"; // Remplacez ceci par la vraie proposition
            verifierReponse(proposition, frame, labelImage, propositionsPanel);
        });
    }

    private static void changerImageEtReponse() {
        Random random = new Random();
        int index = random.nextInt(images.size());
        imageCourante = images.get(index);
        reponseCorrecte = reponses.get(index);
    }

    private static void afficherImage(JLabel label) {
        ImageIcon imageIcon = new ImageIcon(JeuImages.class.getClassLoader().getResource(imageCourante));
        label.setIcon(imageIcon);
    }

    private static void genererPropositions(JPanel panel) {
        panel.removeAll();

        List<String> propositions = new ArrayList<>(reponses);
        propositions.remove(reponseCorrecte);

        Random random = new Random();
        int positionCorrecte = random.nextInt(propositions.size() + 1);
        propositions.add(positionCorrecte, reponseCorrecte);

        propositions.forEach(proposition -> {
            JButton propositionButton = new JButton(proposition);
            propositionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    verifierReponse(proposition, (JFrame) SwingUtilities.getWindowAncestor(panel), (JLabel) panel.getParent().getComponent(0), panel);
                }
            });
            panel.add(propositionButton);
        });

        panel.revalidate();
        panel.repaint();
    }

    private static void verifierReponse(String reponse, JFrame frame, JLabel labelImage, JPanel propositionsPanel) {
        if (reponse.equals(reponseCorrecte)) {
            JOptionPane.showMessageDialog(null, "Bravo ! C'est la bonne réponse.");
            changerImageEtReponse();
            afficherImage(labelImage);
            genererPropositions(propositionsPanel);
        } else {
            JOptionPane.showMessageDialog(null, "Désolé, ce n'est pas la bonne réponse. Essayez encore !");
        }
    }
}
