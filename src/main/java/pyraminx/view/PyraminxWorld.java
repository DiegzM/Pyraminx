package pyraminx.view;

// Imports
import javafx.scene.Group;
import javafx.scene.shape.Box;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.paint.Color;
import javafx.scene.PointLight;
import javafx.scene.AmbientLight;
import javafx.scene.transform.Rotate;
import javafx.animation.RotateTransition;

/**
 * Class representing the 3D environment of the Pyraminx.
 */
public class PyraminxWorld extends Group {
    // Fields
    // Constructor
    public PyraminxWorld() {
        initLighting();
        initModel();
    }

    public void initLighting() {
        // add lighting
        PointLight pointLight = new PointLight(Color.WHITE);
        pointLight.setTranslateZ(-100);

        AmbientLight ambientLight = new AmbientLight(Color.color(0.8, 0.8, 0.8));

        this.getChildren().addAll(pointLight, ambientLight);
    }

    public void initModel() {
        // test tip3d
        Tip3D tip = new Tip3D(new Color[]{Color.RED, Color.GREEN, Color.BLUE});
        this.getChildren().add(tip);

        // animate tip rotation testing
        RotateTransition rotateTransition = new RotateTransition(javafx.util.Duration.seconds(5), tip);
        rotateTransition.setAxis(Rotate.Y_AXIS);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(RotateTransition.INDEFINITE);
        rotateTransition.setAutoReverse(false);
        rotateTransition.play();
    }

}
