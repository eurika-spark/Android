
# Handler Leak
## 官方声明：
	In Android, Handler classes should be static or leaks might occur, Messages enqueued on the application 
	thread's MessageQueue also retain their target Handler. If the Handler is an inner class, its outer 
	class will be retained as well. To avoid leaking the outer class, declare the Handler as a static 
	nested class with a WeakReference to its outer class.
## 翻译：
	在Android中，Handler类应该是静态的，否则，可能发生泄漏。在应用程序线程的MessageQueue中排队的Message对象
	还保留他们的目标Handler。
	如果Handler是一个内部类（注：无论是匿名还是非匿名，匿名是比较常见用法），
	它的外部类将被保留（至于为什么， 请参考Java嵌套类相关说明）。
	为了避免泄漏外部类，声明一个Handler子类为静态内部类（注：这样就避免了Handler对象对外部类实例的自动引用），
	其内部持有一个对外部类对象的WeakReference。

>## 问题原因
>>（1） 排队中的Message对象对Handler的持有导致泄漏；<br>
>>（2）Handler对象对外部类（如Activity或Service）实例的强引用持 有。<br>
是由于这两个原因同时作用导致出现泄漏的可能。我们的解决方案可以从原因出发，清除这两个原因，就会比较完整的解决这个问题。

>## 解决方案：
>>（1）针对第1个原因，在使用Handler的组件生命周期结束前清除掉MessageQueue中的发送给Handler的Message对象（例如在Activity或Service的onDestroy()中调用Handler的remove*方法）；<br>
>>（2）针对第2个原因，Handler的实现类采用静态内部类的方式，避免对外部类的强引用，在其内部声明一个WeakReference引用到外部类的实例。<br>

*****
##关于Handler的remove*方法，这儿介绍一下（可以参考源码或文档）
	removeCallbacks(Runnable r) ——清除r匹配上的Message。
	removeCallbacks(Runnable r, Object token) ——清除r匹配且匹配token（Message.obj）的Message，token为空时，只匹配r。
	removeCallbacksAndMessages(Object token) ——清除token匹配上的Message。
	removeMessages(int what) ——按what来匹配
	removeMessages(int what, Object object) ——按what来匹配

*****
我们更多需要的是清除以该Handler为target的所有Message（包括Callback），那么调用如下方法即可<br>
	handler.removeCallbacksAndMessages(null);

###最终代码像下面这样
```Java
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
	