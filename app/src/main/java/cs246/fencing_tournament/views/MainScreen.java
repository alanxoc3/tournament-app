package cs246.fencing_tournament.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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

    public void setContestants(View v) {
        Intent action = new Intent(MainScreen.this, EnterContestant.class);
        List<ContestantData> transfer = new ArrayList<ContestantData>();
        Bundle b = new Bundle();
        b.putSerializable("List_Contestant",new ContestantData());
        startActivity(action);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }
}