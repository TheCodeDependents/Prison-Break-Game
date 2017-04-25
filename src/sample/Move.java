package sample;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Erik on 4/10/2017.
 */
public class Move {
    private Board board;
    private Player player;
    private Cell move;
    private int dieRoll;

    Timer timer;
    TimerTask roll;

    public Move(Board board, Player player) {
        this.board = board;
        this.player = player;
        this.dieRoll = rollDice();
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

    // Will generate and return an int
    // representing how far a piece can move
    public int rollDice() {
        int die1, die2, sum = 0;
        timer = new Timer();

        Random rand = new Random();
        int reps = 10 + rand.nextInt(8) + 1;;
        for (int i = 0; i <= reps; i++) {
                die1 = rand.nextInt(6) + 1;
                //Alert.display("d1   " + die1);
                die2 = rand.nextInt(6) + 1;
                //Alert.display("d2   " + die2);
                sum = die1 + die2;
                timer.schedule(new Roll(this.board, die1, die2), i*150);
        }
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

    class Roll extends TimerTask {
        private final Board brd;
        private final int d1;
        private final int d2;

        Roll(Board brd, int d1, int d2){
            this.brd = brd;
            this.d1 = d1;
            this.d2 = d2;
        }

        @Override
        public void run() {
            this.brd.renderDice(this.d1, this.d2);
            //timer.cancel();

        }
    }

}
