package org.dbrg.utils;

import org.apache.log4j.Logger;

public class LogInfo {
	
	static Logger log = Logger.getLogger(LogInfo.class.getName());
	
    public static void logDebug(String message) {
			log.debug(message);
	}

	public static void logInfo(String message) {
			log.info(message);
	}


}