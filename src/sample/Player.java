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

    public Player (Board board, Cell cell) {
        this.cell = cell;
        this.board = board;
        this.room = 15;
    }

    public void draw() {
        int x = (this.cell.getCol() * this.board.getCellSize()) + this.board.getCellSize();
        int y = (this.cell.getRow() * this.board.getCellSize()) + this.board.getCellSize();
        GraphicsContext gc = this.board.getCanvas().getGraphicsContext2D();
        gc.drawImage(new Image("/img/gpc.png"), x, y, this.board.getCellSize(), this.board.getCellSize());
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
}
