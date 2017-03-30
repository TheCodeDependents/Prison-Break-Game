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
    private Canvas canvas;
    private int SIZE;
    private int CELLSIZE;

    // Room colors
    private String[] cols = {"FF0000", "0000FF", "00FF00", "FF00FF", "000000"};
    // Room critical coordinates
    private Tuple[][] cods = {
        new Tuple[]{new Tuple(5,0), new Tuple(5,3), new Tuple(5,3),
                    new Tuple(5,4), new Tuple(5,6), new Tuple(3,6),
                    new Tuple(3,7), new Tuple(0,7)},
        new Tuple[]{new Tuple(24,0), new Tuple(24,5), new Tuple(25,5),
                    new Tuple(25,5), new Tuple(26,5), new Tuple(28,5)},
        new Tuple[]{new Tuple(0,23), new Tuple(2,23), new Tuple(2,24),
                    new Tuple(3,24), new Tuple(3,24), new Tuple(4,24),
                    new Tuple(5,24), new Tuple(5,23), new Tuple(6,23),
                    new Tuple(6,26), new Tuple(6,26), new Tuple(6,27),
                    new Tuple(6,28)},
        new Tuple[]{new Tuple(24,28), new Tuple(24,24), new Tuple(25,24),
                    new Tuple(25,24), new Tuple(26,24), new Tuple(28,24)},
        new Tuple[]{new Tuple(13,13), new Tuple(14,13), new Tuple(14,13),
                    new Tuple(15,13), new Tuple(17,13), new Tuple(17,17),
                    new Tuple(13,17), new Tuple(13,13), new Tuple(14,13)}
    };
    private Room[] rooms = new Room[10];

    public Board(Game game, Canvas canvas, int size) {

        this.game = game;
        this.cell = new Cell[28][28];
        this.canvas = canvas;
        this.rooms = new Room[10];
        this.SIZE = size;
        this.CELLSIZE = this.SIZE/30;

        String test = "Test Message";

        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                this.cell[i][j] = new Cell(i, j);
            }
        }

        this.draw(canvas);
        // Alert.display(test);
    }

    public Cell getCell(int row, int col) {
        return this.cell[row][col];
    }

    public Game getGame() {
        return this.game;
    }

    public Canvas getCanvas() { return this.canvas; }

    public void draw(Canvas canvas)
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.ORANGE);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        gc.setFill(Color.YELLOW);
        gc.setStroke(Color.GREY);
        gc.setLineWidth(1);
        gc.strokeRect(this.CELLSIZE,this.CELLSIZE,(this.SIZE-(2*this.CELLSIZE)),(this.SIZE-(2*this.CELLSIZE)));
        gc.fillRect(this.CELLSIZE, this.CELLSIZE, (this.SIZE-(2*CELLSIZE)), (this.SIZE-(2*CELLSIZE)));
        for (int i = (2*this.CELLSIZE); i < (this.SIZE-(this.CELLSIZE)); i += this.CELLSIZE) {
            gc.strokeLine(i, this.CELLSIZE, i, (this.SIZE-this.CELLSIZE));
            gc.strokeLine(this.CELLSIZE, i, (this.SIZE-this.CELLSIZE), i);
        }

        this.initRooms(gc);
    }

    public void initRooms(GraphicsContext gc)
    {
        int i = 0;
        for (Tuple[] cod : this.cods) {
            this.rooms[i] = new Room(this.cols[i], cod , cod.length, this, gc);
            this.rooms[i].draw();
            i++;
        }
    }

    public int getCellSize()
    {
        return this.CELLSIZE;
    }
}
