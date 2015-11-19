package com.hevo.study.app.app;

import android.app.Application;

public class App extends Application {
	private static App singleton;

	public static App getInstance() {
		return singleton;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		singleton = this;
		
		// 针对异常的捕捉要进行全局监控整个项目		Deal with exception for whole App
		CrashHandler catchHandler = CrashHandler.getInstance();  
        catchHandler.init(getApplicationContext());  
	}

}
