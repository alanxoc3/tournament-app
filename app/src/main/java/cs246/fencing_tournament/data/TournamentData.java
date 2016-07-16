package cs246.fencing_tournament.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Pools;
import android.util.Log;

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

    // Default Constructor
    public TournamentData() {
        contestants = new ArrayList<ContestantData>();
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

            /*
            Algorithm Explained.
            The first part figures out how large each pool will be, and how many pools there will
            be, based on the list of contestants.

            The second part uses the data from the first part to create a match, add the match to
            the correct pool and both contestants.
            */
        }
    }

    // TODO Check to make certain the bracket is filled in the proper order
    /**
     * Creates the Tournament Bracket based on results from the Pool
     * <p>
     * Copies the Contestant Data for all participants into a Deque and ranks them. The ranked
     * deque is used to create a the tournament bracket, stored as a vector implemented binary tree
     * of matches.
     */

    public void fillBracket(){
        LinkedList<ContestantData> contestantList;
    }
    /*
    public void fillBracket(){


        Deque <ContestantData> contestantSort = new ArrayDeque<ContestantData>();
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
    }*/

    // PARCELABLE STUFF

    @Override
    public int describeContents() {
        return 0;
    }

    protected TournamentData(Parcel in) {
        int loadStatus = in.readInt();

        contestants = null;
        pools = null;

        switch (loadStatus) {
            case 1:
                contestants = new ArrayList<ContestantData>();
                in.readList(contestants, ContestantData.class.getClassLoader());
                break;
            case 2:
                pools = new ArrayList<PoolData>();
                in.readList(pools, PoolData.class.getClassLoader());
                break;
            case 3:
                contestants = new ArrayList<ContestantData>();
                pools = new ArrayList<PoolData>();
                in.readList(contestants, ContestantData.class.getClassLoader());
                in.readList(pools, PoolData.class.getClassLoader());
                break;
            default: break;
        }

        // Bracket would go here, when it is ready.
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        int loadStatus = 0;
        if (pools == null && contestants != null) {
            loadStatus = 1;
        } else if (pools != null && contestants == null) {
            loadStatus = 2;
        } else if (pools != null) {
            loadStatus = 3;
        }

        dest.writeInt(loadStatus);

        switch (loadStatus) {
            case 1:
                dest.writeList(contestants);
                break;
            case 2:
                dest.writeList(pools);
                break;
            case 3:
                dest.writeList(contestants);
                dest.writeList(pools);
                break;
            default: break;
        }

        // Bracket would go here when it is ready.
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
