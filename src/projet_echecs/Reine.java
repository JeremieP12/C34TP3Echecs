package projet_echecs;

public class Reine extends Piece{

    private Position position;

    public Reine(String nom, String couleur) {
        super(nom, couleur);
        this.position = new Position(5,5);
    }

    @Override
    public boolean estValide(Position depart, Position arrivee) {
        if(arrivee.getLigne()>7 || arrivee.getLigne()<0 || arrivee.getColonne()>7 || arrivee.getColonne()<0){ return false; }

    }
}
