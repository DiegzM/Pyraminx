package pyraminx.model;

public class PyraminxModel {
    private final int[] state;
    private final int stickersPerFace;

    public PyraminxModel(int layers) {
        this.stickersPerFace = (int)Math.pow(layers, 2);
        this.state = new int[(int)(stickersPerFace * 4)];
        for (int i = 0; i < layers; i++) {
            for (int j = 0; j < (int)Math.pow(layers, 2); j++) {
                state[(this.stickersPerFace * i) + j] = i;
            }
        }
    }
}
