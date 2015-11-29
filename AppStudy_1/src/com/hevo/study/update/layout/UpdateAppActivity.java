package com.hevo.study.update.layout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.hevo.study.R;

public class UpdateAppActivity extends Activity {

	private Button updateBtn = null;
	private ImageView waitImgView = null;
	private AnimationDrawable animationDrawable = null;
	
	private static Handler versionHandler = new Handler(){};
	
	//  Progress Bar
	private ProgressDialog progressBar; 

	private final static String CHECK_VERSION_URL = "http://192.168.7.103:8080/AppBackEnd/checkVersion";
	private final static String UPDATE_DOWNLOAD_URL = "http://192.168.7.103:8080/AppBackEnd/updateVersion";
	private final static String UPDATE_APK_FILE_NAME_PRE = "AppStudy_";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check_update_main_activity);

		updateBtn = (Button) findViewById(R.id.update_btn);
		updateBtn.setOnClickListener(new UpdateBtnClickListener());

		waitImgView = (ImageView) findViewById(R.id.update_loading_view);
		animationDrawable = (AnimationDrawable) this.getResources()
				.getDrawable(R.drawable.loading_animation);
		waitImgView.setImageDrawable(animationDrawable);
		
		progressBar = new ProgressDialog(this); 
	}

	private final class CheckAsyncTask extends
			AsyncTask<String, Integer, VersionBean> {
		// 子线程中 运行
		@Override
		protected VersionBean doInBackground(String... params) {
			VersionBean version = null;
			try {
				version = VersionService.getVerionInfo(CHECK_VERSION_URL);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return version;
		}

		// UI线程中运行，相当于从子线程发送Msg给 handler对象
		@Override
		protected void onPostExecute(VersionBean result) {
			waitImgView.setVisibility(View.GONE);
			animationDrawable.stop();
			showNoticeDialog(result);
		}
	}

	private final class UpdateBtnClickListener implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			// 等待动画，需要添加遮罩蒙版 TODO
			if (waitImgView.getVisibility() == View.GONE) {
				waitImgView.setVisibility(View.VISIBLE);
				animationDrawable.start();
			}
			new CheckAsyncTask().execute(new String[] { CHECK_VERSION_URL });
		}
	}

	/**
	 * 显示软件更新对话框
	 */
	private void showNoticeDialog(final VersionBean version) {

		// 构造对话框
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle(R.string.check_update_pop_title);
		// 没有可更新的内容
		if (!needUpdateCheck(version)) {
			String msg = "No New Version Need to Update .."; 
			msg += version != null ? version.getFullVersionName() : "";
			builder.setMessage(msg);
			builder.setNegativeButton(R.string.check_update_later,
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
			try {
				Dialog noticeDialog = builder.create();
				noticeDialog.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}

		StringBuilder sb = new StringBuilder();
		sb.append("New Update:  " + version.getFullVersionName() + "\n");
		sb.append("Update Info:\n");
		for (String info : version.getUpdateInfoList()) {
			sb.append("\t\t\t" + info + "\n");
		}
		sb.append("\n");
		sb.append("Do you want to update?");

		builder.setMessage(sb.toString());
		// 更新 In Handler + progress
		builder.setPositiveButton(R.string.check_update_update_btn,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 显示下载进度条
						downloadApkFile(UPDATE_APK_FILE_NAME_PRE + version.getFullVersionName() + ".apk");
					}
				});
		
		// 更新 In AsyncTask + progress
		builder.setNeutralButton("Update In Way of AsyncProgress", 
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 显示下载进度条
						new UpdateProgressTask().execute(new String[]{ 
								UPDATE_APK_FILE_NAME_PRE + version.getFullVersionName() + ".apk", 
								UPDATE_DOWNLOAD_URL});
					}
			});
		
		// 稍后更新
		builder.setNegativeButton(R.string.check_update_later,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		Dialog noticeDialog = builder.create();
		noticeDialog.show();
	}

	/********************************************[ 传统 Handler + Progress 写法]*************************************************************/
	/**
	 * 显示软件下载对话框
	 */
	private void downloadApkFile(final String fileName) {
		
		progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressBar.setTitle("Download");
		progressBar.setMessage("Please waiting for the downloading ...");
		progressBar.setProgress(0);
		progressBar.show();
		
		new Thread() {
			public void run() {
				VersionService.downloadAPK(UPDATE_DOWNLOAD_URL, fileName, progressBar);
				finishDownload(fileName);
			}

		}.start();
	}
	
	private void finishDownload(final String fileName) {
		versionHandler.post(new Runnable() {
			@Override
			public void run() {
				progressBar.cancel();
				setupApk(fileName);
			}
		});
	}

	/**
	 * 安装文件，一般固定写法 
	 */
	private void setupApk(String fileName) {
		Intent intent = new Intent(Intent.ACTION_VIEW);  
        intent.setDataAndType(Uri.fromFile(new File(Environment  
                .getExternalStorageDirectory()
                , fileName)),  
                "application/vnd.android.package-archive");  
        startActivity(intent);  
	}
	
	/********************************************[ Progress + AsyncTask 写法]*************************************************************/
	private final class UpdateProgressTask extends AsyncTask<String, Integer, String> {

		private String fileName = null;
		
		//onPreExecute方法用于在执行后台任务前做一些UI操作  
        @Override  
        protected void onPreExecute() {  
    		progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    		progressBar.setTitle("Download In Way of AsyncTask");
    		progressBar.setMessage("Please Hold on for the downloading ...");
    		progressBar.setProgress(0);
    		progressBar.show();
        } 
		
		@Override
		protected String doInBackground(String... params) {
			fileName = params[0];
			String url = params[1];

			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(url);
			HttpResponse response;
			try {
				response = client.execute(get);
				HttpEntity entity = response.getEntity();

				// get file size
				int length = (int) entity.getContentLength();

				InputStream inStream = entity.getContent();
				FileOutputStream fileOutputStream = null;
				if (inStream != null) {
					File file = new File(Environment.getExternalStorageDirectory(), // get SDCard Directory
							fileName);
					fileOutputStream = new FileOutputStream(file);
					// byte[] buf = new byte[1024];
					byte[] buffer = new byte[256]; // for testing the performance
					int len = -1;
					int process = 0;
					while ((len = inStream.read(buffer)) != -1) {
						fileOutputStream.write(buffer, 0, len);
						process += len;
						//调用publishProgress公布进度,最后onProgressUpdate方法将被执行  
	                    publishProgress((int) ((process / (float) length) * 100));  
					}
				}
				fileOutputStream.flush();
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
				
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
	    //onProgressUpdate方法用于更新进度信息  
        @Override  
        protected void onProgressUpdate(Integer... progresses) {  
            progressBar.setProgress(progresses[0]);  
        }		
		
        //onPostExecute方法用于在执行完后台任务后更新UI,显示结果  
        @Override  
        protected void onPostExecute(String result) {  
        	progressBar.dismiss();
			setupApk(fileName);	// result as fileName
        } 
        
        //onCancelled方法用于在取消执行中的任务时更改UI  
        @Override  
        protected void onCancelled() {  
            progressBar.setProgress(0);  
            progressBar.cancel();
            Toast.makeText(getApplicationContext(), "Canceled Current Update SUCCESSFULLY", Toast.LENGTH_SHORT)
            	.show();
        } 		
	}
	
	
	private boolean needUpdateCheck(VersionBean version) {
		
		if(version == null || version.getVersionCode() == 0 ) {
			return false;
		}
		PackageInfo packageInfo = null;
		int versionCode = 0;
		
		try {
			packageInfo = getPackageManager().getPackageInfo(this.getPackageName(), 0);
			versionCode = packageInfo.versionCode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(versionCode < version.getVersionCode()) {
			return true;
		}
		return false;
	}
	
	@Override
	protected void onDestroy() {
		versionHandler.removeCallbacksAndMessages(null);
		super.onDestroy();
	}
	
}
