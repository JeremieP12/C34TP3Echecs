package projet_echecs;

public class Position
{

    private int ligne ; // de 0 à 7 voir schéma
    private int colonne; // de 0 à 7


    public Position(int ligne, int colonne)
    {
        this.ligne = ligne;
        this.colonne= colonne;
    }

    public int getLigne ()
    {
        return ligne;
    }

    public int getColonne ()
    {
        return colonne;
    }

    public void setLigne (int ligne)
    {
        this.ligne = ligne;
    }

    public void setColonne ( int colonne )
    {
        this.colonne = colonne;
    }

}
