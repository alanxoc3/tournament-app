package cs246.fencing_tournament;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by alanxoc3 on 6/8/16.
 */
public class ContestantData {
	private String _name;
	private int _id;
	private List<MatchData> _matchHistory;

	public ContestantData() {
		this("");
	}

	public ContestantData(String name) {
		_id = nextId();
		_matchHistory = new ArrayList<MatchData>();
		this._name = name;
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

	// For making the id bigger each time.
	private static int globalId = 0;

	private static int nextId() {
		return globalId++;
	}
}
