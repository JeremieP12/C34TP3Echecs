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
        this.getCase(0,0).setPieceSurCase(new Tour("t1","noir"));
        this.getCase(0,1).setPieceSurCase(new Cavalier("c1","noir"));
        this.getCase(0,2).setPieceSurCase(new Fou("f1","noir"));
        this.getCase(0,3).setPieceSurCase(new Reine("r1","noir"));
        this.getCase(0,4).setPieceSurCase(new Roi("noir"));
        this.getCase(0,5).setPieceSurCase(new Fou("f2","noir"));
        this.getCase(0,6).setPieceSurCase(new Cavalier("c2","noir"));
        this.getCase(0,7).setPieceSurCase(new Tour("t2","noir"));
        this.getCase(7,0).setPieceSurCase(new Tour("t1","blanc"));
        this.getCase(7,1).setPieceSurCase(new Cavalier("c1","blanc"));
        this.getCase(7,2).setPieceSurCase(new Fou("f1","blanc"));
        this.getCase(7,3).setPieceSurCase(new Reine("r1","blanc"));
        this.getCase(7,4).setPieceSurCase(new Roi("blanc"));
        this.getCase(7,5).setPieceSurCase(new Fou("f2","blanc"));
        this.getCase(7,6).setPieceSurCase(new Cavalier("c2","blanc"));
        this.getCase(7,7).setPieceSurCase(new Tour("t2","blanc"));

        for (int i = 0; i < 8; i++) {
            this.getCase(1,i).setPieceSurCase(new Pion("p"+i,"noir"));
            this.getCase(6,i).setPieceSurCase(new Pion("p"+i,"blanc"));
        }

    }

    @Override
    public Case getCase(int ligne, int colonne) {
        return location[ligne][colonne];
    }

    @Override
    public boolean cheminPossible(Position depart, Position arrivee) {
        if (!this.getCase(depart.getLigne(),depart.getColonne()).getPieceSurCase().estValide(arrivee,arrivee)){
            return false;
        }
    }

    @Override
    public boolean captureParUnPionPossible(Position depart, Position arrivee) {
        return false;
    }
}
