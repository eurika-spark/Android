package com.hevo.study.print.service;

import com.hevo.study.print.service.impl.PrintServiceImpl;
import com.hevo.study.print.utils.LogHelper;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class LoadPrintPatternService extends Service {

	private IPrintService printService = null;
	
	@Override
	public IBinder onBind(Intent arg0) {
		LogHelper.gLogger.debug("------onBind IN LoadPrintPatternService.....");
		return (IBinder) printService;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		printService = new PrintServiceImpl();
		printService.loadingPrnPatternInMem();
		LogHelper.gLogger.debug("------onCreate IN LoadPrintPatternService.....");
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		stopSelf();
		return super.onUnbind(intent);
	}
	
	@Override
	public void onDestroy() {
		LogHelper.gLogger.debug("------onDestroy IN LoadPrintPatternService.....");
		super.onDestroy();
	}
	
}
