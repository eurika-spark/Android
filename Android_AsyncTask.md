
# AsyncTask
<br>
## 一个异步任务的执行一般包括以下几个步骤：
>#### 1.execute(Params... params)，执行一个异步任务，需要我们在代码中调用此方法，触发异步任务的执行。
>#### 2.onPreExecute()，在execute(Params... params)被调用后立即执行，一般用来在执行后台任务前对UI做一些标记。
>#### 3.doInBackground(Params... params)，在onPreExecute()完成后立即执行，用于执行较为费时的操作，此方法将接收输入参数和返回计算结果。在执行过程中可以调用publishProgress(Progress... values)来更新进度信息。
>#### 4.onProgressUpdate(Progress... values)，在调用<b>publishProgress(Progress... values)</b>时，此方法被执行，直接将进度信息更新到UI组件上。
>#### 5.onPostExecute(Result result)，当后台操作结束时，此方法将会被调用，计算结果将做为参数传递到此方法中，直接将结果显示到UI组件上。

<br>
## 在使用的时候，有几点需要格外注意：
>#### 1.异步任务的实例必须在UI线程中创建。
>#### 2.execute(Params... params)方法必须在UI线程中调用。
>#### 3.不要手动调用onPreExecute()，doInBackground(Params... params)，onProgressUpdate(Progress... values)，<br>
onPostExecute(Result result)这几个方法。
>#### 4.不能在doInBackground(Params... params)中更改UI组件的信息。
>#### 5.一个任务实例只能执行一次，如果执行第二次将会抛出异常。

*****
```Java
private class MyTask extends AsyncTask<String, Integer, String> {
	// onPreExecute方法用于在执行后台任务前做一些UI操作
	@Override
	protected void onPreExecute() {
		Log.i(TAG, "onPreExecute() called");
		textView.setText("loading...");
	}

	// doInBackground方法内部执行后台任务,不可在此方法内修改UI
	@Override
	protected String doInBackground(String... params) {
		Log.i(TAG, "doInBackground(Params... params) called");
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(params[0]);
			HttpResponse response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				InputStream is = entity.getContent();
				long total = entity.getContentLength();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buf = new byte[1024];
				int count = 0;
				int length = -1;
				while ((length = is.read(buf)) != -1) {
					baos.write(buf, 0, length);
					count += length;
					// 调用publishProgress公布进度,最后onProgressUpdate方法将被执行
					publishProgress((int) ((count / (float) total) * 100));
					// 为了演示进度,休眠500毫秒
					Thread.sleep(500);
				}
				return new String(baos.toByteArray(), "gb2312");
			}
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
		return null;
	}

	// onProgressUpdate方法用于更新进度信息
	@Override
	protected void onProgressUpdate(Integer... progresses) {
		Log.i(TAG, "onProgressUpdate(Progress... progresses) called");
		progressBar.setProgress(progresses[0]);
		textView.setText("loading..." + progresses[0] + "%");
	}

	// onPostExecute方法用于在执行完后台任务后更新UI,显示结果
	@Override
	protected void onPostExecute(String result) {
		Log.i(TAG, "onPostExecute(Result result) called");
		textView.setText(result);

		execute.setEnabled(true);
		cancel.setEnabled(false);
	}

	// onCancelled方法用于在取消执行中的任务时更改UI
	@Override
	protected void onCancelled() {
		Log.i(TAG, "onCancelled() called");
		textView.setText("cancelled");
		progressBar.setProgress(0);

		execute.setEnabled(true);
		cancel.setEnabled(false);
	}
}
    
    