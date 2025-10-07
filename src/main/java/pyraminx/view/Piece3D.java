package pyraminx.view;

// Imports  
import javafx.scene.Group;
import javafx.scene.paint.Color;

/**
 * Abstract class representing a 3D piece of the Pyraminx puzzle.
 */
public abstract class Piece3D extends Group {
    // Fields
    private Color[] colors;

    // Constructor
    public Piece3D(Color[] colors) {
        this.colors = colors;
    }
    // Get colors
    public Color[] getColors() {
        return colors;
    }

    // Move by x, y, z
    public abstract void move(double x, double y, double z);

    // Rotate around axis (x, y, z) by angle
    public abstract void rotate(double angle, double x, double y, double z);

    // Abstract void to create the 3D shape of the piece
    public abstract void createShape();
}
