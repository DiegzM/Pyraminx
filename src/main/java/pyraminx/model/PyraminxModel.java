package pyraminx.model;

public class PyraminxModel {

    // Counts
    public static final int TIP_COUNT = 4;
    public static final int EDGE_COUNT = 6;
    public static final int CENTER_COUNT = 4;

    // Tip slots
    public static final int TIP_TOP = 0;
    public static final int TIP_LEFT = 1;
    public static final int TIP_RIGHT = 2;
    public static final int TIP_BACK = 3;

    // Edge slots
    public static final int EDGE_FRONT_LEFT   = 0;
    public static final int EDGE_FRONT_RIGHT  = 1;
    public static final int EDGE_FRONT_BOTTOM = 2;
    public static final int EDGE_RIGHT_BOTTOM = 3;
    public static final int EDGE_BACK_BOTTOM  = 4;
    public static final int EDGE_LEFT_BOTTOM  = 5;

    // Center slots
    public static final int CENTER_TOP   = 0;
    public static final int CENTER_LEFT  = 1;
    public static final int CENTER_RIGHT = 2;
    public static final int CENTER_BACK  = 3;

    // --- Corrected face cycles ---
    private static final int[] TOP_TIPS   = { TIP_TOP, TIP_LEFT, TIP_RIGHT };
    private static final int[] TOP_EDGES  = { EDGE_FRONT_LEFT, EDGE_FRONT_RIGHT, EDGE_FRONT_BOTTOM };
    private static final int[] TOP_CENTER = { CENTER_TOP };

    private static final int[] LEFT_TIPS   = { TIP_TOP, TIP_BACK, TIP_LEFT };
    private static final int[] LEFT_EDGES  = { EDGE_FRONT_LEFT, EDGE_LEFT_BOTTOM, EDGE_BACK_BOTTOM };
    private static final int[] LEFT_CENTER = { CENTER_LEFT };

    private static final int[] RIGHT_TIPS   = { TIP_TOP, TIP_RIGHT, TIP_BACK };
    private static final int[] RIGHT_EDGES  = { EDGE_FRONT_RIGHT, EDGE_RIGHT_BOTTOM, EDGE_BACK_BOTTOM };
    private static final int[] RIGHT_CENTER = { CENTER_RIGHT };

    private static final int[] BACK_TIPS   = { TIP_TOP, TIP_LEFT, TIP_RIGHT };
    private static final int[] BACK_EDGES  = { EDGE_FRONT_BOTTOM, EDGE_LEFT_BOTTOM, EDGE_RIGHT_BOTTOM };
    private static final int[] BACK_CENTER = { CENTER_BACK };

    public enum Face { TOP, LEFT, RIGHT, BACK }

    // Fields
    private final Tip[] tips;
    private final Edge[] edges;
    private final Center[] centers;

    public PyraminxModel() {
        tips = new Tip[TIP_COUNT];
        edges = new Edge[EDGE_COUNT];
        centers = new Center[CENTER_COUNT];
        initializePieces();
    }

