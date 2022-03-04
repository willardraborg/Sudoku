package Sudoku;

import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SudokuGUI {
    public SudokuGUI(SudokuSolver solver) {
        SwingUtilities.invokeLater(() -> createSudokuWindow(solver));
    }
    private int error;
    private void createSudokuWindow(SudokuSolver solver) {
        JFrame frame = new JFrame("Sudoku");
        Container pane = frame.getContentPane();

        // making sudoku grid
        JTextField[] fields = new JTextField[81];
        JPanel grid = new JPanel(new GridLayout(9, 9));
        for (int i = 0; i < fields.length; i++) {
            fields[i] = new JTextField(2);
            fields[i].setFont(fields[i].getFont().deriveFont(Font.BOLD, 14f));
            grid.add(fields[i]);
        }
        colorFields(fields);
        JPanel centeredGrid = new JPanel(new GridBagLayout());
        centeredGrid.add(grid);

        // creating buttons and adding ActionListeners
        JButton BtnSolve = new JButton("Solve");
        BtnSolve.addActionListener(e -> {
            //System.out.print(Arrays.toString(solver.board[0]));
            //System.out.println(solver.isAllValid());
            try {
                getFields(solver, fields);
                //if (solver.hasDuplicates(solver.board)) {
                 //   JOptionPane.showMessageDialog(null, "This sudoku is unsolvable as a row contains two of the same numbers.");
                if (!solver.solve()) {
                    JOptionPane.showMessageDialog(null, "This sudoku is unsolvable. Please try another combination of numbers!");
                    //only accept numbers 1-9
                } else if (error==-1) { // a blir -1 i getFields
                    JOptionPane.showMessageDialog(null, "I will only accept the numbers 1-9.");
                } else {
                    getFields(solver, fields);
                    solver.solve();
                    updateFields(solver, fields);
                }
                // handle weird characters
            } catch (Exception t) {
                JOptionPane.showMessageDialog(null, "I will only accept the numbers 1-9.");
            }
        });
        JButton BtnClear = new JButton("Clear");
        BtnClear.addActionListener(e -> {
            solver.clear();
            updateFields(solver, fields);
        });

        // creating a panel to add buttons
        JPanel p = new JPanel();

        // adding buttons to panel
        p.add(BtnSolve);
        p.add(BtnClear);

        // adding stuff to frame
        pane.add(centeredGrid, BorderLayout.CENTER);
        pane.add(p, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        // take values from initial board and put them in sudoku board
        updateFields(solver, fields);
    }

    /**
     * Updates the JTextFields in the sudoku window
     * @param solver    the SudokuSolver instance
     * @param fields    the JTextField[]
     */
    private void updateFields(SudokuSolver solver, JTextField[] fields) {
        int place = 0;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (solver.board[row][col] == 0) {
                    fields[place].setText("");
                    place++;
                } else {
                    fields[place].setText(Integer.toString(solver.board[row][col]));
                    place++;
                }
            }
        }
    }

    /**
     * Gets values from JTextFields and adds them to board
     * @param solver
     * @param fields
     */
    private void getFields(SudokuSolver solver, JTextField[] fields) {
        int place = 0;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (fields[place].getText().equals("")) {
                    solver.board[row][col] = 0;
                    place++;
                } else if (Integer.parseInt(fields[place].getText()) < 1 || Integer.parseInt(fields[place].getText()) > 9) {
                    error = -1;
                    place++;
                }
                else {
                    solver.board[row][col] = Integer.parseInt(fields[place].getText());
                    place++;
                }
            }
        }
    }

    /**
     * Colors the fields in select ones
     * @param fields
     */
    private void colorFields(JTextField[] fields){
        int[] COLORED_SQUARES = {0,1,2,9,10,11,18,19,20,6,7,8,15,16,17,24,25,26,30,31,32,39,40,41,48,49,50,54,55,56,63,64,65
        ,72,73,74,60,61,62,69,70,71,78,79,80};

        for(int i : COLORED_SQUARES){
            fields[i].setBackground(Color.LIGHT_GRAY);
        }
    }
}