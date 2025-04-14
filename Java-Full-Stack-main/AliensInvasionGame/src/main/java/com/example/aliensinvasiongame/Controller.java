package com.example.aliensinvasiongame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    ImageView hero;
    @FXML
    Pane pane;
    @FXML
    Text message;

    int score;

    //variables
    private int moveSpeed = 25;
    Timeline move,create,fire,heroFire;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pane.setBackground(new Background(new BackgroundFill(Color.BLACK , null,null)));
        Platform.runLater(()->{
            pane.getScene().setOnKeyPressed(event->keyPressed(event));
            hero.setLayoutY(pane.getHeight()-hero.getFitHeight());
        });
        create= new Timeline(new KeyFrame(Duration.millis(1000),event->genrateEnemy()));
        move= new Timeline(new KeyFrame(Duration.seconds(.5),event->moving()));
        create.setCycleCount(Timeline.INDEFINITE);
        move.setCycleCount(Timeline.INDEFINITE);
        create.play();
        move.play();
        movebullet();
        fireing();
        herofiring();
    }

    private void herofiring() {
        heroFire = new Timeline(new KeyFrame(Duration.seconds(.2),event->{
            startFire(hero);
        }));
        heroFire.setCycleCount(Timeline.INDEFINITE);
        heroFire.play();
    }

    private void fireing() {
        fire = new Timeline(new KeyFrame(Duration.seconds(4),event->{
            for(var item : enemyList){
                startFire(item);
            }
            startFire(hero);
        }));
        fire.setCycleCount(Timeline.INDEFINITE);
        fire.play();
    }

    ArrayList<ImageView> enemyList = new ArrayList<>();
    int y =-10 ;
    int x =0 ;
    int enemySpeed=10;
    private void moving(){
        for(var item : enemyList){
            item.setLayoutY(item.getLayoutY()+enemySpeed);

            if( item.getBoundsInParent().intersects(hero.getBoundsInParent()) || item.getBoundsInParent().getMaxY()>= pane.getHeight() ){
               gameOver();
            }
        }
        enemySpeed+=.5;
    }

    private void genrateEnemy() {
        ImageView enemy = new ImageView(new Image("ememy.png"));
        enemy.setFitWidth(30);
        enemy.setFitHeight(30);
        enemy.setRotate(180);
        Random random = new Random();
        y-=enemy.getFitHeight();
        x = random.nextInt((int)pane.getWidth()-(int)enemy.getFitWidth()-10);
        if(!isComponent(x,y,x+(int)enemy.getFitWidth(),y)) {
            enemy.setX(x);// after -10
            enemy.setY(y);
            pane.getChildren().add(enemy);
            enemyList.add(enemy);
        }
        if (y<-100)
            y=-(int)enemy.getFitHeight();
    }

    private boolean isComponent(int x1, int y1,int x2,int y2) {
        for(var item : pane.getChildren()){
            if (item.getBoundsInParent().intersects(x1, y1, x2 - x1, y2 - y1)) {
                return true; // Component exists in the region
            }
        }
        return false;
    }

    ArrayList<Rectangle> bulletList = new ArrayList<>();
    private void startFire(ImageView enemy) {
        if(enemy!=hero && !enemyList.contains(enemy)) return;
        Rectangle bullet;
        if(enemy==hero) {
            bullet = new Rectangle(enemy.getBoundsInParent().getMinX()+15,enemy.getBoundsInParent().getMinY()-10,5,10);
            bullet.setFill(Color.RED);
        }
        else {
            bullet = new Rectangle(enemy.getBoundsInParent().getMinX()+15,enemy.getBoundsInParent().getMaxY(),5,10);
            bullet.setFill(Color.BLUE);
        }
        pane.getChildren().add(bullet);
        bulletList.add(bullet);
    }
    Timeline timeline;
