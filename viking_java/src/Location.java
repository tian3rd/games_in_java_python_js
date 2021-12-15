public class Location {
    static final int INVALID = -1;
    private final int x;
    private final int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * @param edge A character representing an edge
     * @return Then location of the given edge
     */
    public static Location fromEdge(char edge) {
        // FIXME: Task 4
        int edgeIndex = edge - 'a';
        int m = (int) edgeIndex / 7;
        int n = (int) edgeIndex % 7;
        int x, y;
        if (n < 3) {
            x = 2 * n + 1;
            y = 2 * m;
        } else {
            x = 2 * n - 6;
            y = 2 * m + 1;
        }
        return new Location(x, y);
    }

    public char toEdge() {
        int x = this.getX();
        int y = this.getY();
        int index = (int) ((7 / 2.0) * y + (x - 1) / 2.0);
        char edge = (char) (index + 'a');
        return edge;
    }

    /**
     * @param positon An int representing a tile position (0 - 8)
     * @return An array of locations of that tile position
     */
    public static Location[] fromPosition(int position) {
        Location[] locations = new Location[4];
        String edges = "";

        switch (position) {
            case 0:
                edges = "adeh";
                break;
            case 1:
                edges = "beif";
                break;
            case 2:
                edges = "cfjg";
                break;
            case 3:
                edges = "hkol";
                break;
            case 4:
                edges = "ilpm";
                break;
            case 5: 
                edges = "jmqn";
                break;
            case 6:
                edges = "orvs";
                break;
            case 7:
                edges = "pswt";
                break;
            case 8:
                edges = "qtxu";
                break;
        }

        for (int i = 0; i < edges.length(); i++) {
            locations[i] = fromEdge(edges.charAt(i));
        }

        return locations;
    }

    /**
     * @param loc Another Location object
     * @return True if the Locations are the same
     */
    public boolean isEqual(Location loc) {
        // FIXME: Task 2
        return this.getX() == loc.getX() && this.getY() == loc.getY();
    }
}
