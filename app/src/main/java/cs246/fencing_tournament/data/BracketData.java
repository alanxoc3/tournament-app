package cs246.fencing_tournament.data;

import java.util.List;
import java.util.Vector;

/**
 * Created by Austin on 6/26/2016.
 */
public class BracketData {
    private List<MatchData> matches;

    public boolean searchEmpty() { return matches.isEmpty(); }
    public void add(int index, MatchData match) {matches.add(index, match);}
}