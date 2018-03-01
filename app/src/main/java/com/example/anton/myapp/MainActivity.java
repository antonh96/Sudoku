package com.example.anton.myapp;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.support.v7.widget.Toolbar;
import android.content.Context;


public class MainActivity extends AppCompatActivity {
    private SudokuAdapter s;
    private GridView g;
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Toolbar toolbar = findViewById(R.id.Toolbar);
            toolbar.setBackgroundColor(Color.rgb(255,105,180));
            setSupportActionBar(toolbar);

            g = findViewById(R.id.grid);
            s = new SudokuAdapter();
            g.setNumColumns(9);
            g.setVerticalScrollBarEnabled(false);
            g.setHorizontalSpacing(2);
            g.setVerticalSpacing(2);
            g.setAdapter(s);
            s.onCreate(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu item){
        getMenuInflater().inflate(R.menu.menu_bars, item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_clear:
                s.clear();
                g.setAdapter(s);
                return true;

            case R.id.action_solve:
                if(s.Solve()){
                    g.setAdapter(s);
                    return true;
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("OBS!");
                    builder.setMessage("Sudokut är olösligt!").setNegativeButton(R.string.alert_button, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    return true;
                }
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}

