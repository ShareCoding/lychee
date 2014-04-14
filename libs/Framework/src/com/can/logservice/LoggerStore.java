package com.can.logservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import com.can.framework.AppContext;

public class LoggerStore {
	private File rootPath = null;
	private File logfile = null;
	private PrintWriter writer = null;
	private int countsWrite = 0;
	
	private static final int CALC_PER_WRITE = 10;
	private static final int FILE_MAX_SIZE = 10000;
	private static final int MAX_SPACE_STORE = 2000000;
	
	public LoggerStore() {
		rootPath = new File(AppContext.instance().getContext().getFilesDir() + "/log/");
		if (!rootPath.exists()) {
			rootPath.mkdirs();
		}
		CleanFile();
		NewLogFile();
	}
	
	public void Close() {
		Flush();
		if (writer != null) {
			writer.close();
		}
		logfile = null;
	}
	public void println(String msg) {
		if (writer != null) {
			countsWrite++;
			writer.println(msg);
			if (countsWrite > CALC_PER_WRITE) {
				Flush();
				countsWrite = 0;
				if (logfile.length() > FILE_MAX_SIZE) {
					Close();
					NewLogFile();
				}
			}
		}
	}
	public void Flush() {
		if (writer != null) {
			writer.flush();
		}
	}
	
	private void CleanFile() {
		long s1 = rootPath.getUsableSpace();
		long s2 = DirectorySize(rootPath);
		if (s1 < s2 || s2 > MAX_SPACE_STORE) {
			String[] filelist = rootPath.list();
			if (filelist != null && filelist.length > 0) {
				Arrays.sort(filelist, new LogFileComparator());
				for (int i = 0; i <= filelist.length / 2; i++) {
					File f = new File(rootPath.getAbsolutePath(), filelist[i]);
					if (f.exists()) {
						f.delete();
					}
				}
			}
		}
	}
	
	private long DirectorySize(File file) {
		if (file.isDirectory()) {     
	        File[] children = file.listFiles();     
	        long size = 0;     
	        for (File f : children)     
	            size += f.length();     
	        return size;     
	    }
		return 0;
	}

	private void NewLogFile() {
		if (rootPath == null) return;
		try {
			Long fileid = 0L;
			String[] filelist = rootPath.list();
			if (filelist != null && filelist.length > 0) {
				Arrays.sort(filelist, new LogFileComparator());
				try {
					fileid = Long.parseLong(filelist[filelist.length - 1].substring(3));
					fileid += 1;
				} catch (NumberFormatException e) {
					File f = new File(rootPath, filelist[filelist.length - 1]);
					if (f.exists()) {
						f.delete();
					}
					fileid = 0L;
				}
			}
			
			logfile = new File(rootPath.getAbsolutePath(), "log" + fileid.toString());
			if (logfile.exists()) {
				logfile.delete();
			}
			if (!logfile.createNewFile()) {
				logfile = null;
			} else {
				writer = new PrintWriter(logfile);
			}
		} catch (FileNotFoundException e) {
			logfile = null;
			writer = null;
		} catch (IOException e) {
			logfile = null;
		}
	}
	
	public String[] getLogFiles(int n) {
		ArrayList<String> lists = new ArrayList<String>();
		if (rootPath != null) {
			String[] filelist = rootPath.list();
			if (filelist != null && filelist.length > 0) {
				Arrays.sort(filelist, new LogFileComparator());
				for (int i = (filelist.length - n < 0)? 0: filelist.length - n; i < filelist.length; i++) {
					lists.add(rootPath.getAbsolutePath() + File.separator + filelist[i]);
				}
			}
		}
		return lists.toArray(new String[lists.size()]);
	}
}
