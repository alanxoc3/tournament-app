package cs246.fencing_tournament.screens;

import android.os.Bundle;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import cs246.fencing_tournament.R;
import cs246.fencing_tournament.adapters.SwipeAdapter;

public class PoolScreen extends AppCompatActivity {

	ViewPager viewPager;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool_screen);

			viewPager = (ViewPager) findViewById(R.id.pager);
			SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager(), this);
			viewPager.setAdapter(swipeAdapter);
    }
}
