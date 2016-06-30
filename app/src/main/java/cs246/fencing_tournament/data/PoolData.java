package cs246.fencing_tournament.data;

import java.util.List;
import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by Austin on 6/10/2016.
 */
public class PoolData implements Parcelable {
    /**
     * List of MatchData that describes the Tournament Pool
     */
    private List <MatchData> matches;
    private static final String TAG = "PoolData";

    public PoolData()
    {
        matches = new ArrayList <MatchData>();
        Log.i(TAG, "Local matches variable created");
    }

    /**
     * Add a match to the pool.
     * @param newMatch Match to be added to the pool.
     */
    public void addMatch(MatchData newMatch) {
        matches.add(newMatch);
        Log.i(TAG,"Match Added Succesfully");
    }

    /**
     * Get a given match.
     * <p>
     * Given a match number, the pool is searched for that specific match, which is returned if it
     * exists.
     * @param matchNumber Number for the requested match.
     * @return The specific match requested.
     */
    public MatchData getMatch(int matchNumber) {
        if (matchNumber >= matches.size() || matchNumber < 0) {
            Log.e(TAG, "Given Match does not exist");
            System.out.printf("Given Match does not exist, returning match 0");
            return matches.get(0);
        }
        return matches.get(matchNumber);
    }

    /**
     * Returns the list of matches that describes the Tournament Pool
     * @return The list of matches in the pool
     */
    public List getMatches() {
        return matches;
    }

    /**
     * Get Width Length
     * <p>
     * Since it is a square, width and length are the same.
     * @return the width or the length
     */
    public int getWL() {
        // Testing
        return 5;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected PoolData(Parcel in) {
		if (in.readByte() == 0x01) {
			matches = new ArrayList<MatchData>();
			in.readList(matches, MatchData.class.getClassLoader());
		} else {
			matches = null;
		}
	}

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (matches == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(matches);
        }
    }

    public static final Parcelable.Creator<PoolData> CREATOR = new Parcelable.Creator<PoolData>() {
		@Override
		public PoolData createFromParcel(Parcel in) {
			return new PoolData(in);
		}

		@Override
		public PoolData[] newArray(int size) {
			return new PoolData[size];
		}
	};
}