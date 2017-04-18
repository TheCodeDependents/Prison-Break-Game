package sample;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import java.util.Random;


/**
 * Created by Erik on 4/10/2017.
 */
public class Move {
    private Board board;
    private Player player;
    private Cell move;
    private int dieRoll;

    public Move(Board board, Player player) {
        this.board = board;
        this.player = player;
        this.dieRoll = rollDice();
        this.findLegalMoves(this.dieRoll);
    }



    public void findLegalMoves(int num) {
        int x = (this.player.getCell().getCol());
        int y = (this.player.getCell().getRow());
        int top = ((y - num) < 0) ? (num%2 == y%2) ? 0 : 1 : (y - num);
        int bot = ((y + num) > 28) ? (num%2 == ((28-y)%2)) ? 28 : 27 : (y + num);

        this.board.resetCells();

        for (int i = top; i <= bot; i++) {
            int diff = (num - Math.abs(y - i));
            for (int j = (x - diff); j <= (x + diff); j += 2) {
                if (this.player.getRoom() == this.board.getCell(i, j).getRoom()) {
                    this.board.getCell(i, j).highlight();
                    this.board.getCell(i, j).setValid();
                }
            }
        }
        this.board.initRooms();
        this.board.redrawPlayers();
    }

    // Will generate and return an int
    // representing how far a piece can move
    // Returns: int
    public int rollDice() {
        Random rand = new Random();
        int die1 = rand.nextInt(6) + 1;
        int die2 = rand.nextInt(6) + 1;
        int sum = die1 + die2;
        return sum;
    }

    public boolean makeMove(Cell c) {
        if (c.isValid()) {
            this.player.setCell(c);
            this.board.draw();
            this.player.draw();
            return true;
        }
        return false;
    }

}
