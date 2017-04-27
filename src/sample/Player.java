package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Created by Erik on 4/1/2017.
 */
public class Player {
    private Cell cell;
    private Board board;
    private int room;
    private int id;
    private boolean[] items;

    public Player (Board board, Cell cell, int id) {
        this.cell = cell;
        this.board = board;
        this.room = 15;
        this.id = id;
        this.items = new boolean[4];
        for (int i = 0; i < 4; i++) this.items[i] = false;
    }

    public void draw() {
        int x = (this.cell.getCol() * this.board.getCellSize()) + this.board.getCellSize();
        int y = (this.cell.getRow() * this.board.getCellSize()) + this.board.getCellSize();
        GraphicsContext gc = this.board.getCanvas().getGraphicsContext2D();
        int dim = this.id * 400;
        gc.drawImage(new Image("/img/pcs.png"), dim, 0, 400, 400,
                x, y, this.board.getCellSize(), this.board.getCellSize());
    }

    public Cell getCell() {
        return this.cell;
    }

    public void setCell(Cell c) {
        this.cell = c;
    }

    public int getRoom() {
        return this.room;
    }

    public int getRow(){
        return cell.getRow();
    }

    public int getCol(){
        return cell.getCol();
    }

    public void setItem(int n){
        this.items[n] = true;
    }

    public boolean hasItem(int i){
        return this.items[i];
    }

    public boolean hasAll(){
        boolean ret = true;
        for (int i = 0; i < this.items.length; i++) {
            if (!items[i]) ret = false;
        }
        return ret;
    }
}
