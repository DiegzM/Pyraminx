package pyraminx.model;

public abstract class Piece {

    // PieceType Enum to indicate type
    public enum PieceType {
        TIP, EDGE, CENTER
    }

    // Fields
    protected int id;
    protected PieceType type;
    protected PColor[] stickers;
    protected int orientation;



    // Constructor
    public Piece(int id, PieceType type, PColor[] stickers) {
        this.id = id;
        this.type = type;
        this.stickers = stickers;
    }

    // Get ID
    public int getId() {
        return id;
    }

    // Get the piece type
    public PieceType getType() {
        return type;
    }

    // Get the sticker colors of the piece
    public PColor[] getStickers() {
        return stickers;
    }

    // Get the sticker colors of the piece given an orientation
    public abstract PColor[] getVisibleColors(int orientation);

    // Get the current orientation of the piece
    public int getOrientation() {
        return orientation;
    }

    // Set the orientation of the piece
    public void setOrientation(int orientation) {
        this.orientation = orientation % stickers.length;
        if (this.orientation < 0) this.orientation += stickers.length;
    }

    // Rotates the piece
    public void rotateLocal(int steps) {
        setOrientation(this.orientation + steps);
    }

    // Returns current orientation
    public PColor[] getVisibleColors() { return getVisibleColors(this.orientation); }

    // Print the information about the piece
    @Override
    public String toString() {
        return type + " piece #" + id;
    }
}
