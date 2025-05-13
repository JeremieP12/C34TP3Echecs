package projet_echecs;

public class Cavalier extends Piece{


    public Cavalier(String nom, String couleur) {
        super(nom, couleur);
    }

    @Override
    public boolean estValide(Position depart, Position arrivee) {
        if(arrivee.getLigne()>7 || arrivee.getLigne()<0 || arrivee.getColonne()>7 || arrivee.getColonne()<0){ return false; }
        if (this.norme(depart,arrivee) == 5){
            return true;
        }
        else return false;

    }
}
