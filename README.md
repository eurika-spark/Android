# Android




#JVM

##JVM����
###Trace ���ٲ���

####-verbose:gc
####-XX:+printGC
	���GC��Ϣ
	
####-XX:+PrintGCDetails
	��ӡGC��ϸ��Ϣ
####-XX:+PrintGCDetails
	GC��ϸ�����
```Code
Heap
 def new generation   total 13824K, used 11223K [0x27e80000, 0x28d80000, 0x28d80000)
 eden space 12288K,  91% used [0x27e80000, 0x28975f20, 0x28a80000)
 from space 1536K,   0% used [0x28a80000, 0x28a80000, 0x28c00000)
 to   space 1536K,   0% used [0x28c00000, 0x28c00000, 0x28d80000)
 tenured generation   total 5120K, used 0K [0x28d80000, 0x29280000, 0x34680000)
 the space 5120K,   0% used [0x28d80000, 0x28d80000, 0x28d80200, 0x29280000)
 compacting perm gen  total 12288K, used 142K [0x34680000, 0x35280000, 0x38680000)
 the space 12288K,   1% used [0x34680000, 0x346a3a90, 0x346a3c00, 0x35280000)
 ro space 10240K,  44% used [0x38680000, 0x38af73f0, 0x38af7400, 0x39080000)
 rw space 12288K,  52% used [0x39080000, 0x396cdd28, 0x396cde00, 0x39c80000)
```

####-XX:+PrintGCTimeStamps
	��ӡCG������ʱ���
	
####-Xloggc:log/gc.log
	ָ��GC log��λ�ã����ļ����
	
	
####-XX:+PrintHeapAtGC
	ÿ��һ��GC�󣬶���ӡ����Ϣ

####-XX:+TraceClassLoading
	�����ļ���
```Java
[Loaded java.lang.Object from shared objects file]
[Loaded java.io.Serializable from shared objects file]
[Loaded java.lang.Comparable from shared objects file]
[Loaded java.lang.CharSequence from shared objects file]
[Loaded java.lang.String from shared objects file]
[Loaded java.lang.reflect.GenericDeclaration from shared objects file]
[Loaded java.lang.reflect.Type from shared objects file]
```	
	
####-XX:+PrintClassHistogram
	����Ctrl+Break�󣬴�ӡ�����Ϣ	
		
####-Xmx �CXms
	ָ�����Ѻ���С��
```Java
	System.out.print("Xmx=");
	System.out.println(Runtime.getRuntime().maxMemory()/1024.0/1024+"M");

	System.out.print("free mem=");
	System.out.println(Runtime.getRuntime().freeMemory()/1024.0/1024+"M");

	System.out.print("total mem=");
	System.out.println(Runtime.getRuntime().totalMemory()/1024.0/1024+"M");
```	
	
####-Xmn
	������������С

####-XX:NewRatio
	��������eden+2*s��������������������������ı�ֵ
	4 ��ʾ ������:�����=1:4���������ռ�ѵ�1/5

####-XX:SurvivorRatio
	��������Survivor����eden�ı�
	8��ʾ ����Survivor :eden=2:8����һ��Survivorռ�������1/10

####-XX:+HeapDumpOnOutOfMemoryError
	OOMʱ�����ѵ��ļ�
	
####-XX:+HeapDumpPath
	����OOM��·��
	
####-Xmx20m -Xms5m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=d:/a.dump
```Java
 Vector v=new Vector();
        for(int i=0;i<25;i++)
            v.add(new byte[1*1024*1024]);
```

####-XX:OnOutOfMemoryError
```Java
	// ��OOMʱ��ִ��һ���ű�
	-XX:OnOutOfMemoryError=D:/tools/jdk1.7_40/bin/printstack.bat %p
	// ������OOMʱ����D:/a.txt�н��������̵߳�dump
	// ������OOMʱ�������ʼ�����������������
```

###�ڴ�ѱ���
^����ʵ������������������Ҵ���Ĵ�С
^�ٷ��Ƽ�������ռ�ѵ�3/8
^�Ҵ��ռ��������1/10
^��OOMʱ���ǵ�Dump���ѣ�ȷ�������Ų��ֳ�����

####-XX:PermSize  -XX:MaxPermSize
	�����������ĳ�ʼ�ռ�����ռ�
	���Ǳ�ʾ��һ��ϵͳ�������ɶ��ٸ�����

```Java
// ʹ��CGLIB�ȿ��ʱ�򣬿��ܻ�����������࣬��Щ�࣬�п��ܳű�����������OOM

for(int i=0;i<100000;i++){		// ���ϲ����µ���
    CglibBean bean = new CglibBean("geym.jvm.ch3.perm.bean"+i,new HashMap());
}
```

####-Xss ջ����
	ͨ��ֻ�м���K<br/>
	�����˺������õ����<br/>
	ÿ���̶߳��ж�����ջ�ռ�<br/>
	�ֲ����������� ������ջ��<br/>

```Java
public class TestStackDeep {
	private static int count=0;
	public static void recursion(long a,long b,long c){
		long e=1,f=2,g=3,h=4,i=5,k=6,q=7,x=8,y=9,z=10;
		count++;
		recursion(a,b,c);
	}
	public static void main(String args[]){
		try{
			recursion(0L,0L,0L);
		}catch(Throwable e){
			System.out.println("deep of calling = "+count);
			e.printStackTrace();
		}
	}
}
// �ݹ����
/**
-Xss128K
deep of calling = 701
java.lang.StackOverflowError

-Xss256K
deep of calling = 1817
java.lang.StackOverflowError
*/
```


	