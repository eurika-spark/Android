package com.hevo.study.update.layout;

import java.util.List;

public class VersionBean {

	private int versionCode = 0;
	private String versionName = null;
	private List<String> updateInfoList = null;
	
	public int getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public List<String> getUpdateInfoList() {
		return updateInfoList;
	}
	public void setUpdateInfoList(List<String> updateInfoList) {
		this.updateInfoList = updateInfoList;
	}
}
