package pyraminx.view;

import javafx.geometry.Point3D;
// Imports  
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Mesh;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.paint.PhongMaterial;

/**
 * Abstract class representing a 3D piece of the Pyraminx puzzle.
 */
public abstract class Piece3D extends Group {
    // Fields
    protected Color borderColor = Color.BLACK; // Default border color
    protected float side;
    protected float inset;
    protected float outwardOffset;

    protected Color[] colors;
    protected float height;
    protected MeshView mesh;

    // Constructor
    public Piece3D(double[] position, float side, float inset, float outwardOffset, Color[] colors, Color borderColor) {
        this.colors = colors;
        this.side = side;
        this.inset = inset;
        this.outwardOffset = outwardOffset;
        this.borderColor = borderColor;
        // Set initial position
        this.setTranslateX(position[0]);
        this.setTranslateY(position[1]);
        this.setTranslateZ(position[2]);
    }
    // Get colors
    public Color[] getColors() {
        return colors;
    }

    // Move by x, y, z
    public void move(double x, double y, double z) {
        this.setTranslateX(this.getTranslateX() + x);
        this.setTranslateY(this.getTranslateY() + y);
        this.setTranslateZ(this.getTranslateZ() + z);
    }

    // Get the points of the piece
    public Point3D[] getPoints() {
        if (this.mesh == null) return null;
        if (!(this.mesh.getMesh() instanceof TriangleMesh)) return null;

        TriangleMesh triMesh = (TriangleMesh) this.mesh.getMesh();

        float[] points = triMesh.getPoints().toArray(null);
        Point3D[] point3Ds = new Point3D[points.length / 3];
        for (int i = 0; i < points.length; i += 3) {
            point3Ds[i / 3] = new Point3D(points[i], points[i + 1], points[i + 2]);
        }
        return point3Ds;
    }
    // Rotate around axis (x, y, z) by angle
    public abstract void rotate(double angle, double x, double y, double z);

    // Abstract void to create the 3D shape of the piece
    protected abstract void createShape();

    // Abstract void to create the faces with inset and border
    protected abstract void createFaces(float inset);

    // Get the points of a face from a face index
    protected Point3D[] extractFacePoints(TriangleMesh mesh, int faceIndex) {
        float[] points = mesh.getPoints().toArray(null);
        int[] faces = mesh.getFaces().toArray(null);

        int offset = faceIndex * 6;
        int i0 = faces[offset] * 3;
        int i1 = faces[offset + 2] * 3;
        int i2 = faces[offset + 4] * 3;

        return new Point3D[]{
            new Point3D(points[i0], points[i0 + 1], points[i0 + 2]),
            new Point3D(points[i1], points[i1 + 1], points[i1 + 2]),
            new Point3D(points[i2], points[i2 + 1], points[i2 + 2])
        };
    }

    // Get the insetted points of a face
    
    protected Point3D[] insetFacePoints(Point3D[] originalPts, float inset, float outwardOffset) {
        // Extract points
        Point3D p0 = originalPts[0];
        Point3D p1 = originalPts[1];
        Point3D p2 = originalPts[2];
        
        // Compute centroid
        Point3D centroid = new Point3D(
            (p0.getX() + p1.getX() + p2.getX()) / 3.0,
            (p0.getY() + p1.getY() + p2.getY()) / 3.0,
            (p0.getZ() + p1.getZ() + p2.getZ()) / 3.0
        );

        // Compute face normal
        Point3D v1 = p1.subtract(p0);
        Point3D v2 = p2.subtract(p0);
        Point3D normal = v1.crossProduct(v2).normalize().multiply(outwardOffset);

        // Inset points towards centroid and offset outward
        Point3D insetP0 = p0.add(centroid.subtract(p0).normalize().multiply(inset)).add(normal);
        Point3D insetP1 = p1.add(centroid.subtract(p1).normalize().multiply(inset)).add(normal);
        Point3D insetP2 = p2.add(centroid.subtract(p2).normalize().multiply(inset)).add(normal);


        // Return insetted points
        return new Point3D[]{insetP0, insetP1, insetP2};
    }

    protected MeshView createStickerFace(Point3D[] points, Color color) {
        TriangleMesh stickerMesh = new TriangleMesh();

        // Add points to the mesh
        float[] pts = new float[]{
            (float) points[0].getX(), (float) points[0].getY(), (float) points[0].getZ(),
            (float) points[1].getX(), (float) points[1].getY(), (float) points[1].getZ(),
            (float) points[2].getX(), (float) points[2].getY(), (float) points[2].getZ()
        };
        stickerMesh.getPoints().addAll(pts);

        // Define texture coordinates (dummy values)
        stickerMesh.getTexCoords().addAll(0, 0);

        // Define the face of the sticker
        stickerMesh.getFaces().addAll(0, 0, 1, 0, 2, 0);

        // Create material for the sticker
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(color);

        // Create MeshView for the sticker
        MeshView sticker = new MeshView(stickerMesh);
        sticker.setMaterial(material);

        return sticker;
    }

    public float getHeight() {
        return height;
    }
}
