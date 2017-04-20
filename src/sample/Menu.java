package sample;

/**
 * Created by Erik on 4/1/2017.
 */
public class Menu {

    private Tuple position;    // this is where we want to store the upper left hand corner
    private Game game;
    private boolean isOpen;

    public Menu (Game game){
        this.game = game;
        this.isOpen = true;
    }

    public void draw() {


    }
}
