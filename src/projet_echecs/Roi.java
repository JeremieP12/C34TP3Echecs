package projet_echecs;

public class Roi extends Piece{


    public Roi(String nom, String couleur) {
        super(nom, couleur);
    }

    @Override
    public boolean estValide(Position depart, Position arrivee) {
        if(arrivee.getLigne()>7 || arrivee.getLigne()<0 || arrivee.getColonne()>7 || arrivee.getColonne()<0){ return false; }
        if (arrivee.getLigne() - depart.getLigne() <=1 && arrivee.getColonne() - depart.getColonne() <= 1) {
            return true;
        }
        else return false;
    }

}
