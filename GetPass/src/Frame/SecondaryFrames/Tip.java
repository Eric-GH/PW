package Frame.SecondaryFrames;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Tip {
    Stage tipsWindows = new Stage();

    public Label tipMessage = new Label();
    Button confirm = new Button("CONFIRM");

    VBox tipVBox = new VBox();
    public Tip(){
        Scene TipsScence = new Scene(setBox());
        tipsWindows.setScene(TipsScence);
    }

    VBox setBox(){
        /*
        Set up the elements in the Frame
         */
        tipVBox.setPrefSize(200,100);
        tipMessage.setPrefSize(200,70);
        confirm.setPrefSize(80,30);
        tipMessage.setAlignment(Pos.CENTER);
        VBox.setMargin(confirm,new Insets(0,0,0,60));

        /*
        Set up the action on the confirm button
         */
        confirm.setOnAction(e->{
            tipsWindows.close();
        });

        /*
        add all
         */
        tipVBox.getChildren().addAll(tipMessage,confirm);
        return tipVBox;
    }

}
