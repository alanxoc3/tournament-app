package cs246.fencing_tournament.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.Pools;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cs246.fencing_tournament.data.ContestantData;
import cs246.fencing_tournament.data.PoolData;
import cs246.fencing_tournament.data.TournamentData;

/**
 * Created by alanxoc3 on 6/13/16.
 */
public class SwipeAdapter extends FragmentPagerAdapter {
    private Context context;
    private TournamentData _tournament;

    public SwipeAdapter(FragmentManager fm, Context context, TournamentData tournament) {
        super(fm);
        this.context = context;
        _tournament = tournament;
    }

    @Override
    public Fragment getItem(int position) {
        PoolFragment frag = new PoolFragment();

        List<ContestantData> contestants = _tournament.getContestants();

        if (_tournament.hasPools())
            frag.setPool(_tournament.getPools().get(position));
        else Log.e("SwipeAdapter", "There are no pools within tournament.");

        frag.setContestantList(_tournament.getContestants());

        Bundle bundle = new Bundle();
        bundle.putInt("pool_count", position+1);
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public int getCount() {
        if (_tournament == null) Log.e("SwipeAdapter", "Tournament is null.");
        if (!_tournament.hasPools()) Log.e("SwipeAdapter", "No pools in tournament Bro.");
        return _tournament.getPools().size();
    }
}
