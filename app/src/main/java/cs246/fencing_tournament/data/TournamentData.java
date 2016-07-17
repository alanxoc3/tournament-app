package cs246.fencing_tournament.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Pools;
import android.util.Log;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;
import java.util.Deque;
import java.util.List;

/**
 * The tournament data class is in charge of all the tournament logic and what not.
 * It contains the different classes that have individual logic.
 * Created by Austin on 6/26/2016.
 */
public class TournamentData implements Parcelable {
    private List <PoolData> pools;
    private List <ContestantData> contestants;
    private BracketData bracket;
    private String TAG = "TournamentData";

    // Default Constructor
    public TournamentData() {
        contestants = new ArrayList<>();
        bracket = new BracketData();
    }

    public BracketData getBracket() {
        return bracket;
    }

    public void setBracket(BracketData bracket2) {
        bracket = bracket2;
    }

    public void addContestant(ContestantData newContestant) {
        if (contestants == null) {
            Log.e("TournamentData", "Contestants is null");
            return;
        }

        contestants.add(newContestant);
    }

    public void setContestants(List<ContestantData> contestants) {
        if (contestants != null) {
            this.contestants = contestants;
        }
    }

    public ContestantData getContestant(int index) {
        if (contestants == null) {
            Log.e("TournamentData", "Contestants is null");
            return null;
        }

        return contestants.get(index);
    }

    public List<ContestantData> getContestants() {
        if (contestants == null)
            Log.e("TournamentData", "Contestants is null");
        return contestants;
    }

    public List<PoolData> getPools() {
        return pools;
    }

    public boolean hasPools() {
        return (pools != null);
    }

    public void deletePools() {
        pools = null;
    }

    public void updatePools() {
        for (int i = 0; i < pools.size(); ++i) {
            pools.get(i).syncMatches();
        }
    }
    /**
     * Uses the contestant list to create the pools. The more contestants there are, the more pools
     * will be created. This will not run if there are less than 2 contestants.
     */
    public void generatePools() {
        // RESET THE POOL
        if (contestants == null) {
            Log.e("TournamentData", "Contestants is NULL");
            return;
        }

        if (contestants.size() < 2) {
            Log.e("TournamentData", "Less than 2 contestants.");
            return;
        }

        deletePools();
        pools = new ArrayList<PoolData>();
        final int MAX_POOL_LEN = 6;

        // FIGURE OUT HOW MANY CONTESTANTS GO INTO EACH POOL.
        int numOfPeeps = contestants.size();
        List<Integer> poolLens = new ArrayList<Integer>();
        // If there are 7 people, there are now 2 pools.
        int numOfPools = numOfPeeps / MAX_POOL_LEN + (numOfPeeps % MAX_POOL_LEN != 0 ? 1 : 0);
        int averagePoolLen = numOfPeeps / numOfPools;

        int remainder = numOfPeeps % numOfPools;
        for (int i = 0; i < numOfPools; ++i) {
            if (remainder > 0) {
                poolLens.add(averagePoolLen + 1);
                --remainder;
            } else {
                poolLens.add(averagePoolLen);
            }
            // We want to add a pool as well
            pools.add(new PoolData());
        }

        // NOW POPULATE THE POOLS
        int prevContestantCount = 0;
        for (int poolSpot = 0; poolSpot < numOfPools; ++poolSpot) {
            for (int i = prevContestantCount; i < poolLens.get(poolSpot) + prevContestantCount; ++i) {
                for (int j = prevContestantCount; j < poolLens.get(poolSpot) + prevContestantCount; ++j) {
                    // If you were to uncomment this, then there would be no me vs me matches in the
                    // pool.
                    //if (i != j) {
                    int id1 = contestants.get(i).getId();
                    int id2 = contestants.get(j).getId();
                    MatchData newMatch = new MatchData(id1, id2);
                    pools.get(poolSpot).addMatch(newMatch);
                    contestants.get(i).addMatch(newMatch);
                    contestants.get(j).addMatch(newMatch);
                    //}
                }
            }

            // Make sure that you are continuing on the contestant streak.
            prevContestantCount += poolLens.get(poolSpot);
        }
            /*
            Algorithm Explained.
            The first part figures out how large each pool will be, and how many pools there will
            be, based on the list of contestants.

            The second part uses the data from the first part to create a match, add the match to
            the correct pool and both contestants.
            */

        for (int i = 0; i < pools.size(); ++i) {
            pools.get(i).syncMatches();
        }
    }

