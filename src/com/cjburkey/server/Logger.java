package com.cjburkey.server;

import java.util.logging.Level;

public class Logger {
	
	public static final void log(Object msg) {
		MainServer.instance.getLogger().log(Level.INFO, ChatUtil.color((String) msg));
	}
	
}