import org.junit.rules.Timeout;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;

public class IsBoardStringWellFormedTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(1);
    // public Timeout perTestTimeout = Timeout.seconds(0.5);

    private void test(String boardString, Boolean expected) {
        Boolean actual = Vikings.isBoardStringWellFormed(boardString);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testWellFormed() {
        testTrivial();
        for (int i = 0; i < Objective.OBJECTIVES.length; i++) {
            test(Objective.OBJECTIVES[i].getInitialState(), true);
        }
    }

    @Test
    public void testNotWellFormed() {
        testTrivial();
        for (int i = 0; i < Utilities.notWellFormed.length; i++) {
            test(Utilities.notWellFormed[i], false);
        }
    }

    @Test
    public void testBadBoats() {
        for (int i = 0; i < Utilities.badBoats.length; i++) {
            test(Utilities.badBoats[i], false);
        }
    }

    @Test
    public void testTrivial() {
        test(Objective.OBJECTIVES[0].getInitialState(), true);
        test("XXXXXXXXXXXXXXXXXXXX", false);
    }
}
