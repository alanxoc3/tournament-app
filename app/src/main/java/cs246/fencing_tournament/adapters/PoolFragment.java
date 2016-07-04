package cs246.fencing_tournament.adapters;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cs246.fencing_tournament.R;
import cs246.fencing_tournament.data.PoolData;

/**
 * A simple {@link Fragment} subclass.
 */
public class PoolFragment extends Fragment {
    private TextView textView;
    private PoolData _pool;

    public PoolFragment() {
        // Required empty public constructor
    }

    public void setPool(PoolData pool) {
        _pool = pool;
    }

    public PoolData getPool() {
        return _pool;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmentQ
        View view = inflater.inflate(R.layout.page_fragment_layout, container, false);
        textView = (TextView) view.findViewById(R.id.textView);
        Bundle bundle = getArguments();
        String message = Integer.toString(bundle.getInt("pool_count"));
        textView.setText("Pool " + message);

        PoolCanvas poolCanvas = (PoolCanvas) view.findViewById(R.id.view);
        poolCanvas.setPool(_pool);

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
