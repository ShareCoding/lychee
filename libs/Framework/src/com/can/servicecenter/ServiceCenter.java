package com.can.servicecenter;

import com.can.dbservice.DBCenter;
import com.can.logservice.LogCenter;


public class ServiceCenter{
	
	private DBCenter mDBCenter = null;
	private LogCenter mLogCenter = null;
	private static ServiceCenter sServiceCenter = new ServiceCenter();
	
	public static ServiceCenter getInstance(){
		return sServiceCenter;
	}
	
	
	public final DBCenter getDBCenter(){
		if (mDBCenter == null || !mDBCenter.isAlive()){
			mDBCenter = new DBCenter();
		}
		mDBCenter.addRef();
		return mDBCenter;
	}
	
	public final void releaseDBCenter(){
		if (mDBCenter != null){
			mDBCenter.releaseRef();
		}
	}
	
	// for log service
	public final LogCenter getLogCenter(){	//TODO
		if (mLogCenter == null || !mLogCenter.isAlive()){
			mLogCenter = new LogCenter();
		}
		mLogCenter.addRef();
		return mLogCenter;
	}
	public final void releaseLogCenter(){
		if (mLogCenter != null){
			mLogCenter.releaseRef();
		}
	}

	public void releaseService(){
		if (mLogCenter != null){
			mLogCenter.releaseService();
			mLogCenter = null;
		}
		
		if (mDBCenter != null){
			mDBCenter.releaseService();
			mDBCenter = null;
		}
		
	}
	
}



