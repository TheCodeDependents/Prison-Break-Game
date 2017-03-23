package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Erik on 3/22/2017.
 */
public class Board {

    private Cell[][] cell;
    private Game game;

    public Board(Game game, Canvas canvas) {

        this.game = game;
        this.cell = new Cell[28][28];

        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                this.cell[i][j] = new Cell(i, j);
            }
        }

        this.draw(canvas);
    }

    public Cell getCell(int row, int col) {
        return this.cell[row][col];
    }

    public Game getGame() {
        return this.game;
    }

    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.ORANGE);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        gc.setFill(Color.YELLOW);
        gc.setStroke(Color.GREY);
        gc.setLineWidth(1);
        gc.strokeRect(100,100,2800,2800);
        gc.fillRect(100, 100, 2800, 2800);
        for (int i = 200; i < 2900; i += 100) {
            gc.strokeLine(i, 100, i, 2900);
            gc.strokeLine(100, i, 2900, i);
        }
    }
}
