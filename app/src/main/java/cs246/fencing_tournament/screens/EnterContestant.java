package cs246.fencing_tournament.screens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import cs246.fencing_tournament.R;
import cs246.fencing_tournament.data.ContestantData;


public class EnterContestant extends AppCompatActivity {

    public Button finished;

    public void done(View v){
        List<ContestantData> contestants = new ArrayList<ContestantData>();
        Intent action = new Intent(EnterContestant.this, MainScreen.class);

        action.putExtra("ActivityFrom",1);



        startActivity(action);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_contestant);

    }
}