//    private void movebullet() {
//        ArrayList<Rectangle> out = new ArrayList<>();
//        timeline = new Timeline(new KeyFrame(Duration.millis(30),event -> {
//        for(var item:bulletList){
//            if(item.getFill() == Color.RED){
//                item.setLayoutY(item.getLayoutY()-3);
//                for(var enemy: enemyList){
//                    if(enemy.getBoundsInParent().intersects(item.getBoundsInParent())){
//                        item.setVisible(false);
//                        pane.getChildren().remove(enemy);
//                    }
//                }
//            }
//            else {
//                item.setLayoutY(item.getLayoutY() + 3);
//            }
//            if(item.getBoundsInParent().intersects(hero.getBoundsInParent())){
//               gameOver();
//            }
//
//            if(item.getBoundsInParent().getMinY()>pane.getHeight() || item.getBoundsInParent().getMaxY()<0){
//                out.add(item);
//            }
//        }
//        pane.getChildren().removeAll(out);
//        bulletList.removeAll(out);
//        }));
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();
//    }
private void movebullet() {
    ArrayList<Rectangle> out = new ArrayList<>(); // To store bullets that need to be removed
    ArrayList<ImageView> enemiesToRemove = new ArrayList<>(); // To store enemies to remove

    timeline = new Timeline(new KeyFrame(Duration.millis(30), event -> {
        for (var bullet : bulletList) {
            if (bullet.getFill() == Color.RED) { // Hero's bullet
                bullet.setLayoutY(bullet.getLayoutY() - 3);

                // Check collision with enemies
                for (var enemy : enemyList) {
                    if (enemy.getBoundsInParent().intersects(bullet.getBoundsInParent())) {
                        bullet.setVisible(false);
                        pane.getChildren().remove(bullet);
                        score++;
                        out.add(bullet); // Mark bullet for removal
                        enemiesToRemove.add(enemy); // Mark enemy for removal
                        break; // Stop further checks for this bullet
                    }
                }
            } else { // Enemy's bullet
                bullet.setLayoutY(bullet.getLayoutY() + 3);

                // Check collision with hero
                if (bullet.getBoundsInParent().intersects(hero.getBoundsInParent())) {
                    gameOver();
                }
            }

            // Check if the bullet goes out of bounds
            if (bullet.getBoundsInParent().getMinY() > pane.getHeight() || bullet.getBoundsInParent().getMaxY() < 0) {
                out.add(bullet);
            }
        }

        // Remove bullets and enemies marked for removal
        pane.getChildren().removeAll(out);
        bulletList.removeAll(out);
        pane.getChildren().removeAll(enemiesToRemove);
        enemyList.removeAll(enemiesToRemove);

    }));
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
}

    private void gameOver() {
        System.out.println("Out with score "+score);
        move.stop();
        create.stop();
        fire.stop();
        heroFire.stop();
        if(timeline!=null)
            timeline.stop();
        message.setVisible(true);
        pane.getScene().setOnKeyPressed(event->{
            if(event.getCode() ==  KeyCode.P){
                message.setVisible(false);
                pane.getScene().setOnKeyPressed(e->keyPressed(e));
                pane.getChildren().removeAll(enemyList);
                pane.getChildren().removeAll(bulletList);
                enemyList.removeAll(enemyList);
                bulletList.removeAll(bulletList);
                create.playFromStart();
                move.playFromStart();
                movebullet();
                fireing();
                herofiring();
            }
        });
    }

    @FXML
    void keyPressed(KeyEvent event ){
        System.out.println("Key Pressed");
        if(event.getCode() == KeyCode.LEFT){
            System.out.println("LEFT PRESSED");
            if(hero.getLayoutX()-moveSpeed>=0) {
                hero.setLayoutX(hero.getLayoutX() - moveSpeed);
            }
            else{
                hero.setLayoutX(0);
            }
        }
        else if(event.getCode() == KeyCode.RIGHT){
            System.out.println("Right Pressed");
            if(hero.getLayoutX() + moveSpeed + hero.getFitWidth()<= pane.getWidth()) {
                hero.setLayoutX(hero.getLayoutX()+moveSpeed);
            }
            else{
                System.out.println("last");
                hero.setLayoutX(pane.getWidth()-hero.getFitWidth());
            }
        }
    }
}
