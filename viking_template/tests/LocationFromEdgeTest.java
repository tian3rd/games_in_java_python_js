import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class LocationFromEdgeTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(1);

    private void test(char edge, Location trueLocation, boolean expected) {
        Location loc = Location.fromEdge(edge);
        assertEquals(expected, loc.getX() == trueLocation.getX() && loc.getY() == trueLocation.getY());
    }

    @Test
    public void testAll() {
        for (int i = 0; i < 24; i++) {
            test((char) ('a' + i), locations[i], true);
        }
    }

    @Test
    public void testWrongLocations() {
        testTrivial();
        for (int i = 0; i < 24; i++) {
            char edge = (char) ('a' + i);
            for (int j = 0; j < 24; j++) {
                if (i != j) {
                    test(edge, locations[j], false);
                }
            }
        }
    }

    @Test
    public void testNonLocations() {
        for (int x = 0; x < 7; x += 2) {
            for (int y = 0; y < 7; y += 2) {
                Location nonLocation = new Location(x, y);
                for (int i = 0; i < 24; i++) {
                    char edge = (char) ('a' + i);
                    test(edge, nonLocation, false);
                }
            }
        }
    }

    @Test
    public void testTrivial() {
        test('a', new Location(1, 0), true);
    }

    private Location[] locations = {
        // in alphabetical order
        new Location(1, 0), // edge a
        new Location(3, 0),
        new Location(5, 0),
        new Location(0, 1), // edge d
        new Location(2, 1),
        new Location(4, 1),
        new Location(6, 1),
        new Location(1, 2), // edge h
        new Location(3, 2),
        new Location(5, 2),
        new Location(0, 3), // edge k
        new Location(2, 3),
        new Location(4, 3),
        new Location(6, 3),
        new Location(1, 4), // edge o
        new Location(3, 4),
        new Location(5, 4),
        new Location(0, 5), // edge r
        new Location(2, 5),
        new Location(4, 5),
        new Location(6, 5),
        new Location(1, 6), // edge v
        new Location(3, 6),
        new Location(5, 6)  // edge x
};
}
