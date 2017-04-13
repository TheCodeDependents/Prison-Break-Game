package sample;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Created by Erik on 4/10/2017.
 */
public class Move {
    private Board board;
    private Player player;
    private Cell move;

    public Move(Board board, Player player) {
        this.board = board;
        this.player = player;

    }

    public void findLegalMoves(int num) {
        int x = (this.player.getCell().getCol());
        int y = (this.player.getCell().getRow());
        int top = ((y - num) < 0) ? (num%2 == y%2) ? 0 : 1 : (y - num);
        int bot = ((y + num) > 28) ? (num%2 == ((28-y)%2)) ? 28 : 27 : (y + num);

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
    }

    public void makeMove(Cell c) {
        if (c.isValid()) {
            this.player.setCell(c);
            this.board.draw();
            this.player.draw();
            this.board.resetCells();
            this.findLegalMoves(5);
        }
    }

}
