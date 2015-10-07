

##Android
手机的驱动安装的PC上


@android: 	访问android包下的R文件		系统的资源 


FrameLayout 和层相似，可以叠加


Ctrl + F 模拟器切换屏幕


单元测试 或者 创建 单元测试项目(针对某个项目进行创建)  推荐！！！
<use-library android:name="android.test.runner">

<instrumentation android:name="" android:targetPackage="" android:label="" />		单元测试的启动

单元测试累 extends AndroidTestCase





日志
日志级别： Error > Warn > Info > Debug > Verbose
system.out --级别


File
写入
FileOutPutStream os = context.openFileOutput(fileName, mode)
os.write();
os.close();

内置存储
默认保存在 data/data/<package name>/files文件夹

读入
FileInput ins = context.opentFileInput(fileName);		// 默认从 data/data/<package name>/files文件夹 读入
byte[] buffer = new byte[1024];
int i = ins.read(buffer); 		// 读完了 i=-1；如果没读完，i=读到的数据size

模式：
Private	只能被本应用访问 覆盖之前的内容
Append  只能被本应用访问 追加
Readable	可被其他应用读取
Writeable	可被其他应用写入 但 不能读
Readable + Writeable	可被其他应用读取和写入


保存/读取到 SD卡
mnt/sdcard 需要在app应用中添加权限	详见Doc
先判断 外存储的状态
Environment.getExternalStorageStatue()
获得sdCard路径
Environment.getExternalStorageDir()


使用pull解析器 解析xml
看一下actonia 是怎么解析xml的 




SharePerference 
getEditor
save data --- editor.commit();

在activity中得到SharePerference 可使用getPerference(mode) 方法，默认使用activity类的名称作为文件名称



SQLiteHelper

onCreate() 当数据库文件不存在时，触发
onUpgrade() 当版本号变化时，触发



SQLiteExper SQLite管理工具

transaction commit()  rollback()
事务的提交或回滚是由事务的标志决定的，默认情况下，事务的标志为false
若，事务的标志为True，则提交
否则回滚

db.setTransactionSuccessful();	--设置事务的标志为True



ListView
Adapter的作用是把数据绑定到item上， item是ListView中的一行
ListView中的一行(item),指向一个xml文件

SimpleAdapter
SimpleCursorAdapter 注意一个异常 '_id' 必须有_id这一列

自定义适配器
继承 BaseAdapter



ContentProvider		好处：统一了数据的访问方式
extends contentProvider
需要在Manifast.xml中进行配置，authorities --唯一标示
其schema固定为 content://

contentValue --类似 Map

UriMatcher 帮助类，用来匹配分析URI
matcher.addURI("前缀", "路径", mode)
matcher.addURI("前缀", "路径/#", mode)		-- #代表数字 	/  *代表任意字符
matcher.match()

Uri的工具类
Uri.parse("content://xxx/xxx/");
ContentUris.withAppendedId(uri, sth);		-- 组装URI
ContentUris.parseId(uri);					-- 获得id 如 /person/id 

ContentResolver 访问 Provider
访问ContentProvider，需要用ContentResolver对象
ContentResolver = this.getContext().getContextResolver();

resolver.insert  --> 实际上调用 contentProvider的 insert()
resolver.insert(uri, contentValue);

返回类型
getType(Uri) 
如果你返回的数据是集合(多条)，必须以 vnd.android.cursor.dir/ 开头
如果是一条数据, 则必须以 vnd.android.cursor.item/ 开头
例如：
return "vnd.android.cursor.dir/person"

ContentProvider 变化通知	contentObserver			<One More Time>
在Provider里面，
this.getContext().getContextResolver().notifyChange(uri, observer); observer --必须通知的观察者
observer extends contentObserver


Json
JsonArray -- 将String类型的Json数组
JsonObject -- JsonArray中的item

jsonObject.getInt("key");


Get / Post
HttpURLConnection conn
conn.setRequestMethod("get/post");
conn.setDoOutput(true);
conn.set for header  -- for post
output out = conn.getOutputStream();
out.write(byte[])		-- 此时只是将信息写入到output的缓存中，没有真正的写入到服务端
conn.getResponseCode()		-- 只有当客户端企图获得服务端信息时(任何信息)，才将信息写入服务端

--HttpClient [可暂缓，以后学习]
NameValuePair 存放参数



Xml模板，也使用流将文件模板读入，然后replaceAll(占位符),来生产xml文本


在Android开发中，RandomAccessFile(file, mode) mode 尽量使用"rwd"	--每次都将内容更新到设备上

http Get header中有 Range: "bytes=0-1000"


