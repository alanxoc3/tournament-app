package cs246.fencing_tournament.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Austin on 6/26/2016.
 */
public class BracketData implements Parcelable {
    private List<MatchData> matches;

    public BracketData() {
        matches = new ArrayList<MatchData>();
    }

    public boolean isEmpty() { return matches.isEmpty(); }
    public void add(int index, MatchData match) {matches.add(index, match);}

    public MatchData getMatch(int index) {
        return matches.get(index);
    }

    public int matchesSize() {
        return matches.size();
    }

    // PARCELABLE STUFF
    @Override
    public int describeContents() {
        return 0;
    }

    protected BracketData(Parcel in) {
        if (in.readByte() == 0x01) {
            matches = new ArrayList<>();
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

    public static final Parcelable.Creator<BracketData> CREATOR = new Parcelable.Creator<BracketData>() {
        @Override
        public BracketData createFromParcel(Parcel in) {
            return new BracketData(in);
        }

        @Override
        public BracketData[] newArray(int size) {
            return new BracketData[size];
        }
    };
}