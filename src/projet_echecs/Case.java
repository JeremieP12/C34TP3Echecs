package projet_echecs;

public class Case {

    private Piece pieceSurCase;

    public Case(){
        this.pieceSurCase = null;
    }

    public Case(Piece pieceSurCase){
        this.pieceSurCase = pieceSurCase;
    }

    public boolean isOccupee(){
        return pieceSurCase != null;
    }

    public boolean isMemeCouleur(String couleur){
        if (!isOccupee()) { return false;}
        return this.pieceSurCase.getCouleur().equals(couleur);
    }

    public Piece retirerPieceCase(){
        Piece piece = this.pieceSurCase;
        this.pieceSurCase = null;
        return piece;
    }

    public Piece getPieceSurCase() {
        return pieceSurCase;
    }

    public void setPieceSurCase(Piece pieceSurCase) {
        this.pieceSurCase = pieceSurCase;
    }
}