Activity 
Intent
显示Intent
intent.setClassName(context, "Full Path Class");
intent.setClassName("xxx.xx.xxx.xxxx", "Full Path Class");		--跨应用的Intent，调用其他的app    xx.xxx.xxx(包名)
intent.setConponent(new Coponent(context, XXX.class));

隐示Intent
动作action，类别category，数据data(URI或数据类型)
<Intent-filter>
	<action android:name="xxx" />
	<category android:name="yyy" />
	<data schema="abc" host="def.hig" path="" />	URI来描述的 
	<data mimeType="image/*" />					数据类型：txt: text/plain		xml: text/xml	image/gif
	
Intent.setAction("xxx")		-- match
Intent.addCategory("yyy")	
startActivity(intent);		// 该方法会为intent添加一个默认类别 android.intent.category.default
##(没设数据参数的时候) 只要Intent中的Action和Category都出现在Intent-Filter中，就能与之匹配，否则匹配失败
intent.setData(Uri.parse("abc"//def.hig/...""));						--URI
intent.setType("image/jpeg");	// 清除之前设置的setData()的数据		--数据类型
--同时设置data和type
intent.setDataAndType(data, type);


Activity
A -> B and B(result) ->(backTo) A 
A中就必须使用 startActivityForResult(intent, 请求码);
A中
onActivityResult(请求码, 识别码, Intent data)

B中
setResult(识别码, Intent data);



Activity生命周期
>运行状态，暂停状态，停止状态
>三个嵌套生命周期循环：
完整生命周期循环，可视生命周期循环 onStart() onStop()，前台生命周期 onResume() onPause()
> onSaveInstanceState()	onRestoreInstanceState()
1.onSaveInstanceState() 内存不足、直接按Home键、屏幕方向改变时，由系统销毁一个Activity，此时调用onSaveInstanceState()
缓存一些数据  data放入到Bundle对象中
2.onRestoreInstanceState() 当上面的Activity重新被创建的时候
从Bundle对象中取data即可

屏幕切换时，不希望Activity被销毁，需如下配置：
<activity android:configChange="keyBoardHidden|orientation" />
在activity中，得到onConfigurationChanged() 事件




BroadcastReciver
<receiver android:name=".类名" >
	<intent-filter>
		<action android:name="xxxx" />		-- 广播的订阅
	</intent-filter>
</receiver>

类 extends BroadcastReciver

@override
onReceiver(context, intent)   data in intent

sms = intent.getExtra().get("pdus");	-- 获得短信内容

普通广播(完全异步的，接收者不能将结果传递给下一个接收者，无法终止广播的传递)、
context.sendBroadcast()

有序广播(根据接收者的级别进行有序传递, 有限级别在intent-filter中声明，数值越大级别越高，可以终止广播)
context.sendOrderedBroadcase()
abortBroadcast() 	--终止广播


Service 服务
extends Service		创建在应用的包 或者 子包底下
启动
服务不能自己运行起来
context.startService()  --	context.stopService()	访问者(Activity) 和 服务没有交互
context.bindService()	--  context.unbindService()	访问者(Activity) 和 服务 有交互


Activity调用Service中方法：
Intent intent = new Intent(context, XXXService.class);
context.startService(intent);

跨进程访问Service就必须使用 bindService() 和 unbindService()
bindService(intent, conn, BIND_AUTO_CREATE); 	// BIND_AUTO_CREATE 绑定自动创建服务

conn -- XXX extends ServiceConnection
override  onServiceConnected() and onServiceDisconnected
onServiceConnected(conponentName, Ibinder)		--Ibinder 为Service返回给客户端的 Binder对象

当客户端Activity 调用onDestroy() 显示的接触Service的绑定
onDestroy() {
	unbindService(conn);
}

两个应用之间的通信：
Service远程服务：AIDL(Android Interface Definition Language)
AIDL规则：
1、接口名与aidl文件名相同
2、接口和方法不能加权限修饰符 如：public protected
3、aidl默认支持java中的基础类型 int, long...String List，map 等等 ， 故不需要import 
当编写好aidl文件后，系统会生成一些对应的代码，此代码类中，有一个名为Stub的类，此类继承了Binder 


远程服务端需要编写 AIDL文件，声明接口信息
然后创建对应的Service

客户端，也需要拷贝aidl文件，让编译器在客户端生成对应的代码
XXXX xxx = XXXX.Stub.asInterface(Ibinder客户端的)



39,40,41,42



// 安装delphi7


//==获得android | Java Doc 官网
//==Git windows 上传项目 文件
/==Java enum 了解一下, 看看actonia 怎么做的
==android打印模板	， 模板的设计模式，	存一下邮件 from Hisense
==AndroidPos项目，日结布局


//网银，发银行卡给Hisense

/==研究一下android 嵌入浏览器
//==适配器模式









































































































































































































































































































































































































































































































































































































































































































































