package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;

/**
 * Created by Erik on 4/1/2017.
 */
public class Menu {

    private Tuple position;    // this is where we want to store the upper left hand corner
    private Game game;
    private boolean isOpen;

    private Cell[][] cell;
    private Canvas menucanv;
    private int SIZE;
    private int CELLSIZE;

    public Menu (Game game){
        this.game = game;
        this.isOpen = true;
    }

    public void draw() {
        GraphicsContext gc = this.menucanv.getGraphicsContext2D();

        gc.setFill(Color.ORANGE);
        gc.fillRect(0,0,menucanv.getWidth(),menucanv.getHeight());
        gc.setFill(Color.web("FFFF00"));
        gc.setStroke(Color.GREY);
        gc.setLineWidth(1);
        gc.strokeRect(this.CELLSIZE,this.CELLSIZE,(this.SIZE-(2*this.CELLSIZE)),(this.SIZE-(2*this.CELLSIZE)));
        gc.fillRect(this.CELLSIZE, this.CELLSIZE, (this.SIZE-(2*CELLSIZE)), (this.SIZE-(2*CELLSIZE)));
        for (int i = (2*this.CELLSIZE); i < (this.SIZE-(this.CELLSIZE)); i += this.CELLSIZE) {
            gc.strokeLine(i, this.CELLSIZE, i, (this.SIZE-this.CELLSIZE));
            gc.strokeLine(this.CELLSIZE, i, (this.SIZE-this.CELLSIZE), i);
        }
    }
}
