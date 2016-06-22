package cs246.fencing_tournament.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by alanxoc3 on 6/13/16.
 */
public class SwipeAdapter extends FragmentPagerAdapter {
    private Context context;

    public SwipeAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = new PoolFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pool_count", position+1);
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public int getCount() {
        return ViewSettings.NUM_OF_POOLS;
    }
}
