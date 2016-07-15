package cs246.fencing_tournament.screens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import cs246.fencing_tournament.R;
import cs246.fencing_tournament.data.ContestantData;

public class ViewPlayer extends AppCompatActivity {
    private ContestantData player;
    public static final String TAG = "ViewPlayer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_player);

        player = getIntent().getParcelableExtra("Player");
        if(player == null){
            Log.e(TAG, "Player did not get passed.");
        }

        // Display Player Stats
        TextView name = (TextView) findViewById(R.id.name);
        name.setText(player.getName());

        TextView wins = (TextView) findViewById(R.id.wins);
        wins.setText("Wins:  " + Integer.toString(player.getWins()));

        TextView losses = (TextView) findViewById(R.id.losses);
        losses.setText("Losses:  " + Integer.toString(player.getLosses()));

        TextView score = (TextView) findViewById(R.id.score);
        score.setText("Total Score:  " + Integer.toString(player.getTotalPoints()));
    }
}
