package pyraminx.model;

/**
 *
 *
 *
 */
public class PyraminxModel {
    private Piece[] pieces;
    private int[] orientaions;

    public PyraminxModel() {
        pieces = new Piece[14];
    }

    private void initializePieces() {
        // Tip Pieces
        pieces[0] = new TipPiece(0, new PColor[]{PColor.RED,  PColor.BLUE, PColor.YELLOW}); // TOP TIP
        pieces[1] = new TipPiece(1,  new PColor[]{PColor.RED, PColor.YELLOW, PColor.GREEN}); // FRONT LEFT TIP
        pieces[2] = new TipPiece(2,  new PColor[]{PColor.RED, PColor.GREEN, PColor.BLUE}); // FRONT RIGHT TIP
        pieces[3] = new TipPiece(3,  new PColor[]{PColor.GREEN, PColor.YELLOW, PColor.BLUE}); // BACK TIP

        // Edge Pieces
        // Center Pieces

    }
}

