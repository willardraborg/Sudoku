package Sudoku;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SudokuSolverTests {
    private SudokuSolver sudoku;
    private final int[][] testBoard = {
            { 0, 0, 8, 0, 0, 9, 0, 6, 2 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 5 },
            { 1, 0, 2, 5, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 2, 1, 0, 0, 9, 0 },
            { 0, 5, 0, 0, 0, 0, 6, 0, 0 },
            { 6, 0, 0, 0, 0, 0, 0, 2, 8 },
            { 4, 1, 0, 6, 0, 8, 0, 0, 0 },
            { 8, 6, 0, 0, 0, 0, 1, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 4, 0, 0 }
            };

    @BeforeEach
    void setUp() {
        // Create empty Sudoku
        sudoku = new SudokuSolver();
    }

    @AfterEach
    void tearDown() {
        sudoku = null;
    }

    @Test
    void testEmpty() {
        assertTrue(sudoku.solve());
    }

    /**
     * Tests with the predefined board that was given in the instructions
     */

    @Test
    void testPredefinedBoard() {
        sudoku.setMatrix(testBoard);
        assertTrue(sudoku.solve());
    }

    /**
     * Tests with an unsolvable board, row is invalid
     */

    @Test
    void testUnsolvableRow() {
        sudoku.add(0, 0, 1);
        sudoku.add(0, 3, 1);

        assertTrue(sudoku.isNumInRow(1, 0));

        //assertFalse(sudoku.solve(sudoku.board));
    }

    /**
     * Tests with an unsolvable board, column is invalid
     */

    @Test
    void testUnsolvableColumn() {
        sudoku.add(0, 0, 1);
        sudoku.add(1, 0, 1);

        assertTrue(sudoku.isNumInCol(1, 0));
    }

    /**
     * Tests with an unsolvable board, grid is invalid
     */

    @Test
    void testUnsolvableGrid() {
        sudoku.add(0, 0, 1);
        sudoku.add(0, 1, 2);
        sudoku.add(0, 2, 3);
        sudoku.add(1, 0, 4);
        sudoku.add(1, 1, 5);
        sudoku.add(1, 2, 6);
        sudoku.add(2, 3, 7);

        assertFalse(sudoku.solve());
    }

    /**
     * Tests the public set & add methods
     */

    @Test
    void testGetSetNumber() {
        assertEquals(sudoku.get(0, 0), 0);
        assertThrows(IllegalArgumentException.class, () -> sudoku.get(10, 10), "Should throw error");

        sudoku.add(0, 0, 1);

        assertThrows(IllegalArgumentException.class, () -> sudoku.add(0, 0, 12), "Should throw error");
        assertEquals(sudoku.get(0, 0), 1);

    }

    /**
     * Tests the public method remove
     */

    @Test
    void testClearNumber() {
        sudoku.add(0, 0, 1);
        assertEquals(sudoku.get(0, 0), 1);

        sudoku.remove(0, 0);
        assertEquals(sudoku.get(0, 0), 0);

        assertThrows(IllegalArgumentException.class, () -> sudoku.remove(10, 10), "Should throw error");
    }

    /**
     * Tests the public methods isValid and isAllValid
     */

    @Test
    void testIsAllValid() {
        sudoku.add(0, 0, 1);
        sudoku.add(4, 1, 1);

        assertFalse(sudoku.isValid(sudoku.board,0, 0, 1), "IsValid is not correct");

        assertTrue(sudoku.solve());
        assertFalse(sudoku.isAllValid());

        assertThrows(IllegalArgumentException.class, () -> sudoku.isValid(sudoku.board,10, 10, 10), "Should throw error");
    }

    /**
     * Tests the public method clear
     */

    @Test
    void testClearAll() {
        assertTrue(sudoku.solve());

        sudoku.clear();

        for(int r = 0; r < 9; r++) {
            for(int c = 0; c < 9; c++) {
                assertEquals(sudoku.get(r, c), 0);
            }
        }
    }

    /**
     * Tests the public method setMatrix
     */

    @Test
    void testSetMatrix() {
        sudoku.setMatrix(testBoard);
        assertEquals(sudoku.get(2, 0), 1);
        assertEquals(sudoku.get(0, 2), 8);
    }

    /**
     * Tests the public method getMatrix
     */

    @Test
    void testGetMatrix() {
        int[][] empty = sudoku.getMatrix();

        for(int r = 0; r < 9; r++) {
            for(int c = 0; c < 9; c++) {
                assertEquals(empty[r][c], 0);
            }
        }

        // Test first row once solved
        sudoku.solve();
        int[][] solved = sudoku.getMatrix();

        for(int i = 0; i < 9; i++) {
            assertEquals(solved[0][i], i + 1);
        }
    }
}