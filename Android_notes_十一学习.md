

##Android
�ֻ���������װ��PC��


@android: 	����android���µ�R�ļ�		ϵͳ����Դ 


FrameLayout �Ͳ����ƣ����Ե���


Ctrl + F ģ�����л���Ļ


��Ԫ���� ���� ���� ��Ԫ������Ŀ(���ĳ����Ŀ���д���)  �Ƽ�������
<use-library android:name="android.test.runner">

<instrumentation android:name="" android:targetPackage="" android:label="" />		��Ԫ���Ե�����

��Ԫ������ extends AndroidTestCase





��־
��־���� Error > Warn > Info > Debug > Verbose
system.out --����


File
д��
FileOutPutStream os = context.openFileOutput(fileName, mode)
os.write();
os.close();

���ô洢
Ĭ�ϱ����� data/data/<package name>/files�ļ���

����
FileInput ins = context.opentFileInput(fileName);		// Ĭ�ϴ� data/data/<package name>/files�ļ��� ����
byte[] buffer = new byte[1024];
int i = ins.read(buffer); 		// ������ i=-1�����û���꣬i=����������size

ģʽ��
Private	ֻ�ܱ���Ӧ�÷��� ����֮ǰ������
Append  ֻ�ܱ���Ӧ�÷��� ׷��
Readable	�ɱ�����Ӧ�ö�ȡ
Writeable	�ɱ�����Ӧ��д�� �� ���ܶ�
Readable + Writeable	�ɱ�����Ӧ�ö�ȡ��д��


����/��ȡ�� SD��
mnt/sdcard ��Ҫ��appӦ�������Ȩ��	���Doc
���ж� ��洢��״̬
Environment.getExternalStorageStatue()
���sdCard·��
Environment.getExternalStorageDir()


ʹ��pull������ ����xml
��һ��actonia ����ô����xml�� 




SharePerference 
getEditor
save data --- editor.commit();

��activity�еõ�SharePerference ��ʹ��getPerference(mode) ������Ĭ��ʹ��activity���������Ϊ�ļ�����



SQLiteHelper

onCreate() �����ݿ��ļ�������ʱ������
onUpgrade() ���汾�ű仯ʱ������



SQLiteExper SQLite������

transaction commit()  rollback()
������ύ��ع���������ı�־�����ģ�Ĭ������£�����ı�־Ϊfalse
��������ı�־ΪTrue�����ύ
����ع�

db.setTransactionSuccessful();	--��������ı�־ΪTrue



ListView
Adapter�������ǰ����ݰ󶨵�item�ϣ� item��ListView�е�һ��
ListView�е�һ��(item),ָ��һ��xml�ļ�

SimpleAdapter
SimpleCursorAdapter ע��һ���쳣 '_id' ������_id��һ��

�Զ���������
�̳� BaseAdapter



ContentProvider		�ô���ͳһ�����ݵķ��ʷ�ʽ
extends contentProvider
��Ҫ��Manifast.xml�н������ã�authorities --Ψһ��ʾ
��schema�̶�Ϊ content://

contentValue --���� Map

UriMatcher �����࣬����ƥ�����URI
matcher.addURI("ǰ׺", "·��", mode)
matcher.addURI("ǰ׺", "·��/#", mode)		-- #�������� 	/  *���������ַ�
matcher.match()

Uri�Ĺ�����
Uri.parse("content://xxx/xxx/");
ContentUris.withAppendedId(uri, sth);		-- ��װURI
ContentUris.parseId(uri);					-- ���id �� /person/id 

ContentResolver ���� Provider
����ContentProvider����Ҫ��ContentResolver����
ContentResolver = this.getContext().getContextResolver();

resolver.insert  --> ʵ���ϵ��� contentProvider�� insert()
resolver.insert(uri, contentValue);

��������
getType(Uri) 
����㷵�ص������Ǽ���(����)�������� vnd.android.cursor.dir/ ��ͷ
�����һ������, ������� vnd.android.cursor.item/ ��ͷ
���磺
return "vnd.android.cursor.dir/person"

ContentProvider �仯֪ͨ	contentObserver			<One More Time>
��Provider���棬
this.getContext().getContextResolver().notifyChange(uri, observer); observer --����֪ͨ�Ĺ۲���
observer extends contentObserver


Json
JsonArray -- ��String���͵�Json����
JsonObject -- JsonArray�е�item

jsonObject.getInt("key");


Get / Post
HttpURLConnection conn
conn.setRequestMethod("get/post");
conn.setDoOutput(true);
conn.set for header  -- for post
output out = conn.getOutputStream();
out.write(byte[])		-- ��ʱֻ�ǽ���Ϣд�뵽output�Ļ����У�û��������д�뵽�����
conn.getResponseCode()		-- ֻ�е��ͻ�����ͼ��÷������Ϣʱ(�κ���Ϣ)���Ž���Ϣд������

--HttpClient [���ݻ����Ժ�ѧϰ]
NameValuePair ��Ų���



Xmlģ�壬Ҳʹ�������ļ�ģ����룬Ȼ��replaceAll(ռλ��),������xml�ı�


��Android�����У�RandomAccessFile(file, mode) mode ����ʹ��"rwd"	--ÿ�ζ������ݸ��µ��豸��

http Get header���� Range: "bytes=0-1000"


Activity 
Intent
��ʾIntent
intent.setClassName(context, "Full Path Class");
intent.setClassName("xxx.xx.xxx.xxxx", "Full Path Class");		--��Ӧ�õ�Intent������������app    xx.xxx.xxx(����)
intent.setConponent(new Coponent(context, XXX.class));

