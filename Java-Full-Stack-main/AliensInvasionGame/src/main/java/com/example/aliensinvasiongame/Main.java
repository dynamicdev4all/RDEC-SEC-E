package com.example.aliensinvasiongame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
//import javafx.scene.media.MediaPlayer;
import javax.print.attribute.standard.Media;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Scene1.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.requestFocus();
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.setTitle("GAME");
        stage.getIcons().add(new Image("/ememy.png"));
        stage.show();
    }
    //MediaPlayer mediaPlayer;
    //WebView webView;

    public static void main(String[] args) {
        launch(args);
    }
}
