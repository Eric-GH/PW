package View;

/**
 * Author: Hao Li
 * Date: 05/20,2019
 * The Secondary frame for give user tips
 * JavaFX
 */
import Controller.CentralControl;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Tips {
    public Stage tipsWindows = new Stage();
    public Label tipMessage = new Label();
    private Button confirm = new Button("CONFIRM");
    public boolean delete_flag = false; // check if this tips for delete addnew password records
    private VBox tipVBox = new VBox();
    CentralControl controller;

    /**
     * Construct for the Tips frame
     */
    Tips(){
        Scene TipsScence = new Scene(setBox());
        tipsWindows.setScene(TipsScence);
        TipsScence.getStylesheets().add("CSS/SecondaryFrame.css");
        tipsWindows.initModality(Modality.APPLICATION_MODAL);
    }

    /**
     * Set the controller
     * @param controller Central controller
     */
    void setController(CentralControl controller){
        this.controller = controller;
        confirm.setOnAction(controller::tipsFunction);

    }

    /**
     * Create the VBox for components
     * @return tipVBox
     */
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
