package cs246.fencing_tournament;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import cs246.fencing_tournament.data.BracketData;
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
    /*
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
*/
    /*
    @Test
    public void testSort(){
        TournamentData tournament = new TournamentData();
        List <ContestantData> contestants = new ArrayList<>();
        BracketData bracket = new BracketData();

        ContestantData contestant1 = new ContestantData("James");
        ContestantData contestant2 = new ContestantData("John");
        ContestantData contestant3 = new ContestantData("Joseph");
        ContestantData contestant4 = new ContestantData("Jared");
        ContestantData contestant5 = new ContestantData("Jack");
        ContestantData contestant6 = new ContestantData("Joe");

        contestants.add(contestant1);
        contestants.add(contestant2);
        contestants.add(contestant3);
        contestants.add(contestant4);
        contestants.add(contestant5);
        contestants.add(contestant6);

        MatchData match1 = new MatchData(contestant1.getId(), contestant2.getId());
        MatchData match2 = new MatchData(contestant3.getId(), contestant4.getId());
        MatchData match3 = new MatchData(contestant5.getId(), contestant6.getId());

        match1.setP1Score(1);
        match1.setP2Score(4);
        match1.setVicId(match1.getId2());
        match1.applyResults(contestants);

        match2.setP1Score(2);
        match2.setP2Score(5);
        match2.setVicId(match2.getId2());
        match2.applyResults(contestants);

        match3.setP1Score(3);
        match3.setP2Score(6);
        match3.setVicId(match3.getId2());
        match3.applyResults(contestants);

        int n = contestants.size();
        int k;

        for (int m = n; m >= 0; m--) {
            for (int i = 0; i < n - 1; i++) {
                k = i + 1;
                if (contestants.get(i).greaterThan(contestants.get(k))) {
                    ContestantData temp = new ContestantData(contestants.get(i));
                    contestants.set(i,contestants.get(k));
                    contestants.set(k, temp);
                }
            }
        }

        Vector<LinkedList<ContestantData>> listVector = new Vector<>();

        // Determine number of contestants and byes on the lowest level of the bracket.
        int numContestants = 1;
        while (numContestants < contestants.size()){
            numContestants *=2;
        }
        int numByes = numContestants - contestants.size();
        int totalMatches = numContestants - 1;

        // Add the byes to the end of the sorted contestant list
        while (numByes > 0){
            ContestantData bye = new ContestantData("bye");
            contestants.add(bye);
            --numByes;
        }

        // Create linked lists for each contestant playing
        for (int i = 0; i < contestants.size(); i++) {
            LinkedList<ContestantData> tempList = new LinkedList<ContestantData>();
            tempList.add(contestants.get(i));
            listVector.add(tempList);
        }

        // Sort the contestants until they are sorted by pairs in the order of the bracket
        while (listVector.size() > 1) {
            int j = listVector.size() - 1;
            int i;
            int m = listVector.size() / 2;
            for (i = 0; i < m; ++i) {
                listVector.trimToSize();
                listVector.get(i).addAll(listVector.get(j));
                listVector.removeElementAt(j);
                --j;
            }
        }

        for (int i = 0; i < totalMatches; ++i){
            MatchData emptyMatch = new MatchData();
            bracket.add(i,emptyMatch);
        }
        // Go through the sorted contestants pairing them up and adding them to the bracket
        for (int i = 0; i < listVector.firstElement().size(); i += 2) {
            int id1 = listVector.firstElement().get(i).getId();
            int id2 = listVector.firstElement().get(i + 1).getId();
            MatchData newMatch = new MatchData(id1, id2);
            bracket.set(totalMatches - (i / 2) - 1, newMatch);
        }

        for (int z = 0; z < bracket.matchesSize(); z ++){
            System.out.printf("Match number " + z + "\n");
            System.out.printf(Integer.toString(bracket.getMatch(z).getId1()) + "  ");
            System.out.printf(Integer.toString(bracket.getMatch(z).getId2()) + "\n");
        }
    }
    */
    @Test
    public void testFill() {
        TournamentData tournament = new TournamentData();
        List<ContestantData> contestants = new ArrayList<>();
        BracketData bracket = new BracketData();

        ContestantData contestant1 = new ContestantData("James");
        ContestantData contestant2 = new ContestantData("John");
        ContestantData contestant3 = new ContestantData("Joseph");
        ContestantData contestant4 = new ContestantData("Jared");
        ContestantData contestant5 = new ContestantData("Jack");
        ContestantData contestant6 = new ContestantData("Joe");

        contestants.add(contestant1);
        contestants.add(contestant2);
        contestants.add(contestant3);
        contestants.add(contestant4);
        contestants.add(contestant5);
        contestants.add(contestant6);

        MatchData match1 = new MatchData(contestant1.getId(), contestant2.getId());
        MatchData match2 = new MatchData(contestant3.getId(), contestant4.getId());
        MatchData match3 = new MatchData(contestant5.getId(), contestant6.getId());

        match1.setP1Score(1);
        match1.setP2Score(4);
        match1.setVicId(match1.getId2());
        match1.applyResults(contestants);

        match2.setP1Score(2);
        match2.setP2Score(5);
        match2.setVicId(match2.getId2());
        match2.applyResults(contestants);

        match3.setP1Score(3);
        match3.setP2Score(6);
        match3.setVicId(match3.getId2());
        match3.applyResults(contestants);

        int n = contestants.size();
        int k;

        for (int m = n; m >= 0; m--) {
            for (int i = 0; i < n - 1; i++) {
                k = i + 1;
                if (contestants.get(i).greaterThan(contestants.get(k))) {
                    ContestantData temp = new ContestantData(contestants.get(i));
                    contestants.set(i, contestants.get(k));
                    contestants.set(k, temp);
                }
            }
        }
        System.out.println("About to call fillBracket");
        tournament.setContestants(contestants);
        tournament.fillBracket();
    }
}