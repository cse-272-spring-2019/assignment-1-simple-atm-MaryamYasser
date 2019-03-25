package sample;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.geometry.Pos;

public class PopUp {

        static boolean answer;

    public static void Error(String title,String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label passerror = new Label(message);
        passerror.setTextFill(Color.NAVAJOWHITE);
        passerror.setFont(Font.font("Krungthep"));
        passerror.setStyle("-fx-background-color: black");


        VBox popup = new VBox(10);
        popup.getChildren().add(passerror);
        popup.setAlignment(Pos.CENTER);
        popup.setStyle("-fx-background-color: slategrey");
        Scene error= new Scene(popup,150,150);
        window.setScene(error);
        window.showAndWait();
    }

    public static boolean confirm(String title,String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label1 = new Label(message);
        label1.setTextFill(Color.NAVAJOWHITE);
        label1.setFont(Font.font("Krungthep"));
        label1.setStyle("-fx-background-color: black");

        Button yess = new Button("Yes");
        yess.setTextFill(Color.NAVAJOWHITE);
        yess.setFont(Font.font("Krungthep"));
        yess.setStyle("-fx-background-color: black");
        yess.setOnAction(e -> {
            answer= true;
            window.close();
        });
        Button noo = new Button("No");
        noo.setTextFill(Color.NAVAJOWHITE);
        noo.setFont(Font.font("Krungthep"));
        noo.setStyle("-fx-background-color: black");
        noo.setOnAction(e -> {
            answer= false;
            window.close();
        });


        VBox confirm = new VBox(10);
        confirm.getChildren().addAll(label1,yess,noo);
        confirm.setAlignment(Pos.CENTER);
        confirm.setStyle("-fx-background-color: slategrey");

        Scene scene1= new Scene(confirm,150,150);
        window.setScene(scene1);
        window.showAndWait();
        return answer;


    }}






