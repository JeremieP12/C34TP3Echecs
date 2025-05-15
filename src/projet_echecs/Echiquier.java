package projet_echecs;

public class Echiquier implements MethodesEchiquier{

    private Case[][] location ;

    public Echiquier(){
        for (int i = 0;i < 8;i++){
            for (int j = 0;j<8;j++){
                this.location = new Case[i][j];
            }
        }
    }

    @Override
    public void debuter() {

    }

    @Override
    public Case getCase(int ligne, int colonne) {
        return location[ligne][colonne];
    }

    @Override
    public boolean cheminPossible(Position depart, Position arrivee) {
        return false;
    }

    @Override
    public boolean captureParUnPionPossible(Position depart, Position arrivee) {
        return false;
    }
}
