package cs246.fencing_tournament.adapters;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cs246.fencing_tournament.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PoolFragment extends Fragment {
    private TextView textView;

    public PoolFragment() {
        // Required empty public constructor
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
        return view;
    }
}
