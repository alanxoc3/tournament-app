package cs246.fencing_tournament.screens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.content.Intent;
import cs246.fencing_tournament.R;
import cs246.fencing_tournament.data.ContestantData;

import java.util.List;

public class ViewContestants extends AppCompatActivity {

    public List <ContestantData> contestants;
    private String[] nameArray;
    public static final String TAG = "ViewContestants";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contestants);
        contestants = getIntent().getParcelableArrayListExtra("ContestantsArray");
        if(contestants == null){
            Log.e(TAG, "Contestants not passed");
        }

        if (contestants.size() != 0) {
            nameArray = new String[contestants.size()];
            for (int i = 0; i < contestants.size(); ++i){
                nameArray[i] = contestants.get(i).getName();
            }
        }
        else {
            Log.i(TAG, "No contestants Entered");
            nameArray = new String[1];
            nameArray[0] = "There are no Contestants";
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nameArray);

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent action = new Intent(ViewContestants.this, ViewPlayer.class);
                if (!contestants.isEmpty()) {
                    action.putExtra("Player", contestants.get(position));
                    startActivity(action);
                }
                else {
                Log.i(TAG, "Attempted to ViewPlayer with no contestants in the list");
                }
        }
        });
    }
}
