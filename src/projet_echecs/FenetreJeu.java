package projet_echecs;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FenetreJeu extends JFrame {

    private Echiquier e;        //echiquier
    private JLabel[][] tab;    //tableau de JLabels

    private JPanel panelControle = new JPanel();  // panel du haut
    private JPanel panelGrille = new JPanel();  // panel du bas ( grille )
    GridLayout gridLayout1 = new GridLayout();

    private JButton boutonDebuter = new JButton();
    private JTextField champTexte = new JTextField();
    private JButton boutonReset = new JButton();

    private JPanel panelNoir, panelBlanc; // panels où on placera les pieces capturées

    public FenetreJeu()   // constructeur appelle méthode JBInit
    {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {


        tab = new JLabel[8][8];   // création du tableau de JLabel
        e = new Echiquier();      // création de l'échiquier


        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(800, 629));
        this.setTitle("Jeu d'Echecs");
        panelControle.setBounds(new Rectangle(5, 10, 550, 45));
        panelControle.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        panelControle.setLayout(null);
        panelGrille.setBounds(new Rectangle(5, 65, 550, 465));
        panelGrille.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        panelGrille.setLayout(gridLayout1);
        gridLayout1.setColumns(8);
        gridLayout1.setRows(8);
        this.getContentPane().add(panelGrille, null);
        panelControle.add(boutonReset, null);
        panelControle.add(champTexte, null);
        panelControle.add(boutonDebuter, null);
        this.getContentPane().add(panelControle, null);
        boutonDebuter.setBounds(new Rectangle(15, 10, 130, 25));
        boutonDebuter.setText("DEBUTER");
        champTexte.setBounds(new Rectangle(160, 10, 215, 25));

        // les écouteurs
        boutonReset.setText("RESET");
        boutonReset.setBounds(new Rectangle(390, 10, 130, 25));
        GestionnaireEvenement gest = new GestionnaireEvenement();
        boutonDebuter.addMouseListener(gest);
        boutonReset.addMouseListener(gest);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tab[i][j] = new JLabel(); // création du JLabel
                panelGrille.add(tab[i][j]);  // ajouter au Panel
                tab[i][j].setOpaque(true);
                tab[i][j].setHorizontalAlignment(SwingConstants.CENTER);  // pour que les pieces apparaissent au centre de la case
                tab[i][j].addMouseListener(gest);  // ajouter l'écouteur aux sources
                // 1. attribuer couleur aux JLabels
                if ((i + j) % 2 == 0) {
                    tab[i][j].setBackground(Color.LIGHT_GRAY);
                } else {
                    tab[i][j].setBackground(Color.DARK_GRAY);
                }
            }
        }
        //2. deux nouveaux panels pour les pièces capturées
        panelNoir = new JPanel(new FlowLayout());
        panelNoir.setBackground(new Color(5, 5, 5, 160));
        panelNoir.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        panelNoir.setBounds(new Rectangle(572, 65, 100, 465));
        this.getContentPane().add(panelNoir);

        panelBlanc = new JPanel(new FlowLayout());
        panelBlanc.setBackground(new Color(255, 250, 250, 170));
        panelBlanc.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        panelBlanc.setBounds(new Rectangle(680, 65, 100, 465));
        this.getContentPane().add(panelBlanc);
    }

    // classe privée pour la gestion d'Événements
    private class GestionnaireEvenement extends MouseAdapter {

        Piece pieceTampon;
        ImageIcon iconeTampon;
        int ligneClic; // ligne où on a cliqué
        int colonneClic; // colonne où on a cliqué
        Position depart, arrivee;
        String couleurControle = "blanc";


        public void mouseReleased(MouseEvent eve) {
            // si on clique sur le bouton débuter
            if (eve.getSource() == boutonDebuter) {
                // 3.quoi faire ?
                // Attribuer les icones aux JLabels (pièces autres que Pions)
                e.debuter();
                tab[0][0].setIcon(new ImageIcon("Icones\\TN.gif"));
                tab[0][1].setIcon(new ImageIcon("Icones\\CN.gif"));
                tab[0][2].setIcon(new ImageIcon("Icones\\FN.gif"));
                tab[0][3].setIcon(new ImageIcon("Icones\\DN.gif"));
                tab[0][4].setIcon(new ImageIcon("Icones\\RN.gif"));
                tab[0][5].setIcon(new ImageIcon("Icones\\FN.gif"));
                tab[0][6].setIcon(new ImageIcon("Icones\\CN.gif"));
                tab[0][7].setIcon(new ImageIcon("Icones\\TN.gif"));
                tab[7][0].setIcon(new ImageIcon("Icones\\TB.gif"));
                tab[7][1].setIcon(new ImageIcon("Icones\\CB.gif"));
                tab[7][2].setIcon(new ImageIcon("Icones\\FB.gif"));
                tab[7][3].setIcon(new ImageIcon("Icones\\DB.gif"));
                tab[7][4].setIcon(new ImageIcon("Icones\\RB.gif"));
                tab[7][5].setIcon(new ImageIcon("Icones\\FB.gif"));
                tab[7][6].setIcon(new ImageIcon("Icones\\CB.gif"));
                tab[7][7].setIcon(new ImageIcon("Icones\\TB.gif"));
                // Attribuer les icones de Pions aux JLabels
                for (int i = 0; i < 8; i++) {
                    tab[1][i].setIcon(new ImageIcon("Icones\\PN.gif"));
                    tab[6][i].setIcon(new ImageIcon("Icones\\PB.gif"));
                }
                champTexte.setText("C'est aux " + couleurControle + "s à jouer ");
            }

            // si on clique sur le bouton reset
            else if (eve.getSource() == boutonReset) {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        e.getCase(i,j).retirerPieceCase();
                        tab[i][j].setIcon(null);
                    }
                }
                pieceTampon = null;
                iconeTampon = null;
                couleurControle = "blanc";

            } else { // donc on a cliqué sur un Label
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (eve.getSource() == tab[i][j]) {
                            ligneClic = i;
                            colonneClic = j;
                            System.out.println("Clic (" + i + "," + j + ")");
                        }
                    }
                }

                //5. votre travail

                // 1cas : clique sur une case occupée et que le tampon est vide -->cas départ
                if (e.getCase(ligneClic, colonneClic).isMemeCouleur(couleurControle) && pieceTampon == null) {
                    //initialiser position depart
                    depart = new Position(ligneClic, colonneClic);
                    //prendre l'icone et la mettre dans le tampon, prendre la piece et la mettre dans le tampon
                    iconeTampon = (ImageIcon) tab[ligneClic][colonneClic].getIcon();
                    pieceTampon = e.getCase(ligneClic, colonneClic).getPieceSurCase();
                    //enlever le tampon de la place d'origine pas la piece
                    tab[ligneClic][colonneClic].setIcon(null);

                }
                // 2e cas : clique sur une case vide; tampon plein cas d'arrivee, pas de pion en diagonale
                else if (!e.getCase(ligneClic, colonneClic).isOccupee() && pieceTampon != null) {
                    //initialiser position d'arrivee
                    arrivee = new Position(ligneClic, colonneClic);
                    //verfier estValide, exclue les pions en diagonales
                    if (e.getCase(depart.getLigne(), depart.getColonne()).getPieceSurCase().estValide(depart, arrivee)) {
                        // verifier cheminPossible
                        if (e.cheminPossible(depart, arrivee)) {


                        if (e.verifierPromotion(depart, arrivee))
                        {
                            if ( pieceTampon.getCouleur() =="noir")
                            {
                                //changer l'IconeTampon pour une reine
                                iconeTampon =  new ImageIcon ("Icones\\DN.gif");

                                //changer la pieceTampon pour une Reine
                                pieceTampon = new Reine("r1", "noir");
                            }
                            else
                            {
                                //changer l'IconeTampon pour une reine
                                iconeTampon =  new ImageIcon ("Icones\\DB.gif");

                                //changer la pieceTampon pour une Reine
                                pieceTampon = new Reine("r1", "blanc");
                            }
                        }

                            //clean : enlever la piece du tampon et la mettre sur l'arrivee
                            e.getCase(ligneClic, colonneClic).setPieceSurCase(pieceTampon);

                            pieceTampon = null;
                            //enlever la piece de sa place d'origine
                            e.getCase(depart.getLigne(), depart.getColonne()).retirerPieceCase();

                            // l'enlever du tampon
                            tab[ligneClic][colonneClic].setIcon(iconeTampon);
                            iconeTampon = null;

                            alterne();
                        }
                    }
                }
                else if(e.getCase(ligneClic,colonneClic) == e.getCase(depart.getLigne(),depart.getColonne())){
                    e.getCase(ligneClic,colonneClic).setPieceSurCase(pieceTampon);
                    tab[ligneClic][colonneClic].setIcon(iconeTampon);
                    pieceTampon = null;
                    iconeTampon = null;
                }

                // 3e cas : clique sur une case occupee et tampon plein : case d arrivee + pion en diagonale ( peut-etre piece qui ne bouge pas )


                }
                // du grand else
            } // de la méthode mouseReleased


            private void alterne() {
            if (this.couleurControle.equals("blanc")) {
                this.couleurControle = "noir";
            } else {
                this.couleurControle = "blanc";
            }
            champTexte.setText("C'est aux " + couleurControle + "s à jouer ");
        }
    }
    //de la classe de gestion


    // main pour pouvoir exécuter l'interface graphique
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FenetreJeu frame = new FenetreJeu();
                    frame.setVisible(true);
                    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

