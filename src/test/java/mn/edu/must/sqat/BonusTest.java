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



    @Test
    @DisplayName("DU: null attendance, line 31-32T")
    void duNullAttendance() {
        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> DataFlowTargets.bonus(null, 1)
        );
    }

    @Test
    @DisplayName("DU: negative threshold, line 31-32T")
    void duNegativeThreshold() {
        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> DataFlowTargets.bonus(new int[]{1}, -1)
        );
    }

    @Test
    @DisplayName("DU: empty array, present 35-50T, score 51-56")
    void duEmptyThresholdZero() {
        assertEquals(5, DataFlowTargets.bonus(new int[]{}, 0));
    }

    @Test
    @DisplayName("DU: empty array, present 35-50F, score 49-56")
    void duEmptyBelowThreshold() {
        assertEquals(0, DataFlowTargets.bonus(new int[]{}, 1));
    }


    @Test
    @DisplayName("DU: best 43-53F, score 51-56, short streak")
    void duShortStreakBelowFive() {
        int[] attendance = {1, 1, 0, 1};
        assertEquals(5, DataFlowTargets.bonus(attendance, 2));
    }

}
