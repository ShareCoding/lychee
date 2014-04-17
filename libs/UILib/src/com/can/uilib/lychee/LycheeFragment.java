package com.can.uilib.lychee;

import java.util.ArrayList;

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
	private ViewPager mViewPager;
	private PagerAdapter mPagerAdapter;
	
	private ArrayList<View> mSlideViews = new ArrayList<View>();
	
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
        
        View view = inflater.inflate(R.layout.mainview, null);
        mSlideViews.add(view);
        view = inflater.inflate(R.layout.settingview, null);
        mSlideViews.add(view);
        view = inflater.inflate(R.layout.scanview, null);
        mSlideViews.add(view);
        
        mViewPager = (ViewPager)v.findViewById(R.id.pager);
        mPagerAdapter = new PagerAdapter() {

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mSlideViews.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				// TODO Auto-generated method stub
				((ViewPager) container).removeView(mSlideViews.get(position));
			}

			@Override
			public Object instantiateItem(View container, int position) {
				// TODO Auto-generated method stub
				((ViewPager) container).addView(mSlideViews.get(position));
				return mSlideViews.get(position);
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}
        	
        };
        
        mViewPager.setAdapter(mPagerAdapter);
        return v;
	}
	
	
	
}




