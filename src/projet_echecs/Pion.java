package projet_echecs;

public class Pion extends Piece{


    public Pion(String nom, String couleur) {
        super(nom, couleur);
    }

    @Override
    public boolean estValide(Position depart, Position arrivee) {
        if(arrivee.getLigne()>7 || arrivee.getLigne()<0 || arrivee.getColonne()>7 || arrivee.getColonne()<0){ return false; }
            if (this.getCouleur().equalsIgnoreCase("blanc")){
                return estValideBlanc(depart,arrivee);
            }
            else return estValideNoir(depart,arrivee);
    }
    private static boolean estValideBlanc(Position depart, Position arrivee){
        if (depart.getColonne() == arrivee.getColonne()){
            if (arrivee.getLigne() - depart.getLigne() == -1){
                return true;
            }
            if (depart.getLigne() == 6){
                if (arrivee.getLigne() == 4) {
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean estValideNoir(Position depart, Position arrivee) {
        if (depart.getColonne() == arrivee.getColonne()) {
            if (arrivee.getLigne() - depart.getLigne() == 1) {
                return true;
            }
            if (depart.getLigne() == 1){
                if (arrivee.getLigne() == 3) {
                    return true;
                }
            }
        }
        return false;
    }

}
