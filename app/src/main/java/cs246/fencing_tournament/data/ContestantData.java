package cs246.fencing_tournament.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by alanxoc3 on 6/8/16.
 */
public class ContestantData implements Parcelable{
	private String _name;
	private int _id;
	private List<MatchData> _matchHistory;

	// Default Constructor
	public ContestantData() {
		this("");
	}

	// Non Default Constructor
	public ContestantData(String name) {
		_id = nextId();
		_matchHistory = new ArrayList<MatchData>();
		this._name = name;
	}

	// Copy Constructor
	public ContestantData(ContestantData rhs) {
		_id = rhs.getId();
		_matchHistory = rhs.getMatches();
		this._name = rhs.getName();
	}

	public void addMatch(MatchData newMatch) {
		if (newMatch != null) {
			_matchHistory.add(newMatch);
		}
	}

	public void clearHistory() {
		_matchHistory.clear();
	}

	public List<MatchData> getMatches() {
		return _matchHistory;
	}
	public int getId() {
		return _id;
	}
	public String getName() {
		return _name;
	}

    public void setName(String name) { this._name = name; }

    // Returns true if this guy is greater than that guy
    public boolean greaterThan(ContestantData contestant) {
		if (getWinRate() > contestant.getWinRate())
			return true;
		else if (getWinRate() < contestant.getWinRate())
			return false;
		else if (getPointDifference() > contestant.getPointDifference())
			return true;
		else if (getPointDifference() < contestant.getPointDifference())
			return false;
		else if (getTotalPoints() > contestant.getTotalPoints())
			return true;
		else if (getTotalPoints() < contestant.getTotalPoints())
			return false;
		else
			// Tie case, return true by default
			return true;
	}

    public int getWins() {
		int wins = 0;
		for (int i = 0; i < _matchHistory.size(); ++i){
			if (_matchHistory.get(i).getVicId() == _id){
				wins++;
			}
		}
		return wins;
    }

	public int getLosses() {
		int losses = 0;
		for (int i = 0; i < _matchHistory.size(); ++i) {
			if (_matchHistory.get(i).getVicId() != _id) {
				losses++;
			}
		}
		return losses;
	}

	public float getWinRate() {
		return (getWins() / _matchHistory.size());
	}

	// Return total of points scored by contestant from each match
    public int getTotalPoints() {
		int points = 0;
		for (int i = 0; i < _matchHistory.size(); ++i) {
			if (_id == _matchHistory.get(i).getId1())
				points += _matchHistory.get(i).getP1Score();
			else if (_id == _matchHistory.get(i).getId2())
				points += _matchHistory.get(i).getP2Score();
			else
				System.out.printf("ERROR: Contestant did not participate in this match");
			}
		return points;
    }

	// Returns total of points scored by opponents from each match
    public int getTotalPointsAgainst() {
		int pointsAgainst = 0;
		for (int i = 0; i < _matchHistory.size(); ++i) {
			if (_id == _matchHistory.get(i).getId1())
				pointsAgainst += _matchHistory.get(i).getP2Score();
			else if (_id == _matchHistory.get(i).getId2())
				pointsAgainst += _matchHistory.get(i).getP1Score();
			else
				System.out.printf("ERROR: Contestant did not participate in this match");
		}
		return pointsAgainst;
    }

	// Return composite score of points given and recieved.
	public int getPointDifference() {
		return (getTotalPoints() - getTotalPointsAgainst());
	}

	// For making the id bigger each time.
	private static int globalId = 0;

	private static int nextId() {
		return globalId++;
	}

	// Return contestant specified by given ID
    public static ContestantData findById(List<ContestantData> contestants, int id) {
		for (int i = 0; i < contestants.size(); ++i) {
			if (contestants.get(i).getId() == id) {
				return contestants.get(i);
			}
		}
		System.out.printf("There is not contestant with the given ID");
		return new ContestantData();
    }

	protected ContestantData(Parcel in) {
		_name = in.readString();
		_id = in.readInt();
		if (in.readByte() == 0x01) {
			_matchHistory = new ArrayList<MatchData>();
			in.readList(_matchHistory, MatchData.class.getClassLoader());
		} else {
			_matchHistory = null;
		}
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(_name);
		dest.writeInt(_id);
		if (_matchHistory == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeList(_matchHistory);
		}
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<ContestantData> CREATOR = new Parcelable.Creator<ContestantData>() {
		@Override
		public ContestantData createFromParcel(Parcel in) {
			return new ContestantData(in);
		}

		@Override
		public ContestantData[] newArray(int size) {
			return new ContestantData[size];
		}
	};
}