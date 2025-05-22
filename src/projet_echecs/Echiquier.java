package projet_echecs;

public class Echiquier implements MethodesEchiquier{

    private Case[][] location;

    private int nbReineNoir = 1;
    private int nbReineBlanc = 1;

    public Echiquier(){
        this.location =  new Case[8][8];
        for (int i = 0;i < 8;i++){
            for (int j = 0;j<8;j++){
                this.location[i][j] = new Case();
            }
        }
    }

    @Override
    public void debuter() {
        this.getCase(0,0).setPieceSurCase(new Tour("t1","noir"));
        this.getCase(0,1).setPieceSurCase(new Cavalier("c1","noir"));
        this.getCase(0,2).setPieceSurCase(new Fou("f1","noir"));
        this.getCase(0,3).setPieceSurCase(new Reine("r"+nbReineNoir,"noir"));
        this.getCase(0,4).setPieceSurCase(new Roi("noir"));
        this.getCase(0,5).setPieceSurCase(new Fou("f2","noir"));
        this.getCase(0,6).setPieceSurCase(new Cavalier("c2","noir"));
        this.getCase(0,7).setPieceSurCase(new Tour("t2","noir"));
        this.getCase(7,0).setPieceSurCase(new Tour("t1","blanc"));
        this.getCase(7,1).setPieceSurCase(new Cavalier("c1","blanc"));
        this.getCase(7,2).setPieceSurCase(new Fou("f1","blanc"));
        this.getCase(7,3).setPieceSurCase(new Reine("r"+nbReineBlanc,"blanc"));
        this.getCase(7,4).setPieceSurCase(new Roi("blanc"));
        this.getCase(7,5).setPieceSurCase(new Fou("f2","blanc"));
        this.getCase(7,6).setPieceSurCase(new Cavalier("c2","blanc"));
        this.getCase(7,7).setPieceSurCase(new Tour("t2","blanc"));
        int compteurPion = 1;
        for (int i = 0; i < 8; i++) {
            this.getCase(1,i).setPieceSurCase(new Pion("p"+compteurPion,"noir"));
            this.getCase(6,i).setPieceSurCase(new Pion("p"+compteurPion,"blanc"));
            compteurPion++;
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

        if (arrivee.getLigne() == depart.getLigne() && arrivee.getColonne() == depart.getColonne() ){
            return true;
        }
        if (this.getCase(depart.getLigne(),depart.getColonne()).getPieceSurCase().getNom().charAt(0) == 'p'){
            if (captureParUnPionPossible(depart,arrivee)){ return true; }
        }

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

        if (pieceDepart.getNom().charAt(0) == 'p' && this.getCase(arrivee.getLigne(), arrivee.getColonne()).isOccupee())
        {
            return false;
        }

        boolean ligneAvance = true;
        boolean colonneAvance = true;
        if (arrivee.getLigne()-depart.getLigne()<0){ ligneAvance = false; }
        if (arrivee.getColonne()-depart.getColonne()<0){ colonneAvance = false; }

        int deplacement = Math.max(Math.abs(arrivee.getLigne() - depart.getLigne()),Math.abs(arrivee.getColonne() - depart.getColonne()));

        if (depart.getColonne() == arrivee.getColonne()) {
            for (int i = 1; i <= deplacement-1; i++) {
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
            for (int i = 1; i <= deplacement-1; i++) {
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
        if (depart.getLigne() != arrivee.getLigne() && depart.getColonne() != arrivee.getColonne()) {
            for (int i = 1; i <= deplacement-1; i++) {
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
        return true;
    }

    @Override
    public boolean captureParUnPionPossible(Position depart, Position arrivee) {
        if (this.getCase(depart.getLigne(),depart.getColonne()).getPieceSurCase().getCouleur().equalsIgnoreCase("blanc")){
            return captureParUnPionPossibleBlanc(depart,arrivee);
        }
        else return captureParUnPionPossibleNoir(depart,arrivee);
    }

    private boolean captureParUnPionPossibleBlanc(Position depart,Position arrivee){
        if (arrivee.getLigne() == depart.getLigne()-1 && arrivee.getColonne() == depart.getColonne()-1 ) {
            if (this.getCase(depart.getLigne() - 1, depart.getColonne() - 1).isOccupee()) {
                if (this.getCase(depart.getLigne() - 1, depart.getColonne() - 1).getPieceSurCase().getCouleur().equals("noir")) {
                    return true;
                }
            }
        }
        if (arrivee.getLigne() == depart.getLigne()-1 && arrivee.getColonne() == depart.getColonne()+1 ) {
            if (this.getCase(depart.getLigne() - 1, depart.getColonne() + 1).isOccupee()) {
                if (this.getCase(depart.getLigne() - 1, depart.getColonne() + 1).getPieceSurCase().getCouleur().equals("noir")) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean captureParUnPionPossibleNoir(Position depart, Position arrivee){
        if (arrivee.getLigne() == depart.getLigne()+1 && arrivee.getColonne() == depart.getColonne()-1 ) {
            if (this.getCase(depart.getLigne() + 1, depart.getColonne() - 1).isOccupee()) {
                if (this.getCase(depart.getLigne() + 1, depart.getColonne() - 1).getPieceSurCase().getCouleur().equals("blanc")) {
                    return true;
                }
            }
        }
        if (arrivee.getLigne() == depart.getLigne()+1 && arrivee.getColonne() == depart.getColonne()+1 ) {
            if (this.getCase(depart.getLigne() + 1, depart.getColonne() + 1).isOccupee()) {
                if (this.getCase(depart.getLigne() + 1, depart.getColonne() + 1).getPieceSurCase().getCouleur().equals("blanc")) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean verifierPromotion(Position depart, Position arrivee){
        if (cheminPossible(depart,arrivee) || captureParUnPionPossible(depart,arrivee)) {
            if (this.getCase(depart.getLigne(), depart.getColonne()).getPieceSurCase().getCouleur().equals("blanc")
                    && this.getCase(depart.getLigne(), depart.getColonne()).getPieceSurCase().getNom().charAt(0) == 'p'
                    && arrivee.getLigne() == 7) {
                return true;
            }
            if (this.getCase(depart.getLigne(), depart.getColonne()).getPieceSurCase().getCouleur().equals("noir")
                    && this.getCase(depart.getLigne(), depart.getColonne()).getPieceSurCase().getNom().charAt(0) == 'p'
                    && arrivee.getLigne() == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean roqueEstPossible(Position depart,Position arrivee){
        if (this.getCase(depart.getLigne(),depart.getColonne()).getPieceSurCase().getNom().equals("k")){
            if (this.getCase(depart.getLigne(),depart.getColonne()).getPieceSurCase().getCouleur().equals("blanc")){
                if (depart.getLigne()==7 && depart.getColonne()==4){
                    if (arrivee.getLigne()==7 && arrivee.getColonne()==6){
                        if (this.getCase(7,7).getPieceSurCase().getNom().charAt(0) == 't'
                                && this.getCase(7,7).getPieceSurCase().getCouleur().equals("blanc")){
                            return true;
                        }
                    }
                    else if (arrivee.getLigne()==7 && arrivee.getColonne()==2) {
                        if (this.getCase(7,0).getPieceSurCase().getNom().charAt(0) == 't'
                                && this.getCase(7,0).getPieceSurCase().getCouleur().equals("blanc")){
                            return true;
                        }

                    }
                }
            }
            if (this.getCase(depart.getLigne(),depart.getColonne()).getPieceSurCase().getCouleur().equals("noir")){
                if (depart.getLigne()==0 && depart.getColonne()==4){
                    if (arrivee.getLigne()==0 && arrivee.getColonne()==6){
                        if (this.getCase(0,7).getPieceSurCase().getNom().charAt(0) == 't'
                                && this.getCase(0,7).getPieceSurCase().getCouleur().equals("noir")){
                            return true;
                        }
                    }
                    else if (arrivee.getLigne()==0 && arrivee.getColonne()==2) {
                        if (this.getCase(0,0).getPieceSurCase().getNom().charAt(0) == 't'
                                && this.getCase(0,0).getPieceSurCase().getCouleur().equals("noir")){
                            return true;
                        }

                    }
                }
            }
        }
        return false;
    }

    public int getNbReineNoir() {
        return nbReineNoir;
    }

    public void setNbReineNoir(int nbReineNoir) {
        this.nbReineNoir = nbReineNoir;
    }

    public int getNbReineBlanc() {
        return nbReineBlanc;
    }

    public void setNbReineBlanc(int nbReineBlanc) {
        this.nbReineBlanc = nbReineBlanc;
    }

}
