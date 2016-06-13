package cs246.fencing_tournament.data;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by Austin on 6/10/2016.
 */
public class PoolData {
    private List <MatchData> matches;

    public PoolData()
    {
        matches = new ArrayList <MatchData>();
    }

    public void addMatch(MatchData newMatch) {
        
        matches.add(newMatch);
    }

    public MatchData getMatch(int matchNumber) {

        return matches.get(matchNumber);
    }

    public List getMatches() {

        return matches;
    }
}

