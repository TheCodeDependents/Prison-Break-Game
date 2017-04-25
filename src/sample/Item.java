package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

/**
 * Created by Jesse on 4/21/2017.
 */
public class Item {
    private Board board;
    private Image image;
    private Random rgen;
    private int row,col,id;

    public Item(Board board, int idn, Image image){
        this.board = board;
        this.rgen = new Random();
        this.image = image;
        this.id = idn;
        this.generateItem();
    }

    public int getID(){
        return this.id;
    }

    public void generateItem(){
        this.row = (rgen.nextInt(28));
        this.col = (rgen.nextInt(28));
        while(board.getCell(row,col).getRoom() == 10){
            this.row = (rgen.nextInt(28));
            this.col = (rgen.nextInt(28));
        }
        board.getCell(row,col).setItemTrue();
    }

    public Cell getItemCell(){
        return board.getCell(row,col);
    }

    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;
    }

    public void draw(){
        GraphicsContext gc = this.board.getCanvas().getGraphicsContext2D();
        gc.drawImage(this.image,(col * this.board.getCellSize()) + this.board.getCellSize(),(row * this.board.getCellSize()) + this.board.getCellSize(),this.board.getCellSize(),this.board.getCellSize());
    }
}
