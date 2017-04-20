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
        //this.findLegalMoves(this.dieRoll);
        this.board.resetCells();
        this.board.openDoor();
        this.mark2(this.player.getCell().getRow(),this.player.getCell().getCol(),this.dieRoll,0);
        this.board.initRooms();
        this.board.redrawPlayers();

    }

    public void mark2(int r, int c, int roll, int moves){
        int block = 1;
        if(roll%2==0){
            block = 0;
        }
        if(moves <= roll){
            if(moves%2 == block && !(this.board.getCell(r,c).getStatus())){
                this.board.getCell(r,c).highlight();
                this.board.getCell(r,c).setValid();
            }if(r+1<28 && ((this.isRoomVisited(r+1,c)==this.isRoomVisited(r,c))|| this.board.getCell(r+1, c).getEntry())){
                this.mark2(r+1,c,roll,moves+1);
            }
            if(c-1>=0 && ((this.isRoomVisited(r,c-1)==this.isRoomVisited(r,c))|| this.board.getCell(r, c-1).getEntry())){
                this.mark2(r,c-1,roll,moves+1);
            }
            if(c+1 < 28 && ((this.isRoomVisited(r,c+1)==this.isRoomVisited(r,c))|| this.board.getCell(r, c+1).getEntry())){
                this.mark2(r,c+1,roll,moves+1);
            }
            if(r-1 >= 0 && ((this.isRoomVisited(r-1,c)==this.isRoomVisited(r,c))|| this.board.getCell(r-1, c).getEntry())){
                this.mark2(r-1,c,roll,moves+1);
            }
            else{
                return;
            }
        }else{
            return;
        }
    }

    public int isRoomVisited(int r, int c){
        if((r>=0 && r<=6)&&(c>=0 && c<=6)){
            return 1;
        }
        if((r>=0 && r<=4) && (c>=10 && c<=17)){
            return 2;
        }
        if(r>=0 && r<=4 && c>=20 && c<=27){
            return 3;
        }
        if(r>=9 && r<=18 && c>=0 && c<=6){
            return 4;
        }
        if(r>=12 && r<=15 && c>=12 && c<=15){
            return 5;
        }
        if(r>=7 && r<=16 && c>=21 && c<=27){
            return 6;
        }
        if(r>=21 && r<=27 && c>=0 && c<=7){
            return 7;
        }
        if((r>=22 && r<=27) && (c>=10 && c<=21)){
            return 8;
        }
        if(r>=19 && r<=27 && c>=24 && c<=27){
            return 9;
        }
        if((r>=0 && r <28) && (c>=0 && c <28)){
            return 10;
        }
        else{
            return 11;
        }
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
