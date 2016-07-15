package cs246.fencing_tournament.screens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private String[] myStringArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contestants);
        contestants = getIntent().getParcelableArrayListExtra("ContestantsArray");

        if (contestants.size() != 0) {
            myStringArray = new String[contestants.size()];
            for (int i = 0; i < contestants.size(); ++i){
                myStringArray[i] = contestants.get(i).getName();
            }
        }
        else {
            myStringArray = new String[1];
            myStringArray[0] = "There are no Contestants";
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, myStringArray);

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
        }
        });
    }
}
