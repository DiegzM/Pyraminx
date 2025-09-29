package pyraminx.model;

public class CenterPiece extends Piece {
    public CenterPiece(int id, PColor[] stickers) {
        super(id, PieceType.CENTER, stickers);

        if (stickers.length != 3) {
            throw new IllegalArgumentException("Stickers must be of length 3");
        }
    }
    public PColor[] getVisibleColors(int orientation) {
        PColor[] rotated = new PColor[3];
        for (int i = 0; i < rotated.length; i++) {
            rotated[i] = stickers[(i + orientation) % 3];
        }
        return rotated;
    }
}
