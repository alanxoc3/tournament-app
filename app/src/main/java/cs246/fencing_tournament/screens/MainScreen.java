package cs246.fencing_tournament.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cs246.fencing_tournament.R;
import cs246.fencing_tournament.data.ContestantData;
import cs246.fencing_tournament.data.MatchData;
import cs246.fencing_tournament.data.PoolData;
import cs246.fencing_tournament.data.TournamentData;

public class MainScreen extends AppCompatActivity {
    //List<ContestantData> transfer; // THIS IS JUST UNTIL WE HAVE TOURNAMENTDATA UP AND RUNNING
    TournamentData tournament;
    public String TAG = "MainScreen";

    private TextView vContest;
    private Button bracketView;
    private Button poolView ;
    private Button eContest;
    private boolean hasPools = false;

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
        if (!hasPools) {
            tournament.generatePools();
            hasPools = true;
        }

        if(tournament.hasPools()) {
            action.putExtra("Tournament", tournament);
            startActivityForResult(action, 5);
        }

        eContest.setEnabled(false);
        bracketView.setEnabled(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Both bracket and pool just take in a tournament.
        if ((requestCode == 5 || requestCode == 4) && resultCode == RESULT_OK && data != null) {
            if (data.hasExtra("Tournament")) {
                tournament = data.getParcelableExtra("Tournament");
                Log.e(TAG, "Got the Tournament");
            }
        }
        
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Log.i("onActivityResult", Boolean.toString(data.hasExtra("ContestantsArray")));
            if (data.hasExtra("ContestantsArray")) {
                List<ContestantData> tmpContestants = data.getParcelableArrayListExtra("ContestantsArray");
                tournament.setContestants(tmpContestants);
            }
            if (poolView != null){
                if (tournament != null && tournament.getContestants().size() > 1){
                    poolView.setEnabled(true);
                }
                else {
                    poolView.setEnabled(false);
                }
            }
        }
    }

    public void startBracket(View v) {
        Intent action = new Intent(MainScreen.this, BracketScreen.class);

        if(tournament.hasPools()) {
            // ALAN TEST FOR BRACKETDATA
            int powOf2 = (int) Math.pow(2, (int) Math.ceil( Math.log(tournament.getContestants().size()) / Math.log(2)));
            for (int i = 0; i < powOf2 - 1; ++i) {
                tournament.getBracket().add(new MatchData(tournament.getContestant(0).getId(), tournament.getContestant(1).getId()));
            }

            // Fill bracket would be called here I think.
            // action.putParcelableArrayListExtra("PoolsArray", (ArrayList<PoolData>) tournament.getPools());
            action.putExtra("Tournament", tournament);
            startActivityForResult(action, 4);
        }
    }

    public void setContestants(View v) {
        Intent action = new Intent(MainScreen.this, EnterContestant.class);

        action.putParcelableArrayListExtra("ContestantsArray",(ArrayList<ContestantData>)tournament.getContestants());
        startActivityForResult(action,1);
    }
}