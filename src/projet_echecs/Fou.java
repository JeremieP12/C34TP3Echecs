package projet_echecs;

public class Fou extends Piece{


    public Fou(String nom, String couleur) {
        super(nom, couleur);
    }

    @Override
    public boolean estValide(Position depart, Position arrivee) {
        if(arrivee.getLigne()>7 || arrivee.getLigne()<0 || arrivee.getColonne()>7 || arrivee.getColonne()<0){ return false; }
        if (arrivee.getLigne()-depart.getLigne() == arrivee.getColonne() - depart.getColonne()) {
            return true;
        }
        else return false;

    }
}
