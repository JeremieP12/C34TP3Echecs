package projet_echecs;

public interface MethodesEchiquier {
    public abstract void debuter();
    public abstract Case getCase ( int ligne, int colonne );
    public abstract boolean cheminPossible ( Position  depart , Position arrivee);
    public abstract boolean captureParUnPionPossible ( Position depart,Position arrivee );
}