package com.hevo.study.app.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import android.os.Environment;
import de.mindpipe.android.logging.log4j.LogConfigurator;


public class LogHelper {

    public static final String PRE_LOG_NAME = "HevoLog";
    
    public static final String SUF_LOG_NAME = ".log";
	
    public static Logger gLogger;
    
	static {
	   SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ROOT);       
	   String date = sDateFormat.format(new java.util.Date()); 
	   final LogConfigurator logConfigurator = new LogConfigurator();
	   logConfigurator.setFileName(Environment.getExternalStorageDirectory() 
			   + File.separator + PRE_LOG_NAME 
			   + date.trim() 
			   + SUF_LOG_NAME);
	   // Set the root log level
	   logConfigurator.setRootLevel(Level.DEBUG);
	   logConfigurator.setLevel("org.apache", Level.ERROR);
	   logConfigurator.configure();
	   gLogger = Logger.getLogger("TradeLog");
	}
	
}
