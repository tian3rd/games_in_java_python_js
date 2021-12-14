/**
 * This class represents the Vikings - Brainstorm game.
 * <p>
 * The board state is represented by a single string.
 * The first 18 characters represent the placement of the nine tiles in
 * order from left to right and top to bottom.
 * <p>
 * 0   1   2
 * 3   4   5
 * 6   7   8
 * <p>
 * The two tile types are represented by the uppercase characters 'N'
 * and 'O', with the four rotations represented by the digits 0-3.
 * The four boats are represented by an uppercase character which is the
 * first letter of the name of the colour i.e. 'B','G','R','Y'.
 * Edges are represented by a lowercase character 'a'-'x'.
 * <p>
 * <pre>
 *   a   b   c
 * d 0 e 1 f 2 g
 *   h   i   j
 * k 3 l 4 m 5 n
 *   o   p   q
 * r 6 s 7 t 8 u
 *   v   w   x
 * </pre>
 * <p>
 * Thus the string 'Bl' represents a blue boat at edge 'l' (in between
 * tiles '3' and '4'.
 * <p>
 * The first objective in the game book is
 * "N0O1N1N0O0O1N0N3N1Rt", "Rv"
 * Where "N0O1N1N0O0O1N0N3N1Rt" represents the initial boardState and "Rv" represents the objective (ie:
 * the red boat get to edge v)
 */
public class Vikings {
    /**
     * An array of the nine tiles on the Vikings game board.
     * Since this data structure reflects the board state,
     * it is initially empty until we initialize a game.
     */
    final Tile[] tiles;

    /**
     * An array of boats for the current game,
     * containing at least 1 and no more than 4 boats.
     */
    final Boat[] boats;

    /**
     * The objective of this game
     */
    private Objective objective;

    public Vikings(Objective objective) {
        this.objective = objective;
        String boardString = objective.getInitialState();
        tiles = Tile.fromBoardString(boardString);
        boats = Boat.fromBoardString(boardString);
    }

    /**
     * Construct a game for a given level of difficulty.
     * This chooses a new objective and creates a new instance of
     * the game at the given level of difficulty.
     *
     * @param difficulty The difficulty of the game.
     */
    public Vikings(int difficulty) {
        this(Objective.newObjective(difficulty));
    }

    public Objective getObjective() {
        return objective;
    }

    /**
     * A boardString is well-formed if it contains:
     * - nine tiles, each with:
     * - a correct type for each tile;
     * - the correct rotation of tiles (0-3);
     * - at least 1 boat at a valid edge; and
     * - no more than 1 boat of each colour, in order B-G-R-Y.
     * Note: a well-formed boardString is not necessarily a valid boardString!
     * (For example, tiles and boats may overlap.)
     *
     * @param boardString A string representing the board
     * @return true if boardString is well-formed, false if boardString is not well-formed.
     */
    public static boolean isBoardStringWellFormed(String boardString) {
        // FIXME Task 3
        return false;
    }

    /**
     * @param boardString a well-formed board string
     * @param position1   Position of tile 1
     * @param position2   Position of tile 2
     * @return true if Tiles overlap, False if tiles do not overlap
     */
    public static boolean doTilesOverlap(String boardString, int position1, int position2) {
        // FIXME Task 6
        return false;
    }

    /**
     * A board string is valid if it is well-formed,
     * there are no two boats on the same edge, and no two pieces overlapping.
     *
     * @param boardString a board string
     * @return True if valid, false if invalid.
     */
    public static boolean isBoardStringValid(String boardString) {
        // FIXME Task 7
        return false;
    }

    /**
     * Given two adjacent tiles, decide whether they interlock.
     * Two tiles interlock if there is no gap on the edge between them.
     * For example: in OBJECTIVES[0], Tile "O0" in position "4" interlocks with Tile "O1" in position "2".
     * However, Tile "O0" in position "4" does not interlock with Tile "N1" in position "3".
     *
     * @param position1 int representing the position of tile 1
     * @param position2 int representing the position of tile 2
     * @return True if tiles interlock, False if they do not interlock.
     */
    public static boolean doTilesInterlock(String boardString, int position1, int position2) {
        // FIXME Task 8
        return false;
    }

    /**
     * Returns true if the given tile is currently able to be rotated
     * i.e. it has a boat on at least one edge, and its rotation is not
     * blocked by any other tile.
     *
     * @param boardString a valid board string
     * @param position    '0'-'8' represents the position of the tile to be rotated
     * @return True if tile can be rotated, otherwise return false (tile can't be rotated)
     */
    public static boolean canRotateTile(String boardString, int position) {
        // FIXME Task 9
        return true;
    }

    /**
     * Rotate the specified tile one quarter-turn (90 degrees) clockwise.
     *
     * @param boardString String representing the board
     * @param pos         position of the tile to be rotated
     * @return An updated boardString that reflects the rotation
     */
    public static String rotateTile(String boardString, int pos) {
        // FIXME Task 10
        return "";
    }

    /**
     * Given an objective, return a sequence of rotations that solves it.
     * The sequence of rotations is a String in which each character is an
     * integer representing the position of the tile to be rotated.
     * All rotations are clockwise quarter-turns (90 degrees).
     * For example, the String "8887" represents three clockwise quarter-
     * turns of tile number eight, followed by a single turn of tile
     * number 7.
     *
     * @param objective an objective for the Vikings game
     * @return a String representing the sequence of rotations to solve the objective,
     * or an empty String if no solution exists
     */
    public static String findSolution(Objective objective) {
        // FIXME Task 11
        return "";
    }
}
