package org.gvozdetscky.GUI;

import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;

public class MenuBarApplication extends MenuBar {

    public MenuBar createAndGet() {
        MenuBar menuBar = new MenuBar();

        Menu about = new Menu("Справка");

        MenuItem menuAboutProg = new MenuItem("О программе");

        menuAboutProg.setOnAction(event -> showAboutProgramm());

        about.getItems().add(menuAboutProg);

        menuBar.getMenus().add(about);

        return menuBar;
    }

    private void showAboutProgramm() {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("Инфо");
        dialog.setHeaderText("Программа по распознаванию лиц!\n" +
                "Разработчик: Гвоздецкий Егор\n\n\n" +
                "Контакты: vk.com/gvozdetscky.egor");

        Image image = new Image("https://sun9-26.userapi.com/c636224/v636224916/b5fa/Ah1tLNp24LQ.jpg");
        ImageView imageView = new ImageView(image);

        dialog.setGraphic(imageView);

        dialog.showAndWait();
    }

}
