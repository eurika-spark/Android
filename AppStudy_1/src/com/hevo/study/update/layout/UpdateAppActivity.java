package com.hevo.study.update.layout;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
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
	
	private final static String CHECK_VERSION_URL = "http://192.168.7.101:8080/AppBackEnd/checkVersion";

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
	}

	private final class CheckAsyncTask extends AsyncTask<String, Integer, VersionBean> {
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
			// 等待动画，需要添加遮罩蒙版		TODO
			if(waitImgView.getVisibility() == View.GONE) {
				waitImgView.setVisibility(View.VISIBLE);
				animationDrawable.start();
			}
			new CheckAsyncTask().execute(new String[]{ CHECK_VERSION_URL });
		}
	}

	/**
	 * 显示软件更新对话框
	 */
	private void showNoticeDialog(VersionBean version) {
		
		// 构造对话框
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle(R.string.check_update_pop_title);
		// 没有可更新的内容
		if(version == null) {
			builder.setMessage("No New Version Need to Update");
			builder.setNegativeButton(R.string.check_update_later,
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
			Dialog noticeDialog = builder.create();
			noticeDialog.show();
			return; 
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("New Update:  " + version.getVersionName() + "." + version.getVersionCode() + "\n");
		sb.append("Update Info:\n");
		for (String info : version.getUpdateInfoList()) {
			sb.append("\t\t\t" + info + "\n");
		}
		sb.append("\n");
		sb.append("Do you want to update?");
		
		builder.setMessage(sb.toString());
		// 更新
		builder.setPositiveButton(R.string.check_update_update_btn,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 显示下载对话框
						showDownloadDialog();
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

	/**
	 * 显示软件下载对话框
	 */
	private void showDownloadDialog() {
		Toast.makeText(this, " Updating..ing...ing...", Toast.LENGTH_SHORT).show();
//		// 构造软件下载对话框
//		AlertDialog.Builder builder = new Builder(this);
//		builder.setTitle("Updating...");
//		// 给下载对话框增加进度条
//		final LayoutInflater inflater = LayoutInflater.from(this);
//		View v = inflater.inflate(R.layout.softupdate_progress, null);
//		mProgress = (ProgressBar) v.findViewById(R.id.update_progress);
//		builder.setView(v);
//		// 取消更新
//		builder.setNegativeButton(R.string.soft_update_cancel,
//				new OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						dialog.dismiss();
//						// 设置取消状态
//						cancelUpdate = true;
//					}
//				});
//		mDownloadDialog = builder.create();
//		mDownloadDialog.show();
//		// 现在文件
//		downloadApk();
	}

}
