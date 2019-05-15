package Frame;

/**
 * Author: Hao Li
 * Date: 05/03,2019
 */

import Frame.SecondaryFrames.Add;
import Frame.SecondaryFrames.Delete;
import javafx.geometry.Insets;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;

public class MenuBar extends HBox {

    public int menu_user_id;
    public Add add;
    public Delete delete;
    MenuBar(int menu_user_id){
        this.menu_user_id = menu_user_id;
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
        addNew.setOnAction(e->{
            add = new Add(menu_user_id);
            add.Addwindows.show();
        });
        return addNew;
    }


    MenuItem DeleteAll(){
        MenuItem deleteAll = new MenuItem("Delete all Passwords");
        deleteAll.setOnAction(e->{
            delete = new Delete(menu_user_id);
            delete.delWindows.show();
        });
        return deleteAll;
    }


    MenuItem QuitItem(){
        MenuItem quitItem = new MenuItem("Quit");
        quitItem.setOnAction(event -> System.exit(0));
        return quitItem;
    }

}
