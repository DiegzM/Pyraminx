package pyraminx.view;

// Imports
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.shape.Box;

/**
 * Class representing a 3D tip piece of the Pyraminx puzzle.
 */

public class Tip3D extends Piece3D {
    // Fields
    private MeshView mesh;
    private Color borderColor = Color.WHITE; // Default border color

    // Constructor
    public Tip3D(Color[] colors) {
        super(colors);
        createShape();
    }
    
    // Override the abstract move method
    @Override
    public void move(double x, double y, double z) {
        // Move the piece by updating its translation properties
        this.setTranslateX(this.getTranslateX() + x);
        this.setTranslateY(this.getTranslateY() + y);
        this.setTranslateZ(this.getTranslateZ() + z);
    }

    // Override the abstract rotate method
    @Override
    public void rotate(double angle, double x, double y, double z) {

    }
    
    // Create the pyramid shape with triangular faces and black borders
    @Override
    public void createShape() {
        // HARDCODE (temporary): create a piece size 20 and inset 2
        float side = 20f;
        float h = (float) (side * Math.sqrt(6f) / 3f); // height of tetrahedron
        float r = (float) (side * Math.sqrt(3f) / 3f); // radius of circumscribed circle of base
        
        // Create the tetrahedron mesh
        TriangleMesh triangleMesh = new TriangleMesh();

        // Create the points (vertices) of the pyramid
        float[] pts = new float[] {
            0,  -h,  0,                                         // apex
            0,  0,  r,                                         // base 1 (back)
            (float)(r * -Math.sqrt(3f) / 2f), 0, (-r/2),   // base 2 (front left)
            (float)(r * Math.sqrt(3f) / 2f), 0, (-r/2)     // base 3 (front right)
        };

        // Add points (vertices) to the mesh
        triangleMesh.getPoints().addAll(pts);

        // Define texture coordinates (dummy values)
        triangleMesh.getTexCoords().addAll(0, 0);

        // Define the faces of the pyramid (triangles)
        triangleMesh.getFaces().addAll(
            0, 0, 2, 0, 1, 0, // Face 1 (apex, base vertex 2, base vertex 1)
            0, 0, 1, 0, 3, 0, // Face 2 (apex, base vertex 1, base vertex 3)
            0, 0, 3, 0, 2, 0, // Face 3 (apex, base vertex 3, base vertex 2)
            1, 0, 2, 0, 3, 0 // Base face (base vertex 1, base vertex 2, base vertex 3)
        );

        // Add black base color to the whole mesh
        PhongMaterial baseMaterial = new PhongMaterial();
        baseMaterial.setDiffuseColor(borderColor);
        
        // Create meshview and add to group
        float centroidOffset = h / 4f;

        this.mesh = new MeshView(triangleMesh);
        this.mesh.setMaterial(baseMaterial);

        Sphere pivot = new Sphere(2);
        pivot.setMaterial(new PhongMaterial(Color.YELLOW));
        pivot.setTranslateY(-30);
        pivot.setTranslateX(0);
        pivot.setTranslateZ(0);

        // add box 20 size to test
        Box box = new Box(20, 20, 20);
        box.setMaterial(new PhongMaterial(Color.PURPLE));
        box.setTranslateY(0);
        box.setTranslateX(0);
        box.setTranslateZ(0);

        this.getChildren().addAll(box, pivot);
    }

}
