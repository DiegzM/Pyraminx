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
            model.printState();
        }
    }

    @Test
    void testOppositeRotationsCancel() {
        PyraminxModel model = new PyraminxModel();
        model.rotateFace(PyraminxModel.Face.TOP, true);
        model.rotateFace(PyraminxModel.Face.TOP, false);
        assertTrue(model.isSolved(), "A clockwise + counterclockwise turn should cancel out");
        model.printState();
    }

    @Test
    void testDifferentFacesScramble() {
        PyraminxModel model = new PyraminxModel();
        model.rotateFace(PyraminxModel.Face.TOP, true);
        model.rotateFace(PyraminxModel.Face.LEFT, true);
        assertFalse(model.isSolved(), "Two different face turns should scramble the puzzle");
        model.printState();
    }

    @Test
    void testFourTurnsEquivalentToOneCounterClockwise() {
        for (PyraminxModel.Face face : PyraminxModel.Face.values()) {
            PyraminxModel model1 = new PyraminxModel();
            PyraminxModel model2 = new PyraminxModel();

            // 4 clockwise turns
            for (int i = 0; i < 4; i++) {
                model1.rotateFace(face, true);
            }

            // 1 counterclockwise turn
            model2.rotateFace(face, false);

            assertEquals(model1.isSolved(), model2.isSolved(),
                    "Four clockwise turns of " + face + " should equal one counterclockwise turn");
        }
    }

    @Test
    void testFullCycleOfAllFaces() {
        PyraminxModel model = new PyraminxModel();
        // Rotate each face 3 times (full cycle)
        for (PyraminxModel.Face face : PyraminxModel.Face.values()) {
            for (int i = 0; i < 3; i++) {
                model.rotateFace(face, true);
            }
        }
        assertTrue(model.isSolved(), "Three turns of every face should return to solved");
    }


}