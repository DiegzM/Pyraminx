package pyraminx.view;

import javafx.animation.Animation;
// Imports
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.geometry.Point3D;
import javafx.scene.layout.Region;
import javafx.scene.transform.Rotate;
import javafx.animation.AnimationTimer;

import pyraminx.controller.*;

/**
 * View class for the Pyraminx, handling 3D rendering and camera setup.
 */
public class PyraminxView extends Group {
    
    // Fields
    private static final int CAMERA_DISTANCE = 200;
    private static final Color BACKGROUND_COLOR = Color.web("#090909");
    private static final double ROTATE_CAMERA_SPEED = 0.5;
    private static final double ROTATE_CAMERA_DAMPING = 0.85;

    private StackPane root = new StackPane();
    private PerspectiveCamera camera;
    private Group cameraPivot = new Group();
    private SubScene subScene;
    private PyraminxWorld world;

    private boolean isDragging = false;
    private double inertiaX = 0;
    private double inertiaY = 0;

    // Constructor
    public PyraminxView() {

        // Create the Pyraminx 3D environment
        world = new PyraminxWorld();

        // Create the subscene
        subScene = new SubScene(world, 1, 1, true, SceneAntialiasing.BALANCED);
        subScene.setFill(BACKGROUND_COLOR);

        // Make subscene resize with the pane
        subScene.widthProperty().bind(root.widthProperty());
        subScene.heightProperty().bind(root.heightProperty());

        // Ensure root can shrink to fit the subscene
        root.setMinSize(0, 0);
        root.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        root.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Create the camera
        camera = new PerspectiveCamera(true);
        camera.setNearClip(0.1);
        camera.setFarClip(10_000);

        // Set the camera pivot
        cameraPivot.getChildren().add(camera);
        camera.setTranslateZ(-CAMERA_DISTANCE);

        // Add camera to subscene
        subScene.setCamera(camera);
        getChildren().add(cameraPivot);
        
        // Initialize Damping Animation
        initializeDampingAnimation();

        // Add scene to root
        root.getChildren().add(subScene);

    }

    public void initializeDampingAnimation() {
        
        // Create and start the camera rotation damping animation
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!isDragging) {
                    inertiaX *= ROTATE_CAMERA_DAMPING;
                    inertiaY *= ROTATE_CAMERA_DAMPING;
                    if (Math.abs(inertiaX) < 0.01) inertiaX = 0;
                    if (Math.abs(inertiaY) < 0.01) inertiaY = 0;
                    cameraPivot.getTransforms().add(new Rotate(inertiaX, Rotate.Y_AXIS));
                    cameraPivot.getTransforms().add(new Rotate(-inertiaY, Rotate.X_AXIS));
                }
            }
        }.start();
    }

    public void onMouseDragged(double deltaX, double deltaY) {
        // debug deltas
        System.out.println("DeltaX: " + deltaX + ", DeltaY: " + deltaY);

        // rotate camera pivot
        cameraPivot.getTransforms().add(new Rotate(deltaX * ROTATE_CAMERA_SPEED, Rotate.Y_AXIS));
        cameraPivot.getTransforms().add(new Rotate(-deltaY * ROTATE_CAMERA_SPEED, Rotate.X_AXIS));

        inertiaX = deltaX * ROTATE_CAMERA_SPEED;
        inertiaY = deltaY * ROTATE_CAMERA_SPEED;

        isDragging = true;
    }

    public void onMouseReleased() {
        isDragging = false;
    }

    public StackPane getRoot() {
        return root;
    }

    public SubScene getSubScene() {
        return subScene;
    }
}