package projet_echecs;

import org.junit.Test;

import static org.junit.Assert.*;

public class PieceTest {

    Roi k;
    Reine r;
    Fou f;
    Tour t;
    Cavalier c;
    Pion p1;
    Pion p2;

    @org.junit.Before
    public void setUp() throws Exception {
        k = new Roi("noir");
        r = new Reine("r","noir");
        f = new Fou("f1","noir");
        t = new Tour("t1","noir");
        c = new Cavalier("c1","noir");
        p1 = new Pion("p1","noir");
        p2 = new Pion("p1","blanc");
    }

    @Test
    public void pieceHorsPlateau(){
        assertFalse(k.estValide(new Position(1, 1), new Position(8, 2)));
    }

    @Test
    public void roiEstValide(){
        assertTrue(k.estValide(new Position(1, 1), new Position(2, 2)));
    }

    @Test
    public void roiNonValide(){
        assertFalse(k.estValide(new Position(1, 1), new Position(3, 2)));
    }

    @Test
    public void reineEstValideLigneDroite(){
        assertTrue(r.estValide(new Position(1, 1), new Position(1, 6)));
    }

    @Test
    public void reineEstValideDiagonal(){
        assertTrue(r.estValide(new Position(1, 1), new Position(4, 4)));
    }

    @Test
    public void reineNonValide(){
        assertFalse(r.estValide(new Position(1, 1), new Position(4, 6)));
    }

    @Test
    public void cavalierEstValide(){
        assertTrue(c.estValide(new Position(1, 1), new Position(3, 2)));
    }

    @Test
    public void cavalierNonValide(){
        assertFalse(c.estValide(new Position(1, 1), new Position(3, 3)));
    }

    @Test
    public void pionEstValide(){
        assertTrue(p1.estValide(new Position(1, 1), new Position(2, 1)));
    }

    @Test
    public void pionEstValide2Cases(){
        assertTrue(p1.estValide(new Position(1, 1), new Position(3, 1)));
    }

    @Test
    public void pionNonValide(){
        assertFalse(p1.estValide(new Position(2, 1), new Position(1, 1)));
    }
    @Test
    public void pionNonValide2Cases(){
        assertFalse(p1.estValide(new Position(2, 1), new Position(4, 1)));
    }

    @Test
    public void pionEstValide2Blanc(){
        assertTrue(p2.estValide(new Position(6, 1), new Position(4, 1)));
    }


}