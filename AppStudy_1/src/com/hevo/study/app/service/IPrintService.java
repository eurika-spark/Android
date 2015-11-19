package com.hevo.study.app.service;

/**
 * @author Administrator
 * Interface of Print
 */
public interface IPrintService {

	public final String templateFileLoc = "Print_Pattern.txt";
	
	public void loadingPrnPatternInMem();

	public void RefreshMen4Pattern(String content);

	public boolean savePatternInSDCard(String content);
	
}
