package projet_echecs;

import javax.swing.JFrame;

import java.awt.Dimension;

import javax.swing.JPanel;

import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class FenetreJeu extends JFrame {
    private Echiquier e;        //echiquier
    private JLabel[][] tab;     //tableau de JLabels

    private JPanel panelControle = new JPanel();    // panel du haut
    private JPanel panelGrille = new JPanel();      // panel du bas (grille)
    GridLayout gridLayout1 = new GridLayout();

    private JButton boutonDebuter = new JButton();
    private JTextField champTexte = new JTextField();
    private JButton boutonReset = new JButton();

    private JPanel panelNoir, panelBlanc; // panels o� on placera les pieces captur�es

    public FenetreJeu() {   // constructeur appelle m�thode JBInit
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        tab = new JLabel[8][8];   // cr�ation du tableau de JLabel
        e = new Echiquier();      // cr�ation de l'�chiquier


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
        boutonReset.setText("RESET");
        boutonReset.setBounds(new Rectangle(390, 10, 130, 25));

        // les �couteurs (listners)
        GestionnaireEvenement gest = new GestionnaireEvenement();
        boutonDebuter.addMouseListener(gest);
        boutonReset.addMouseListener(gest);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tab[i][j] = new JLabel();    // cr�ation du JLabel
                panelGrille.add(tab[i][j]);  // ajouter au Panel
                tab[i][j].setOpaque(true);
                tab[i][j].setHorizontalAlignment(SwingConstants.CENTER);  // pour que les pieces apparaissent au centre de la case
                tab[i][j].addMouseListener(gest);  // ajouter l'�couteur aux sources


                // 1. attribuer couleur aux JLabels
                if ((i+j) % 2 != 0) {
                    tab[i][j].setBackground(Color.DARK_GRAY);
                }
                else {
                    tab[i][j].setBackground(Color.LIGHT_GRAY);
                }
            }
        }

        //2. deux nouveaux panels pour les pi�ces captur�es

        panelNoir = new JPanel(new FlowLayout());
        panelNoir.setBackground(new Color(5,5,5,160));
        panelNoir.setBorder(new LineBorder(new Color(0,0,0),1,true));
        panelNoir.setBounds(new Rectangle(572,65,100,465));
        this.getContentPane().add(panelNoir);
        panelBlanc = new JPanel(new FlowLayout());
        panelBlanc.setBackground(new Color(255,255,255,170));
        panelBlanc.setBorder(new LineBorder(new Color(0,0,0),1,true));
        panelBlanc.setBounds(new Rectangle(680,65,100,465));
        this.getContentPane().add(panelBlanc);

    }

    // classe priv�e pour la gestion d'�v�nements
    private class GestionnaireEvenement extends MouseAdapter {

        Piece pieceTampon;
        ImageIcon iconeTampon;
        int ligneClic;
        int colonneClic;
        Position depart, arrivee;
        String couleurControle = "blanc";


        public void mouseReleased(MouseEvent eve) {
            // si on clique sur le bouton d�buter
            if (eve.getSource() == boutonDebuter) {
                // 3.quoi faire ?
                // attribuer les icones aux JLabels
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
                for (int i = 0; i < 8; i++) {
                    tab[1][i].setIcon(new ImageIcon("Icones\\PN.gif"));
                    tab[6][i].setIcon(new ImageIcon("Icones\\PB.gif"));
                }

                champTexte.setText("C'est le tour des " + couleurControle + "s");
            }


            // si on clique sur le bouton reset
            else if (eve.getSource() == boutonReset) {
                //4. votre travail
            } else { // donc on a cliqu� sur un Label
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (eve.getSource() == tab[i][j]) {
                            ligneClic = i;
                            colonneClic = j;
                            System.out.println("Case ("+i+","+j+")");
                        }
                    }
                }
                //5. votre travail
                if(e.getCase(ligneClic,colonneClic).isOccupee() && pieceTampon==null){
                    depart = new Position(ligneClic,colonneClic);
                    iconeTampon = (ImageIcon) tab[ligneClic][colonneClic].getIcon();
                    pieceTampon = e.getCase(ligneClic,colonneClic).getPieceSurCase();
                    tab[ligneClic][colonneClic].setIcon(null);
                }
                else if (!e.getCase(ligneClic,colonneClic).isOccupee() && pieceTampon!=null) {
                    arrivee = new Position(ligneClic,colonneClic);
                    if (e.getCase(depart.getLigne(),depart.getColonne()).getPieceSurCase().estValide(depart,arrivee)){
                        if (e.cheminPossible(depart,arrivee)){
                            //promotion
                            e.getCase(ligneClic,colonneClic).setPieceSurCase(pieceTampon);
                            pieceTampon = null;
                            e.getCase(depart.getLigne(),depart.getColonne()).retirerPieceCase();

                            tab[ligneClic][colonneClic].setIcon(iconeTampon);
                            iconeTampon = null;

                            alterne();
                        }
                    }
                }
            } // du grand else
        } // de la m�thode mouseReleased

        public void alterne(){
            if (couleurControle.equals("blanc")){
                couleurControle = "noir";
            }
            else {
                couleurControle = "blanc";
            }
        }
    } // de la classe de gestion



    // main pour pouvoir ex�cuter l'interface graphique
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