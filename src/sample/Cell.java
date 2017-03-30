package sample;

/**
 * Created by Erik on 3/22/2017.
 */

public class Cell {

    private int col;
    private int row;
    private boolean isOccupied;

    public Cell(int r, int c) {
        this.row = r;
        this.col = c;
    }

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }
}
