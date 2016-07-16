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
    }

    /**
     * Add a match to the pool.
     * @param newMatch Match to be added to the pool.
     */
    public void addMatch(MatchData newMatch) {
        matches.add(newMatch);
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
            Log.e(TAG, "Given Match does not exist. Null returned.");
            return null;
        }

        int row = matchNumber / getWL();
        int col = matchNumber % getWL();

        if (row < col) {
            int tmp = row;
            row = col;
            col = tmp;
        }

        return matches.get(row * getWL() + col);
    }

    /**
     * Returns true if the pool contains that match. And that match isn't a "self vs self match".
     * @param matchNumber
     * @return
     */
    public boolean isValidMatch(int matchNumber) {
        return (matchNumber < matches.size() && matchNumber >= 0)
                && (matchNumber % (getWL() + 1) != 0);
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
     * Since it is a square, width and length are the same.
     * This should simply be the square root of the number of matches.
     * @return the width or the length
     */
    public int getWL() {
        return (int) Math.sqrt(matches.size());
    }

    /**
     * Returns the number of matches that the pool contains.
     * @return
     */
    public int size() {
        return matches.size();
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