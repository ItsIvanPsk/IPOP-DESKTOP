

import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class RecordItemController {
    
    @FXML
    private Label id, userName, score;
    @FXML
    private Button visibility;

    public static boolean isVisible = true;


    public void setId(String id) {
        this.id.setText(id);
    }

    public void setUsername(String userName) {
        this.userName.setText(userName);
    }

    public void setScore(String score) {
        this.score.setText(score);
    }

    public void setVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Boolean getVisible() {
        return isVisible;
    }

    public void setupButton() {
        if (isVisible){
            visibility.setText("Mostrar");
        } else { visibility.setText("Ocultar"); }
    }

    @FXML
    public void showButton() {
        isVisible = !isVisible;
        setupButton();
        JSONObject obj = new JSONObject("{}");
        obj.put("idRanking", id.getText());
        obj.put("isVisible", isVisible);
        UtilsHTTP.sendPOST(Main.protocol + "://" + Main.host + ":" + Main.port + "/api/hide_ranking", obj.toString(),
                (response) -> { 
                    HallOfFameController c0 = (HallOfFameController) UtilsViews.getController("HallOfFame");
                    c0.loadRankingList();
                });
    }

}
