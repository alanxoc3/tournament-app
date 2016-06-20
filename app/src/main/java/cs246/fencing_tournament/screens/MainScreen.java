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

public class MainScreen extends AppCompatActivity {



    public void viewContestants(View v) {

    }

    public void startPool(View v) {
        Intent action = new Intent(MainScreen.this, PoolScreen.class);
        startActivity(action);
    }

    public void startBracket(View v) {

    }

    List<ContestantData> transfer;              //THIS IS JUST UNTIL WE HAVE TOURNAMENTDATA UP AND RUNNING

    public void setContestants(View v) {
        Intent action = new Intent(MainScreen.this, EnterContestant.class);

        if (transfer == null)
            transfer = new ArrayList<ContestantData>();

        action.putParcelableArrayListExtra("ContestantsArray",(ArrayList<ContestantData>)transfer); //transfer);
        startActivity(action);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        transfer = getIntent().getParcelableArrayListExtra("ContestantsArray");

    }
}