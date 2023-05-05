import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static UtilsWS socketClient;

    /* 
        
        public static int port = 5461;
        public static String protocol = "http";
        public static String host = "containers-us-west-10.railway.app";
        public static String protocolWS = "wss";
    */

    // Exemple de configuració per Railway
    
    public static int port = 3001;
    public static String protocol = "http";
    public static String host = "localhost";
    public static String protocolWS = "ws";      

    public static void main(String[] args) {

        // Iniciar app JavaFX   
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {

        final int windowWidth = 600;
        final int windowHeight = 400;

        UtilsViews.parentContainer.setStyle("-fx-font: 14 arial;");
        UtilsViews.addView(getClass(), "HallOfFame", "./assets/hall_of_fame.fxml");

        Scene scene = new Scene(UtilsViews.parentContainer);
        
        stage.setScene(scene);
        stage.onCloseRequestProperty(); // Call close method when closing window
        stage.setTitle("IPOP Game");
        stage.setMinWidth(windowWidth);
        stage.setMinHeight(windowHeight);
        stage.show();

        // Add icon only if not Mac
        if (!System.getProperty("os.name").contains("Mac")) {
            Image icon = new Image("file:./assets/icon.png");
            stage.getIcons().add(icon);
        }
    }

    @Override
    public void stop() { 
        socketClient.close();
        System.exit(1);
    }
}
