package cs246.fencing_tournament.screens;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cs246.fencing_tournament.R;
import cs246.fencing_tournament.data.ContestantData;
import cs246.fencing_tournament.data.PoolData;
import cs246.fencing_tournament.data.TournamentData;

public class MainScreen extends AppCompatActivity {
    //List<ContestantData> transfer; // THIS IS JUST UNTIL WE HAVE TOURNAMENTDATA UP AND RUNNING
    List<PoolData> pools;

    TournamentData tournament;

    private TextView vContest;
    private Button bracketView;
    private Button poolView ;
    private Button eContest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        if (getIntent().hasExtra("Tournament"))
            tournament = getIntent().getParcelableExtra("Tournament");
        else tournament = new TournamentData();

        if (getIntent().hasExtra("ContestantsArray")) {
            List<ContestantData> tmpContestants = getIntent().getParcelableArrayListExtra("ContestantsArray");
            tournament.setContestants(tmpContestants);
        }
        vContest = (Button) findViewById(R.id.viewContestant);
        bracketView = (Button) findViewById(R.id.bracketView);
        poolView = (Button) findViewById(R.id.poolView);
        eContest = (Button) findViewById(R.id.enterContestant);
        if (vContest != null){
            vContest.setEnabled(true);
        }
        if (bracketView != null){
            bracketView.setEnabled(false);
        }
        if (poolView != null){
            if (tournament != null && tournament.getContestants().size() > 1){
                poolView.setEnabled(true);
            }
            else {
                poolView.setEnabled(false);
            }
        }
        if (eContest != null){
            eContest.setEnabled(true);
        }
    }

    public void viewContestants(View v) {
        Intent action = new Intent(MainScreen.this, ViewContestants.class);

        action.putParcelableArrayListExtra("ContestantsArray",(ArrayList<ContestantData>)tournament.getContestants());
        startActivity(action);
    }

    public void startPool(View v) {
        Intent action = new Intent(MainScreen.this, PoolScreen.class);
        tournament.generatePools();

        if(tournament.hasPools()) {
            // action.putParcelableArrayListExtra("PoolsArray", (ArrayList<PoolData>) tournament.getPools());
            action.putExtra("Tournament", tournament);
            startActivity(action);
        }
        eContest.setEnabled(false);
        bracketView.setEnabled(true);
    }

    public void startBracket(View v) {

    }

    public void setContestants(View v) {
        Intent action = new Intent(MainScreen.this, EnterContestant.class);

        action.putParcelableArrayListExtra("ContestantsArray",(ArrayList<ContestantData>)tournament.getContestants());
        startActivity(action);
    }
}