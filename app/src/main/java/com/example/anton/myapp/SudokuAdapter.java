package com.example.anton.myapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

public class SudokuAdapter extends BaseAdapter {
    private EditText[][] editTexts;
    private SudokuModel model;

    /**
     * Creates 9*9 writable boxes
     * @param activity which is needed for creating edittexts
     */
    public void onCreate(Activity activity){
        editTexts = new EditText[9][9];
        model = new SudokuModel(new int[9][9]);
        int count = 0;
        for(int i = 0; i<9; i++ ){
            for(int j = 0; j<9; j++ ){
                editTexts[i][j] = new EditText(activity);
                editTexts[i][j].setId(count);
                editTexts[i][j].setInputType(InputType.TYPE_CLASS_NUMBER);
                editTexts[i][j].setFilters(new InputFilter[] {new InputFilter.LengthFilter(1)});
                editTexts[i][j].setGravity(Gravity.CENTER);
                if(setColor(i,j)){
                    editTexts[i][j].setBackgroundColor(Color.rgb(255,105,180));
                }
                else editTexts[i][j].setBackgroundColor(Color.LTGRAY);

                count++;
            }
        }
    }
    /**
     * Returns the number of boxes in sudoku
     * @return the number of boxes in sudoku
     */
    @Override
    public int getCount() {
        return 9*9;
    }

    /**
     * Gets all the numbers placed by the user in the boxes and updates the model
     * Updates the boxes if the sudoku is possible to solve
     * @return true if possible else false
     */
    public boolean Solve(){
        int number;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                try {
                    number = Integer.parseInt(editTexts[i][j].getText().toString());
                } catch (Exception e){
                    number = 0;
                }
                if(number != 0){
                    model.setNumber(i,j,number);
                }
                else model.setNumber(i,j,0);

            }
        }

        if(model.solveSudoku()) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    editTexts[i][j].setText(Integer.toString(model.getNumber(i, j)));
                }
            }
            return true;
        }
         return false;
    }
    /**
     * Clears all the boxes in sudoku
     */
    public void clear(){
        for(int i = 0; i<9; i++ ){
            for(int j = 0; j<9; j++ ){
                model.setNumber(i,j,0);
                editTexts[i][j].getText().clear();
            }
        }
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    private boolean setColor(int row, int col){
        return (row < 3 && col < 3 ||
                row > 5 && col < 3 ||
                row < 3 && col > 5 ||
                row > 5 && col > 5 ||
                row > 2 && col > 2 && row < 6 && col < 6);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    /**
     * Returns the box in row, column
     * @param * id of box, view and viewgroup
     * @return the box int row, column
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int row = i/9;
        int col = i%9;
        return editTexts[row][col];
    }
}
