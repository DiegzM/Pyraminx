package pyraminx.view;

// Imports
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

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

        // First layer
        Tip3D tip1 = new Tip3D(new double[]{0, -1.5 * height, 0}, side, inset, outwardOffset,
                new Color[]{Color.YELLOW, Color.RED, Color.BLUE, borderColor}, borderColor);
        pieces[0] = tip1;

        // Second layer
        Edge3D edge1 = new Edge3D(new double[]{0, -height / 2, circum_radius}, side, inset, outwardOffset,
                new Color[]{Color.YELLOW, borderColor, Color.BLUE, borderColor}, borderColor);
        pieces[1] = edge1;

        Center3D center1 = new Center3D(new double[]{0, -height / 2, 0}, side, inset, outwardOffset,
                new Color[]{Color.YELLOW, Color.RED, Color.BLUE, borderColor}, borderColor);
        pieces[2] = center1;

        Edge3D edge2 = new Edge3D(new double[]{-side/2, -height/2, -inner_radius}, side, inset, outwardOffset,
                new Color[]{Color.YELLOW, Color.RED, borderColor, borderColor}, borderColor);
        pieces[3] = edge2;

        Edge3D edge3 = new Edge3D(new double[]{side/2, -height/2, -inner_radius}, side, inset, outwardOffset,
                new Color[]{borderColor, Color.RED, Color.BLUE, borderColor}, borderColor);
        pieces[4] = edge3;


        // Third Layer
        Tip3D tip2 = new Tip3D(new double[]{0, height/2, circum_radius * 2}, side, inset, outwardOffset,
                new Color[]{Color.YELLOW, borderColor, Color.BLUE, Color.GREEN}, borderColor);
        pieces[5] = tip2;

        Center3D center2 = new Center3D(new double[]{0, height/2, circum_radius}, side, inset, outwardOffset,
                new Color[]{Color.YELLOW, borderColor, Color.BLUE, Color.GREEN}, borderColor);
        pieces[6] = center2;

        Edge3D edge4 = new Edge3D(new double[]{-side/2, height/2, inner_radius}, side, inset, outwardOffset,
                new Color[]{Color.YELLOW, borderColor, borderColor, Color.GREEN}, borderColor);
        pieces[7] = edge4;

        Edge3D edge5 = new Edge3D(new double[]{side/2, height/2, inner_radius}, side, inset, outwardOffset,
                new Color[]{borderColor, borderColor, Color.BLUE, Color.GREEN}, borderColor);
        pieces[8] = edge5;

        Tip3D tip3 = new Tip3D(new double[]{-side, height/2, -inner_radius * 2}, side, inset, outwardOffset,
                new Color[]{Color.YELLOW, Color.RED, borderColor, Color.GREEN}, borderColor);
        pieces[9] = tip3;
        Center3D center3 = new Center3D(new double[]{-side/2, height/2, -inner_radius}, side, inset, outwardOffset,
                new Color[]{Color.YELLOW, Color.RED, borderColor, Color.GREEN}, borderColor);
        pieces[10] = center3;
        Edge3D edge6 = new Edge3D(new double[]{0, height/2, -inner_radius * 2}, side, inset, outwardOffset,
                new Color[]{borderColor, Color.RED, borderColor, Color.GREEN}, borderColor);
        pieces[11] = edge6;
        Center3D center4 = new Center3D(new double[]{side/2, height/2, -inner_radius}, side, inset, outwardOffset,
                new Color[]{borderColor, Color.RED, Color.BLUE, Color.GREEN}, borderColor);
        pieces[12] = center4;
        Tip3D tip4 = new Tip3D(new double[]{side, height/2, -inner_radius * 2}, side, inset, outwardOffset,
                new Color[]{borderColor, Color.RED, Color.BLUE, Color.GREEN}, borderColor);
        pieces[13] = tip4;

        for (Group piece : pieces) {
            if (piece != null) {
                root.getChildren().add(piece);
            }
        }
    }
}
