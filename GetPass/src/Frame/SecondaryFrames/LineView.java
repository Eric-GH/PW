package Frame.SecondaryFrames;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class LineView extends HBox {
    Label name_view = new Label("Name:");
    Label username_view = new Label("Username:");
    Label password_view = new Label("Password:");
    TextArea name_area = new TextArea();
    TextArea user_area = new TextArea();
    TextArea pass_area = new TextArea();

    LineView(){
        this.setPrefSize(600,25);
        name_view.setPrefSize(40,20);
        username_view.setPrefSize(70,20);
        password_view.setPrefSize(70,20);
        name_area.setPrefSize(120,20);
        pass_area.setPrefSize(120,20);
        user_area.setPrefSize(120,20);
        name_area.setMaxHeight(20);
        user_area.setMaxHeight(20);
        pass_area.setMaxHeight(20);
        HBox.setMargin(username_view,new Insets(0,0,0,10));
        HBox.setMargin(password_view,new Insets(0,0,0,10));
        this.setPadding(new Insets(3,0,3,20));
        this.getChildren().addAll(name_view,name_area,username_view,user_area,password_view,pass_area);
    }

    public void setName_view(Label name_view) {
        this.name_view = name_view;
    }

    public void setUsername_view(Label username_view) {
        this.username_view = username_view;
    }

    public void setPassword_view(Label password_view) {
        this.password_view = password_view;
    }

    public void setName_area(TextArea name_area) {
        this.name_area = name_area;
    }

    public void setUser_area(TextArea user_area) {
        this.user_area = user_area;
    }

    public void setPass_area(TextArea pass_area) {
        this.pass_area = pass_area;
    }
}
