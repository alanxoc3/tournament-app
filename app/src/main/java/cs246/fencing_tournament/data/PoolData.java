package cs246.fencing_tournament.data;

import java.util.List;
import java.util.ArrayList;
import android.util.Log;

/**
 * Created by Austin on 6/10/2016.
 */
public class PoolData {
    private List <MatchData> matches;
    private static final String TAG = "PoolData";

    public PoolData()
    {
        matches = new ArrayList <MatchData>();
        Log.i(TAG, "Local matches variable created");
    }

    public void addMatch(MatchData newMatch) {
        matches.add(newMatch);
        Log.i(TAG,"Match Added Succesfully");
    }

    public MatchData getMatch(int matchNumber) {
        if (matchNumber >= matches.size() || matchNumber < 0) {
            Log.e(TAG, "Given Match does not exist");
            System.out.printf("Given Match does not exist, returning match 0");
            return matches.get(0);
        }
        return matches.get(matchNumber);
    }

    public List getMatches() {
        return matches;
    }
}