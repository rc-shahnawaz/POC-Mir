package com.example.myfirstandroidapp;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.ListFragment;

import java.util.HashMap;
import java.util.List;


/**
 * Created by RoyalCyber on 7/22/2019.
 */
//Extending FragmentStatePagerAdapter
public class PagerAdapter extends FragmentStatePagerAdapter {
    //integer to count number of tabs
    int tabCount;
    Context context;
    HashMap<Integer, List<Object>> map;

    //Constructor to the class
    public PagerAdapter(Context context, FragmentManager fm, int tabCount, HashMap map) {
        super(fm);
        //Initializing tab count
        this.tabCount = tabCount;
        this.context = context;
        this.map = map;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        ListFragment fragment = new ListFragment();
        List list = this.map.get(0);
        ArrayAdapter adapter = new ArrayAdapter<>(this.context, android.R.layout.simple_list_item_1, list);
        fragment.setListAdapter(adapter);
        return fragment;
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }

}
