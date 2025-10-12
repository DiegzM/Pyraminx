package pyraminx.view;

// Imports
import javafx.scene.Group;
import javafx.scene.shape.Box;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.paint.Color;
import javafx.scene.PointLight;
import javafx.scene.AmbientLight;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;

import pyraminx.view.Pyraminx3D;

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
        Group pyraminx = new Pyraminx3D(20f, 2f, 0.5f);
        this.getChildren().add(pyraminx);
    }
}
