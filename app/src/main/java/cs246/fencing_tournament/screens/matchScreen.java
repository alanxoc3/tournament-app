package cs246.fencing_tournament.screens;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cs246.fencing_tournament.R;
import cs246.fencing_tournament.data.MatchData;

public class matchScreen extends AppCompatActivity {

    private boolean p1Yellow;
    private boolean p2Yellow;
    private MatchData thisMatch;

    public void yellow1(View v){
        if (p1Yellow){
            red1(v);
        }
        else {
            p1Yellow = true;
            int yellow = Color.YELLOW;
            Button box = (Button) findViewById(R.id.cards);
            box.setBackgroundColor(yellow);
        }
    }
    public void yellow2(View v){
        if (p2Yellow){
            red2(v);
        }
        else {
            p2Yellow = true;
            int yellow = Color.YELLOW;
            Button box = (Button) findViewById(R.id.cards2);
            box.setBackgroundColor(yellow);
        }
    }
    public void red1(View v){
        p1Yellow = true;
        int red = Color.RED;
        Button box = (Button) findViewById(R.id.cards);
        box.setBackgroundColor(red);
        thisMatch.pointP1();
        update();
    }
    public void red2(View v){
        p2Yellow = true;
        int red = Color.RED;
        Button box = (Button) findViewById(R.id.cards2);
        box.setBackgroundColor(red);
        thisMatch.pointP2();
        update();
    }

    public void pointP1(View v){
        thisMatch.pointP1();
        update();
    }
    public void pointP2(View v){
        thisMatch.pointP2();
        update();
    }

    public void update() {
        TextView score = (TextView) findViewById(R.id.p1Score);
        score.setText(Integer.toString(thisMatch.getP1Score()));
        score = (TextView) findViewById(R.id.p2Score);
        score.setText(Integer.toString(thisMatch.getP2Score()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_screen);

        thisMatch = new MatchData(1,2); //THIS WILL HAVE TO BE CHANGED!!

        int defalt = Color.WHITE;
        Button box = (Button) findViewById(R.id.cards);
        box.setBackgroundColor(defalt);
        box = (Button) findViewById(R.id.cards2);
        box.setBackgroundColor(defalt);
        p1Yellow = false;
        p2Yellow = false;


    }
}