    public void sortContestants(){
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
    }

    /**
     * Creates the Tournament Bracket based on results from the Pool
     * <p>
     * Copies the Contestant Data for all participants into a vector of linked lists. The linked
     * lists are connected together to sort them into the pairs for the bracket. This final list
     * is used to create a the tournament bracket, stored as a vector implemented binary tree
     * of matches.
     */

    public void fillBracket() {
        Vector<LinkedList<ContestantData>> listVector = new Vector<>();
        System.out.println(contestants.size());
        sortContestants();
        System.out.println(contestants.size());

        // Determine number of contestants and byes on the lowest level of the bracket.
        int numContestants = 1;
        while (numContestants < contestants.size()){
            numContestants *= 2;
        }

        int numByes = numContestants - contestants.size();
        int totalMatches = numContestants - 1;

        // Add the byes to the end of the sorted contestant list
        while (numByes > 0) {
            ContestantData bye = new ContestantData("bye");
            contestants.add(bye);
            numByes--;

        }
        System.out.println("About to Create vector of lists");
        System.out.println(contestants.size());
        // Create linked lists for each contestant playing
        for (int i = 0; i < contestants.size(); i++) {
            LinkedList<ContestantData> tempList = new LinkedList<ContestantData>();
            System.out.println("About to add new lists");
            tempList.add(contestants.get(i));
            System.out.println("About to setElementAt");
            listVector.add(tempList);
            System.out.println("Just setElement");
        }
        System.out.println("Created vector of lists");

        // Sort the contestants until they are sorted by pairs in the order of the bracket
        System.out.println(listVector.size());
        while (listVector.size() > 1) {
            int j = listVector.size() - 1;
            int i;
            int k = listVector.size() / 2;
            for (i = 0; i < k; ++i) {
                listVector.trimToSize();
                listVector.get(i).addAll(listVector.get(j));
                listVector.removeElementAt(j);
                --j;
                System.out.println(listVector.size());
            }
        }
        System.out.println(listVector.get(0).size());
        System.out.println("About to push empty matches");
        for (int i = 0; i <= totalMatches; ++i){
            MatchData emptyMatch = new MatchData();
            bracket.add(i,emptyMatch);
        }
        System.out.println("Pushed empty matches");
        // Go through the sorted contestants pairing them up and adding them to the bracket
        for (int i = 0; i < listVector.firstElement().size(); i += 2) {
            System.out.println("About to get id1");
            int id1 = listVector.firstElement().get(i).getId();
            System.out.println("About to get id2");
            int id2 = listVector.firstElement().get(1).getId();
            System.out.println("About to create");
            MatchData newMatch = new MatchData(id1, id2);
            System.out.println("About to set bracket");
            bracket.set(totalMatches - (i/2) - 1, newMatch);
        }
    }

    // PARCELABLE STUFF

    @Override
    public int describeContents() {
        return 0;
    }

    protected TournamentData(Parcel in) {
        contestants = new ArrayList<>();
        pools = new ArrayList<>();
        in.readList(contestants, ContestantData.class.getClassLoader());
        in.readList(pools, PoolData.class.getClassLoader());
        bracket = in.readParcelable(BracketData.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(contestants);
        dest.writeList(pools);
        dest.writeParcelable(bracket, flags);

        if (contestants == null || pools == null) {
            Log.e(TAG, "WE ARE NULL HERE IN THE TOURNAMENT!!!!");
        }
    }

    public static final Parcelable.Creator<TournamentData> CREATOR = new Parcelable.Creator<TournamentData>() {
        @Override
        public TournamentData createFromParcel(Parcel in) {
            return new TournamentData(in);
        }

        @Override
        public TournamentData[] newArray(int size) {
            return new TournamentData[size];
        }
    };
}
