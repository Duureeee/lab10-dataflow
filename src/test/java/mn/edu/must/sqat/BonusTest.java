package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BonusTest {

    @Test
    @DisplayName("AllDefs: threshold met, streak 46-41, score 51-54-56")
    void allDefsThresholdMet() {
        int[] attendance = {1, 1, 1, 1, 1, 0, 1};
        assertEquals(8, DataFlowTargets.bonus(attendance, 5));
    }

    @Test
    @DisplayName("AllDefs: threshold missed, score 49-54-56")
    void allDefsThresholdMissed() {
        int[] attendance = {0, 1, 1, 1, 1, 1};
        assertEquals(3, DataFlowTargets.bonus(attendance, 7));
    }


}
