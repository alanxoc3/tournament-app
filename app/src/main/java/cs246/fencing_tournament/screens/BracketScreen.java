package cs246.fencing_tournament.screens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cs246.fencing_tournament.R;
import cs246.fencing_tournament.adapters.BracketCanvas;
import cs246.fencing_tournament.data.BracketData;
import cs246.fencing_tournament.data.ContestantData;
import cs246.fencing_tournament.data.MatchData;
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
        _ercanbrack.setBracketScreen(this);
    }

    public void openDriver(int matchNum) {
        Intent intent = new Intent(this, Match_Driver.class);
        intent.putParcelableArrayListExtra("ContestantsArray", (ArrayList<ContestantData>) _tournament.getContestants());
        Log.e(TAG, "SIZE OF BRACKMATCH: " + _tournament.getBracket().matchesSize());
        intent.putExtra("Match", _tournament.getBracket().getMatch(matchNum));
        intent.putExtra("MatchNum", matchNum);
        startActivityForResult(intent, 6);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Both bracket and pool just take in a tournament.
        if (requestCode == 6 && resultCode == RESULT_OK && data != null) {
            int matchNum = -1;
            matchNum = data.getIntExtra("MatchNum", -2);
            if (matchNum == -2) Log.e(TAG, "MatchNum Not Passed");

            if (data.hasExtra("Match") && matchNum >= 0) {
                _tournament.getBracket().setMatch(matchNum,
                        (MatchData) data.getParcelableExtra("Match"));
                Log.e(TAG, "Got the Match");
            } else Log.e(TAG, "NO MATCH");
        }
    }
}