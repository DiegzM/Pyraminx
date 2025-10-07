package pyraminx.view;

import javafx.scene.paint.Color;

public class Center3D extends Piece3D {
    // Constructor
    public Center3D(Color[] colors) {
        super(colors);
    }
    // Move by x, y, z
    @Override
    public void move(double x, double y, double z) {}

    // Rotate around axis (x, y, z) by angle
    @Override
    public void rotate(double angle, double x, double y, double z) {}

    // Abstract void to create the 3D shape of the piece
    @Override
    public void createShape() {}
}
