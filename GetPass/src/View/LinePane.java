package View;

/**
 * Author: Hao Li
 * Date: 05/19,2019
 * The Secondary frame for show one line of the password record
 * JavaFX
 */
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class LinePane extends HBox {

    private TextArea name_area = new TextArea();
    private TextArea user_area = new TextArea();
    private TextArea pass_area = new TextArea();
    Button view_delete = new Button("⊝");

    /**
     * Construct to set up the elements in LinePane frame
     */
    LinePane(){
        /*
        Create 2 line
         */
        Separator frontSep = new Separator();
        Separator backSep = new Separator();
        /*
        Set up the id for each elements
         */
        this.setId("LinePane");
        name_area.setId("line_area");
        user_area.setId("line_area");
        pass_area.setId("line_area");
        view_delete.setId("view_delete");
        frontSep.setId("lineSeparator");
        backSep.setId("lineSeparator");

        /*
        Set up margin and editable for elements
         */
        user_area.setEditable(false);
        name_area.setEditable(false);
        pass_area.setEditable(false);
        frontSep.setOrientation(Orientation.VERTICAL);
        backSep.setOrientation(Orientation.VERTICAL);
        HBox.setMargin(frontSep,new Insets(-9,0,0,30));
        HBox.setMargin(backSep,new Insets(-9,0,0,0));
        HBox.setMargin(user_area,new Insets(0,0,0,20));
        HBox.setMargin(pass_area,new Insets(0,0,0,20));
        HBox.setMargin(view_delete,new Insets(0,0,0,10));
        view_delete.setPadding(new Insets(0,0,5,0));
        this.getStylesheets().add("CSS/View_css.css");
        this.getChildren().addAll(frontSep,name_area,user_area,pass_area,view_delete,backSep);
    }
    /*
        Create Setter for elements in LinePane
    */
    void setName_area(String name) {
        this.name_area.setText(name);
    }

    void setUser_area(String user) {
        this.user_area.setText(user);
    }

    void setPass_area(String pass) {
        this.pass_area.setText(pass);
    }
}
