package cs246.fencing_tournament.screens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import cs246.fencing_tournament.R;
import cs246.fencing_tournament.data.ContestantData;


public class EnterContestant extends AppCompatActivity {

    public Button finished;

    private List<ContestantData> contestants;

    private static final String beforeTag = "#ContestentsBS";
    private static final String afterTag = "#ContestentsAS";
    private static final String BadNameTag = "BadName";

    public void done(View v){
        if (contestants == null) {
            contestants = new ArrayList<ContestantData>();
        }

        EditText player = (EditText) findViewById(R.id.List);
        if(player != null) {
            String [] temp = player.getText().toString().split("\n");
            for (int i = 0; i < temp.length; i++) {
                if (temp[i].equals("1"))
                    Log.e(BadNameTag, "You entered a bad name");
                contestants.add(new ContestantData(temp[i]));
            }

        }
        //contestants.add(new ContestantData("Jenna"));

        Intent action = new Intent(EnterContestant.this, MainScreen.class);

        if (contestants != null)
            Log.i(afterTag, Integer.toString(contestants.size()));

        action.putParcelableArrayListExtra("ContestantsArray",(ArrayList<ContestantData>)contestants);
        startActivity(action);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_contestant);
        contestants = getIntent().getParcelableArrayListExtra("ContestantsArray");
        if (contestants != null)
            Log.i(beforeTag, Integer.toString(contestants.size()));


    }
}