��ʾIntent
����action�����category������data(URI����������)
<Intent-filter>
	<action android:name="xxx" />
	<category android:name="yyy" />
	<data schema="abc" host="def.hig" path="" />	URI�������� 
	<data mimeType="image/*" />					�������ͣ�txt: text/plain		xml: text/xml	image/gif
	
Intent.setAction("xxx")		-- match
Intent.addCategory("yyy")	
startActivity(intent);		// �÷�����Ϊintent���һ��Ĭ����� android.intent.category.default
##(û�����ݲ�����ʱ��) ֻҪIntent�е�Action��Category��������Intent-Filter�У�������֮ƥ�䣬����ƥ��ʧ��
intent.setData(Uri.parse("abc"//def.hig/...""));						--URI
intent.setType("image/jpeg");	// ���֮ǰ���õ�setData()������		--��������
--ͬʱ����data��type
intent.setDataAndType(data, type);


Activity
A -> B and B(result) ->(backTo) A 
A�оͱ���ʹ�� startActivityForResult(intent, ������);
A��
onActivityResult(������, ʶ����, Intent data)

B��
setResult(ʶ����, Intent data);



Activity��������
>����״̬����ͣ״̬��ֹͣ״̬
>����Ƕ����������ѭ����
������������ѭ����������������ѭ�� onStart() onStop()��ǰ̨�������� onResume() onPause()
> onSaveInstanceState()	onRestoreInstanceState()
1.onSaveInstanceState() �ڴ治�㡢ֱ�Ӱ�Home������Ļ����ı�ʱ����ϵͳ����һ��Activity����ʱ����onSaveInstanceState()
����һЩ����  data���뵽Bundle������
2.onRestoreInstanceState() �������Activity���±�������ʱ��
��Bundle������ȡdata����

��Ļ�л�ʱ����ϣ��Activity�����٣����������ã�
<activity android:configChange="keyBoardHidden|orientation" />
��activity�У��õ�onConfigurationChanged() �¼�




BroadcastReciver
<receiver android:name=".����" >
	<intent-filter>
		<action android:name="xxxx" />		-- �㲥�Ķ���
	</intent-filter>
</receiver>

�� extends BroadcastReciver

@override
onReceiver(context, intent)   data in intent

sms = intent.getExtra().get("pdus");	-- ��ö�������

��ͨ�㲥(��ȫ�첽�ģ������߲��ܽ�������ݸ���һ�������ߣ��޷���ֹ�㲥�Ĵ���)��
context.sendBroadcast()

����㲥(���ݽ����ߵļ���������򴫵�, ���޼�����intent-filter����������ֵԽ�󼶱�Խ�ߣ�������ֹ�㲥)
context.sendOrderedBroadcase()
abortBroadcast() 	--��ֹ�㲥


Service ����
extends Service		������Ӧ�õİ� ���� �Ӱ�����
����
�������Լ���������
context.startService()  --	context.stopService()	������(Activity) �� ����û�н���
context.bindService()	--  context.unbindService()	������(Activity) �� ���� �н���


Activity����Service�з�����
Intent intent = new Intent(context, XXXService.class);
context.startService(intent);

����̷���Service�ͱ���ʹ�� bindService() �� unbindService()
bindService(intent, conn, BIND_AUTO_CREATE); 	// BIND_AUTO_CREATE ���Զ���������

conn -- XXX extends ServiceConnection
override  onServiceConnected() and onServiceDisconnected
onServiceConnected(conponentName, Ibinder)		--Ibinder ΪService���ظ��ͻ��˵� Binder����

���ͻ���Activity ����onDestroy() ��ʾ�ĽӴ�Service�İ�
onDestroy() {
	unbindService(conn);
}

����Ӧ��֮���ͨ�ţ�
ServiceԶ�̷���AIDL(Android Interface Definition Language)
AIDL����
1���ӿ�����aidl�ļ�����ͬ
2���ӿںͷ������ܼ�Ȩ�����η� �磺public protected
3��aidlĬ��֧��java�еĻ������� int, long...String List��map �ȵ� �� �ʲ���Ҫimport 
����д��aidl�ļ���ϵͳ������һЩ��Ӧ�Ĵ��룬�˴������У���һ����ΪStub���࣬����̳���Binder 


Զ�̷������Ҫ��д AIDL�ļ��������ӿ���Ϣ
Ȼ�󴴽���Ӧ��Service

�ͻ��ˣ�Ҳ��Ҫ����aidl�ļ����ñ������ڿͻ������ɶ�Ӧ�Ĵ���
XXXX xxx = XXXX.Stub.asInterface(Ibinder�ͻ��˵�)



39,40,41,42



// ��װdelphi7


//==���android | Java Doc ����
//==Git windows �ϴ���Ŀ �ļ�
/==Java enum �˽�һ��, ����actonia ��ô����
==android��ӡģ��	�� ģ������ģʽ��	��һ���ʼ� from Hisense
==AndroidPos��Ŀ���ս᲼��


//�����������п���Hisense

/==�о�һ��android Ƕ�������
//==������ģʽ



#AsyncTask = Thread + handler + threadpool
���з��� doInBackground() ���������߳���
onPostExecute() ���������߳��У�

�����̵߳� doInBackground()���н����󣬻Ὣ�����Ӱ��UI���󣩷��ظ��ڲ���handler����
��onPostExecute()�����е�����Ϊ����handlerҪִ�еĶ���





































































































































































































































































































































































































































































































































































































































































































































