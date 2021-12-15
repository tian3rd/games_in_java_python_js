import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class LocationsEqualTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(1);

    private void test(Location loc1, Location loc2, boolean expected) {
        boolean result = loc1.isEqual(loc2);
        assertEquals("Expected " + expected + " for location 1: " + loc1 + " and location 2: " + loc2 + ", but got " + result + ".", expected, result);
    }

    @Test
    public void testEqual() {
        test(new Location(1, 2), new Location(1, 2), true);
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                test(new Location(x, y), new Location(x, y), true);
            }
        }
    }

    @Test
    public void testNotEqual() {
        test(new Location(0, 1), new Location(1, 1), false);
        ArrayList<Location> locs = new ArrayList<Location>();
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                locs.add(new Location(x, y));
            }
        }

        for (int i = 0; i < locs.size(); i++) {
            for (int j = 0; j < locs.size(); j++) {
                if (i != j) {
                    test(locs.get(i), locs.get(j), false);
                }
            }
        }
        
    }
}
