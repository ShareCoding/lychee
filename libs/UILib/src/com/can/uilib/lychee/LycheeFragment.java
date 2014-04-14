package com.can.uilib.lychee;

import com.can.uilib.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;


public class LycheeFragment extends SherlockFragment{

	private Activity mAct;
	private ViewPager mPager;
	private PagerAdapter mPagerAdapter;
	
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		mAct = activity;
		super.onAttach(mAct);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lychee, container, false);
        
        mPager = (ViewPager)v.findViewById(R.id.pager);
        mPagerAdapter = new PagerAdapter() {

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return false;
			}
        	
        };
        
        
        return v;
	}
	
	
	
}




