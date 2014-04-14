package com.can.logservice;

import com.can.servicecenter.IService;

public class LogCenter implements IService {
	private static LoggerThread logger = null;
	private int count = 0;

	private boolean mIsAlive = true;
	public LogCenter() {
	}

	
	@Override
	public synchronized void addRef(){
		count++;
		if (logger == null)
			logger = new LoggerThread();
	}
	
	@Override
	public synchronized void releaseRef() {
		count--;
		if (count == 0 && logger != null) {
			try {
				logger.stopLogger();
			} catch (InterruptedException e) {
			}
			logger = null;
		}
	}

	@Override
	public synchronized void releaseService() {
		mIsAlive = false;
		count = 0;
		if (logger != null) {
			try {
				logger.stopLogger();
			} catch (InterruptedException e) {
			}
			logger = null;
		}
	}

	@Override
	public boolean isAlive() {
		return mIsAlive;
	}
	
	// Debug msg
	public void D(String tag, String msg) {
		Log("(D)" + tag + "|" + msg);
	}
	// Error msg
	public void E(String tag, String msg) {
		Log("(E)" + tag + "|" + msg);
	}
	// Warn msg
	public void W(String tag, String msg) {
		Log("(W)" + tag + "|" + msg);
	}
	// Info msg
	public void I(String tag, String msg) {
		Log("(I)" + tag + "|" + msg);
	}
	// VERBOSE msg
	public void V(String tag, String msg) {
		Log("(V)" + tag + "|" + msg);
	}
	
	protected synchronized void Log(String msg) {
		if (logger != null) {
			logger.log(msg);
		}
	}
	
	public synchronized void Flush() {
		if (logger != null) {
			logger.flush();
		}
	}
	
	public String[] getLogFiles() {
		return getLogFiles(3);
	}
	public synchronized String[] getLogFiles(int n) {
		if (logger != null) {
			return logger.getLogFiles(n);
		}
		return null;
	}
}
