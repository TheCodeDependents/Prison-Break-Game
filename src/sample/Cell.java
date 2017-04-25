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
    private boolean isEntry;
    private boolean hasItem;

    public Cell(int r, int c, int size, GraphicsContext gc) {
        this.row = r;
        this.col = c;
        this.size = size;
        this.gc = gc;
        this.isValid = false;
        this.room = this.roomNumber(r,c);
        this.isEntry = false;
        this.hasItem = false;
    }

    public void setItemTrue(){
        this.hasItem = true;
    }

    public void setItemFalse(){
        this.hasItem = false;
    }

    public boolean getItem(){
        return this.hasItem;
    }

    public void setEntryTrue(){
        this.isEntry = true;
    }

    public boolean getEntry(){
        return this.isEntry;
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

    public int roomNumber(int r, int c){
        if((r>=0 && r<=6)&&(c>=0 && c<=6)){
            return 1;
        }
        if((r>=0 && r<=4) && (c>=10 && c<=17)){
            return 2;
        }
        if(r>=0 && r<=4 && c>=20 && c<=27){
            return 3;
        }
        if(r>=9 && r<=18 && c>=0 && c<=6){
            return 4;
        }
        if(r>=12 && r<=15 && c>=12 && c<=15){
            return 5;
        }
        if(r>=7 && r<=16 && c>=21 && c<=27){
            return 6;
        }
        if(r>=21 && r<=27 && c>=0 && c<=7){
            return 7;
        }
        if((r>=22 && r<=27) && (c>=10 && c<=21)){
            return 8;
        }
        if(r>=19 && r<=27 && c>=24 && c<=27){
            return 9;
        }
        if((r>=0 && r <28) && (c>=0 && c <28)){
            return 10;
        }
        else{
            return 11;
        }
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
