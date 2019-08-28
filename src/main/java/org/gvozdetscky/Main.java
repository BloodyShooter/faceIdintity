package org.gvozdetscky;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.gvozdetscky.logic.detectionapi.Detection;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;

public class Main extends Application {

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

            JsonParser parser = new JsonParser();

            JsonElement jsonElement = parser.parse(respounce);

            JsonObject rootObject = jsonElement.getAsJsonObject();

            float evclide_distance = rootObject.get("evclide_distance").getAsFloat();

            int otvet = (int)((1 - evclide_distance) * 100);
            System.out.println(otvet);

            JsonArray box1 = rootObject.get("image1").getAsJsonObject().get("box").getAsJsonArray();

            PixelReader reader = image1.getPixelReader();
            WritableImage newImage = new WritableImage(reader, box1.get(0).getAsInt(), box1.get(1).getAsInt(),
                    box1.get(2).getAsInt() - box1.get(0).getAsInt(), box1.get(3).getAsInt() - box1.get(1).getAsInt());

            findFaceImageView1.setImage(newImage);

            JsonArray box2 = rootObject.get("image2").getAsJsonObject().get("box").getAsJsonArray();

            int x = box2.get(0).getAsInt();
            int y = box2.get(1).getAsInt();
            int w = box2.get(2).getAsInt() - box2.get(0).getAsInt();
            int h = box2.get(3).getAsInt() - box2.get(1).getAsInt();

            System.out.println("Размеры картинки" + image2.getWidth() + ", " + image2.getHeight());

            reader = image2.getPixelReader();
            newImage = new WritableImage(reader, x, y,
                    w, h);

            findFaceImageView2.setImage(newImage);

            if (otvet > 40) {
                result.setText("Это один и тот же человек");
            } else {
                result.setText("Это два разных человека");
            }
        });

        Scene scene = new Scene(root, 500, 500);

        MenuBar menubar = new MenuBar();

        Menu about = new Menu("Справка");

        MenuItem menuAboutProg =new MenuItem("О программе");

        menuAboutProg.setOnAction(event -> showAboutProgramm());

        about.getItems().add(menuAboutProg);

        menubar.getMenus().add(about);

        primaryStage.setScene(scene);

        root.setTop(menubar);

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
