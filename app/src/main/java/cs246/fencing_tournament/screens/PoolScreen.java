package cs246.fencing_tournament.screens;

import android.os.Bundle;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import cs246.fencing_tournament.R;
import cs246.fencing_tournament.adapters.SwipeAdapter;
import cs246.fencing_tournament.data.MatchData;
import cs246.fencing_tournament.data.PoolData;
import cs246.fencing_tournament.data.TournamentData;

public class PoolScreen extends AppCompatActivity {
	ViewPager viewPager;
    // List<PoolData> pools;
    TournamentData tournament;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool_screen);

        tournament = getIntent().getParcelableExtra("TOURNAMENT");

		viewPager = (ViewPager) findViewById(R.id.pager);
		SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager(), this, tournament);

		viewPager.setAdapter(swipeAdapter);
    }
}
