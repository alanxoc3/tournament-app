package cs246.fencing_tournament.screens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import cs246.fencing_tournament.R;
import cs246.fencing_tournament.adapters.BracketCanvas;
import cs246.fencing_tournament.data.TournamentData;

public class BracketScreen extends AppCompatActivity {
    private TournamentData _tournament;
    private BracketCanvas _ercanbrack; // Only the best at BYUi
    public String TAG = "BracketScreen";

    public void onBackPressed() {
        Intent action = new Intent();
        action.putExtra("Tournament",_tournament);
        setResult(RESULT_OK, action);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bracket_screen);

        if (getIntent().hasExtra("Tournament")) {
            _tournament = getIntent().getParcelableExtra("Tournament");
        } else _tournament = null;

        _ercanbrack = (BracketCanvas) findViewById(R.id.view2);
        if (_tournament == null) Log.e(TAG, "Tournament is null with Ercanbrack.");
        _ercanbrack.setTournament(_tournament);
    }
}
