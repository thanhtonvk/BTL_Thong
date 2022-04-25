package com.example.cuahangbanphukien.Activity.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.example.cuahangbanphukien.Activity.Fragment.GioHangFragment;
import com.example.cuahangbanphukien.Activity.Fragment.TrangChuFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TrangChuFragment();
            case 1:
                return new GioHangFragment();

            default:
                return new TrangChuFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
