package cs246.fencing_tournament;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import cs246.fencing_tournament.data.ContestantData;
import cs246.fencing_tournament.data.MatchData;
import cs246.fencing_tournament.data.PoolData;
import cs246.fencing_tournament.data.TournamentData;
import static org.junit.Assert.*;

/**
 * Created by Austin on 6/28/2016.
 * And Alan :)
 */
public class TournamentDataTest {
    @Test
    public void testTournament(){
        TournamentData tournament = new TournamentData();
        final int TEST_LEN = 7;
        for (int i = 0; i < TEST_LEN; ++i) {
            tournament.addContestant(new ContestantData("Person" + (i + 1)));
        }

        if(tournament.hasPools()) {
            System.out.println("We have pools");
        } else {
            System.out.println("We DO NOT have pools");
        }

        tournament.generatePools();
        List<PoolData> pools = tournament.getPools();
        for (int i = 0; i < pools.size(); ++i) {
            System.out.println("POOL " + (i + 1) + " | SIZE: " + pools.get(i).size() + " | WL: " + pools.get(i).getWL());
            for (int j = 0; j < pools.get(i).size(); ++j) {
                int id1 = pools.get(i).getMatch(j).getId1();
                int id2 = pools.get(i).getMatch(j).getId2();

                String name1 = ContestantData.findById(tournament.getContestants(), id1).getName();
                String name2 = ContestantData.findById(tournament.getContestants(), id2).getName();
                System.out.println("NAME 1: " + name1 + " | NAME 2: " + name2);
            }
            System.out.println();
        }

        tournament.deletePools();

        if(tournament.hasPools()) {
            System.out.println("We have pools");
        } else {
            System.out.println("We DO NOT have pools");
        }
    }
}