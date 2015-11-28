
# Handler Leak
## �ٷ�������
In Android, Handler classes should be static or leaks might occur, Messages enqueued on the application thread's MessageQueue also retain their target Handler. If the Handler is an inner class, its outer class will be retained as well. To avoid leaking the outer class, declare the Handler as a static nested class with a WeakReference to its outer class.
## ���룺
��Android�У�Handler��Ӧ���Ǿ�̬�ģ����򣬿��ܷ���й©����Ӧ�ó����̵߳�MessageQueue���Ŷӵ�Message���� ���������ǵ�Ŀ��Handler��
���Handler��һ���ڲ��ࣨע���������������Ƿ������������ǱȽϳ����÷����������ⲿ�ཫ������������Ϊʲô�� ��ο�JavaǶ�������˵������
Ϊ�˱���й©�ⲿ�࣬����һ��Handler����Ϊ��̬�ڲ��ࣨע�������ͱ�����Handler������ⲿ��ʵ�����Զ����ã������ڲ�����һ�����ⲿ������WeakReference��

## ����ԭ��
###��1�� �Ŷ��е�Message�����Handler�ĳ��е���й©��
###��2��Handler������ⲿ�ࣨ��Activity��Service��ʵ����ǿ���ó� �С�
������������ԭ��ͬʱ���õ��³���й©�Ŀ��ܡ����ǵĽ���������Դ�ԭ����������������ԭ�򣬾ͻ�Ƚ������Ľ��������⡣

## ���������
###��1����Ե�1��ԭ����ʹ��Handler������������ڽ���ǰ�����MessageQueue�еķ��͸�Handler��Message����������Activity��Service��onDestroy()�е���Handler��remove*��������
###��2����Ե�2��ԭ��Handler��ʵ������þ�̬�ڲ���ķ�ʽ��������ⲿ���ǿ���ã������ڲ�����һ��WeakReference���õ��ⲿ���ʵ����

    ����Handler��remove*�������������һ�£����Բο�Դ����ĵ���

removeCallbacks(Runnable r) �������rƥ���ϵ�Message��

removeCallbacks(Runnable r, Object token) �������rƥ����ƥ��token��Message.obj����Message��tokenΪ��ʱ��ֻƥ��r��

removeCallbacksAndMessages(Object token) �������tokenƥ���ϵ�Message��

removeMessages(int what) ������what��ƥ��

removeMessages(int what, Object object) ������what��ƥ��

���Ǹ�����Ҫ��������Ը�HandlerΪtarget������Message������Callback������ô�������·�������

    handler.removeCallbacksAndMessages(null);

    ���մ�������������
package org.dragonboy.example;
 
import java.lang.ref.WeakReference;
 
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
 
public class MyActivity extends Activity {
    private MyHandler mHandler;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         mHandler = new MyHandler(this);
    }
 
    @Override
    protected void onDestroy() {
        // Remove all Runnable and Message.
        mHandler.removeCallbacksAndMessages(null);
 
        super.onDestroy();
    }
 
    static class MyHandler extends Handler {
        // WeakReference to the outer class's instance.
        private WeakReference<MyActivity> mOuter;

        public MyHandler(MyActivity activity) {
            mOuter = new WeakReference<MyActivity>(activity);
        }
 
        @Override
        public void handleMessage(Message msg) {
            MyActivity outer = mOuter.get();
            if (outer != null) {
                // Do something with outer as your wish.
            }
        }
    }
}