package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

/**
 * Created by Jesse on 4/21/2017.
 */
public class Item {
    private Board board;
    private int row, col, room, id;

    public Item(Board board, int idn){
        this.board = board;
        this.id = idn;
        this.generateItem();
    }

    public int getID(){
        return this.id;
    }

    public void generateItem(){
        Random rand = new Random();
        this.room = rand.nextInt(9);
        Alert.display("one");
        Alert.display("----> " + room);
        while (this.board.getRoom(this.room).hasItem()) {
            Alert.display("two");
            this.room = rand.nextInt(9);
        }

        // Count number of cells
        int count = 0;
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                if (this.board.getCell(i, j).getRoom() == room) count++;
            }
        }
        int cell = rand.nextInt(count) + 1;
        for (int i = 0; i < 28 && cell > 0; i++) {
            for (int j = 0; j < 28 && cell > 0; j++) {
                if (this.board.getCell(i, j).getRoom() == room) {
                    this.row = i; this.col = j;
                    cell--;
                }
            }
        }

        board.getCell(this.row, this.col).setItemTrue();
        this.board.getRoom(room).toggleItem(true);
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
        gc.drawImage(new Image("/img/items.png"),
                this.id * 40, 0, 40, 40,
                (col * this.board.getCellSize()) + this.board.getCellSize(),
                (row * this.board.getCellSize()) + this.board.getCellSize(),
                this.board.getCellSize(),this.board.getCellSize());
    }
    public int getRoomNum() {
        return this.room;
    }
}
