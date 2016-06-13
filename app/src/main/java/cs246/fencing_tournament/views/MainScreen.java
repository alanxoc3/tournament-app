package cs246.fencing_tournament.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cs246.fencing_tournament.R;

public class MainScreen extends AppCompatActivity {

    public void viewContestants(View v) {

    }

    public void startPool(View v) {
        Intent action = new Intent(MainScreen.this, PoolScreen.class);
        startActivity(action);
    }

    public void startBracket(View v) {

    }

    public void setContestants(View v) {
        Intent action = new Intent(MainScreen.this, EnterContestant.class);
        startActivity(action);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }
}