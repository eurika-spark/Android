package com.hevo.study.progress.layout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hevo.study.R;

public class ProgressMainActivity extends Activity {

	private Button proBtn1 = null;
	private Button proBtn2 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progress_main_activity);
		
		proBtn1 = (Button) findViewById(R.id.progress_1_btn);
		proBtn1.setOnClickListener(new PopProgress1ClickListener());
		
		proBtn2 = (Button) findViewById(R.id.progress_2_btn);
		proBtn2.setOnClickListener(new PopProgress2ClickListener());
	}
	
	/**
	 * 圆形进度条（旋转圆圈-等待）
	 */
	final class PopProgress1ClickListener implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			
			final ProgressDialog dialog = new ProgressDialog(view.getContext());
			// 设置进度条的形式为圆形转动的进度条
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			// 设置是否可以通过点击Back键取消
			dialog.setCancelable(true);
			// 设置在点击Dialog外是否取消Dialog进度条
			dialog.setCanceledOnTouchOutside(false);
			dialog.setIcon(R.drawable.ic_launcher);
//			dialog.setTitle("提示");
			// dismiss监听
			dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
				@Override
				public void onDismiss(DialogInterface dialog) {

				}
			});
			// 监听Key事件被传递给dialog
			dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
				@Override
				public boolean onKey(DialogInterface dialog, int keyCode,
						KeyEvent event) {
					return false;
				}
			});
			// 监听cancel事件
			dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					Toast.makeText(getApplicationContext(), "OnCancel Listener Launched!!!", Toast.LENGTH_SHORT)
						.show();
				}
			});
			//设置可点击的按钮，最多有三个(默认情况下)
			dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {

						}
					});
			dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					});
			dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "中立",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
			dialog.setMessage("这是一个圆形进度条");
			dialog.show();
			
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						Thread.sleep(5000);
//						// cancel和dismiss方法本质都是一样的，都是从屏幕中删除Dialog,唯一的区别是
//						// 调用cancel方法会回调DialogInterface.OnCancelListener如果注册的话,dismiss方法不会回掉
//						dialog.cancel();
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//
//				}
//			}).start();
		}
	}
	
	final class PopProgress2ClickListener implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			
			final ProgressDialog dialog = new ProgressDialog(view.getContext());
			// 设置水平进度条
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			// 设置是否可以通过点击Back键取消
			dialog.setCancelable(true);
			// 设置在点击Dialog外是否取消Dialog进度条
			dialog.setCanceledOnTouchOutside(false);
			// 设置提示的title的图标，默认是没有的
			dialog.setIcon(R.drawable.ic_launcher);
			dialog.setMax(100);
			
			dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {

						}
					});
			dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {

						}
					});
			dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "中立",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {

						}
					});
			dialog.setMessage("这是一个水平进度条");
			dialog.show();
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					int i = 0;
					while (i < 100) {
						try {
							Thread.sleep(200);
							// 更新进度条的进度,可以在子线程中更新进度条进度
							dialog.incrementProgressBy(10);
							//二级进度条更新方式
							// dialog.incrementSecondaryProgressBy(10)
							i++;

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					// 在进度条走完时删除Dialog
					dialog.dismiss();
				}
			}).start();			
		}
	}
	
}
