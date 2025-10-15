package pyraminx.view;

// Imports
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.geometry.Point3D;

import java.sql.Time;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Pyraminx3D extends Group {

    // Fields
    private Group[] pieces;
    private Group root;

    // Piece parameters
    private final float side;
    private final float inset;
    private final float outwardOffset;
    private final Color borderColor = Color.BLACK;

    // Constructor
    public Pyraminx3D(float side, float inset, float outwardOffset) {
        this.side = side;
        this.inset = inset;
        this.outwardOffset = outwardOffset;

        root = new Group();
        pieces = new Group[14]; // 4 tips, 6 edges, 4 centers
        createPieces();

        this.getChildren().add(root);
    }

    private void createPieces() {
        // Calculate height and inner radius
        float height = (float) (side * Math.sqrt(6f) / 3f);
        float inner_radius = (float) (side * Math.sqrt(3) / 6);
        float circum_radius = (float) (side * Math.sqrt(3f) / 3f);

        
    }
}