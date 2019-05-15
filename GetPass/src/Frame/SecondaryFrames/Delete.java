package Frame.SecondaryFrames;
/**
 * Author: Hao Li
 * Date: 05/07,2019
 */
import Databass.DeletePass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Delete {
    public Stage delWindows = new Stage();
    VBox delVBox = new VBox();
    Label ask = new Label("Are you sure to delete ALL passwords?");
    Button delCancel = new Button("Cancel");
    Button delConfirm = new Button("Confirm");
    public int delete_current_user_id=0;

    public Delete(){
        Scene DelScene = new Scene(setDelVBox());
        delWindows.setScene(DelScene);
    }

    VBox setDelVBox(){
        delVBox.setPrefSize(300,150);
        ask.setPrefSize(300,100);
        ask.setAlignment(Pos.CENTER);
        delVBox.getChildren().addAll(ask,bottomButtons());
        return delVBox;
    }

    HBox bottomButtons(){
        HBox delHBox = new HBox();
        delHBox.setPrefSize(300,50);
        delCancel.setPrefSize(80,30);
        delConfirm.setPrefSize(80,30);
        HBox.setMargin(delCancel,new Insets(0,0,0,20));
        HBox.setMargin(delConfirm,new Insets(0,0,0,100));
        delCancel.setOnAction(e->{
            delWindows.close();
        });
        delConfirm.setOnAction(e->{
            DeletePass deletePass = new DeletePass();
            deletePass.DeleteAll(delete_current_user_id);
            Tip t = new Tip();
            if (deletePass.message.equals("true")){
                delWindows.close();
                t.tipMessage.setText("All passwords DELETED!");
                t.tipsWindows.show();
            }
            else {
                delWindows.close();
                t.tipMessage.setText("Delete Error");
                t.tipsWindows.show();
            }

        });

        delHBox.getChildren().addAll(delCancel,delConfirm);
        return delHBox;
    }
}
