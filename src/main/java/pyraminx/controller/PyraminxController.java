package pyraminx.controller;

// Imports
import pyraminx.view.PyraminxView;
import pyraminx.view.SettingsView;
import pyraminx.model.PyraminxModel;

/**
 * Controller for the Pyraminx puzzle.
 */
public class PyraminxController {
    private PyraminxModel model;
    private PyraminxView pyraminxView;
    private SettingsView settingsView;

    private double lastX, lastY;
    private boolean isDragging = false;

    public PyraminxController(PyraminxModel model, PyraminxView pyraminxView, SettingsView settingsView) {
        this.model = model;
        this.pyraminxView = pyraminxView;
        this.settingsView = settingsView;
    }

    public void initializeHandlers() {
        // Attach event handlers
        pyraminxView.getSubScene().setOnMousePressed(event -> {
            lastX = event.getSceneX();
            lastY = event.getSceneY();
            isDragging = true;
        });
        pyraminxView.getSubScene().setOnMouseDragged(event -> {
            double dx = event.getSceneX() - lastX;
            double dy = event.getSceneY() - lastY;
            lastX = event.getSceneX();
            lastY = event.getSceneY();
            handleMouseDrag(dx, dy);
        });
        pyraminxView.getSubScene().setOnMouseReleased(event -> {
            isDragging = false;
            handleMouseReleased();
        });
    }

    public void handleMouseDrag(double deltaX, double deltaY) {
        pyraminxView.onMouseDragged(deltaX, deltaY);
    }

    public void handleMouseReleased() {
        pyraminxView.onMouseReleased();
    }

}
