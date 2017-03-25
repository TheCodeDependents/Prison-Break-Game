package sample;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;


/**
 * Created by Erik on 3/23/2017.
 */
public class Alert {
    public static void display(String msg) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Message");
        window.setMinWidth(300);

        Label label = new Label();
        label.setText(msg);
        Button close = new Button("Close");
        close.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, close);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
