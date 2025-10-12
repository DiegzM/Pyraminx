package pyraminx.view;

// Imports
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.geometry.Point3D;

/**
 * Class representing a 3D tip piece of the Pyraminx puzzle.
 */

public class Center3D extends Piece3D {

    // Constructor
    public Center3D(double[] position, float side, float inset, float outwardOffset, Color[] colors, Color borderColor) {
        super(position, side, inset, outwardOffset, colors, borderColor);
        createShape();
    }

    // Override the abstract rotate method
    @Override
    public void rotate(double angle, double x, double y, double z) {

    }
    
    // Create the pyramid shape with triangular faces and black borders
    @Override
    protected void createShape() {
        float h = (float) (side * Math.sqrt(6f) / 3f); // height of tetrahedron
        float r = (float) (side * Math.sqrt(3f) / 3f); // radius of circumscribed circle of base
        float bottom_radius = (float) (side * Math.sqrt(3f) / 6f) * 2f; // inner radius of bottom triangle
        // Set height field
        this.height = h;
        
        // Create the tetrahedron mesh
        TriangleMesh triangleMesh = new TriangleMesh();

        // Create the points (vertices) of the pyramid
        float[] pts = new float[] {
            0,  -h/2,  r,                                                   // Top vertex 1 (back)
            (float)(-Math.sqrt(3f) / 2f * r),  -h/2, (-r/2),            // Top vertex 2 (left)
            (float)(Math.sqrt(3f) / 2f * r),  -h/2, (-r/2),            // Top vertex 3 (right)
            (float)(-Math.sqrt(3f) / 2f * bottom_radius), h/2, (bottom_radius / 2f) ,   // Bottom vertex 1 (left)
            0,   h/2,  -bottom_radius,                                                  // Bottom vertex 2 (front)
            (float)(Math.sqrt(3f) / 2f * bottom_radius),  h/2, (bottom_radius / 2f)   // Bottom vertex 3 (right)
        };

        // Add points (vertices) to the mesh
        triangleMesh.getPoints().addAll(pts);

        // Define texture coordinates (dummy values)
        triangleMesh.getTexCoords().addAll(0, 0);

        // Define the faces of the pyramid (triangles)
        triangleMesh.getFaces().addAll(
            0,0, 1,0, 2,0,                  // Top
            3,0, 5,0, 4,0,                  // Bottom

            0,0, 3,0, 1,0,                  // Left (COLOR)
            1,0, 3,0, 4,0,                  // Left (BORDER)

            1,0, 4,0, 2,0,                  // Front (COLOR)
            2,0, 4,0, 5,0,                  // Front (BORDER)

            2,0, 5,0, 0,0,                   // Right (COLOR)
            0,0, 5,0, 3,0                   // Right (BORDER)

        );

        // Add black base color to the whole mesh
        PhongMaterial baseMaterial = new PhongMaterial();
        baseMaterial.setDiffuseColor(borderColor);
        
        // Create meshview and add to group
        this.mesh = new MeshView(triangleMesh);
        this.mesh.setMaterial(baseMaterial);
        this.getChildren().add(this.mesh);

        // Create sticker faces
        createFaces(inset);
    }

    // Create sticker faces
    @Override
    protected void createFaces(float inset) {
        if (this.mesh == null) return;

        // Get the faces of this.mesh
        TriangleMesh mesh = (TriangleMesh) this.mesh.getMesh();

        // Loop through each point of the mesh and create inset faces
        for (int face = 0; face < mesh.getFaces().size() / 6; face++) {
            // Get original points from mesh by face
            Point3D[] originalPts = extractFacePoints(mesh, face);

            // Inset the face points
            Point3D[] insetPts = insetFacePoints(originalPts, inset, outwardOffset);

            // Get color
            Color stickerColor;

            switch (face) {
                case 2 -> stickerColor = colors[0]; // Left
                case 4 -> stickerColor = colors[1]; // Front
                case 6 -> stickerColor = colors[2]; // Right
                case 1 -> stickerColor = colors[3]; // Base
                default -> stickerColor = borderColor; // Top and Bottom
            };

            // Build and add the sticker
            MeshView sticker = createStickerFace(insetPts, stickerColor);
            this.getChildren().add(sticker);
        }

    }

}
