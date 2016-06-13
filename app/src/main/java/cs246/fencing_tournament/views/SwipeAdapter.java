package cs246.fencing_tournament.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by alanxoc3 on 6/13/16.
 */
public class SwipeAdapter extends FragmentStatePagerAdapter {
    public SwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("count", position+1);
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
