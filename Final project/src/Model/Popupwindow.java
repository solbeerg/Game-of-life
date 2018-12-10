/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Popupwindow {

    static String url=" ";

    /**
     * Method for generating a pop-up window.
     *
     * @param tittle String value for the title of the pop-up window.
     * @param message String value for the text written on the pop-up window.
     */
    public static void display(String tittle, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(tittle);
        window.setMinWidth(800);
        window.setMinHeight(250);
        Label label =new Label();
        label.setText(message);
        Button closebutton=new Button("Close");
        closebutton.setOnAction(e->window.close());


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closebutton);
        layout.setAlignment(Pos.CENTER);

        Scene scene=new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

}
