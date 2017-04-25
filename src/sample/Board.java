package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Timer;

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
    private Cell[] playerStartCells;
    private Menu menu;

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
    private Room[] rooms = new Room[9];
    public double x0, y0;
    static Timer timer = new Timer();

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

        this.playerStartCells = new Cell[this.game.getNumPlayers()];
        for (int j = 0; j < this.game.getNumPlayers(); j++) {
            int row = 10 + ((j%2)*8);         // Position game pieces
            int col = 10 + ( (j > 1) ? 8 : 0 );    // in start squares
            this.playerStartCells[j] = new Cell (row, col, this.CELLSIZE, canvas.getGraphicsContext2D());
        }

        // draw board
        this.draw();

        // Init and draw rooms
        this.initRooms();

        //Init and draw menu
       // this.menu = new Menu(this.game, this.canvas.getGraphicsContext2D());

        //Creating the mouse event handler
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        x0 = event.getX();
                        y0 = event.getY();
                        handleClick(x0,y0);
                    }
                }
        );

    }

    private void handleClick (double x0, double y0) {
        int row = (int)(y0/this.CELLSIZE);
        int col = (int)(x0/this.CELLSIZE);
        //Alert.display("col:" + col + "      row:" + row);
        boolean success = this.game.getCurrentMove().makeMove(this.cell[row-1][col-1]);
        if (success) this.game.initNextMove();
    }

    public void openDoor(){
        //Room 1
        cell[3][6].setEntryTrue();
        cell[3][7].setEntryTrue();
        //Room 2
        cell[4][11].setEntryTrue();
        cell[5][11].setEntryTrue();
        cell[4][16].setEntryTrue();
        cell[5][16].setEntryTrue();
        //Room 3
        cell[5][25].setEntryTrue();
        cell[4][25].setEntryTrue();
        //Room 4
        cell[15][6].setEntryTrue();
        cell[15][7].setEntryTrue();
        cell[8][2].setEntryTrue();
        cell[9][2].setEntryTrue();
        //Room 5
        cell[20][5].setEntryTrue();
        cell[21][5].setEntryTrue();
        cell[24][7].setEntryTrue();
        cell[24][8].setEntryTrue();
        //Room 6
        cell[21][15].setEntryTrue();
        cell[22][15].setEntryTrue();
        cell[21][16].setEntryTrue();
        cell[22][16].setEntryTrue();
        cell[25][9].setEntryTrue();
        cell[25][10].setEntryTrue();
        cell[23][21].setEntryTrue();
        cell[23][22].setEntryTrue();
        //Room 7
        cell[18][25].setEntryTrue();
        cell[19][25].setEntryTrue();
        //Room 8
        cell[10][20].setEntryTrue();
        cell[10][21].setEntryTrue();
        cell[16][23].setEntryTrue();
        cell[17][23].setEntryTrue();
        //Room 9
        cell[11][13].setEntryTrue();
        cell[12][13].setEntryTrue();
    }

    public Cell getCell(int row, int col) {
        try {
            return this.cell[row][col];
        } catch(Error e) {
            System.out.println("Err:   " + row + "  " + col);
        }
        return null;
    }

    public Cell getPlayerStartCell(int i) {
        return this.playerStartCells[i];
    }

    public Game getGame() {
        return this.game;
    }

    public Canvas getCanvas() { return this.canvas; }

    // Draw everything visible
    public void draw()
    {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();

        //gc.setFill(Color.ORANGE);
        //gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        gc.drawImage(new Image("/img/concrete.jpg"), 0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.rgb(255,255,0,0.5));
        gc.setStroke(Color.GREY);
        gc.setLineWidth(1);
        gc.strokeRect(this.CELLSIZE,this.CELLSIZE,(this.SIZE-(2*this.CELLSIZE)),(this.SIZE-(2*this.CELLSIZE)));
        gc.fillRect(this.CELLSIZE, this.CELLSIZE, (this.SIZE-(2*CELLSIZE)), (this.SIZE-(2*CELLSIZE)));
        for (int i = (2*this.CELLSIZE); i < (this.SIZE-(this.CELLSIZE)); i += this.CELLSIZE) {
            gc.strokeLine(i, this.CELLSIZE, i, (this.SIZE-this.CELLSIZE));
            gc.strokeLine(this.CELLSIZE, i, (this.SIZE-this.CELLSIZE), i);
        }

        this.initRooms();

        gc.setFill(Color.GREY);
        gc.fillRect(1240,40,300,1120);

        gc.setFill(Color.BLUE);
        gc.fillRect(40, 1200, 1120, 275);

        gc.setFill(Color.GREY);
        gc.setLineWidth(1);
        gc.setFont(Font.font ("Verdana", 50));
        gc.fillText("Players", 53, 1240);
        gc.fillText("Dice Roll", 565, 1240);
        gc.fillText("Options", 933, 1240);

        gc.setFill(Color.BLACK);
        gc.fillText("Player 1", 40, 1295);
        gc.fillText("Player 2", 40, 1350);
        gc.fillText("Player 3", 40, 1405);
        gc.fillText("Player 4", 40, 1460);

        // Borders
        gc.setLineWidth(4);
        // Horizontal
        gc.strokeLine(42,1255,1158,1255);
        // Vertical
        gc.strokeLine(250,1202,250,1473);
        gc.strokeLine(450,1202,450,1473);
        gc.strokeLine(900,1202,900,1473);

        // Vertical menu lines
        gc.setLineWidth(1);
        gc.strokeLine(300,1201,300,1474);
        gc.strokeLine(350,1201,350,1474);
        gc.strokeLine(400,1201,400,1474);

        // Horizontal menu lines
        gc.strokeLine(41,1310,449,1310);
        gc.strokeLine(41,1365,449,1365);
        gc.strokeLine(41,1420,449,1420);

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

    public void redrawPlayers() {
        for (int i = 0; i < this.game.getNumPlayers(); i++) {
            this.game.getPlayer(i).draw();
        }
    }

    public void pulsePlayer() {

    }

    // Reset all board cells to be invalid, prior to new move
    public void resetCells() {
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                this.cell[i][j].setInvalid();
            }
        }
    }

    public Menu getMenu() {return this.menu;}

    public int getCellSize()
    {
        return this.CELLSIZE;
    }

    public void refreshAll() {
        this.draw();
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                if (this.cell[i][j].isValid()) this.cell[i][j].highlight();
            }
        }
        for (int x = 0; x < 9; x++) {
            this.rooms[x].draw();
        }
        this.redrawPlayers();
        this.menu.draw();
    }

    public int renderDice(int num1, int num2) {
        //Alert.display("hhhh");

        int result = 0;
        GraphicsContext gc = this.canvas.getGraphicsContext2D();


        int row = (num1 > 3) ? 1 : 0;
        int col = (num1%3 == 0) ? 2 : ((num1 == 2) || (num1 == 5)) ? 1 : 0;
        int xpos = (col*40);
        int ypos = row*40;
        gc.drawImage(new Image("/img/dice.png"), xpos, ypos, 40, 40,
                600, 1300, 70, 70);
        result += num1;

        row = (num2 > 3) ? 1 : 0;
        col = (num2%3 == 0) ? 2 : ((num2 == 2) || (num2 == 5)) ? 1 : 0;
        xpos = (col*40);
        ypos = row*40;
        gc.drawImage(new Image("/img/dice.png"), xpos, ypos, 40, 40,
                700, 1300, 70, 70);
        result += num2;

        return result;
    }

}
