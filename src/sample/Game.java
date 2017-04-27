package sample;

import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
    private int SIZE;

    public Game(Stage primaryStage) {
        this.SIZE = 1200;
        // Scroll pane and canvas for game board
        ScrollPane pane = new ScrollPane();
        Canvas canvas = new Canvas(1200, 1600);
        pane.setContent( canvas );
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        // Pane and canvas for game menu
        //Pane menu = new Pane();
        //Canvas menucanv = new Canvas(this.SIZE, this.SIZE/4);
        // menu.getChildren().add(menucanv);
        // Bind the width/height property to the wrapper Pane

        primaryStage.setTitle("Prison Break!");
        Scene scene = new Scene(pane, this.SIZE, this.SIZE);
        primaryStage.setScene(scene);
        primaryStage.show();

        this.numPlayers = 4;
        this.activePlayer = 0;
        this.player = new Player[this.numPlayers];
        this.board = new Board(this, canvas, this.SIZE);
        for (int i = 0; i < this.numPlayers; i++) {
            this.player[i] = new Player(this.board, this.board.getPlayerStartCell(i), i);
        }
        this.board.getMenu().draw();
        for (int i = 0; i < this.numPlayers; i++) {
            this.player[i].draw();
        }

        this.playerReady = true;
        this.currentMove = new Move(this.board, this.player[0]);
        this.board.pulsePlayer();
    }

    public void initNextMove() {
        this.board.draw();
        for(int j =0; j < 4; j++){

            // Player collects item
            if((board.item[j].getCol() == this.player[this.activePlayer].getCol()) && (board.item[j].getRow() == this.player[this.activePlayer].getRow())){
                board.item[j].getItemCell().setItemFalse();
                board.getRoom(board.item[j].getRoomNum()).toggleItem(false);
                this.player[this.activePlayer].setItem(j);
                board.item[j].generateItem();
                System.out.println("Player collects item " + j);
            }

            // Determine item visibility
            board.item[j].setInvisible();
            for(int k = 0; k < 4; k++) {
                if (board.item[j].getItemCell().getRoom() == this.player[k].getCell().getRoom()) {
                    board.item[j].setVisible();
                }
            }
        }

        this.activePlayer = (this.activePlayer + 1)%this.numPlayers;
        this.currentMove = new Move(this.board, this.player[this.activePlayer]);
        this.board.pulsePlayer();
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

    public Player getActivePlayer(){
        return this.player[this.activePlayer];
    }
}

