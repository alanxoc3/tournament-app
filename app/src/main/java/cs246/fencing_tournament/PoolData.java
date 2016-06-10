package cs246.fencing_tournament;

import java.util.List;

/**
 * Created by Austin on 6/10/2016.
 */
public class PoolData {

    public PoolData() {};

    private List <MatchData> matches;

    public List getMatches() {
        return matches;
    }
    public void addMatch(MatchData newMatch) {
        matches.add(newMatch);
    }
}

