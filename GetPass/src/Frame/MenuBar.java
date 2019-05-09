package Frame;
import javafx.geometry.Insets;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;

public class MenuBar extends HBox {

    MenuBar(){
        this.setPrefHeight(25);
        this.setPrefWidth(500);
        this.setPadding(new Insets(0,0,0,10));
        this.getChildren().addAll(BuM());
    }



    MenuButton BuM(){
        MenuButton menuButton = new MenuButton("Menu");
        menuButton.setPrefSize(80,25);
        menuButton.getItems().addAll(AddNew(),DeleteAll(),QuitItem());

        return menuButton;
    }

    MenuItem AddNew(){
        MenuItem addNew = new MenuItem("Add New Password");
        return addNew;
    }


    MenuItem DeleteAll(){
        MenuItem deleteAll = new MenuItem("Delete all Passwords");
        return deleteAll;
    }


    MenuItem QuitItem(){
        MenuItem quitItem = new MenuItem("Quit");
        quitItem.setOnAction(event -> System.exit(0));
        return quitItem;
    }

}
