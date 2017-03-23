package sample;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

/**
 * Created by Erik on 3/22/2017.
 */
public class Game {
    private Board board;

    public Game(Stage primaryStage) {
        ScrollPane pane = new ScrollPane();
        Canvas canvas = new Canvas(3000, 3000);
        pane.setContent( canvas );
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        primaryStage.setTitle("Prison Break!");
        Scene scene = new Scene(pane, 3000,3000);
        primaryStage.setScene(scene);
        primaryStage.show();
        this.board = new Board(this, canvas);
    }

    public Board getBoard() {
        return this.board;
    }
}
