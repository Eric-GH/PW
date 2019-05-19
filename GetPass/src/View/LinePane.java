package View;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class LinePane extends HBox {
    public TextArea name_area = new TextArea();
    public TextArea user_area = new TextArea();
    public TextArea pass_area = new TextArea();
    public Button view_delete = new Button("DELETE");

    /**
     * Construct to set up the elements in LinePane frame
     */
    public LinePane(){
        this.setId("LinePane");
        name_area.setId("line_area");
        user_area.setId("line_area");
        pass_area.setId("line_area");
        user_area.setEditable(false);
        name_area.setEditable(false);
        pass_area.setEditable(false);
        HBox.setMargin(name_area,new Insets(0,0,0,15));
        HBox.setMargin(user_area,new Insets(0,0,0,10));
        HBox.setMargin(pass_area,new Insets(0,0,0,10));
        HBox.setMargin(view_delete,new Insets(0,0,0,25));
        this.setPadding(new Insets(3,0,3,0));
        this.getStylesheets().add("./CSS/View_css.css");
        this.getChildren().addAll(name_area,user_area,pass_area,view_delete);
    }
    /*
  Setter for elements in LinePane
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
