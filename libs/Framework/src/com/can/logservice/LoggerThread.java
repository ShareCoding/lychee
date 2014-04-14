package com.can.logservice;
import java.lang.Thread;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class LoggerThread extends Thread{
	private static final String SHUTDOWN_REQ = "LOGGER_THREAD_SHUTDOWN";
	private static final String FLUSH_REQ = "LOGGER_THREAD_FLUSH";
	private BlockingQueue<String> itemsToLog = new ArrayBlockingQueue<String>(100);
	private volatile boolean shuttingDown;
	private volatile boolean loggerTerminated; 
	private LoggerStore logstore = new LoggerStore();
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ", Locale.CHINA);
	
	public LoggerThread() {
		startLogger();
	}
	
	private void startLogger() {
		shuttingDown = false;
		loggerTerminated = false;
		start();
	}

	public void stopLogger() throws InterruptedException {
		shuttingDown = true;
		try {
			itemsToLog.put(SHUTDOWN_REQ);
		} catch (InterruptedException iex) {
		}
	}
	
	public void log(String msg) {
		if (shuttingDown || loggerTerminated)
			return;
		try {
			itemsToLog.put(msg);
		} catch (InterruptedException iex) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("Unexpected interruption");
		}
	}
	
	public void flush() {
		try {
			itemsToLog.put(FLUSH_REQ);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String[] getLogFiles(int n) {
		if (logstore != null) {
			return logstore.getLogFiles(n);
		}
		return null;
	}
	public void run() {
		String item = null;
		try {
			while (true) {
				if (shuttingDown) {
					item = itemsToLog.poll();
				} else {
					item = itemsToLog.poll(30L, TimeUnit.SECONDS);
				}
				if (item == null) {
					logstore.Flush();
					continue;
				} else if (item.equals(FLUSH_REQ)) {
					logstore.Flush();
					continue;
				} else if (item.equals(SHUTDOWN_REQ)) {
					break;
				}

				String prefix = formatter.format(new Date(System.currentTimeMillis()));     
				logstore.println(prefix + item);
			}
		} catch (InterruptedException iex) {
		} finally {
			loggerTerminated = true;
		}
		logstore.Close();
	}
}
