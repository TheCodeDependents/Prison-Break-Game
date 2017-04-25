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
    private boolean itemCrowbar,itemShovel,itemGrappling,itemNailfiler,itemKnife;

    public Player (Board board, Cell cell, int id) {
        this.cell = cell;
        this.board = board;
        this.room = 15;
        this.id = id;
        this.itemCrowbar = false;
        this.itemShovel = false;
        this.itemGrappling = false;
        this.itemNailfiler = false;
        this.itemKnife = false;

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
        if(n == 0){
            itemCrowbar = true;
        }
        if(n == 1){
            itemShovel = true;
        }
        if(n == 2){
            itemGrappling = true;
        }
        if(n == 3){
            itemNailfiler = true;
        }
        else{
            itemKnife = true;
        }
    }

    public boolean isWinner(){
        return (itemCrowbar && itemShovel && itemGrappling && itemNailfiler && itemKnife);
    }

    public boolean getItemCrowbar(){
        return itemCrowbar;
    }

    public boolean getItemShovel(){
        return itemShovel;
    }

    public boolean getItemGrappling(){
        return itemGrappling;
    }

    public boolean getItemNailfiler(){
        return itemNailfiler;
    }

    public boolean getItemKnife(){
        return itemKnife;
    }
}
