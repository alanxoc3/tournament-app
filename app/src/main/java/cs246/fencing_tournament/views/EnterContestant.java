package cs246.fencing_tournament.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    public void done(View v){
        if (contestants == null) {
            contestants = new ArrayList<ContestantData>();
        }

        EditText player = (EditText) findViewById(R.id.List);
        if(player != null)
            contestants.add(new ContestantData(player.getText().toString()));

        //contestants.add(new ContestantData("Jenna"));

        Intent action = new Intent(EnterContestant.this, MainScreen.class);

        action.putParcelableArrayListExtra("ContestantsArray",(ArrayList<ContestantData>)contestants);
        startActivity(action);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_contestant);
        contestants = getIntent().getParcelableArrayListExtra("ContestantsArray");
    }
}