    private void initializePieces() {
        // Tips
        tips[TIP_TOP]   = new Tip(TIP_TOP,   new PColor[]{PColor.RED, PColor.GREEN, PColor.BLUE});
        tips[TIP_LEFT]  = new Tip(TIP_LEFT,  new PColor[]{PColor.RED, PColor.YELLOW, PColor.GREEN});
        tips[TIP_RIGHT] = new Tip(TIP_RIGHT, new PColor[]{PColor.RED, PColor.BLUE, PColor.YELLOW});
        tips[TIP_BACK]  = new Tip(TIP_BACK,  new PColor[]{PColor.GREEN, PColor.YELLOW, PColor.BLUE});

        // Edges
        edges[EDGE_FRONT_LEFT]   = new Edge(EDGE_FRONT_LEFT,   new PColor[]{PColor.RED, PColor.GREEN});
        edges[EDGE_FRONT_RIGHT]  = new Edge(EDGE_FRONT_RIGHT,  new PColor[]{PColor.RED, PColor.BLUE});
        edges[EDGE_FRONT_BOTTOM] = new Edge(EDGE_FRONT_BOTTOM, new PColor[]{PColor.RED, PColor.YELLOW});
        edges[EDGE_RIGHT_BOTTOM] = new Edge(EDGE_RIGHT_BOTTOM, new PColor[]{PColor.BLUE, PColor.YELLOW});
        edges[EDGE_BACK_BOTTOM]  = new Edge(EDGE_BACK_BOTTOM,  new PColor[]{PColor.GREEN, PColor.YELLOW});
        edges[EDGE_LEFT_BOTTOM]  = new Edge(EDGE_LEFT_BOTTOM,  new PColor[]{PColor.GREEN, PColor.BLUE});

        // Centers
        centers[CENTER_TOP]   = new Center(CENTER_TOP,   new PColor[]{PColor.RED, PColor.GREEN, PColor.BLUE});
        centers[CENTER_LEFT]  = new Center(CENTER_LEFT,  new PColor[]{PColor.RED, PColor.YELLOW, PColor.GREEN});
        centers[CENTER_RIGHT] = new Center(CENTER_RIGHT, new PColor[]{PColor.RED, PColor.BLUE, PColor.YELLOW});
        centers[CENTER_BACK]  = new Center(CENTER_BACK,  new PColor[]{PColor.GREEN, PColor.YELLOW, PColor.BLUE});
    }

    // --- Rotation helpers ---
    private <T extends Piece> void cycle(T[] arr, int[] indices, boolean cw) {
        if (cw) {
            T temp = arr[indices[indices.length - 1]];
            for (int i = indices.length - 1; i > 0; i--) {
                arr[indices[i]] = arr[indices[i - 1]];
            }
            arr[indices[0]] = temp;
        } else {
            T temp = arr[indices[0]];
            for (int i = 0; i < indices.length - 1; i++) {
                arr[indices[i]] = arr[indices[i + 1]];
            }
            arr[indices[indices.length - 1]] = temp;
        }
    }

    private void applyOrientation(Piece[] arr, int[] indices, int delta) {
        for (int idx : indices) arr[idx].rotateLocal(delta);
    }


    public void rotateFace(Face face, boolean clockwise) {
        switch (face) {
            case TOP   -> rotate(TOP_TIPS, TOP_EDGES, TOP_CENTER, clockwise);
            case LEFT  -> rotate(LEFT_TIPS, LEFT_EDGES, LEFT_CENTER, clockwise);
            case RIGHT -> rotate(RIGHT_TIPS, RIGHT_EDGES, RIGHT_CENTER, clockwise);
            case BACK  -> rotate(BACK_TIPS, BACK_EDGES, BACK_CENTER, clockwise);
        }
    }

    private void rotate(int[] tipCycle, int[] edgeCycle, int[] centerCycle, boolean cw) {
        cycle(tips, tipCycle, cw);
        cycle(edges, edgeCycle, cw);
        cycle(centers, centerCycle, cw);

        // Orientation updates
        applyOrientation(tips, tipCycle, cw ? 1 : -1);
        applyOrientation(centers, centerCycle, cw ? 1 : -1);

        // Edges: no orientation change on face turns
    }



    // --- Solved check ---
    public boolean isSolved() {
        for (int i = 0; i < TIP_COUNT; i++)
            if (tips[i].getId() != i || tips[i].getOrientation() != 0) return false;
        for (int i = 0; i < EDGE_COUNT; i++)
            if (edges[i].getId() != i || edges[i].getOrientation() != 0) return false;
        for (int i = 0; i < CENTER_COUNT; i++)
            if (centers[i].getId() != i || centers[i].getOrientation() != 0) return false;
        return true;
    }

    // --- Debug printer ---
    public void printState() {
        System.out.println("Tips:");
        for (int i = 0; i < TIP_COUNT; i++) {
            System.out.println("  " + tips[i]);
        }
        System.out.println("Edges:");
        for (int i = 0; i < EDGE_COUNT; i++) {
            System.out.println("  " + edges[i]);
        }
        System.out.println("Centers:");
        for (int i = 0; i < CENTER_COUNT; i++) {
            System.out.println("  " + centers[i]);
        }
    }

}