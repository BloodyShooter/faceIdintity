package org.gvozdetscky;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Приложение по индитификации");

        BorderPane root = new BorderPane();

        VBox vbox = new VBox();

        Button uploadImage1 = new Button("Загрузка изображения 1");
        Button uploadImage2 = new Button("Загрузка изображения 2");

        Scene scene = new Scene(root, 300, 300);

        MenuBar menubar = new MenuBar();

        Menu about = new Menu("Справка");

        MenuItem menuAboutProg =new MenuItem("О программе");

        menuAboutProg.setOnAction(event -> showAboutProgramm());

        about.getItems().add(menuAboutProg);

        menubar.getMenus().add(about);

        primaryStage.setScene(scene);

        root.setTop(menubar);

        root.setCenter(vbox);

        vbox.getChildren().addAll(uploadImage1, uploadImage2);

        primaryStage.show();
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
