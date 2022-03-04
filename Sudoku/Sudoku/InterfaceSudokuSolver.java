package Sudoku;

public interface InterfaceSudokuSolver {
    /**
     * @param board     The board
     * Tries to solve the sudoku recursively
     * @return true if sudoku was solved
     */
    boolean solve();

    /**
     * Puts digit in the box row, col.
     *
     * @param row   The row
     * @param col   The column
     * @param digit The digit to insert in box row, col
     * @throws IllegalArgumentException if row, col or digit is outside the range
     *                                  [0..9]
     */
    void add(int row, int col, int digit) throws IllegalArgumentException;

    /**
     * @param row   The row
     * @param col   The col
     * Sets values in row, col to 0
     */
    void remove(int row, int col);

    /**
     * @param row   The row
     * @param col   The col
     * Fetches values from row, col
     */
    int get(int row, int col);

    /**
     * @param board     The board
     * @param num       Number to check in row, col, box
     * @param row       The row
     * @param col       The col
     * Checks that all filled in digits follows the sudoku rules.
     */
    boolean isValid(int[][] board, int num, int row, int col);

    /**
     * Clears the board by setting the boxes to zero
     */
    void clear();

    /**
     * Fills the grid with the digits in m. The digit 0 represents an empty box.
     *
     * @param m the matrix with the digits to insert
     * @throws IllegalArgumentException if m has the wrong dimension or contains
     *                                  values outside the range [0..9]
     */
    void setMatrix(int[][] m) throws IllegalArgumentException;

    /**
     * @return board
     */
    int[][] getMatrix();
}