package Frame;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FunctionBar extends HBox {
    //Button search = new Button();
    //Button viewAll = new Button();

    FunctionBar(){
        this.setPrefHeight(100);
        this.setPrefWidth(500);
        this.setPadding(new Insets(30,0,0,75));
        this.getChildren().addAll(SearchField(),SearchButton(),ViewAllButton());
        this.setStyle("-fx-background-color: #00ffff;");
    }

    TextField SearchField(){
        TextField field = new TextField();
        field.setPrefHeight(35);
        field.setPrefWidth(200);
        return field;
    }

    Button SearchButton(){
        Button search = new Button("Search");
        search.setPrefHeight(35);
        search.setPrefWidth(80);
        HBox.setMargin(search, new Insets(0,0,0,10));
        return search;
    }

    Button ViewAllButton(){
        Button viewAll = new Button("VIEW ALL");
        viewAll.setPrefHeight(35);
        viewAll.setPrefWidth(80);
        HBox.setMargin(viewAll,new Insets(0,0,0,10));
        return viewAll;
    }

}
