

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

    @FXML
    public void showButton() {
        
    }

}
