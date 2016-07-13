package cs246.fencing_tournament.screens;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.view.View;
import android.widget.ArrayAdapter;

import cs246.fencing_tournament.R;
import cs246.fencing_tournament.data.ContestantData;

import java.util.List;

public class ViewContestants extends ListActivity {

    public List<ContestantData> contestants;
    private String[] myStringArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contestants);
        contestants = getIntent().getParcelableArrayListExtra("ContestantsArray");

        if (contestants.size() != 0) {
            myStringArray = new String[contestants.size()];
            for (int i = 0; i < contestants.size(); ++i) {
                myStringArray[i] = contestants.get(i).getName();
            }
        } else {
            myStringArray = new String[1];
            myStringArray[0] = "There are no Contestants";
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, myStringArray);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView listView, View v, int position, long id) {
        super.onListItemClick(listView, v, position, id);
        for (int i = 0; i < myStringArray.length; ++i) {
            if (contestants.get(i).getName().contentEquals(myStringArray[position - 1])) {

            }
        }

    }
}

