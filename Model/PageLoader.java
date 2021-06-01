package Model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.nio.file.Paths;

public class PageLoader {
    private static Stage stage;
    private static Scene scene;
    public static final int WIDTH = 360;
    public static final int HEIGHT = 600;

    public static void initStage(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("SBU GRAM");
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(true);
        stage.getIcons().add(new Image(Paths.get("Images/photo_2021-05-24_21-27-52.jpg").toUri().toString()));
    }

    public void load(String url) throws IOException {
        scene = new Scene(new PageLoader().loadFXML(url));
        stage.getIcons().add(new Image(Paths.get("Images/Untitled.png").toUri().toString()));
        stage.setScene(scene);
        stage.show();
    }

    public Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/View/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public void load(String fxml, Object controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/View/" + fxml + ".fxml"));
        fxmlLoader.setController(controller);
        fxmlLoader.load();
    }
}
