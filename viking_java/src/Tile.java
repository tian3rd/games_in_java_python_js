/**
 * A tile on the Vikings game board
 */
public class Tile {
    public static int NUM_POSITIONS = 8;

    private final TileType tileType;

    private final int position;

    private int orientation;

    public Tile(TileType tileType, int orientation, int position) {
        this.tileType = tileType;
        this.orientation = orientation;
        this.position = position;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public TileType getTileType() {
        return tileType;
    }

    public int getPosition() {
        return position;
    }

    /**
     * Decode a tile string to create a tile
     * E.g., Tile("N1", 4) returns a Tile with tileType N, orientation 1 and position 4
     * 
     * @param tileString Two character string representing the tile type and orientation, e.g., N0
     * @param position The position of the tile on the board (0-8)
     * @return A tile instance with Tile type, orientation and position as specified
     */
    public static Tile fromString(String tileString, int position) {
        char tileTypeChar = tileString.charAt(0);
        int orientation = Character.getNumericValue(tileString.charAt(1));
        TileType type;
        if (tileTypeChar == 'N') {
            type = TileType.N;
        } else {
            type = TileType.O;
        }
        return new Tile(type, orientation, position);
    }

    /**
     * @param boardString A well formed board string representing a current state of the board
     * @return An array of tiles representing the board
     */
    public static Tile[] fromBoardString(String boardString) {
        Tile[] tiles = new Tile[NUM_POSITIONS];
        for (int i = 0; i < NUM_POSITIONS; i++) {
            tiles[i] = fromString(boardString.substring(i * 2, i * 2 + 2), i);
        }
        return tiles;
    }

    @Override
    public String toString() {
        return "Tile " + tileType + " position " + position + " orientation " + orientation;
    }
    
}
