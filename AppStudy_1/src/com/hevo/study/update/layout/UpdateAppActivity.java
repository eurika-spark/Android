package com.hevo.study.update.layout;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.hevo.study.R;

public class UpdateAppActivity extends Activity {

	private Button updateBtn = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check_update_main_activity);

		updateBtn = (Button) findViewById(R.id.update_btn);
		updateBtn.setOnClickListener(new UpdateBtnClickListener());
	}

	private final class UpdateBtnClickListener implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			// 等待动画		TODO
			// 显示提示对话框
			showNoticeDialog();
		}
	}

	/**
	 * 显示软件更新对话框
	 */
	private void showNoticeDialog() {
		// 构造对话框
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle(R.string.check_update_pop_title);
		builder.setMessage(R.string.check_update_info);
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
