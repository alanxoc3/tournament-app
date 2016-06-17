package cs246.fencing_tournament.data;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by alanxoc3 on 6/8/16.
 */
public class ContestantData implements Serializable{
	private String _name;
	private int _id;
	private List<MatchData> _matchHistory;

	//these are to allow us to pass ContestantData between activity
	private void writeObject(java.io.ObjectOutputStream out)
			throws IOException{
		out.writeInt(_id);
		//out.writeObject(_matchHistory);  //in order to do this we have to use parcible instead of Serializable
		out.writeObject(_name);
	}
	private void readObject(java.io.ObjectInputStream in)
			throws IOException, ClassNotFoundException{
		_id = in.readInt();
		//_matchHistory = (List<MatchData>)in.readObject();
		_name = (String) in.readObject();
	}
	private void readObjectNoData()
			throws ObjectStreamException {
		_id = -1;
	}

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
