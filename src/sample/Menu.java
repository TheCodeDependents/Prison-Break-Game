package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.scene.text.Font;


/**
 * Created by Erik on 4/1/2017.
 */
public class Menu {

    private Tuple position;    // this is where we want to store the upper left hand corner
    private Game game;
    // private boolean isOpen;
    private int top;
    private Cell[][] cell;
    private GraphicsContext gc;
    private int SIZE;
    private int CELLSIZE;

    public Menu (Game game, GraphicsContext gc){
        this.game = game;
        // this.isOpen = true;
        this.gc = gc;
        this.top = 0;
        this.draw();
    }

    public void draw() {
        this.gc.setFill(Color.GREY);
        this.gc.fillRect(1240,40,300,1120);

        this.gc.setFill(Color.BLUE);
        this.gc.fillRect(40, 1200, 1120, 275);

        this.gc.setFill(Color.GREY);
        this.gc.setLineWidth(1);
        this.gc.setFont(Font.font ("Verdana", 50));
        this.gc.fillText("Players", 53, 1240);
        this.gc.fillText("Dice Roll", 565, 1240);
        this.gc.fillText("Options", 933, 1240);

        this.gc.setFill(Color.BLACK);
        this.gc.fillText("Player 1", 40, 1295);
        this.gc.fillText("Player 2", 40, 1350);
        this.gc.fillText("Player 3", 40, 1405);
        this.gc.fillText("Player 4", 40, 1460);

        // Borders
        this.gc.setLineWidth(4);
        // Horizontal
        this.gc.strokeLine(42,1255,1158,1255);
        // Vertical
        this.gc.strokeLine(250,1202,250,1473);
        this.gc.strokeLine(450,1202,450,1473);
        this.gc.strokeLine(900,1202,900,1473);

        // Vertical menu lines
        this.gc.setLineWidth(1);
        this.gc.strokeLine(300,1201,300,1474);
        this.gc.strokeLine(350,1201,350,1474);
        this.gc.strokeLine(400,1201,400,1474);

        // Horizontal menu lines
        this.gc.strokeLine(41,1310,449,1310);
        this.gc.strokeLine(41,1365,449,1365);
        this.gc.strokeLine(41,1420,449,1420);
    }
}
