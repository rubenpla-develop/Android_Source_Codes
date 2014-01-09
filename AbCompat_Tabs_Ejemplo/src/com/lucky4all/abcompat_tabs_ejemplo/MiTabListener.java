package com.lucky4all.abcompat_tabs_ejemplo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.util.Log;
 
public class MiTabListener implements TabListener {
 
    private Fragment fragment;
 
    public MiTabListener(Fragment fg)
    {
        this.fragment = fg;
    }
 
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        Log.i("ActionBar", tab.getText() + " reseleccionada.");
    }
 
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        Log.i("ActionBar", tab.getText() + " seleccionada.");
        ft.replace(R.id.LinearLayout1, fragment);
    }
 
    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        Log.i("ActionBar", tab.getText() + " deseleccionada.");
        ft.remove(fragment);
    }
}