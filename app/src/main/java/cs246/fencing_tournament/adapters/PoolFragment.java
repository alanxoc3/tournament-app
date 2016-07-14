package cs246.fencing_tournament.adapters;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cs246.fencing_tournament.R;
import cs246.fencing_tournament.data.ContestantData;
import cs246.fencing_tournament.data.PoolData;
import cs246.fencing_tournament.screens.MainScreen;
import cs246.fencing_tournament.screens.Match_Driver;

/**
 * A simple {@link Fragment} subclass.
 */
public class PoolFragment extends Fragment {
    private TextView textView;
    private PoolData _pool;
    private List<ContestantData> _contestantList;
    private int poolIndex;
    private boolean lockit;

    public PoolFragment() {
        // Required empty public constructor
    }

    public void setContestantList(List<ContestantData> contestantList) {
        if (contestantList == null) Log.e("PoolFragment", "Setting contestant list to null.");
        _contestantList = contestantList;
    }

    public List<ContestantData> getContestantList() {
        return _contestantList;
    }

    public void setPool(PoolData pool) {
        if (pool == null) Log.e("PoolFragment", "Setting pool to null");
        _pool = pool;
    }

    public PoolData getPool() {
        return _pool;
    }

    @Override
    public void onResume() {
        super.onResume();
        lockit = false;
    }

    @Override
    public void onStop() {
        super.onStop();
        lockit = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmentQ
        View view = inflater.inflate(R.layout.page_fragment_layout, container, false);
        textView = (TextView) view.findViewById(R.id.textView);
        Bundle bundle = getArguments();
        poolIndex = bundle.getInt("pool_count") - 1;
        textView.setText("Pool " + Integer.toString(poolIndex + 1));

        PoolCanvas poolCanvas = (PoolCanvas) view.findViewById(R.id.view);
        poolCanvas.setPool(_pool);
        poolCanvas.setContestantList(_contestantList);
        poolCanvas.setPoolFragment(this);

        lockit = false;

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void openDriver(int matchNum) {
        // Intent action = new Intent(EnterContestant.this, MainScreen.class);

        // if (contestants != null)
        //    Log.i(afterTag, Integer.toString(contestants.size()));

        // action.putParcelableArrayListExtra("ContestantsArray",(ArrayList<ContestantData>)contestants);
        // startActivity(action);
        if (_pool.isValidMatch(matchNum) && !lockit) {
            lockit = true;
            Intent intent = new Intent(getActivity(), Match_Driver.class);
            intent.putParcelableArrayListExtra("ContestantsArray", (ArrayList<ContestantData>) _contestantList);
            intent.putExtra("pool_data", _pool);
            intent.putExtra("pool_count", poolIndex);
            intent.putExtra("match_num", matchNum);
            getActivity().startActivity(intent);
        }
    }
}