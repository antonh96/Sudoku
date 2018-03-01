package com.example.anton.myapp;


public class SudokuModel {
    private int[][] sudoku;
    private int n = 9;

    /**
     * Creates a new sudoku from a matrix
     * @param * matrix
     */

    public SudokuModel (int[][] sudoku) {
        this.sudoku = sudoku;
    }
    /**
     * Returns the number in row, column
     * @param *index of row, column
     * @return the number in row, column
     */
    public int getNumber(int r, int c) {
        return sudoku[r][c];
    }

    /**
     * Sets a new number in row, column
     * @param *index of row, column and the number to be set
     */
    public void setNumber(int r, int c, int nbr) {
        sudoku[r][c] = nbr;
    }

    private boolean checkSudoku() {
        for(int i = 0; i<9; i++ ){
            for(int j = 0; j<9; j++ ){
                if(getNumber(i,j) != 0) {
                    int temp = getNumber(i, j);
                    setNumber(i, j, 0);
                    if (!canPlace(i, j, temp)) {
                        setNumber(i, j, temp);
                        return false;
                    }
                setNumber(i,j,temp);
                }
            }
        }
        return true;
    }

    /**
     * Solving sudoku if possible
     * Checking the sudoku if rules have been broken before it solves the sudoku recursively
     * @return true if possible else false
     */
    public boolean solveSudoku () {
        if(!checkSudoku()) {
            return false;
        }
        else return solve(0,0);
    }

    private boolean solve(int r, int c) {
        if(r == 9 ) {
            return true;
        }
        if(getNumber(r,c) != 0) {
            int number = getNumber(r,c);
            setNumber(r,c,0);
            if(canPlace(r,c,number)) {
                setNumber(r,c,number);
                if(changeRow(r,c)) {
                    return true;
                }
            }
            setNumber(r,c, number);
            return false;
        }

        for(int nbr = 1; nbr < 10; nbr++) {
            if(canPlace(r,c,nbr)) {
                setNumber(r,c,nbr);
                if(changeRow(r,c)) {
                    return true;
                }
                else {
                    resetBox(r, c);
                }
            }
        }
        return false;
    }

    private void resetBox(int r, int c) {
        setNumber(r,c,0);
    }

    private boolean changeRow(int r, int c) {
        if(c < 8) {
            return solve(r, c+1);
        }
        else {
            return solve(r+1,0);
        }
    }

    private boolean canPlace(int r, int c, int nbr) {

        for(int col = 0; col < n ; col++ ) {
            if(sudoku[r][col] == nbr) {
                return false;
            }
        }
        for (int row = 0; row < n; row++) {
            if (sudoku[row][c] == nbr) {
                return false;
            }
        }
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (sudoku[(r/3 * 3) + row][(c/3 * 3) + col] == nbr) {
                    return false;
                }
            }
        }
        return true;
    }
}