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

    // Print the information about the piece
    @Override
    public String toString() {
        return type + " piece #" + id;
    }
}
