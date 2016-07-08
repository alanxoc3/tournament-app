package cs246.fencing_tournament.screens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import cs246.fencing_tournament.R;
import cs246.fencing_tournament.data.ContestantData;
import java.util.List;

public class ViewContestants extends AppCompatActivity {

    public List <ContestantData> contestants;

    public void displayContestants() {
        for (int i = 0; i < contestants.size(); ++i){

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contestants);
    }
}
