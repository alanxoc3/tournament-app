package cs246.fencing_tournament.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.Pools;

import java.util.List;

import cs246.fencing_tournament.data.PoolData;

/**
 * Created by alanxoc3 on 6/13/16.
 */
public class SwipeAdapter extends FragmentPagerAdapter {
    private Context context;
    private List<PoolData> _listOfPools;

    public SwipeAdapter(FragmentManager fm, Context context, List<PoolData> listOfPools) {
        super(fm);
        this.context = context;

        _listOfPools = listOfPools;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = new PoolFragment();
        ((PoolFragment)frag).setPool(_listOfPools.get(position));
        Bundle bundle = new Bundle();
        bundle.putInt("pool_count", position+1);
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public int getCount() {
        return _listOfPools.size();
    }
}
