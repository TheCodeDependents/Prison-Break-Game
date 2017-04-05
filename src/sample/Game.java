package sample;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by Erik on 3/22/2017.
 */
public class Game {
    private Board board;

    public Game(Stage primaryStage) {
        int SIZE = 1200;
        // Scroll pane and canvas for game board
        ScrollPane pane = new ScrollPane();
        Canvas canvas = new Canvas(SIZE, SIZE);
        pane.setContent( canvas );
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        // Pane and canvas for game menu
        Pane menu = new Pane();
        Canvas menucanv = new Canvas(SIZE, SIZE/4);
        menu.getChildren().add(menucanv);
        // Bind the width/height property to the wrapper Pane

        primaryStage.setTitle("Prison Break!");
        Scene scene = new Scene(pane, SIZE, SIZE);
        primaryStage.setScene(scene);
        primaryStage.show();
        this.board = new Board(this, canvas, SIZE);

    }

    public Board getBoard() {
        return this.board;
    }
}
