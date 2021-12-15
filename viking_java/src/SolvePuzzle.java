public class SolvePuzzle {
    public final Objective objective;
    public final String solution;

    public SolvePuzzle(Objective objective) {
        this.objective = objective;
        this.solution = puzzleSolution(objective);
    }

    public static String puzzleSolution(Objective objective) {
        // Objective objective = new Objective("BaGx", "O1N1O0N3N1N2N3N3O1BlGh", 9);
        Vikings viking = new Vikings(objective);
        String solution = viking.findSolution(objective);
        // System.out.println(solution);
        return solution;
    }

    @Override
    public String toString() {
        return this.objective.toString() + "\n" + "Solution: " + this.solution;
    }

    public static void main(String[] args) {
        int problem = Integer.parseInt(args[0]);
        String boardString = args[1];
        String target = args[2];
        SolvePuzzle puzzle = new SolvePuzzle(new Objective(target, boardString, problem));
        System.out.println(puzzle);
        
    }
    
}
