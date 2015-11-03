package com.example.trip.util;

import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2015-9-24.
 */
public abstract class BaseFragment extends Fragment {
    protected boolean isVisible;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        // TODO Auto-generated method stub
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            lazyLoadData();
        } else {
            isVisible = false;
        }
    }
    public abstract void lazyLoadData();

}
