package cs246.fencing_tournament.screens;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cs246.fencing_tournament.R;

public class matchScreen extends AppCompatActivity {

    public void whatever(View v){
        int yellow = Color.YELLOW;
        Button box = (Button) findViewById(R.id.cards);
        box.setBackgroundColor(yellow);
    }

    public void yellow1(View v){

    }
    public void yellow2(View v){

    }
    public void red1(View v){

    }
    public void red2(View v){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_screen);

        int defalt = Color.WHITE;
        Button box = (Button) findViewById(R.id.cards);
        box.setBackgroundColor(defalt);
        box = (Button) findViewById(R.id.cards2);
        box.setBackgroundColor(defalt);
    }
}
