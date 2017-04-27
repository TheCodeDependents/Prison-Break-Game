package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * Created by Erik on 4/1/2017.
 */
public class Menu {

    private Tuple position;    // this is where we want to store the upper left hand corner
    private Game game;
    // private boolean isOpen;
    // private int top;
    // private Cell[][] cell;
    private Board board;
    private GraphicsContext gc;
    // private int SIZE;
    // private int CELLSIZE;

    public Menu (Game game, Board board, GraphicsContext gc){
        this.game = game;
        // this.isOpen = true;
        this.gc = gc;
        this.board = board;
        // this.top = 0;
    }

    public void draw() {
        this.gc.setFill( Color.rgb(0,0,128,0.5) );
        this.gc.fillRect(40, 1200, 1120, 350);

        this.gc.setFill(Color.GREY);
        this.gc.setLineWidth(1);
        this.gc.setFont(Font.font ("Verdana", 50));
        //this.gc.fillText("Players", 53, 1240);
        this.gc.fillText("Dice Roll", 565, 1240);
        this.gc.fillText("Options", 933, 1240);

        for (int i = 0; i < 4; i++) {
            gc.drawImage(new Image("/img/items.png"), i*40, 0, 40, 40,
                    (145 + (i * 70)), 1210, 50, 50);
        }
        for (int i = 0; i < 4; i++) {
            gc.drawImage(new Image("/img/pcs.png"), i*400, 0, 400, 400,
                    55, (1270 + (i*70)), 60, 60);
            int blit = (this.game.getPlayer(i).hasAll()) ? 1 : 0;
            for (int j = 0; j < 4; j++) {
                if (this.game.getPlayer(i).hasItem(j)) {
                    gc.drawImage(new Image("/img/check.png"), blit*200, 0, 200, 200,
                            (145+(j*70)), (1275+(i*70)), 50, 50);
                }
            }
        }

        // Borders
        this.gc.setLineWidth(2);
        this.gc.setStroke(Color.rgb(211, 211, 211,0.3));
        // Horizontal
        this.gc.strokeLine(42,1265,415,1265);
        // Vertical
        this.gc.strokeLine(130,1202,130,1548);
        this.gc.strokeLine(417,1202,417,1548);
        this.gc.strokeLine(900,1202,900,1548);

        // Vertical menu lines
        this.gc.setLineWidth(1);
        this.gc.strokeLine(203,1201,203,1549);
        this.gc.strokeLine(275,1201,275,1549);
        this.gc.strokeLine(345,1201,345,1549);

        // Horizontal menu lines
        this.gc.strokeLine(41,1335,416,1335);
        this.gc.strokeLine(41,1405,416,1405);
        this.gc.strokeLine(41,1475,416,1475);
    }
}
