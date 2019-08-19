package org.gvozdetscky;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Приложение по индитификации");

        VBox root = new VBox();

        Button uploadImage1 = new Button("Загрузка изображения 1");
        Button uploadImage2 = new Button("Загрузка изображения 2");

        Scene scene = new Scene(root, 300, 300);

        primaryStage.setScene(scene);

        root.getChildren().addAll(uploadImage1, uploadImage2);

        primaryStage.show();
    }
}
