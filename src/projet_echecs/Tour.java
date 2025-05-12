package projet_echecs;

public class Tour extends Piece{


    public Tour(String nom, String couleur) {
        super(nom, couleur);
    }

    @Override
    public boolean estValide(Position depart, Position arrivee) {
        if(arrivee.getLigne()>7 || arrivee.getLigne()<0 || arrivee.getColonne()>7 || arrivee.getColonne()<0){ return false; }
        if (depart.getLigne() == arrivee.getLigne()){
            return true;
        }
        else if (depart.getColonne() == arrivee.getColonne()){
            return true;
        }
        else return false;
    }
}
