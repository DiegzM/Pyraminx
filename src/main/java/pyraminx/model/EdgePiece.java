package pyraminx.model;

public class EdgePiece extends Piece {
    public EdgePiece(int id, PColor[] stickers) {
        super(id, PieceType.EDGE, stickers);

        if (stickers.length != 2) {
            throw new IllegalArgumentException("Stickers must be of length 2");
        }
    }
    public PColor[] getVisibleColors(int orientation) {
        if (orientation == 0) {
            return stickers.clone();
        } else {
            return new PColor[]{stickers[1], stickers[0]};
        }
    }
}
