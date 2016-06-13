package cs246.fencing_tournament;

import org.junit.Test;
import java.util.List;

import cs246.fencing_tournament.data.MatchData;
import cs246.fencing_tournament.data.PoolData;

/**
 * Created by Austin on 6/10/2016.
 */
public class PoolDataTest {
    @Test
    public void testAddMatch() throws Exception {
        PoolData pd = new PoolData();
        System.out.println("Created Pool");
        MatchData newMatch = new MatchData(1, 2);
        System.out.println("Created New Match");
        pd.addMatch(newMatch);
        System.out.println("Added New Match");
        int id1 = pd.getMatch(0).getId1();
        assert (id1 == 1);
    }

    @Test
    public void testGetMatches() throws Exception {
        PoolData pd = new PoolData();
        for (int i = 0; i < 5; ++i) {
            MatchData newMatch = new MatchData(i, i+1);
            pd.addMatch(newMatch);
        }
        System.out.println("Created PoolData with 5 matches");
        List<MatchData> matchList = pd.getMatches();
        System.out.println("Pulled Matches using getMatches");
        for (int j = 0; j < matchList.size(); ++j) {
            System.out.println(matchList.get(j).getId1()+ " vs " + matchList.get(j).getId2());
            assert(matchList.get(j).equals(pd.getMatch(j)));
        }

    }
}