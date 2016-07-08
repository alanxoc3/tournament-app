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

public class PoolScreen extends AppCompatActivity {
	ViewPager viewPager;
    List<PoolData> pools;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool_screen);

        pools = getIntent().getParcelableArrayListExtra("PoolsArray");
        Log.e("PoolScreen", "SIZE: " + pools.size());

		viewPager = (ViewPager) findViewById(R.id.pager);
		SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager(), this, pools);

		viewPager.setAdapter(swipeAdapter);
    }
}
