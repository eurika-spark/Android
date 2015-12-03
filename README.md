# Android




#JVM

##JVM配置

###Trace 跟踪参数
####-verbose:gc -XX:+printGC
	输出GC信息
	
####-XX:+PrintGCDetails
	打印GC详细信息
```Bash
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
	打印CG发生的时间戳
	
####-Xloggc:log/gc.log
	指定GC log的位置，以文件输出
	
	
####-XX:+PrintHeapAtGC
	每次一次GC后，都打印堆信息

####-XX:+TraceClassLoading
	监控类的加载
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
	按下Ctrl+Break后，打印类的信息	
	分别显示：序号、实例数量、总大小、类型  --列表
		
###堆分配	
####-Xmx –Xms
	指定最大堆和最小堆
```Java
	System.out.print("Xmx=");
	System.out.println(Runtime.getRuntime().maxMemory()/1024.0/1024+"M");

	System.out.print("free mem=");
	System.out.println(Runtime.getRuntime().freeMemory()/1024.0/1024+"M");

	System.out.print("total mem=");
	System.out.println(Runtime.getRuntime().totalMemory()/1024.0/1024+"M");
```	
	
####-Xmn
	设置新生代大小

####-XX:NewRatio
	新生代（eden+2*s）和老年代（不包含永久区）的比值
	4 表示 新生代:老年代=1:4，即年轻代占堆的1/5

####-XX:SurvivorRatio
	设置两个Survivor区和eden的比
	8表示 两个Survivor :eden=2:8，即一个Survivor占年轻代的1/10

####-XX:+HeapDumpOnOutOfMemoryError
	OOM时导出堆到文件
	
####-XX:+HeapDumpPath
	导出OOM的路径
	
####-Xmx20m -Xms5m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=d:/a.dump
```Java
 Vector v=new Vector();
        for(int i=0;i<25;i++)
            v.add(new byte[1*1024*1024]);
```

####-XX:OnOutOfMemoryError
```Java
	// 在OOM时，执行一个脚本
	-XX:OnOutOfMemoryError=D:/tools/jdk1.7_40/bin/printstack.bat %p
	// 当程序OOM时，在D:/a.txt中将会生成线程的dump
	// 可以在OOM时，发送邮件，甚至是重启程序
```

###内存堆比例
^根据实际事情调整新生代和幸存代的大小
^官方推荐新生代占堆的3/8
^幸存代占新生代的1/10
^在OOM时，记得Dump出堆，确保可以排查现场问题

###永久区（方法区）
####-XX:PermSize  -XX:MaxPermSize
	设置永久区的初始空间和最大空间
	他们表示，一个系统可以容纳多少个类型

```Java
// 使用CGLIB等库的时候，可能会产生大量的类，这些类，有可能撑爆永久区导致OOM

for(int i=0;i<100000;i++){		// 不断产生新的类
    CglibBean bean = new CglibBean("geym.jvm.ch3.perm.bean"+i,new HashMap());
}
```

###栈分配
####-Xss 栈设置
	通常只有几百K<br/>
	决定了函数调用的深度<br/>
	每个线程都有独立的栈空间<br/>
	局部变量、参数 分配在栈上<br/>

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
// 递归调用
/**
-Xss128K
deep of calling = 701
java.lang.StackOverflowError

-Xss256K
deep of calling = 1817
java.lang.StackOverflowError
*/
```


	