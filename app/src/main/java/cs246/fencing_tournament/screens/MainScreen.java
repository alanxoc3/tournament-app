package cs246.fencing_tournament.screens;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        tournament = new TournamentData();

        List<ContestantData> tmpContestants = getIntent().getParcelableArrayListExtra("ContestantsArray");
        tournament.setContestants(tmpContestants);
    }

    public void viewContestants(View v) {

    }

    public void startPool(View v) {
        Intent action = new Intent(MainScreen.this, PoolScreen.class);
        tournament.generatePools();

        if(tournament.hasPools()) {
            action.putParcelableArrayListExtra("PoolsArray", (ArrayList<PoolData>) tournament.getPools());
            startActivity(action);
        }
    }

    public void startBracket(View v) {

    }

    public void setContestants(View v) {
        Intent action = new Intent(MainScreen.this, EnterContestant.class);

        action.putParcelableArrayListExtra("ContestantsArray",(ArrayList<ContestantData>)tournament.getContestants());
        startActivity(action);
    }

}