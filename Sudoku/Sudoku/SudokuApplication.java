package Sudoku;

public class SudokuApplication {
    public static void main(String[] args) {
        SudokuSolver solver = new SudokuSolver();
        new SudokuGUI(solver);

    }
}
