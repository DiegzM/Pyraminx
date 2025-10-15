package pyraminx.model;

/**
 *
 *
 *
 */

public class PyraminxModel {

    // Constants
    public static final int TIP_COUNT = 4;
    public static final int EDGE_COUNT = 6;
    public static final int CENTER_COUNT = 4;

    // Tip Constants
    public static final int TIP_TOP = 0;
    public static final int TIP_LEFT = 1;
    public static final int TIP_RIGHT = 2;
    public static final int TIP_BACK = 3;

    // Edge Constants
    public static final int EDGE_FRONT_LEFT    = 0; 
    public static final int EDGE_FRONT_RIGHT   = 1; 
    public static final int EDGE_FRONT_BOTTOM  = 2; 
    public static final int EDGE_RIGHT_BOTTOM  = 3; 
    public static final int EDGE_BACK_BOTTOM   = 4; 
    public static final int EDGE_LEFT_BOTTOM   = 5;

    // Center Constants
    public static final int CENTER_TOP = 0;
    public static final int CENTER_LEFT = 1;
    public static final int CENTER_RIGHT = 2;
    public static final int CENTER_BACK = 3;

    // Fields
    private Tip[] tips;
    private Edge[] edges;
    private Center[] centers;
    private int[] orientations;
    
    public PyraminxModel() {
        tips = new Tip[4];
        edges = new Edge[6];
        centers = new Center[4];
    }
    private void initializePieces() {
        
    }
}
