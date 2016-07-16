package cs246.fencing_tournament.screens;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cs246.fencing_tournament.R;
import cs246.fencing_tournament.adapters.SwipeAdapter;
import cs246.fencing_tournament.data.ContestantData;
import cs246.fencing_tournament.data.MatchData;
import cs246.fencing_tournament.data.PoolData;
import cs246.fencing_tournament.data.TournamentData;

public class PoolScreen extends AppCompatActivity {
	ViewPager viewPager;
    TournamentData tournament;
    List<PoolData> pools;
    public static final String TAG = "PoolScreen";

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "Result Code is: " + resultCode + " Request is: " + requestCode);
        if (RESULT_OK == resultCode) {
            // Setting the contestants.
            List<ContestantData> contestants = data.getParcelableArrayListExtra("ContestantsArray");
            tournament.getContestants().clear();
            tournament.getContestants().addAll(contestants);

            // Setting the pool/
            MatchData tmpMatch = data.getParcelableExtra("Match");
            int poolNum = data.getIntExtra("PoolNum", -1);
            int matchNum = data.getIntExtra("MatchNum", -1);

            if (poolNum == -1) Log.e(TAG, "Pool Num is " + poolNum);
            if (matchNum == -1) Log.e(TAG, "Match Num is " + matchNum);

            Log.e(TAG, "==========Victory: " + ContestantData.findById(contestants, tmpMatch.getVicId()).getName());
            Log.e(TAG, "==========MatchNum: " + matchNum);

            tournament.getPools().get(poolNum).getMatches().set(matchNum, tmpMatch);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool_screen);

        // pools = getIntent().getParcelableArrayListExtra("PoolsArray");
        tournament = getIntent().getParcelableExtra("Tournament");
        if (tournament == null) {
            Log.e("PoolScreen", "Tournament is NULL");
        }

        pools = tournament.getPools();
        if (pools == null) {
            Log.e("PoolScreen", "Pools are NULL");
        }

        Log.e("PoolScreen", "SIZE: " + pools.size());

		viewPager = (ViewPager) findViewById(R.id.pager);
		SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager(), this, tournament);

		viewPager.setAdapter(swipeAdapter);
    }
}