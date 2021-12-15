import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Vikings {
    final Tile[] tiles;
    final Boat[] boats;
    private Objective objective;

    public Vikings(Objective objective) {
        this.objective = objective;
        String boardString = objective.getInitialState();
        tiles = Tile.fromBoardString(boardString);
        boats = Boat.fromBoardString(boardString);
    }

    /**
     * Construct a game for a given level of difficulty which chooses a new objective and creates a new instance of the game at the given level of difficulty
     * 
     * @param difficulty The level of difficulty to play at
     */
    public Vikings(int difficulty) {
        this(Objective.newObjective(difficulty));
    }

    public Objective getObjective() {
        return objective;
    }

    /**
     * @param boardString The string representation of the board
     * @return True if the board is valid, false otherwise
     */
    public static boolean isBoardStringWellFormed(String boardString) {
        // FIXME: Task 3
        if (boardString.length() % 2 == 1 ||  boardString.length() < 20 || boardString.length() > 28) {
            return false;
        } else {
            // 1. Check 9 tiles
            int numNTiles = 0; 
            for (int i = 0; i < 9; i++) {
                if (!"NO".contains(""+boardString.charAt(2 * i)) || !"0123".contains(""+boardString.charAt(2 * i + 1))) {
                    return false;
                }
                if (boardString.charAt(2 * i) == 'N') {
                    numNTiles++;
                }
            }
            if (numNTiles != 6) {
                return false;
            }
            // 2. Check 1-4 boats
            Map<Character, Integer> boatColors = new HashMap<>();
            for (int i = 9; i < boardString.length() / 2; i++) {
                if (!"BGRY".contains("" + boardString.charAt(2 * i)) || !"abcdefghijklmnopqrstuvwx".contains(""+boardString.charAt(2 * i + 1))) {
                    return false;
                }
                // 3. Check repeated colors
                boatColors.putIfAbsent(boardString.charAt(2 * i), 0);
                boatColors.put(boardString.charAt(2 * i), boatColors.get(boardString.charAt(2 * i)) + 1);
                if (boatColors.get(boardString.charAt(2 * i)) > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @param boardString A well-formed board string
     * @param position1 Position of the tile 1
     * @param position2 Position of the tile 2
     * @return True if Tiles overlap, false otherwise
     */
    public static boolean doTilesOverlap(String boardString, int position1, int position2) {
        // FIXME: Task 6
        assert isBoardStringWellFormed(boardString);
        if (position1 == position2) {
            return false;
        }
        if (position1 > position2) {
            int temp = position1;
            position1 = position2;
            position2 = temp;
        }
        String tile1 = boardString.substring(position1 * 2, position1 * 2 + 2);
        String tile2 = boardString.substring(position2 * 2, position2 * 2 + 2);
        if ("O0 O2".contains(tile1)) {
            if (position2 - position1 == 3) {
                if ("N0 N1 O0 O2".contains(tile2)) {
                    return true;
                }
            }
        } else if ("O1 O3".contains(tile1)) {
            if (position2 - position1 == 1 && position1 % 3 != 2) {
                if ("N0 N3 O1 O3".contains(tile2)) {
                    return true;
                }
            }
        } else if (tile1.equals("N1")) {
            if (position2 - position1 == 1 && position1 % 3 != 2) {
                if ("O1 O3 N0 N3".contains(tile2)) {
                    return true;
                }
            }
        } else if (tile1.equals("N2")) {
            if (position2 - position1 == 1 && position1 % 3 != 2) {
                if ("O1 O3 N0 N3".contains(tile2)) {
                    return true;
                }
            } else if (position2 - position1 == 3) {
                if ("O0 O2 N0 N1".contains(tile2)) {
                    return true;
                }
            }
        } else if (tile1.equals("N3")) {
            if (position2 - position1 == 3) {
                if ("O0 O2 N0 N1".contains(tile2)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * A board string is valid if it is well formed and there are no two boats on the same edge, and no two pieces overlapping.
     * 
     * @param boardString The string representation of the board
     * @return True if the board is valid, false otherwise
     */
    public static boolean isBoardStringValid(String boardString) {
        // FIXME: Task 7
        if (!isBoardStringWellFormed(boardString)) {
            return false;
        }
        // 1. Check no two boats on the same edge
        Map<Character, String> edgeHasBoat = new HashMap<>();
        for (int i = 9; i < boardString.length() / 2; i++) {
            edgeHasBoat.putIfAbsent(boardString.charAt(2 * i + 1), "");
            edgeHasBoat.put(boardString.charAt(2 * i + 1), edgeHasBoat.get(boardString.charAt(2 * i + 1)) + boardString.charAt(2 * i));
            if (edgeHasBoat.get(boardString.charAt(2 * i + 1)).length() > 1) {
                return false;
            }
        }
        // 2. Check no two pieces overlapping
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (doTilesOverlap(boardString, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Given two adjacent tiles, decide whether they interlock. A tile interlocks with another if there's no gap in the edge between them
     * 
     * @param boardString The string representation of the board
     * @param position1 Position of the tile 1
     * @param position2 Position of the tile 2
     * @return True if the tiles interlock, false otherwise
     */
    public static boolean doTilesInterlock(String boardString, int position1, int position2) {
        // FIXME: Task 8
        // left-right: "01 12 34 45 67 78"; right-left: "10 21 43 54 76 87"; up-down: "03 14 25 36 47 58"; down-up: "30 41 52 63 74 85"
        if (position1 > position2) {
            int temp = position1;
            position1 = position2;
            position2 = temp;
        }
        String tile1 = boardString.substring(position1 * 2, position1 * 2 + 2);
        String tile2 = boardString.substring(position2 * 2, position2 * 2 + 2);
        String relativePositions = "" + position1 + position2;
        String upDown = "03 14 25 36 47 58";
        String upDownLock = "O0 O2 N0 N1";;
        String leftRight = "01 12 34 45 67 78";
        String leftRightLock = "O1 O3 N0 N3";
        if ("O0 O2".contains(tile1)) {
            if ((leftRight.contains(relativePositions) && leftRightLock.contains(tile2)) || upDown.contains(relativePositions)) {
                return true;
            }
        } else if ("O1 O3".contains(tile1)) {
            if (leftRight.contains(relativePositions) || (upDown.contains(relativePositions) && upDownLock.contains(tile2))) {
                return true;
            }
        } else if ("N0".contains(tile1)) {
            if ((leftRight.contains(relativePositions) && !"O0 N1 N2".contains(tile2)) || (upDown.contains(relativePositions) && upDownLock.contains(tile2))) {
                return true;
            }
        } else if ("N1".contains(tile1)) {
            if (leftRight.contains(relativePositions) || (upDown.contains(relativePositions) && upDownLock.contains(tile2))) {
                return true;
            }
        } else if ("N3".contains(tile1)) {
            if ((leftRight.contains(relativePositions) && leftRightLock.contains(tile2)) || upDown.contains(relativePositions)) {
                return true;
            }
        } else if ("N2".contains(tile1)) {
            if ((leftRight.contains(relativePositions) || upDown.contains(relativePositions))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if the given tile is able to be rotated, i.e., it has a boat on at least one edge, and its rotation is not blocked by another boat
     * 
     * @param boardString The string representation of the board
     * @param position The position of the tile
     * @return True if the tile is able to be rotated, false otherwise
     */
    public static boolean canRotateTile(String boardString, int position) {
        // FIXME: Task 9
        String tile = boardString.substring(position * 2, position * 2 + 2);
        String[] edgesAtPosition = {"aehd", "bfie", "cgjf", "hlok", "impl", "jnqm", "osvr", "ptws", "quxt"};
        // 1. Check if the tile has a boat on at least one edge
        boolean hasBoat = false;
        for (int i = 9; i < boardString.length() / 2; i++) {
            if (edgesAtPosition[position].contains("" + boardString.charAt(2 * i + 1))) {
                hasBoat = true;
                break;
            }
        }
        if (!hasBoat) {
            return false;
        }
        // 2. Check if the tile's rotation is blocked by another boat
        String[] neighborsAtPosition = {"13", "024", "15", "046", "1357", "248", "37", "468", "57"};
        if ("O0 O2".contains(tile)) {
            // check left and right neighbors
            if (!canRotateTileLeft(boardString, position) || !canRotateTileRight(boardString, position)) {
                return false;
            }
        } else if ("O1 O3".contains(tile)) {
            // check up and down neighbors
            if (!canRotateTileUp(boardString, position) || !canRotateTileDown(boardString, position)) {
                return false;
            }
        } else if ("N0".contains(tile)) {
            if (!canRotateTileRight(boardString, position) || !canRotateTileDown(boardString, position)) {
                return false;
            }
        } else if ("N1".contains(tile)) {
            if (!canRotateTileLeft(boardString, position) || !canRotateTileDown(boardString, position)) {
                return false;
            }
        } else if ("N2".contains(tile)) {
            if (!canRotateTileLeft(boardString, position) || !canRotateTileUp(boardString, position)) {
                return false;
            }
        } else if ("N3".contains(tile)) {
            if (!canRotateTileRight(boardString, position) || !canRotateTileUp(boardString, position)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Helper function to check left overlapping tiles
     * 
     * @param boardString The string representation of the board
     * @param position The position of the first tile
     * @returns True if left tile is clear for rotation, false otherwise
     */
    public static boolean canRotateTileLeft(String boardString, int position) {
        if (position % 3 == 0) {
            return true;
        } else {
            return !doTilesInterlock(boardString, position, position - 1);
        }
    }

    /**
     * Helper function to check right overlapping tiles
     * 
     * @param boardString The string representation of the board
     * @param position The position of the first tile
     * @returns True if right tile is clear for rotation, false otherwise
     */
    public static boolean canRotateTileRight(String boardString, int position) {
        if (position % 3 == 2) {
            return true;
        } else {
            return !doTilesInterlock(boardString, position, position + 1);
        }
    }

    /**
     * Helper function to check upper overlapping tiles
     * 
     * @param boardString The string representation of the board
     * @param position The position of the first tile
     * @returns True if upper tile is clear for rotation, false otherwise
     */
    public static boolean canRotateTileUp(String boardString, int position) {
        if (position / 3 == 0) {
            return true;
        } else {
            return !doTilesInterlock(boardString, position, position - 3);
        }
    }

    /**
     * Helper function to check lower overlapping tiles
     * 
     * @param boardString The string representation of the board
     * @param position The position of the first tile
     * @returns True if lower tile is clear for rotation, false otherwise
     */
    public static boolean canRotateTileDown(String boardString, int position) {
        if (position / 3 == 2) {
            return true;
        } else {
            return !doTilesInterlock(boardString, position, position + 3);
        }
    }

    /**
     * Rotate the specified tile 90 degrees clockwise
     * 
     * @param boardString The string representation of the board
     * @param position The position of the tile to rotate
     * @return The string representation of the board after the rotation
     */
    public static String rotateTile(String boardString, int position) {
        // FIXME: Task 10
        String[] edgesAtPosition = {"aehd", "bfie", "cgjf", "hlok", "impl", "jnqm", "osvr", "ptws", "quxt"};
        // 1. rotate the specified tile
        char tileType = boardString.charAt(position * 2);
        Integer newTileDirection = (Integer.valueOf(boardString.charAt(position * 2 + 1)) + 1) % 4;
        String newTile = "" + tileType + newTileDirection;
        // 2. update the boats on the edges
        String newBoats = "";
        for (int i = 9; i < boardString.length() / 2; i++) {
            if (edgesAtPosition[position].contains("" + boardString.charAt(2 * i + 1))) {
                int edgeIndex = (edgesAtPosition[position].indexOf("" + boardString.charAt(2 * i + 1)) + 1) % 4;
                newBoats += "" + boardString.charAt(2 * i) + edgesAtPosition[position].charAt(edgeIndex);
            } else {
                newBoats += boardString.substring(2 * i, 2 * i + 2);
            }
        }
        String newBoardString = "";
        newBoardString += boardString.substring(0, position * 2) + newTile + boardString.substring(position * 2 + 2, 18) + newBoats;

        return newBoardString;
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
        // Some thoughts: 1. how to generate the Graph of different boats arrangements? At most there are 24*23*22*21 arrangements;
        // 2. Using breadthFirstSearch, how to trace down the rotations between different arrangements? (BeYl --> BhYl + "1"?)
        String initialBoard = objective.getInitialState();
        String target = objective.getTargetPlacement();

//        System.out.println("============================");
//        System.out.println("Initial board: " + initialBoard);
//        System.out.println("Target: " + target);

        List<Integer> q = new ArrayList<>();
        List<String> boards = new ArrayList<>();
        List<String> steps = new ArrayList<>();
        Set<String> uiqueBoards = new HashSet<>();
        // initialize states
        for (int i = 0; i < 9; i++) {
            if (canRotateTile(initialBoard, i)) {
                q.add(i);
                boards.add(initialBoard);
                steps.add("");
            }
        }
        uiqueBoards.add(initialBoard);

        while (true) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                int rotateAtPos = q.remove(0);
                String curBoard = boards.remove(0);
                String curStep = steps.remove(0);
                // rotate once
                String rotated1Board = rotateTile(curBoard, rotateAtPos);
                String next1Step = curStep + rotateAtPos;
                if (isGameover(rotated1Board, target)) {
//                    System.out.println("Current boards: \n" + boards.toString());
//                    System.out.println("Current steps: \n" + steps.toString());
//                    System.out.println("Current positions: \n" + q.toString());
                    return next1Step;
                } else { // add other positions for next level rotations
                    if (uiqueBoards.contains(rotated1Board)) continue;
                    else {
                        uiqueBoards.add(rotated1Board);
                    }
                    for (int j = 0; j < 9; j++) {
                        if (j == rotateAtPos) continue;
                        if (canRotateTile(rotated1Board, j)) {
                            q.add(j);
                            boards.add(rotated1Board);
                            steps.add(next1Step);
                        }
                    }
                }
                // rotate twice
                String rotated2Board = rotateTile(curBoard, rotateAtPos);
                rotated2Board = rotateTile(rotated2Board, rotateAtPos);
                String next2Steps = curStep + rotateAtPos + "" + rotateAtPos;
                if (isGameover(rotated2Board, target)) {
//                    System.out.println("Current boards: \n" + boards.toString());
//                    System.out.println("Current steps: \n" + steps.toString());
//                    System.out.println("Current positions: \n" + q.toString());
                    return next2Steps;
                } else { // add other positions for next level rotations
                    if (uiqueBoards.contains(rotated2Board)) continue;
                    else {
                        uiqueBoards.add(rotated2Board);
                    }
                    for (int j = 0; j < 9; j++) {
                        if (j == rotateAtPos) continue;
                        if (canRotateTile(rotated2Board, j)) {
                            q.add(j);
                            boards.add(rotated2Board);
                            steps.add(next2Steps);
                        }
                    }
                }

                // rotate thrice
                String rotated3Board = rotateTile(curBoard, rotateAtPos);
                rotated3Board = rotateTile(rotated3Board, rotateAtPos);
                rotated3Board = rotateTile(rotated3Board, rotateAtPos);
                String next3Steps = curStep + rotateAtPos + "" + rotateAtPos + "" + rotateAtPos;
                if (isGameover(rotated3Board, target)) {
//                    System.out.println("Current boards: \n" + boards.toString());
//                    System.out.println("Current steps: \n" + steps.toString());
//                    System.out.println("Current positions: \n" + q.toString());
                    return next3Steps;
                } else { // add other positions for next level rotations
                    if (uiqueBoards.contains(rotated3Board)) continue;
                    else {
                        uiqueBoards.add(rotated3Board);
                    }
                    for (int j = 0; j < 9; j++) {
                        if (j == rotateAtPos) continue;
                        if (canRotateTile(rotated3Board, j)) {
                            q.add(j);
                            boards.add(rotated3Board);
                            steps.add(next3Steps);
                        }
                    }
                }

            }
        }
    }

    /**
     * Check if the board reaches the target state
     *
     * @param boardString
     * @param target
     * @return True if it's over, false otherwise
     */
    public static boolean isGameover(String boardString, String target) {
        List<String> boatsOnboard = new ArrayList<>();
        List<String> boatsTargeted = new ArrayList<>();
        for (int i = 9; i < boardString.length() / 2; i++) {
            boatsOnboard.add(boardString.substring(2 * i, 2 * i + 2));
        }
        for (int i = 0; i < target.length() / 2; i++) {
            boatsTargeted.add(target.substring(2 * i, 2 * i + 2));
        }
        Collections.sort(boatsOnboard);
        Collections.sort(boatsTargeted);
        return boatsOnboard.equals(boatsTargeted);
    }

    /** Check if the solution can actually reach the target
     *
     * @param initialBoardString
     * @param solution
     * @param target
     * @return True if it can, false otherwise
     */
    public static boolean isCorrectSolution(String initialBoardString, String solution, String target) {
        String board = initialBoardString;
        for (int i = 0; i < solution.length(); i++) {
            board = rotateTile(board, Integer.valueOf("" + solution.charAt(i)));
        }
        if (isGameover(board, target)) return true;
        else return false;
    }

    public static void main(String[] args) {
// //        System.out.println(canRotateTile("O0O1O0N3N1N2N3N0N2Gt", 8));
//         String test13 = "O0O1N2N3N3N2N3O1N2BiRwYb";
//         String target13 = "BkRbYx";
//         String recommendedSolution13 = "122215554788874410011033301";
//         String mySolution13 = "11122211155544788874103330001";
//         System.out.println("Recommended solution is " + isCorrectSolution(test13, recommendedSolution13, target13) );
//         System.out.println("My solution is " + isCorrectSolution(test13, mySolution13, target13));
//         System.out.println("One less step is " + isCorrectSolution(test13, recommendedSolution13.substring(0, recommendedSolution13.length()-2), target13 ));

//         String test17 = "O1O0N1N0N2N1O0N2N2BfRrYq";
//         String target17 = "BnRaYv";
//         String recommendedSolution17 = "63301103014100154441033663301410002225";
//         String mySolution17 = "633011030144544455544411103663330001411100055";
//         System.out.println("Recommended solution is " + isCorrectSolution(test17, recommendedSolution17, target17) );
//         System.out.println("My solution is " + isCorrectSolution(test17, mySolution17, target17));
//         System.out.println("One less step is " + isCorrectSolution(test17, recommendedSolution17.substring(0, recommendedSolution17.length()-2), target17));

    }
}