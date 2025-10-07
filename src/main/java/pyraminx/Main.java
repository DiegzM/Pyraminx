package pyraminx;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import pyraminx.model.PyraminxModel;
import pyraminx.controller.PyraminxController;
import pyraminx.view.PyraminxView;
import pyraminx.view.SettingsView;
/**
 * Main class to launch the Pyraminx application.
 */
public class Main extends Application {

    public static final int WINDOW_WIDTH = 700;
    public static final int WINDOW_HEIGHT = 500;

    @Override
    public void start(Stage stage) {
        // Initialize the model, view, and controller
        PyraminxModel model = new PyraminxModel();
        PyraminxView pyraminxView = new PyraminxView();
        SettingsView settingsView = new SettingsView();
        PyraminxController controller = new PyraminxController(model, pyraminxView, settingsView);

        // Initialize event handlers
        controller.initializeHandlers();
        
        // Create the main layout
        BorderPane root = new BorderPane();
        root.setCenter(pyraminxView.getRoot());
        root.setRight(settingsView.getRoot());

        // Create the scene
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.BLACK);
        stage.setScene(scene);
        stage.setTitle("Pyraminx");
        stage.show();
    }
    // Launch the application
    public static void main(String[] args) {
        launch(args);
    }
}
