package com.example.anton.myapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuModelTest {
    SudokuModel s;
    @Before
    public void setUp() throws Exception {
        int[][] sudoku = new int[9][9];
        s = new SudokuModel(sudoku);
    }

    @After
    public void tearDown() throws Exception {
        s = null;
    }
    @Test
    public void testEmpty() {
        assertTrue("Sudoku går ej att lösa!",s.solveSudoku());
    }
    @Test
    public void solvableSudoku() {
        int[][] test = new int[][]{
                {0,0,8,0,0,9,0,6,2},
                {0,0,0,0,0,0,0,0,5},
                {1,0,2,5,0,0,0,0,0},
                {0,0,0,2,1,0,0,9,0},
                {0,5,0,0,0,0,6,0,0},
                {6,0,0,0,0,0,0,2,8},
                {4,1,0,6,0,8,0,0,0},
                {8,6,0,0,3,0,1,0,0},
                {0,0,0,0,0,0,4,0,0}};
        s = new SudokuModel(test);
        assertTrue("Sudoku går ej att lösa!",s.solveSudoku());
    }
    @Test
    public void unSolvableRow() {
        int[][] test = new int[][]{
                {0,0,8,0,0,9,0,6,2},
                {0,0,0,0,0,0,0,0,5},
                {1,0,2,5,0,0,0,0,0},
                {0,0,0,2,1,0,0,9,0},
                {0,5,0,0,0,0,6,0,0},
                {6,0,0,0,0,6,0,2,8},
                {4,1,0,6,0,8,0,0,0},
                {8,6,0,0,3,0,1,0,0},
                {0,0,0,0,0,0,4,0,0}};
        s = new SudokuModel(test);
        assertFalse("Sudoku ska inte gå att lösa!",s.solveSudoku());
    }
    @Test
    public void unSolvableBox() {
        int[][] test = new int[][]{
                {0,0,8,0,0,9,0,6,2},
                {0,0,0,0,0,0,0,0,5},
                {1,0,2,5,0,0,0,0,0},
                {0,0,0,2,1,0,0,9,0},
                {0,5,0,0,0,0,6,0,0},
                {6,0,0,0,0,0,0,2,8},
                {4,1,0,6,0,8,0,0,0},
                {8,6,0,0,3,0,1,0,4},
                {0,0,0,0,0,0,4,0,0}};
        s = new SudokuModel(test);
        assertFalse("Sudoku ska inte gå att lösa!",s.solveSudoku());
    }
    @Test
    public void unSolvableCol() {
        int[][] test = new int[][]{
                {0,0,8,0,0,9,0,6,2},
                {0,0,0,0,0,0,0,0,5},
                {1,0,2,5,0,0,0,0,0},
                {0,0,0,2,1,0,0,9,0},
                {0,5,0,0,0,0,6,0,0},
                {6,0,0,0,0,0,0,2,8},
                {4,1,0,6,0,8,0,0,0},
                {8,6,0,0,3,0,1,0,5},
                {0,0,0,0,0,0,4,0,0}};
        s = new SudokuModel(test);
        assertFalse("Sudoku ska inte gå att lösa!",s.solveSudoku());
    }
    @Test
    public void getNumber() throws Exception {
        assertEquals("Nummret borde vara 0!", 0, s.getNumber(0,0));
    }

    @Test
    public void setNumber() throws Exception {
        s.setNumber(3,4,5);
        assertEquals("Nummret borde vara 5!", 5, s.getNumber(3,4));
    }

}