package org.gvozdetscky;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.gvozdetscky.GUI.MenuBarApplication;
import org.gvozdetscky.logic.ParserJson;
import org.gvozdetscky.logic.detectionapi.Detection;
import org.gvozdetscky.model.Box;
import org.gvozdetscky.model.Result;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;

public class Main extends Application {

    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 500;

    private String pathImage1 = "C:\\Users\\Yagorka\\PycharmProjects\\FindFace\\1.png";

    private String pathImage2 = "C:\\Users\\Yagorka\\PycharmProjects\\FindFace\\2.png";

    private ImageView imageView1 = new ImageView();
    private ImageView imageView2 = new ImageView();

    private Image image1;
    private Image image2;

    private ImageView findFaceImageView1 = new ImageView();
    private ImageView findFaceImageView2 = new ImageView();

    private Label result = new Label();

    public void start(Stage primaryStage) throws Exception {

        TextArea textArea = new TextArea();

        imageView1.setFitWidth(300);
        imageView1.setFitHeight(300);

        imageView2.setFitWidth(300);
        imageView2.setFitHeight(300);

        textArea.setEditable(false);

        primaryStage.setTitle("Приложение по индитификации");

        BorderPane root = new BorderPane();

        VBox leftSideBox = new VBox();
        VBox rightSideBox = new VBox();

        HBox hBox = new HBox();

        Button uploadImage1 = new Button("Загрузка изображения 1");
        Button uploadImage2 = new Button("Загрузка изображения 2");


        Button runBtn = new Button("Запустить сравнение");

        uploadImage1.setOnAction(event -> {
            final FileChooser fileChooser = new FileChooser();

            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                String path = file.getAbsolutePath();
                textArea.appendText("Выбрали 1 картинку " + path + "\n");

                try {
                    image1 = new Image(file.toURI().toURL().toString());
                    imageView1.setImage(image1);


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                pathImage1 = path;
            }

        });

        uploadImage2.setOnAction(event -> {
            final FileChooser fileChooser = new FileChooser();

            File file = fileChooser.showOpenDialog(primaryStage);

            if (file != null) {
                String path = file.getAbsolutePath();
                textArea.appendText("Выбрали 2 картинку " + path + "\n");

                try {
                    image2 = new Image(file.toURI().toURL().toString());
                    imageView2.setImage(image2);

                    if (imageView2.getFitHeight() > 300) {
                        imageView2.setFitHeight(300);
                    }

                    if (imageView2.getFitWidth() > 300) {
                        imageView2.setFitWidth(300);
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                pathImage2 = path;
            }
        });

        runBtn.setOnAction(event -> {
            Detection detection = new Detection();

            String respounce = detection.faceIdintityUrl(pathImage1, pathImage2);

            textArea.appendText(respounce);

            ParserJson parserJson = new ParserJson();

            Result result = parserJson.parseJson(respounce);

            int otvet = (int)((1 - result.getEvclidDeistance()) * 100);

            Box box1 = result.getImages().get(0).getBox();
            Box box2 = result.getImages().get(1).getBox();

            PixelReader reader = image1.getPixelReader();
            WritableImage newImage = new WritableImage(reader, box1.getLeft(), box1.getTop(),
                    box1.getRight(), box1.getBottom());

            findFaceImageView1.setImage(newImage);

            reader = image2.getPixelReader();
            newImage = new WritableImage(reader, box2.getLeft(), box2.getTop(),
                    box2.getRight(), box2.getBottom());

            findFaceImageView2.setImage(newImage);

            if (otvet > 40) {
                this.result.setText("Это один и тот же человек");
            } else {
                this.result.setText("Это два разных человека");
            }
        });

        Scene scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        primaryStage.setScene(scene);

        root.setTop(new MenuBarApplication().createAndGet());

        textArea.setMinHeight(600);

        root.setCenter(hBox);

        hBox.getChildren().addAll(leftSideBox, rightSideBox, runBtn, result,
                findFaceImageView1, findFaceImageView2);

        leftSideBox.getChildren().addAll(uploadImage1, imageView1);

        rightSideBox.getChildren().addAll(uploadImage2, imageView2);

        root.setBottom(textArea);

        primaryStage.getIcons().add(new Image(new FileInputStream("C:\\Users\\Yagorka\\Pictures\\estus.png")));

        primaryStage.show();
    }
}
