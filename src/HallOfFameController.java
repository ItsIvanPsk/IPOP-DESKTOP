
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
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class HallOfFameController implements Initializable {

    public ArrayList<PlayerRecord> allRecords; // All the records saved
    public static ArrayList<PlayerRecord> records; // Records to show, [adapter]

    private int loadingCounter = 0;

    private int startPosition = 1, endPosition = 19;
    private int maxCant = 6;

    @FXML
    public VBox vBox;

    @FXML
    public Button goBack, goNext;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) { 
        records = new ArrayList<>();
        allRecords = new ArrayList<>();
        loadRankingList();
    }

    @FXML
    public void enableGoBack(boolean state) { 
        goBack.setDisable(state);
    }

    public void enableGoNext(boolean state) { 
        goNext.setDisable(state);
    }

    @FXML
    public void loadRankingList() {
        JSONObject obj = new JSONObject("{}");
        obj.put("start", startPosition);
        obj.put("end", endPosition);
        
        UtilsHTTP.sendPOST(Main.protocol + "://" + Main.host + ":" + Main.port + "/api/get_ranking", obj.toString(),
                (response) -> {
                    loadRankings(response);
                });
        
    }

    public void loadRankings(String response) {
        JSONObject objResponse = new JSONObject(response);
        if (objResponse.getString("status").equals("OK")) {
            JSONArray JSONlist = objResponse.getJSONArray("message");
            if (JSONlist.length() > 0) {
                URL resource = this.getClass().getResource("./assets/listitem.fxml");            
                vBox.getChildren().clear();
                for (int i = 0; i < JSONlist.length(); i++) {
                    JSONObject ranking = JSONlist.getJSONObject(i);
                    Boolean vs = false;
                    if (ranking.getInt("isVisible") == 0) { vs=false; } else { vs=true; }
                    PlayerRecord newRecord = new PlayerRecord(
                        ranking.getInt("idRanking"),
                        ranking.getInt("correctTotems"),
                        ranking.getInt("wrongTotems"),
                        ranking.getInt("points"),
                        ranking.getInt("cycle_idCycle"),
                        ranking.getString("aliasPlayer").toString(),
                        ranking.getString("timeStart").toString(),
                        ranking.getString("timeEnd").toString(),
                        vs
                    );
                    allRecords.add(newRecord);
                    records.add(newRecord);
                    try {
                        FXMLLoader loader = new FXMLLoader(resource);
                        Parent itemTemplate = loader.load();
                        RecordItemController itemController = loader.getController();
                        itemController.setId(String.valueOf(newRecord.getRankingId()));
                        itemController.setUsername(newRecord.getAliasPlayer());
                        itemController.setScore(String.valueOf(newRecord.getPoints()));
                        itemController.setVisible(newRecord.getIsVisible());
                        itemController.setupButton();
                        vBox.getChildren().add(itemTemplate);
    
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("KOKOKO");
        }
    }

    @FXML
    public void getNext() {
        startPosition += maxCant;
        endPosition += maxCant;
        JSONObject obj = new JSONObject("{}");
        obj.put("start", startPosition);
        obj.put("end", endPosition);
        
        UtilsHTTP.sendPOST(Main.protocol + "://" + Main.host + ":" + Main.port + "/api/get_ranking", obj.toString(),
                (response) -> {
                    System.out.println(response);
                    loadRankings(response);
                });
    }

    @FXML
    public void getBack() {
        if (startPosition > maxCant) {
            startPosition -= maxCant;
            endPosition -= maxCant;
            JSONObject obj = new JSONObject("{}");
            obj.put("start", startPosition);
            obj.put("end", endPosition);
            
            UtilsHTTP.sendPOST(Main.protocol + "://" + Main.host + ":" + Main.port + "/api/get_ranking", obj.toString(),
                    (response) -> {
                        System.out.println(response);
                        loadRankings(response);
                    });
        }
    }

}
