package tech.easyprojects.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import tech.easyprojects.api.AdflyApi;
import tech.easyprojects.apis.NavegatorApi;
import tech.easyprojects.system.Index;

public class IndexController implements Initializable{
    private AdflyApi aA = new AdflyApi();
    private Timer timer;
    
    @FXML private TextField txtThreads;
    @FXML private TextField txtLink;
    @FXML private TextField txtSegundos;
    @FXML private Label txtCiclos;
    
    public void initTraffic(){
        String url = txtLink.getText();
        int segundos = Integer.parseInt(txtSegundos.getText());
        if(url.length() > 0){
            
            int threadsQuantity = Integer.parseInt(txtThreads.getText());

            this.timer = new Timer (1000, (ActionEvent) -> {
                for(int i = 0; i < threadsQuantity; i++){
                    Platform.runLater(() -> {
                        aA.initBot(url, segundos);
                    });
                }

            });
            
            
            
            timer.start();
        }else{
            JOptionPane.showMessageDialog(null, "Escribe una url valida");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Timer dos = new Timer (1000, (ActionEvent) -> {
                Platform.runLater(() -> {
                        int now = Index.cicles;

                        txtCiclos.setText("" + (now));
                });
            });

            dos.start();
    }    

    public void stopThreads(){
        timer.stop();
    }
    
    public void telegramChannel() throws IOException{
        NavegatorApi nA = new NavegatorApi();
        nA.abrirNavegadorPorDefecto("https://t.me/cashbackend");
    }
}
