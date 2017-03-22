package sample;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;

// -----------------------------------

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        ScrollPane pane = new ScrollPane();
        Canvas canvas = new Canvas(3000, 3000);

        pane.setContent( canvas );
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        drawBoard(canvas);

        primaryStage.setTitle("Prison Break!");
        Scene scene = new Scene(pane, 3000,3000);
        primaryStage.setScene(scene);
        primaryStage.show();

        //root.getChildren().add( canvas );
        //ScrollPane sp = createScrollPane(root);
    }

    private void drawBoard(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.ORANGE);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        gc.setFill(Color.YELLOW);
        gc.setStroke(Color.GREY);
        gc.setLineWidth(1);
        gc.strokeRect(100,100,2800,2800);
        gc.fillRect(100, 100, 2800, 2800);
        for (int i = 200; i < 2900; i += 100) {
            gc.strokeLine(i, 100, i, 2900);
            gc.strokeLine(100, i, 2900, i);
        }
    }
}

