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
    private Player[] player;
    private int activePlayer;
    private boolean playerReady;
    private int numPlayers;
    private Move currentMove;

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

        this.numPlayers = 4;
        this.activePlayer = 0;

        this.player = new Player[this.numPlayers];
        for (int i = 0; i < this.numPlayers; i++) {
            this.player[i] = new Player(this.board, this.board.getCell(11,9 + i), i);
            this.player[i].draw();
        }

        this.playerReady = true;
        this.currentMove = new Move(this.board, this.player[0]);
    }

    public void initNextMove() {
        this.board.draw();
        for (int i = 0; i < this.numPlayers; i++) {
            this.player[i].draw();
        }
        this.activePlayer = (this.activePlayer + 1)%this.numPlayers;
        this.currentMove = new Move(this.board, this.player[this.activePlayer]);
    }

    public Board getBoard() {
        return this.board;
    }

    public void setCurrentMove(Move m) {
        this.currentMove = m;
    }
    public Move getCurrentMove() {
        return this.currentMove;
    }

    public int getNumPlayers() {
        return this.numPlayers;
    }

    public Player getPlayer(int num) {
        return this.player[num];
    }
}
