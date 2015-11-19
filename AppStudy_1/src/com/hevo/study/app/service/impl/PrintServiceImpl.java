package com.hevo.study.app.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.lang3.StringUtils;

import com.hevo.study.app.service.IPrintService;
import com.hevo.study.app.utils.LogHelper;

import android.os.Binder;
import android.os.Environment;

public class PrintServiceImpl extends Binder implements IPrintService {

	public static String patternContent = null;
	
	@Override
	public void loadingPrnPatternInMem() {
//		FileInputStream inStream = getApplication().openFileInput(templateFileLoc);		// 默认从 data/data/<package name>/files文件夹 读入
		
		// 判断 sdcard 是否插入
		if(StringUtils.equals(Environment.getExternalStorageState(), Environment.MEDIA_MOUNTED)) {
			
			LogHelper.gLogger.debug("开始提取 模板 ......");
			FileInputStream inStream = null;
			ByteArrayOutputStream outStream = null;
			File txtFile = null;
			try {
				txtFile = new File(Environment.getExternalStorageDirectory(), templateFileLoc);
				inStream = new FileInputStream(txtFile);
				byte[] buffer = new byte[1024];
				
				outStream = new ByteArrayOutputStream();
				
				// 读完了 i=-1；如果没读完，i=读到的数据size
				int len = 0;
				while((len = inStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, len);
				}
				byte[] data = outStream.toByteArray();
				inStream.close();
				outStream.close();
				
				patternContent = new String(data, "UTF-8");
				LogHelper.gLogger.debug("提取 模板 成功......");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					inStream.close();
					outStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			LogHelper.gLogger.debug("---未插入SDcard,请检查......");
		}
	}

	@Override
	public void RefreshMen4Pattern(String content) {
		LogHelper.gLogger.debug("开始更新 模板 到内存......");
		
		// 将新的模板内容加载到内存
		patternContent = content;
		
		// 将新的模板内容写入到SDCard中
		int count = 0;
		while(count < 3) {
			if(savePatternInSDCard(content)) {
				break;
			}
			count++ ;
		}
		
		LogHelper.gLogger.debug("更新 模板 到内存 成功......");
	}

	@Override
	public boolean savePatternInSDCard(String content) {
		
		if(StringUtils.equals(Environment.getExternalStorageState(), Environment.MEDIA_MOUNTED)) {
			LogHelper.gLogger.debug("开始保存 模板 ......");
			try {
				File txtFile = new File(Environment.getExternalStorageDirectory(), IPrintService.templateFileLoc);
				FileOutputStream outStream = new FileOutputStream(txtFile);
				outStream.write(content.getBytes());
				outStream.close();
			} catch (Exception e) {
				e.printStackTrace();
				LogHelper.gLogger.debug("保存 模板 失败 ...... Err:" + e.getMessage());
				return false;
			}
			LogHelper.gLogger.debug("保存 模板 成功 ......");
			return true;
		} else {
			LogHelper.gLogger.debug("---未插入SDcard,请检查......");
			return false;
		}
	}
	
}
