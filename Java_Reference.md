��JDK1.2�汾��ʼ���Ѷ�������÷�Ϊ���ּ��𣬴Ӷ�ʹ�����ܸ������Ŀ��ƶ�����������ڡ������ּ����ɸߵ�������Ϊ��ǿ���á������á������ú������á�

#ǿ����
���һ���������ǿ���ã��Ǿ������ڱز����ٵ�������Ʒ����������������������������ڴ�� �䲻�㣬Java�������Ը�׳�OutOfMemoryError����ʹ�����쳣��ֹ��Ҳ���῿������վ���ǿ���õĶ���������ڴ治�����⡣ 
���磺  

Object o=new Object();       
Object o1=o;     
 ��������е�һ������heap���д����µ�Object����ͨ��o����������󣬵ڶ�����ͨ��o����o1��new Object()���heap���еĶ�������ã����������ö���ǿ����.ֻҪ���ڶ�heap�ж�������ã�gc�Ͳ����ռ��ö���.���ͨ�����´��룺
o=null;       
o1=null;   
�����ʽ������o��o1Ϊnull���򳬳���Χ����gc��Ϊ�ö��󲻴������ã���ʱ�Ϳ����ռ����ˡ������ռ��������ھ�һ�ᱻ�ռ���ʲôʱ���ռ���Ҫȡ����gc���㷨����Ҫ�ʹ����ܶ಻ȷ���ԡ����������ָ��һ������ϣ���´�gc����ʱ�����ռ��ˣ��Ǿ�û�취�ˣ������������������þͿ��������ˡ��������������ڲ�����gc�ռ�������£��������򵥵Ľ�����

heap�ж�����ǿ�ɼ�������ɼ��������ɼ�������ɼ�����Ͳ��ɵ������Ӧ�õ�ǿ��˳����ǿ�����������顣���ڶ������������ֿɼ��Ķ�����������ǿ�����þ��������£�

String abc=new String("abc");  //1       
SoftReference<String> abcSoftRef=new SoftReference<String>(abc);  //2       
WeakReference<String> abcWeakRef = new WeakReference<String>(abc); //3       
abc=null; //4       
abcSoftRef.clear();//5   
  ����Ĵ����У�
    ��һ����heap���д�������Ϊ��abc���Ķ��󣬲�����abc���ö����ǿ����,�ö�����ǿ�ɼ��ġ�

    �ڶ��к͵����зֱ�����heap�ж���������ú������ã���ʱheap�еĶ�������ǿ�ɼ��ġ�

    ������֮��heap�ж�������ǿ�ɼ��ģ������ɼ��ġ�ͬ��������ִ��֮�������ɼ��ġ�


#�����ã�SoftReference��

���һ������ֻ���������ã��Ǿ������ڿ��п����������Ʒ������ڴ�ռ��㹻�������������Ͳ��������������ڴ�ռ䲻���ˣ��ͻ������Щ������ڴ档ֻҪ����������û�л��������ö���Ϳ��Ա�����ʹ�á������ÿ�����ʵ���ڴ����еĸ��ٻ��档
�����ÿ��Ժ�һ�����ö��У�ReferenceQueue������ʹ�ã���������������õĶ����������գ�Java������ͻ����������ü��뵽��֮���������ö����С�

����������Ҫ�����ڴ����еĸ��ٻ��档��jvm�����ڴ治��֮ǰ��������е������ã���������gc���п����ռ���ɼ��Ķ��󣬿��ܽ���ڴ�Խ����⣬�����ڴ������ʲôʱ��ᱻ�ռ�ȡ����gc���㷨��gc����ʱ�����ڴ�Ĵ�С����gc����Ҫ�ռ���������ִ�����¹���,�������abcSoftRefΪ����

    1 ���Ƚ�abcSoftRef��referent����Ϊnull����������heap�е�new String("abc")����

    2 ��heap�е�new String("abc")��������Ϊ�ɽ�����(finalizable)��

    3 ��heap�е�new String("abc")�����finalize()���������ж��Ҹö���ռ�õ��ڴ汻�ͷţ� abcSoftRef����ӵ�����ReferenceQueue�С�

   ע:��ReferenceQueue�����ú������ÿ����п��ޣ����������ñ����У��μ���   

Reference(T paramT, ReferenceQueue<? super T>paramReferenceQueue)     
 
�� Soft Reference ָ���Ķ��󣬼�ʹû���κ� Direct Reference��Ҳ���ᱻ�����һֱҪ�� JVM �ڴ治���� û�� Direct Reference ʱ�Ż������SoftReference ��������� object-cache ֮�õġ����һ�� SoftReference �������԰Ѷ��� cache ������Ҳ��������ڴ治��Ĵ��� ��OutOfMemoryError�����Ҿ��� Soft Reference Ҳ�ʺ�����ʵ�� pooling �ļ��ɡ� 

 A obj = new A();    
Refenrence sr = new SoftReference(obj);    
  
//����ʱ    
if(sr!=null){    
    obj = sr.get();    
}else{    
    obj = new A();    
    sr = new SoftReference(obj);    
}   

