package Frame.SecondaryFrames;
/**
 * Author: Hao Li
 * Date: 05/11,2019
 */

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class LineView extends HBox {
    public TextArea name_area = new TextArea();
    public TextArea user_area = new TextArea();
    public TextArea pass_area = new TextArea();
    public Button view_delete = new Button("DELETE");

    /**
     * Construct to set up the elements in LineView frame
     */
    public LineView(){
        this.setPrefSize(600,30);
        //this.setMaxSize(500,30);
        name_area.setPrefSize(150,25);
        pass_area.setPrefSize(150,25);
        user_area.setPrefSize(150,25);
        view_delete.setPrefSize(70,25);
        name_area.setMaxHeight(25);
        user_area.setMaxHeight(25);
        pass_area.setMaxHeight(25);
        view_delete.setMaxHeight(25);
        name_area.setMinHeight(25);
        user_area.setMinHeight(25);
        pass_area.setMinHeight(25);
        view_delete.setMinHeight(25);
        name_area.setWrapText(true);
        user_area.setWrapText(true);
        pass_area.setWrapText(true);
        user_area.setEditable(false);
        name_area.setEditable(false);
        pass_area.setEditable(false);
        HBox.setMargin(name_area,new Insets(0,0,0,15));
        HBox.setMargin(user_area,new Insets(0,0,0,10));
        HBox.setMargin(pass_area,new Insets(0,0,0,10));
        HBox.setMargin(view_delete,new Insets(0,0,0,25));
        this.setPadding(new Insets(3,0,3,0));
        this.getChildren().addAll(name_area,user_area,pass_area,view_delete);
    }

    /*
      Setter for elements in LineView
     */
    public void setName_area(String name) {
        this.name_area.setText(name);
    }

    public void setUser_area(String user) {
        this.user_area.setText(user);
    }

    public void setPass_area(String pass) {
        this.pass_area.setText(pass);
    }
}
