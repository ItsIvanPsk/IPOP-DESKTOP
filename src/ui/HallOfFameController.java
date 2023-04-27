package ui;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class HallOfFameController implements Initializable {

    public ArrayList<Record> allRecords; // All the records saved
    public static ArrayList<Record> records; // Records to show, [adapter]

    private int loadingCounter = 0;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) { records = new ArrayList<>(); }

    @FXML
    public void getMoreRecords() {  }

    @FXML
    public void disableLeft() {  }

    @FXML
    public void disableRight() {  }

    @FXML
    public void showRecords() {
        
    }

    @FXML
    public void loadUsersList() {
        JSONObject obj = new JSONObject("{}");
        obj.put("type", "userList");

        showLoading();
        /*
         * UtilsHTTP.sendPOST(Main.protocol + "://" + Main.host + ":" + Main.port + "/dades", obj.toString(),
                (response) -> {
                    loadUsers(response);
                    hideLoading();
                });
         */
    }

    private void loadUsers(String response) {

        JSONObject objResponse = new JSONObject(response);

        if (objResponse.getString("status").equals("OK")) {

            JSONArray JSONlist = objResponse.getJSONArray("result");
            URL resource = this.getClass().getResource("./assets/listitem.fxml");

            // Clear the list of consoles
            
            //vBoxList.getChildren().clear();

            // Add received consoles from the JSON to the yPane (VBox) list
            for (int i = 0; i < JSONlist.length(); i++) {

                // Get console information
                JSONObject user = JSONlist.getJSONObject(i);

                try {
                    // Load template and set controller
                    FXMLLoader loader = new FXMLLoader(resource);
                    Parent itemTemplate = loader.load();
                    //ControllerItem itemController = loader.getController();

                    // Fill template with console information
                    //itemController.setId(String.valueOf(user.getInt("id")));
                    //itemController.setUsername(user.getString("nom"));

                    // Add template to the list
                    //vBoxList.getChildren().add(itemTemplate);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void showLoading() {
        loadingCounter++;
        //loading.setVisible(true);
    }

    private void hideLoading() {
        loadingCounter--;
        if (loadingCounter <= 0) {
            loadingCounter = 0;
            //loading.setVisible(false);
        }
    }

}
