package cs246.fencing_tournament.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by alanxoc3 on 6/8/16.
 */
public class MatchData implements Parcelable {
    private int id1;
    private int p1Score;
    private int id2;
    private int p2Score;
    private int vicId;

    // Default Constructor
    public MatchData() {
        id1 = -2;
        id2 = -3;
        p1Score = 0;
        p2Score = 0;
        vicId = -1;
    }
    // Non Default Constructor
    public MatchData(int Id1, int Id2) {
        id1 = Id1;
        id2 = Id2;
        p1Score = 0;
        p2Score = 0;
        vicId = -1;
    }

    /**
     * So, this class contains an id.
     * If you give this method a list, the thing in the list with a matching id will be returned.
     * If no matching id is found, then null is returned.
     * @param conList
     * @return
     */
    public ContestantData getP1(List<ContestantData> conList) {
        return ContestantData.findById(conList, id1);
    }

    /**
     * See getP1() :)
     * @param conList
     * @return
     */
    public ContestantData getP2(List<ContestantData> conList) {
        return ContestantData.findById(conList, id2);
    }

    /**
     * Adds this match to the proper contestants.
     * @param conList A list of the contestants.
     */
    public void applyResults(List<ContestantData> conList) {
        ContestantData con1 = getP1(conList);
        ContestantData con2 = getP2(conList);

        con1.addMatch(this);
        con2.addMatch(this);
    }

    /**
     * Set the id for player 1
     * @param x
     */
    public void setId1(int x){
        id1 = x;
    }

    /**
     * Set the total score for player 1
     * @param x
     */
    public void setP1Score(int x){if (x >= 0 && x <= 15) p1Score = x;}
    public void setId2(int x){
        id2 = x;
    }
    public void setP2Score(int x){
        if (x >= 0 && x <= 15) p2Score = x;
    }
    public void setVicId(int x){
        vicId = x;
    }

    /**
     * Get the id for player 1.
     * @return
     */
    public int getId1(){
        return id1;
    }

    /**
     * Get the total score for player 1.
     * @return
     */
    public int getP1Score(){
        return p1Score;
    }
    public int getId2(){
        return id2;
    }
    public int getP2Score(){
        return p2Score;
    }

    /**
     * Get the player id of whoever won the match.
     * @return
     */
    public int getVicId(){
        return vicId;
    }
    public void pointP1() {setP1Score(getP1Score() + 1);}
    public void pointP2() {setP2Score(getP2Score() + 1);}

    @Override
    public int describeContents() {
        return 0;
    }

    protected MatchData(Parcel in) {
        int[] data = in.createIntArray();

        id1 = data[0];
        id2 = data[1];
        p1Score = data[2];
        p2Score = data[3];
        vicId = data[4];
	}

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        int[] data = new int[5];
        data[0] = id1;
        data[1] = id2;
        data[2] = p1Score;
        data[3] = p2Score;
        data[4] = vicId;
        dest.writeIntArray(data);
    }

    public static final Parcelable.Creator<MatchData> CREATOR = new Parcelable.Creator<MatchData>() {
		@Override
		public MatchData createFromParcel(Parcel in) {
			return new MatchData(in);
		}

		@Override
		public MatchData[] newArray(int size) {
			return new MatchData[size];
		}
	};
}
