package android.com.demotablayout.adapter;

import android.com.demotablayout.fragment.FragmentHome;
import android.com.demotablayout.fragment.FragmentSearch;
import android.com.demotablayout.model.Cat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;


public class MyViewPagerAdapter extends FragmentStatePagerAdapter {


    public MyViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentHome();
            case 1:
                return new FragmentSearch();
            default:
                return new FragmentHome();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title ="";
        switch (position) {
            case 0:
                title ="Home";
                break;
            case 1:
                title ="Search";
                break;
        }
        return title;
    }
}