#�����ã�WeakReference��
���һ������ֻ���������ã��Ǿ������ڿ��п����������Ʒ���������������õ��������ڣ�ֻ���������õĶ���ӵ�и����ݵ��������ڡ��������������߳�ɨ���� ����Ͻ���ڴ�����Ĺ����У�һ��������ֻ���������õĶ��󣬲��ܵ�ǰ�ڴ�ռ��㹻��񣬶�����������ڴ档����������������������һ�����ȼ��ܵ͵��̣߳� ��˲�һ����ܿ췢����Щֻ���������õĶ���
�����ÿ��Ժ�һ�����ö��У�ReferenceQueue������ʹ�ã���������������õĶ����������գ�Java������ͻ����������ü��뵽��֮���������ö����С� 
��gc�������ɼ����󣬲��ͷ�abcWeakRef�����ã��ռ��ö��󡣵���gc������Ҫ�Դ����ò����ҵ������ɼ�����ͨ�����´�����������˵Ŀ����������ã� 

String abc=new String("abc");       
WeakReference<String> abcWeakRef = new WeakReference<String>(abc);       
abc=null;       
System.out.println("before gc: "+abcWeakRef.get());       
System.gc();       
System.out.println("after gc: "+abcWeakRef.get());     
 
���н��:    

before gc: abc    

after gc: null   

gc�ռ����ɼ������ִ�й��̺���ɼ�һ����ֻ��gc��������ڴ�����������ǲ����ռ��ö���

�����ϣ������ʱȡ��ĳ�������Ϣ�����ֲ���Ӱ��˶���������ռ�����ô��Ӧ���� Weak Reference ����ס�˶��󣬶�������һ��� reference��

A obj = new A();    
  
    WeakReference wr = new WeakReference(obj);    
  
    obj = null;    
  
    //�ȴ�һ��ʱ�䣬obj����ͻᱻ��������   
����...    
  
����if (wr.get()==null) {    
����System.out.println("obj �Ѿ�������� ");    
����} else {    
����System.out.println("obj ��δ�����������Ϣ�� "+obj.toString());   
����}   
����...   
}  
 �ڴ����У�͸�� get() ����ȡ�ô� Reference ����ָ���Ķ����������ֵΪ null �Ļ�������˶����Ѿ�������� 
����ļ��ɣ������ Optimizer �� Debugger ����ĳ���ʱ�����õ�����Ϊ���������Ҫȡ��ĳ�������Ϣ�����ǲ����� Ӱ��˶���������ռ��� 

# �����ã�PhantomReference��
"������"����˼�壬������ͬ���裬�������������ö���ͬ�������ò��������������������ڡ����һ����������������ã���ô���ͺ�û���κ�����һ�������κ�ʱ�򶼿��ܱ��������ա���������Ҫ�������ٶ����������յĻ��

�������������ú������õ�һ���������ڣ������ñ�������ö��У�ReferenceQueue������ʹ�á����� ��������׼������һ������ʱ��������������������ã��ͻ��ڻ��ն�����ڴ�֮ǰ������������ü��뵽��֮���������ö����С��������ͨ���ж����ö������� ���Ѿ������������ã����˽ⱻ���õĶ����Ƿ�Ҫ���������ա������������ĳ���������Ѿ������뵽���ö��У���ô�Ϳ����������õĶ�����ڴ汻����֮ǰ��ȡ��Ҫ���ж��� 

����������֮��ͨ��get�������ؽ��ʼ��Ϊnull,ͨ��Դ������ᷢ��,������ͨ�������õĶ���д��referent,ֻ��get�������ؽ��Ϊnull.�ȿ�һ�º�gc�����Ĺ�����˵һ����������.

  1 ����referent����Ϊnull, ֱ�Ӱ�heap�е�new String("abc")��������Ϊ�ɽ�����(finalizable).

  2 �������ú������ò�ͬ, �Ȱ�PhantomRefrence������ӵ�����ReferenceQueue��.Ȼ�����ͷ���ɼ��Ķ���. 

   ��ᷢ�����ռ�heap�е�new String("abc")����֮ǰ,��Ϳ�����һЩ����������.ͨ�����´�������˽���������.

import java.lang.ref.PhantomReference;       
import java.lang.ref.Reference;       
import java.lang.ref.ReferenceQueue;       
import java.lang.reflect.Field;       
      
public class Test {       
    public static boolean isRun = true;       
      
    public static void main(String[] args) throws Exception {       
        String abc = new String("abc");       
        System.out.println(abc.getClass() + "@" + abc.hashCode());       
        final ReferenceQueue referenceQueue = new ReferenceQueue<String>();       
        new Thread() {       
            public void run() {       
                while (isRun) {       
                    Object o = referenceQueue.poll();       
                    if (o != null) {       
                        try {       
                            Field rereferent = Reference.class      
                                    .getDeclaredField("referent");       
                            rereferent.setAccessible(true);       
                            Object result = rereferent.get(o);       
                            System.out.println("gc will collect:"      
                                    + result.getClass() + "@"      
                                    + result.hashCode());       
                        } catch (Exception e) {       
      
                            e.printStackTrace();       
                        }       
                    }       
                }       
            }       
        }.start();       
        PhantomReference<String> abcWeakRef = new PhantomReference<String>(abc,       
                referenceQueue);       
        abc = null;       
        Thread.currentThread().sleep(3000);       
        System.gc();       
        Thread.currentThread().sleep(3000);       
        isRun = false;       
    }       
      
}   
  ���Ϊ
class java.lang.String@96354  