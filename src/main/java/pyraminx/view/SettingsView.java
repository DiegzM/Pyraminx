package pyraminx.view;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;    
/**
 * View for the settings of the Pyraminx puzzle.
 */
public class SettingsView {

    // Fields
    private final String BACKGROUND_COLOR = "#111";

    private final VBox root = new VBox(10);
    
    // Constructor
    public SettingsView() {
        // add test button
        Button testButton = new Button("Test Button");
        root.getChildren().add(testButton);
        root.setStyle("-fx-background-color:" + BACKGROUND_COLOR + "; -fx-padding: 10;");
    }

    // get the root node
    public VBox getRoot() {
        return root;
    }
}