public class Boat {
    public static int NUM_BOATS;

    private final Colour color;
    private Location location;

    public Boat(Colour color, Location location) {
        this.color = color;
        this.location = location;
    }

    public Colour getColour() {
        return color;
    }
    
    public Location getLocation() {
        return location;
    }

    /**
     * @param boatString A two-character string representing the boat's color and location, e.g., "Rx"
     * @return A Boat instance with the specified color and location
     */
    public static Boat fromString(String boatString) {
        char colorChar = boatString.charAt(0);
        char locationChar = boatString.charAt(1);
        Colour color = Colour.RED;
        Location location = Location.fromEdge(locationChar);
        switch (colorChar) {
            case 'B':
                color = Colour.BLUE;
                break;
            case 'G':
                color = Colour.GREEN;
                break;
            case 'Y':
                color = Colour.YELLOW;
                break;
        }
        return new Boat(color, location);
    }

    /**
     * @param boardString A string representing the board
     * @return An array of all Boats found on the board
     */
    public static Boat[] fromBoardString(String boardString) {
        String boatString = boardString.substring(18);
        NUM_BOATS = boatString.length() / 2;
        Boat[] boats = new Boat[NUM_BOATS];
        for (int i = 0; i < NUM_BOATS; i++) {
            boats[i] = Boat.fromString(boatString.substring(i * 2, i * 2 + 2));
        }
        return boats;
    }

    @Override
    public String toString() {
        return "" + color.toChar() + location.toEdge();
    }
}
