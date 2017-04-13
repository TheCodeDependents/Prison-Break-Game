package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.paint.Paint;

/**
 * Created by Erik on 3/24/2017.
 */
public class Room {
    private String color;
    private Tuple[] coordinates;
    private int width;
    private Board board;
    private GraphicsContext gc;
    private int index;
    private boolean hasItem;
    private String itemName;



    public Room(int index, String color, Tuple[] coordinates, int size, Board b, GraphicsContext gc){
        this.color = color;
        this.coordinates = new Tuple[size];
        this.coordinates = coordinates;
        this.width = 8;
        this.board = b;
        this.gc = gc;
        this.index = index;
    }

    public void draw(){
        GraphicsContext gc = this.gc;
        int makedoor = 0;
        gc.setStroke(Color.web(this.color));
        gc.setLineWidth(this.width);
        gc.setLineJoin(StrokeLineJoin.MITER);
        int sz = this.board.getCellSize();
        for (int i = 0; i < this.coordinates.length; i++) {
            int x = (this.coordinates[i].getX()*sz) + sz;
            int y = (this.coordinates[i].getY()*sz) + sz;
            if (i == 0) {
                int x2 = (this.coordinates[i+1].getX()*sz) + sz;
                int y2 = (this.coordinates[i+1].getY()*sz) + sz;
                if (x2 > x) x += this.width/2;
                else if (x2 < x) x -= this.width/2;
                else if (y2 > y) y += this.width/2;
                else y -= this.width/2;
                gc.beginPath();
                gc.moveTo(x, y);
            } else if (this.coordinates[i].getX() == this.coordinates[i - 1].getX() &&
                       this.coordinates[i].getY() == this.coordinates[i - 1].getY()) {
                makedoor = 1;
                gc.stroke();
            } else if (this.coordinates.length == (i+1)) {
                int x2 = (this.coordinates[i-1].getX()*sz) + sz;
                int y2 = (this.coordinates[i-1].getY()*sz) + sz;
                if (x2 > x) x += this.width/2;
                else if (x2 < x) x -= this.width/2;
                else if (y2 > y) y += this.width/2;
                else y -= this.width/2;
                gc.lineTo(x, y);
            } else if (makedoor == 1) {
                int x2 = (this.coordinates[i+1].getX()*sz) + sz;
                int y2 = (this.coordinates[i+1].getY()*sz) + sz;
                if (x2 > x) x += this.width/2;
                else if (x2 < x) x -= this.width/2;
                else if (y2 > y) y += this.width/2;
                else y -= this.width/2;
                gc.beginPath();
                gc.moveTo(x, y);
                makedoor = 0;
            } else {
                if (this.coordinates[i].getX() == this.coordinates[i + 1].getX() &&
                        this.coordinates[i].getY() == this.coordinates[i + 1].getY()) {
                    int x2 = (this.coordinates[i-1].getX() * sz) + sz;
                    int y2 = (this.coordinates[i-1].getY() * sz) + sz;
                    if (x2 > x) x += this.width / 2;
                    else if (x2 < x) x -= this.width / 2;
                    else if (y2 > y) y += this.width / 2;
                    else y -= this.width / 2;
                }
                gc.lineTo(x, y);
            }
            gc.stroke();
        }
    }

    public void setCellNumbers() {
        int bot;
        int top = bot = this.coordinates[0].getY();
        for (Tuple t : this.coordinates) {
            bot = (t.getY() > bot) ? t.getY() : bot;
            top = (t.getY() < top) ? t.getY() : top;
        }

        for (int i = top; i <= bot; i++) {
                int low;
                int high = low = this.coordinates[0].getX();
            for (Tuple t : this.coordinates) {
                low = (t.getX() < low) ? t.getX() : low;
                high = (t.getX() > high) ? t.getX() : high;
            }
            for (int j = low; j <= high; j++) {
                this.board.getCell(i, j).setRoom(this.index);
            }
        }
    }
}
