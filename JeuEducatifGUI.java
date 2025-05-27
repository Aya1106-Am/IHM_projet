package anything;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class JeuEducatifGUI extends JFrame {

    private Random random = new Random();
    private static String[] imagesFaciles = {"chat", "chien", "maison", "arbre", "soleil"};
    private static String[] verbesDifficiles = {"manger", "jouer", "dormir", "aller", "avoir"};

    public JeuEducatifGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setTitle("Jeu Educatif");

        JPanel panel = new JPanel();
        add(panel);

        JButton boutonNiveauFacile = new JButton("Niveau Facile");
        boutonNiveauFacile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ouvrirFenetreNiveauFacile();
            }
        });
        panel.add(boutonNiveauFacile);

        JButton boutonNiveauDifficile = new JButton("Niveau Difficile");
        boutonNiveauDifficile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ouvrirFenetreNiveauDifficile();
            }
        });
        panel.add(boutonNiveauDifficile);

        setVisible(true);
    }

    private void ouvrirFenetreNiveauFacile() {
        FenetreNiveauFacile fenetreNiveauFacile = new FenetreNiveauFacile(this);
        fenetreNiveauFacile.setVisible(true);
    }

    private void ouvrirFenetreNiveauDifficile() {
        FenetreNiveauDifficile fenetreNiveauDifficile = new FenetreNiveauDifficile(this);
        fenetreNiveauDifficile.setVisible(true);
    }

    private static class FenetreNiveauFacile extends JFrame {

        private Random random = new Random();

        public FenetreNiveauFacile(JFrame parent) {
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(400, 200);
            setTitle("Niveau Facile");
            setBounds(100, 100, 800, 600);  // x, y, largeur, hauteur
            setLocationRelativeTo(parent);
            JLabel questionLabel = new JLabel("Qu'est-ce que représente cette image?");
            add(questionLabel);
            JPanel panel = new JPanel();
            add(panel);

            int indexImage = random.nextInt(imagesFaciles.length);
            String image = imagesFaciles[indexImage];

            List<Object> propositions = new ArrayList<>();
            propositions.add(new Objet(image, true));

            // Générer deux objets faux
            String objetFaux1 = genererObjetFaux(image);
            String objetFaux2 = genererObjetFaux(objetFaux1);
            propositions.add(new Objet(objetFaux1, false));
            propositions.add(new Objet(objetFaux2, false));

            // Mélanger les propositions
            Collections.shuffle(propositions);

            String message = "Qu'est-ce que représente cette image?";
            for (Object proposition : propositions) {
                message += "\n- " + proposition.toString();
            }

            int choixUtilisateur = JOptionPane.showOptionDialog(this,
                    message,
                    "Question",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    propositions.toArray(),
                    null);

            if (choixUtilisateur >= 0 && choixUtilisateur < propositions.size()) {
                Objet choix = (Objet) propositions.get(choixUtilisateur);
                if (choix.estCorrect()) {
                    JOptionPane.showMessageDialog(this, "Correct! Bon travail!");
                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect. La réponse correcte est : " + image);
                }
            }

            parent.setEnabled(true);
        }

        private String genererObjetFaux(String objet) {
            // Vous pouvez implémenter une logique pour générer un objet faux cohérent
            // Par exemple, ajouter un préfixe ou un suffixe à l'objet, ou choisir un autre objet
            return objet + " faux"; // Exemple simple
        }
    }

    private static class FenetreNiveauDifficile extends JFrame {

        private Random random = new Random();

        public FenetreNiveauDifficile(JFrame parent) {
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(400, 200);
            setTitle("Niveau Difficile");
            setBounds(100, 100, 800, 600);  // x, y, largeur, hauteur
            setLocationRelativeTo(parent);
            JLabel questionLabel = new JLabel("Quel est le verbe conjugué correctement?");
        add(questionLabel);
            JPanel panel = new JPanel();
            add(panel);

            int indexVerbe = random.nextInt(verbesDifficiles.length);
            String verbe = verbesDifficiles[indexVerbe];
            String question = "Conjuguez le verbe : " + verbe;
            String reponseCorrecte = conjuguer(verbe, getSujet(0));

            List<String> propositions = new ArrayList<>();
            propositions.add(reponseCorrecte); // Proposition vraie

            // Générer deux propositions fausses cohérentes
            String propositionFausse1 = genererPropositionFausse(reponseCorrecte);
            String propositionFausse2 = genererPropositionFausse(propositionFausse1);
            propositions.add(propositionFausse1);
            propositions.add(propositionFausse2);

            // Mélanger les propositions
            Collections.shuffle(propositions);

            // Indice de la proposition vraie dans la liste mélangée
            int indiceReponseCorrecte = propositions.indexOf(reponseCorrecte);

            int choixUtilisateur = JOptionPane.showOptionDialog(this,
                    question,
                    "Question",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    propositions.toArray(),
                    null);

            if (choixUtilisateur == indiceReponseCorrecte) {
                JOptionPane.showMessageDialog(this, "Correct! Bon travail!");
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect. La réponse correcte est : " + reponseCorrecte);
            }

            parent.setEnabled(true);
        }

        private String conjuguer(String verbe, String sujet) {
            String conjugaison = "";

            // Logique simplifiée pour le présent
            if (verbe.endsWith("er")) {
                conjugaison = verbe.substring(0, verbe.length() - 2);
                if (sujet.equals("je")) {
                    conjugaison += "e";
                } else if (sujet.equals("tu")) {
                    conjugaison += "es";
                } else if (sujet.equals("il/elle/on")) {
                    conjugaison += "e";
                }
            } else {
                // Autres verbes (pour l'exemple, tous les autres verbes sont traités de la même manière)
                conjugaison = verbe; // À adapter en fonction de la conjugaison réelle
            }

            return conjugaison;
        }

        private String getSujet(int index) {
            if (index == 0) {
                return "je";
            } else if (index == 1) {
                return "tu";
            } else {
                return "il/elle/on";
            }
        }

        private String genererPropositionFausse(String verbe) {
            // Vous pouvez implémenter une logique pour générer une proposition fausse cohérente
            // Par exemple, ajouter un préfixe ou un suffixe au verbe, ou choisir un autre verbe
            return verbe + "ons"; // Exemple simple
        }
    }

    private static class Objet {
        private String nom;
        private boolean correct;

        public Objet(String nom, boolean correct) {
            this.nom = nom;
            this.correct = correct;
        }

        public String getNom() {
            return nom;
        }

        public boolean estCorrect() {
            return correct;
        }

        @Override
        public String toString() {
            return nom;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JeuEducatifGUI();
            }
        });
    }
}

