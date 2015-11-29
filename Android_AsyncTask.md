
# AsyncTask
*****
<br>
## һ���첽�����ִ��һ��������¼������裺
>#### 1.execute(Params... params)��ִ��һ���첽������Ҫ�����ڴ����е��ô˷����������첽�����ִ�С�
>#### 2.onPreExecute()����execute(Params... params)�����ú�����ִ�У�һ��������ִ�к�̨����ǰ��UI��һЩ��ǡ�
>#### 3.doInBackground(Params... params)����onPreExecute()��ɺ�����ִ�У�����ִ�н�Ϊ��ʱ�Ĳ������˷�����������������ͷ��ؼ���������ִ�й����п��Ե���publishProgress(Progress... values)�����½�����Ϣ��
>#### 4.onProgressUpdate(Progress... values)���ڵ���<b>publishProgress(Progress... values)</b>ʱ���˷�����ִ�У�ֱ�ӽ�������Ϣ���µ�UI����ϡ�
>#### 5.onPostExecute(Result result)������̨��������ʱ���˷������ᱻ���ã�����������Ϊ�������ݵ��˷����У�ֱ�ӽ������ʾ��UI����ϡ�

<br>
## ��ʹ�õ�ʱ���м�����Ҫ����ע�⣺
>#### 1.�첽�����ʵ��������UI�߳��д�����
>#### 2.execute(Params... params)����������UI�߳��е��á�
>#### 3.��Ҫ�ֶ�����onPreExecute()��doInBackground(Params... params)��onProgressUpdate(Progress... values)��<br>
onPostExecute(Result result)�⼸��������
>#### 4.������doInBackground(Params... params)�и���UI�������Ϣ��
>#### 5.һ������ʵ��ֻ��ִ��һ�Σ����ִ�еڶ��ν����׳��쳣��

*****
```Java
private class MyTask extends AsyncTask<String, Integer, String> {
	// onPreExecute����������ִ�к�̨����ǰ��һЩUI����
	@Override
	protected void onPreExecute() {
		Log.i(TAG, "onPreExecute() called");
		textView.setText("loading...");
	}

	// doInBackground�����ڲ�ִ�к�̨����,�����ڴ˷������޸�UI
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
					// ����publishProgress��������,���onProgressUpdate��������ִ��
					publishProgress((int) ((count / (float) total) * 100));
					// Ϊ����ʾ����,����500����
					Thread.sleep(500);
				}
				return new String(baos.toByteArray(), "gb2312");
			}
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
		return null;
	}

	// onProgressUpdate�������ڸ��½�����Ϣ
	@Override
	protected void onProgressUpdate(Integer... progresses) {
		Log.i(TAG, "onProgressUpdate(Progress... progresses) called");
		progressBar.setProgress(progresses[0]);
		textView.setText("loading..." + progresses[0] + "%");
	}

	// onPostExecute����������ִ�����̨��������UI,��ʾ���
	@Override
	protected void onPostExecute(String result) {
		Log.i(TAG, "onPostExecute(Result result) called");
		textView.setText(result);

		execute.setEnabled(true);
		cancel.setEnabled(false);
	}

	// onCancelled����������ȡ��ִ���е�����ʱ����UI
	@Override
	protected void onCancelled() {
		Log.i(TAG, "onCancelled() called");
		textView.setText("cancelled");
		progressBar.setProgress(0);

		execute.setEnabled(true);
		cancel.setEnabled(false);
	}
}
    
    