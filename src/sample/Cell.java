package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Erik on 3/22/2017.
 */

public class Cell {

    private int col;
    private int row;
    private boolean isOccupied;
    private GraphicsContext gc;
    private boolean isValid;
    private int size;
    private int room;

    public Cell(int r, int c, int size, GraphicsContext gc) {
        this.row = r;
        this.col = c;
        this.size = size;
        this.gc = gc;
        this.isValid = false;
        this.room = 15;
    }

    public void setTrue() {
        this.isValid = true;
    }

    public void setFalse() {
        this.isValid = false;
    }

    public boolean getStatus() {
        return this.isValid;
    }

    public void highlight() {
        int x = this.size + (this.col * this.size);
        int y = this.size + (this.row * this.size);
        this.gc.setFill(Color.rgb(255,255,153,0.5));
        this.gc.fillRect(x+2, y+2, (this.size-4), (this.size-4) );
    }

    public void unhighlight() {
        int x = this.col * this.size;
        int y = this.row * this.size;
        this.gc.setFill(Color.web("FFFF00"));
        this.gc.fillRect(x+2, y+2, (this.size-4), (this.size-4));
    }


    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }

    public void setRoom(int r) {
        this.room = r;
    }

    public int getRoom() {
        return this.room;
    }
    public void setValid() {
        this.isValid = true;
    }

    public void setInvalid() {
        this.isValid = false;
    }

    public boolean isValid() {
        return this.isValid;
    }

}
