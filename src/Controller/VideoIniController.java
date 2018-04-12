/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.media.MediaPlayer.Status.PLAYING;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Win10
 */
public class VideoIniController implements Initializable {
    @FXML
    private Button stopBtn;
    @FXML
    private Button playBtn;
    @FXML
    private MediaView mediaVie;
    @FXML
    private Button connexionBtn;
    @FXML
    private Button pauseBtn;
    
    
    MediaPlayer mediaplayer; 
    @FXML
    private AnchorPane pane;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       String VRul= "file:/C:/Users/lv/Desktop/video/bb.mp4";
        //String VRul= "file:/C:/Users/Win10/Desktop/video/bb.mp4";
        //String VRul= "file:/C:/Users/maryem/Desktop/S/video/bb.mp4";
        Media media = new Media (VRul);
        mediaplayer = new MediaPlayer(media);
        mediaVie.setMediaPlayer(mediaplayer); 
        pane.setOpacity(0);
        makeFadeIn();
        mediaplayer.play(); 
    }    

    @FXML
    private void Stop(ActionEvent event) {
        mediaplayer.stop();     
    }

    @FXML
    private void play(ActionEvent event) {
        MediaPlayer.Status status = mediaplayer.getStatus();
        if (status == MediaPlayer.Status.UNKNOWN || status == MediaPlayer.Status.HALTED) {
            return;
        }
        if (status == MediaPlayer.Status.PAUSED || status == MediaPlayer.Status.STOPPED || status == MediaPlayer.Status.READY) {
            mediaplayer.play();
        }
    }

    @FXML
    private void connexion(ActionEvent event) throws IOException {
        mediaplayer.pause();
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("UserLogin.fxml"));
        Scene scene2 = new Scene(root2); 
        primary.setTitle("Login!");
        primary.setScene(scene2);
        primary.show();
    }

    @FXML
    private void pause(ActionEvent event) {
        if (mediaplayer.getStatus()==PLAYING)
        {
             mediaplayer.pause();
        }
        else {
             mediaplayer.play(); 
        } 
    }
     private void makeFadeIn() {
        FadeTransition f=new FadeTransition();
        f.setDuration(Duration.millis(1500));
        f.setNode(pane);
        f.setFromValue(0);
        f.setToValue(1);
        f.play();
    }
    
}
