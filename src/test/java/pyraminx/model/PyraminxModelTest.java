package pyraminx.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PyraminxModelTest {

    @Test
    void testInitialSolved() {
        PyraminxModel model = new PyraminxModel();
        assertTrue(model.isSolved(), "Model should start solved");
    }

    @Test
    void testThreeTurnsReturnToSolved() {
        for (PyraminxModel.Face face : PyraminxModel.Face.values()) {
            PyraminxModel model = new PyraminxModel();
            model.rotateFace(face, true);
            assertFalse(model.isSolved(), "After 1 turn, puzzle should not be solved");
            model.rotateFace(face, true);
            assertFalse(model.isSolved(), "After 2 turns, puzzle should not be solved");
            model.rotateFace(face, true);
            assertTrue(model.isSolved(), "After 3 turns, puzzle should be solved again");
        }
    }

    @Test
    void testOppositeRotationsCancel() {
        PyraminxModel model = new PyraminxModel();
        model.rotateFace(PyraminxModel.Face.TOP, true);
        model.rotateFace(PyraminxModel.Face.TOP, false);
        assertTrue(model.isSolved(), "A clockwise + counterclockwise turn should cancel out");
    }

    @Test
    void testDifferentFacesScramble() {
        PyraminxModel model = new PyraminxModel();
        model.rotateFace(PyraminxModel.Face.TOP, true);
        model.rotateFace(PyraminxModel.Face.LEFT, true);
        assertFalse(model.isSolved(), "Two different face turns should scramble the puzzle");
    }
}