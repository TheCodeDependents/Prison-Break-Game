package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;

/**
 * Created by Erik on 3/22/2017.
 * Edited by Steven on 3/30/2017.
 */
public class Board {

    private Cell[][] cell;
    private Game game;
    private Canvas canvas;
    private int SIZE;
    private int CELLSIZE;

    // Room colors
    private String[] cols = {"FF0000", "FFFFFF", "0000FF", "1a690e", "00FFFF", "00FF00",
            "FF00FF", "78078d", "000000"};
    // Room critical coordinates
    private Tuple[][] cods = {
        // Red Room
        new Tuple[]{new Tuple(7,0), new Tuple(7,3), new Tuple(7,3),
                    new Tuple(7,4), new Tuple(7,7), new Tuple(0,7)},
        // White Room
        new Tuple[]{new Tuple(10,0), new Tuple(10,5), new Tuple(11,5),
                    new Tuple(11,5), new Tuple(12,5), new Tuple(16,5),
                    new Tuple(16,5), new Tuple(17,5), new Tuple(18,5),
                    new Tuple(18,0),},
        // Blue Room
        new Tuple[]{new Tuple(20,0), new Tuple(20,5), new Tuple(25,5),
                    new Tuple(25,5), new Tuple(26,5), new Tuple(28,5)},
        // Dark Green Room
        new Tuple[]{new Tuple(0,9), new Tuple(2,9), new Tuple(2,9),
                    new Tuple(3,9), new Tuple(7,9), new Tuple(7,15),
                    new Tuple(7,15), new Tuple(7,16), new Tuple(7,19),
                    new Tuple(0,19),},
        // Light Blue Room
        new Tuple[]{new Tuple(28,7), new Tuple(21,7), new Tuple(21,10),
                    new Tuple(21,10), new Tuple(21,11), new Tuple(21,17),
                    new Tuple(23,17), new Tuple(23,17), new Tuple(24,17),
                    new Tuple(28,17),},
        // Green Room
        new Tuple[]{new Tuple(0,21), new Tuple(5,21), new Tuple(5,21),
                    new Tuple(6,21), new Tuple(7,21), new Tuple(8,21),
                    new Tuple(8,24), new Tuple(8,24), new Tuple(8,25),
                    new Tuple(8,28)},
        // Pink Room
        new Tuple[]{new Tuple(24,28), new Tuple(24,19), new Tuple(25,19),
                    new Tuple(25,19), new Tuple(26,19), new Tuple(28,19)},
        // Purple Room
        new Tuple[]{new Tuple(10,28), new Tuple(10,26), new Tuple(10,26),
                    new Tuple(10,25), new Tuple(10,22), new Tuple(15,22),
                    new Tuple(15,22), new Tuple(17,22), new Tuple(22,22),
                    new Tuple(22,23), new Tuple(22,23), new Tuple(22,24),
                    new Tuple(22,28)},
        // The Hole
        new Tuple[]{new Tuple(13,12), new Tuple(12,12), new Tuple(12,16),
                    new Tuple(16,16), new Tuple(16,12), new Tuple(14,12),}
    };
    private Room[] rooms = new Room[10];
    public double x0, y0;

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
                this.cell[i][j] = new Cell(i, j, this.CELLSIZE, canvas.getGraphicsContext2D());
            }
        }

        this.draw();
        Player player = new Player(this, this.cell[11][9]);
        player.draw();
        Move mv = new Move(this, player);
        mv.findLegalMoves(5);

        //Creating the mouse event handler

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        x0 = event.getX();
                        y0 = event.getY();
                        handleClick(x0,y0, mv);
                    }
                });


        // Alert.display(test);
    }

    private void handleClick (double x0, double y0, Move mv) {
        int row = (int)(y0/30.00 - 1.00);
        int col = (int)(x0/30.00 - 1.00);
        mv.makeMove(this.cell[row-1][col-1]);
    }

    public Cell getCell(int row, int col) {
        return this.cell[row][col];
    }

    public Game getGame() {
        return this.game;
    }

    public Canvas getCanvas() { return this.canvas; }

    public void draw()
    {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();

        gc.setFill(Color.ORANGE);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        gc.setFill(Color.web("FFFF00"));
        gc.setStroke(Color.GREY);
        gc.setLineWidth(1);
        gc.strokeRect(this.CELLSIZE,this.CELLSIZE,(this.SIZE-(2*this.CELLSIZE)),(this.SIZE-(2*this.CELLSIZE)));
        gc.fillRect(this.CELLSIZE, this.CELLSIZE, (this.SIZE-(2*CELLSIZE)), (this.SIZE-(2*CELLSIZE)));
        for (int i = (2*this.CELLSIZE); i < (this.SIZE-(this.CELLSIZE)); i += this.CELLSIZE) {
            gc.strokeLine(i, this.CELLSIZE, i, (this.SIZE-this.CELLSIZE));
            gc.strokeLine(this.CELLSIZE, i, (this.SIZE-this.CELLSIZE), i);
        }

        this.initRooms();
    }

    // Call each room's draw method
    public void initRooms()
    {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();

        int i = 0;
        for (Tuple[] cod : this.cods) {
            this.rooms[i] = new Room(i, this.cols[i], cod , cod.length, this, gc);
            this.rooms[i].draw();
            i++;
        }
    }

    // Reset all board cells to be invalid, prior to new move
    public void resetCells() {
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                this.cell[i][j].setInvalid();
            }
        }
    }

    public int getCellSize()
    {
        return this.CELLSIZE;
    }

}
