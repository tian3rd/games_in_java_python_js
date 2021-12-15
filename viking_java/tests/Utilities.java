public class Utilities {

    public static String[] badBoats = {
            "O0N1O0N2N1N2N2O0N2", //no boats
            "O0N0O0N3N0N3N3N0O1", // no boats
            "N0N0N3O1O0O1N1N2N2RcBgGqGr", // More than 1 boat of each colour
            "O0O1N1N3O1N1N3N3N3RpGiYbYx",
            "O0O0O1N2N2N2N3N3N3PcGj", // invalid boat character
            "N0O1N2N0N3O1N0O1N2YiRqGeBqBq", // More than 4 boats
            "O0N0N3N3O0O1N3N3N3BdYgRlGqBdRk", // More than 4 boats
            "N3O1N1N3N3O0N3O1N2GaYbRmBy" // invalid boat placement
    };

    public static String[] notWellFormed = {
            "N0O1N1N0O0O1N0N3N1N1Rt", // extra N tile
            "O0O1O0N3N1N2N3N2N2O2Gs", // extra O tile
            "O0N1O0N2O1N2N2O0N2Ye", // incorrect number of tileTypes
            "O0O0N0N3N3N0N3N3N0Ru",
            "O0N1N1N2N3Z0N2O0Be", // only 8 tiles
            "O0O0N2O1N2N2GdRe", // only 6 tiles
            "O1O0N1O1N2N2N3N2R2RaBhYo", //One tile has boat char
            "O0O1N1N3H1N2N3N3N2BbYp", // One tile has invalid character
            "N0N3N1O0O1O0N4N3N2RfYnGt" // invalid orientation
    };

    public static Objective[] overlappingTiles = {
            new Objective("Zz", "O0O3O1N2N2N2N3N3N3RcBjGu", 1),
            new Objective("Zz", "O0O0O1N1N2N2N3N3N3RcBjGu", 2),
            new Objective("Zz", "N3O2N1N3O0O1N3N3N2GbRt", 3),
            new Objective("Zz", "O1N3N1N3O1N1N2N2O0GaBpYu", 4),
            new Objective("Zz", "O1O0N2N0N2N1O0N2N2BfYqRr", 5),
            new Objective("Zz", "O1O0N1N0N2N1O0N0N2BfYqRr", 6),
            new Objective("Zz", "O1N1N1N3O1N2N0N3O1GpYx", 7),
            new Objective("Zz", "O1N1N1N0O1N2N1N3O1GpYx", 8),
            new Objective("Zz", "O1O0N2N3N3O1N2N2N3BaGq", 9),
            new Objective("Zz", "O0O1N2O1N3N2N1N2N2BcYiRrGv", 10),
            new Objective("Zz", "O1O0O0N3N3N3N2N2N0BaGhYr", 11),
            new Objective("Zz", "O0O1N2O1N2N0N1N2N2BcYiRrGv", 12),
    };

    public static int[][] overlappingPos = {
            {1, 2},
            {0, 3},
            {1, 4},
            {0, 1},
            {2, 5},
            {4, 7},
            {3, 6},
            {6, 7},
            {7, 8},
            {3, 4},
            {5, 8},
            {4, 5}
    };

    public static String[] overlappingBoats = {
            "O1N1O0N0O1N2N3N3N3GgRgBp",
            "O0O1N1N3N2N1N2N2O0BdGlRl",
            "O1O0N1N1N2O0N3N3N3BfRkGnYuBk",
            "O1O0O1N1N2N2N3N3N3GcRjBjYj",
            "N0N0N3O1O0O1N1N2N2RcBgGqYrRq",
            "N0O1N2N0N3O1N0O1N2YiRqGi",
            "O0O1N1N3N2N1N2N2O0YdGlRrBuGr",
            "O0N0N0N3N0O0N3O0N2YdRsBs",
            "O0O0O1N2N2N2N3N3N3RcGjGc",
            "N3O1N1N3N0O0N3O0N3RaBbGnYb"
    };

    public static int[][] rotatingTiles = {
            // positions for master objectives
            {4}, // Starting at Objective 45
            {4},
            {3},
            {2, 3},
            {2, 6}, //49
            {1},
            {4},
            {7},
            {2}, // 53
            {5},
            {2},
            {4},
            {0},
            {0, 5},// 58
            {2}
    };

    public static int[][] nonRotatingTiles = {
            // positions for master objectives
            {0, 1, 2, 3, 5, 6, 7, 8}, // 45
            {0, 1, 2, 3, 5, 6, 7, 8},
            {0, 1, 2, 4, 5, 6, 7, 8},
            {0, 1, 4, 5, 6, 7, 8},
            {0, 1, 3, 4, 5, 7, 8}, //49
            {0, 2, 3, 4, 5, 6, 7, 8},
            {0, 1, 2, 3, 5, 6, 7, 8},
            {0, 1, 2, 3, 4, 5, 6, 8},
            {0, 1, 3, 4, 5, 6, 7, 8}, //53
            {0, 1, 2, 3, 4, 6, 7, 8},
            {0, 1, 3, 4, 5, 6, 7, 8},
            {0, 1, 2, 3, 5, 6, 7, 8},
            {1, 2, 3, 4, 5, 6, 7, 8},//57
            {1, 2, 3, 4, 6, 7, 8},
            {0, 1, 3, 4, 5, 6, 7, 8}
    };

    public static String[][] completeObjOne = {
            {"N0O1N1N0O0O1N0N3N1Rt", "8"}, // initial state, position to rotate to next state
            {"N0O1N1N0O0O1N0N3N2Rq", "5"},
            {"N0O1N1N0O0O2N0N3N2Rm", "4"},
            {"N0O1N1N0O1O2N0N3N2Rp", "7"},
            {"N0O1N1N0O1O2N0N0N2Rt", "7"},
            {"N0O1N1N0O1O2N0N1N2Rw", "7"},
            {"N0O1N1N0O1O2N0N2N2Rs", "6"},
            {"N0O1N1N0O1O2N1N2N2Rv"}//final state
    };

    public static String[][] oneMoveExpert = {
            {"N1N1N1O1O0O1N3N3N2RhYt", "0"}, // end state, position, orientation // problem 30
            {"N3O1N1N3O0O1N3N3N3GbRq", "8"}, // 31
            {"O0O1N2O1N2N2N2N2N2BcGrRoYi", "6"},
            {"O1N1O0N0N1N2N0O1N2RpYa", "4", "1"},
            {"O0O2N1N2N2N1O1N2N2BqGeRfYv", "1"},//34
            {"O0O1N1N3N0N1N3O0N2BdRq", "5"},
            {"O0N0N0N3O0O1N2N2N2BrGjRlYd", "2"},
            {"N1N1N1O0O1O0N3N3N2BpRdYn", "7"},
            {"N0N0N3O1O0O1N2N2N2BrGgRoYh", "3"},//38
            {"O1N1O0N3N1N2N2O0N2BaGtRgYh", "0"},
            {"O0N0O1N3O0N3N2N2N2BrGdRjYl", "2"},
            {"N0N0N0O1O0O1N1N2N2BrGjRgYq", "2"},
            {"O2N2N1N3O1O0N2N2N2BnRe", "0"},
            {"O0N0O1N3N0N3N3N0O1BdGjRcYn", "2"},
            {"O0O0N2N2N2N2O1N2N2BvGf", "2"}
    };

    public static int[][] interlockingTiles = {
            // t2 on left of tile 1 - Objective 0
            {1, 0},
            {5, 4},
            {7, 6},
            // t2 on right of tile 1 - Objective 1
            {1, 2},
            {4, 5},
            {7, 8},
            // t2 above t1 - Objective 2
            {5, 2},
            {4, 1},
            {6, 3},
            {7, 4},
            {8, 5},
            // t2 below t1 - objective 5
            {0, 3},
            {1, 4},
            {4, 7}
    };

    public static int[][] nonInterlockingTiles = {
            // t2 on left - Objective 0
            {8, 7}, // Obj 0
            {4, 3}, // Obj 1
            {1, 0}, // Obj 2
            {1, 0}, // Obj 3
            {1, 0}, // Obj 4
            {7, 6}, // obj 5
            // t2 on right
            {1, 2}, // Obj 6
            {7, 8}, // Obj 7
            {1, 2}, // Obj 8
            {3, 4}, // Obj 9
            // t2 above
            {7, 4}, // Obj 10
            {8, 5}, // Obj 11
            {6, 3}, //Obj 12
            {1, 4}, // Obj 13
            {4, 1}, // Obj 14
            // t2 below
            {2, 5}, // Obj 15
            {0, 3}, // Obj 16
            {5, 8}, // Obj 17
            {4, 7}, // Obj 18
            {1, 4} // Obj 19
    };

}