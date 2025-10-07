package pyraminx.model;

/**
 * CenterPiece class representing a center piece of the Pyraminx puzzle.
 * @param id Unique identifier for the center piece.
 * @param stickers Array of three PColor enums representing the colors on the center piece.
 */
public class Center extends Piece {
    public Center(int id, PColor[] stickers) {
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
