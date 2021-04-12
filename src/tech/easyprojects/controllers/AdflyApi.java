package tech.easyprojects.api;

import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import tech.easyprojects.system.Index;

public class AdflyApi{

    public void initBot(String url, int segundos){
        WebView wv = new WebView();
        wv.getEngine().load(url);
        
        wv.getEngine().setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36");
        wv.getEngine().setJavaScriptEnabled(true);
        
        AnchorPane root = new AnchorPane();
        root.setPrefSize(0, 0);
        root.getChildren().add(wv);
        Parent content = root;
        
        Scene scene = new Scene(content);
        Stage window = new Stage();
        window.setTitle("TrafficWeb");
        window.getIcons().add(new Image("/tech/easyprojects/images/icon.png"));
        window.setScene(scene);
        
        window.toBack();
        //window.show();
        window.hide();
        
        wv.getEngine().getLoadWorker().stateProperty()
            .addListener((obs, oldValue, newValue) -> {
              if (newValue == Worker.State.SUCCEEDED) {
                try{
                    
                            wv.getEngine().executeScript(
                                                    //"setInterval(() => {if(document.querySelector('#Interstitual')){if(document.querySelector('#abC')){document.querySelector('#abC').click();}if($(\"#Interstitual iframe\")){if ($(\"#Interstitual iframe\").attr(\"src\") != ''){$(\"#Interstitual iframe\").attr(\"src\", \"\");}}}}, 100);"+
                                                    "var link = location.href; \n"+
                                                    "function esperar(){ \n" + 
                                                        "if(link.search('ad/locked') > -1){\n" + 
                                                            "var newLink = window.location.protocol + '//' + window.location.hostname + $(\"#continue a\").attr(\"href\"); \n" +
                                                            "window.location.href = newLink;\n" +
                                                        "}else{ \n" + 
                                                            "\n" + 
                                                            //"document.write('a');\n" + 
                                                            "if(document.querySelector('.mwButton').getAttribute(\"href\") && document.querySelector('.mwButton').getAttribute(\"href\").length > 0){\n" + 
                                                                "var a = document.querySelector('.mwButton').getAttribute(\"href\"); \n" +
                                                                "window.location.href = a;\n" + 
                                                            "} \n" + 
                                                        "} \n" + 
                                                    "}\n " + "setInterval('esperar()', 1000);");
                            
                        }catch (Exception e) {
                            System.out.println("Error en una web redirección");
                        }
              }
        });
       
        javax.swing.Timer tiempoTres = new javax.swing.Timer(segundos*1000, (ActionEvent) -> {
            Index.cicles++;
            Platform.runLater(() -> {
                window.close();
            });
        });
        
        tiempoTres.start();
        tiempoTres.setRepeats(false);
    }
}
