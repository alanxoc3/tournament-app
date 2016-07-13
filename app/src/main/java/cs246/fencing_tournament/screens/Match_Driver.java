package cs246.fencing_tournament.screens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cs246.fencing_tournament.R;
import cs246.fencing_tournament.data.ContestantData;
import cs246.fencing_tournament.data.MatchData;

public class Match_Driver extends AppCompatActivity {

    private List<ContestantData> contestants;
    private boolean canUpdate = true;
    private ContestantData p1;
    private ContestantData p2;
    private MatchData thisMatch;

    public void callMatch(View v){
        Intent action = new Intent(Match_Driver.this, MatchScreen.class);

        action.putParcelableArrayListExtra("ContestantsArray",(ArrayList<ContestantData>)contestants);
        startActivity(action);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match__driver);

        contestants = getIntent().getParcelableArrayListExtra("ContestantsArray");
        thisMatch = getIntent().getParcelableExtra("Match");
        if (thisMatch == null) {
            thisMatch = new MatchData(1,2); //THIS WILL HAVE TO BE CHANGED!!
            canUpdate = false;
        }
        if (contestants == null) {
            canUpdate = false;
        }

        if (canUpdate){
            p1 = ContestantData.findById(contestants,thisMatch.getId1());
            TextView name = (TextView) findViewById(R.id.p1Name);
            name.setText(p1.getName());
            p2 = ContestantData.findById(contestants,thisMatch.getId2());
            name = (TextView) findViewById(R.id.p2Name);
            name.setText(p2.getName());
        }

    }
}
