package pyraminx.model;

/**
 * EdgePiece class representing an edge piece of the Pyraminx puzzle.
 * @param id Unique identifier for the edge piece.
 * @param stickers Array of two PColor enums representing the colors on the edge piece.
 */
public class Edge extends Piece {
    public Edge(int id, PColor[] stickers) {
        super(id, PieceType.EDGE, stickers);

        if (stickers.length != 2) {
            throw new IllegalArgumentException("Stickers must be of length 2");
        }
    }
    @Override
    public PColor[] getVisibleColors(int orientation) {
        if (orientation == 0) {
            return stickers.clone();
        } else {
            return new PColor[]{stickers[1], stickers[0]};
        }
    }
}
