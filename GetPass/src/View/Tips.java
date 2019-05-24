package View;

import Controller.CentralControl;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Tips {
    public Stage tipsWindows = new Stage();
    public Label tipMessage = new Label();
    public Button confirm = new Button("CONFIRM");
    public boolean delete_flag = false;
    CentralControl controller;

    public VBox tipVBox = new VBox();
    public Tips(){
        Scene TipsScence = new Scene(setBox());
        tipsWindows.setScene(TipsScence);
        TipsScence.getStylesheets().add("./CSS/SecondaryFrame.css");
    }

    public void setController(CentralControl controller){
        this.controller = controller;
        confirm.setOnAction(controller::tipsFunction);

    }
    VBox setBox(){
        /*
        Set up the elements in the Frame
         */
        tipVBox.setId("TipsBox");
        tipMessage.setId("warningLabel");
        confirm.setId("tipsBtn");
        VBox.setMargin(confirm,new Insets(0,0,0,120));
        tipVBox.getChildren().addAll(tipMessage,confirm);
        return tipVBox;
    }

}
