package cs246.fencing_tournament.data;

import java.util.Vector;
import java.util.Deque;
import java.util.List;
/**
 * Created by Austin on 6/26/2016.
 */
public class TournamentData {
    private List <PoolData> pool;
    private List <ContestantData> contestants;
    Deque <ContestantData> contestantSort;
    private BracketData bracket;

    // Function to use the information in the contestant list to create the bracket.
    public void fillBracket(){
        contestantSort.addAll(contestants);
        int numMatches = ((contestantSort.size() + 1) / 2);

        // If there is an even number of contestants, create the first match normally
        if ( (contestantSort.size() & 1) == 0 ) {
            int id1 = contestantSort.pollFirst().getId();
            int id2 = contestantSort.pollFirst().getId();
            MatchData newMatch = new MatchData(id1, id2);
            bracket.add(numMatches + numMatches -1, newMatch);
        }
        // Else create the first match with the best contestant and an empty contestant
        else {
            int id1 = contestantSort.pollFirst().getId();
            MatchData newMatch = new MatchData(id1, -1);
            bracket.add(numMatches + numMatches -1, newMatch);
        }
        // Loop through the rest of the deque, creating a match with the current best and worst players
        for (int i = 1; i < numMatches; ++i) {
            int id1 = contestantSort.pollFirst().getId();
            int id2 = contestantSort.pollFirst().getId();
            MatchData newMatch = new MatchData(id1, id2);
            bracket.add(numMatches + numMatches - 1 - i, newMatch);
        }
    }
}
