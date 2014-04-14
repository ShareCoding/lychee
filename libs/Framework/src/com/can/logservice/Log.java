package com.can.logservice;

import com.can.servicecenter.ServiceCenter;

public final class Log {
	private static LogCenter log = null;
	public static void d(String tag, String msg) {
		if (log == null) {
			log = ServiceCenter.getInstance().getLogCenter();
		}
		android.util.Log.d(tag, msg);
		log.D(tag, msg);
	}
	public static void w(String tag, String msg) {
		if (log == null) {
			log = ServiceCenter.getInstance().getLogCenter();
		}
		android.util.Log.w(tag, msg);
		log.W(tag, msg);
	}
	public static void i(String tag, String msg) {
		if (log == null) {
			log = ServiceCenter.getInstance().getLogCenter();
		}
		android.util.Log.i(tag, msg);
		log.I(tag, msg);
	}
	public static void e(String tag, String msg) {
		if (log == null) {
			log = ServiceCenter.getInstance().getLogCenter();
		}
		android.util.Log.e(tag, msg);
		log.E(tag, msg);
	}
	public static void v(String tag, String msg) {
		if (log == null) {
			log = ServiceCenter.getInstance().getLogCenter();
		}
		android.util.Log.v(tag, msg);
		log.V(tag, msg);
	}
	public static void flush() {
		if (log == null) {
			log = ServiceCenter.getInstance().getLogCenter();
		}
		log.Flush();
	}
}
