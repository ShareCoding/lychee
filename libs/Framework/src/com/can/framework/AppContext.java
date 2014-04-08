package com.can.framework;

import android.content.Context;


public class AppContext{
	
	private Context mAppContext;
	
	private static AppContext inst = new AppContext();
	
	public boolean init(Context context){
		mAppContext = context;
		return true;
	}
	
	public Context getContext(){
		return mAppContext;
	}
	
	public static AppContext instance(){
		return inst;
	}
	
}
