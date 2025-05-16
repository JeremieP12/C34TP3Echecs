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
        Piece pieceDepart = this.getCase(depart.getLigne(),depart.getColonne()).getPieceSurCase();
        Piece pieceArrivee = this.getCase(arrivee.getLigne(),arrivee.getColonne()).getPieceSurCase();

        if (!pieceDepart.estValide(depart,arrivee)) {
            return false;
        }

        if (this.getCase(arrivee.getLigne(),arrivee.getColonne()).isOccupee()){
            if (pieceArrivee.getCouleur().equals(pieceDepart.getCouleur())){
                return false;
            }
        }

        if (pieceDepart.getNom().charAt(0) == 'c'){
            return true;
        }

        boolean ligneAvance = true;
        boolean colonneAvance = true;
        if (arrivee.getLigne()-depart.getLigne()<0){ ligneAvance = false; }
        if (arrivee.getColonne()-depart.getColonne()<0){ colonneAvance = false; }

        int deplacement = Math.max(Math.abs(arrivee.getLigne() - depart.getLigne()),Math.abs(arrivee.getColonne() - depart.getColonne()));

        if (depart.getLigne() != arrivee.getLigne() && depart.getColonne() != arrivee.getColonne()) {
            for (int i = 1; i < deplacement-1; i++) {
                if (ligneAvance && colonneAvance){
                    if (this.getCase(depart.getLigne() + i, depart.getColonne() + i).isOccupee()) {
                        return false;
                    }
                }
                if (!ligneAvance && !colonneAvance){
                    if (this.getCase(depart.getLigne() - i, depart.getColonne() - i).isOccupee()) {
                        return false;
                    }
                }
                if (ligneAvance && !colonneAvance){
                    if (this.getCase(depart.getLigne() + i, depart.getColonne() - i).isOccupee()) {
                        return false;
                    }
                }
                if (!ligneAvance && colonneAvance){
                    if (this.getCase(depart.getLigne() - i, depart.getColonne() + i).isOccupee()) {
                        return false;
                    }
                }

            }
        }
        if (depart.getColonne() == arrivee.getColonne() ) {
            for (int i = 1; i < deplacement-1; i++) {
                if (!ligneAvance){
                    if (this.getCase(depart.getLigne() - i, depart.getColonne()).isOccupee()) {
                        return false;
                    }
                }
                if (ligneAvance){
                    if (this.getCase(depart.getLigne() + i, depart.getColonne()).isOccupee()) {
                        return false;
                    }
                }

            }
        }
        if (depart.getLigne() == arrivee.getLigne() ) {
            for (int i = 1; i < deplacement-1; i++) {
                if (!colonneAvance){
                    if (this.getCase(depart.getLigne(), depart.getColonne()-i).isOccupee()) {
                        return false;
                    }
                }
                if (colonneAvance){
                    if (this.getCase(depart.getLigne(), depart.getColonne()+i).isOccupee()) {
                        return false;
                    }
                }

            }
        }
        return false;
    }

    @Override
    public boolean captureParUnPionPossible(Position depart, Position arrivee) {
        return false;
    }
}
