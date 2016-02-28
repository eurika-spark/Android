

源文件： 以py为扩展名
字节码： 源文件经过编译后生成的扩展名pyc
优化代码： 经过优化的源文件，扩展名为pyo


四则运算
+-*/
整除//
取余 %
幂运算 **

关系运算
<,>,=,<=,>=,!=,==

逻辑运算
and, not, or



// 从键盘上获取一个值，返回string
raw_input()


数据类型
1、数字
	整形int
	长整形long
	浮点float
	复数complex
2、字符串
	'' 和 "" 均可
	"""字符串定义方法

// 切片和索引的应用
a='abcdefg'
a[1:4] // 起始：结束
'bcd'
a[:4]
'abcd'
a[2:]
cdefg
a[::1] // 从开头到结尾，1表示步长度
abcedfg
a[::2]
acdg
a[-1]	// 取字符串最后一个值
g
a[-2:-4:-1]	// 从右向左取，从右边界开始，索引可以为负数
fe

3、列表（序列类型）
	t=['abc']
	t.append("123")	// t=['abc', '123']
	t.remove("123")	// t=['abc']
	
4、元组（序列类型）可以存储一系列的值，存储安全性高，固定的值
	【tuple 元组不可变】
	t = ('milo', 30, "amle")
	t[0]
	mile
	// 元组的拆分
	name,age,gender=t
	name
	'milo'
	age
	30
	gender
	'male'
    // 单一元组的元祖
	t = (2,)	// 单一元组必须这样写
	
5、字典 
	使用{}，映射类型
	dic={'key':'hello', 'val': 'world'}
	dic[key]	//	hello
	dic[val]	//	world
	// 支持混合定义
	h1='goods'		// 变量
	dic={1: 99, h1: 'good', 'key':'hello'}	// dic={1: 99, 'goods': 'good', 'key':'hello'}
	// 可追加key-value
	dic['name']='Floyd'
	// 删除元素
	del(dic['name'])
	dic.pop(h1)
	// 全部清空
	dic.clear()		// dic此时为空字典
	
	// dict 工厂方式生成字典
	dic = dict(['x',1],['y',2])
	
	// 遍历
	for k in dic
		print k
	// dic中的所有key，都会被打印出来
	

	
	
序列的操作
len()	// 序列的长度
+		// 两个序列的拼接
*		// 重复序列
	'abc'*5
	'abcabcabcabcabc'
in		// 判断 是否在序列中
	'c' in 'abc'
	TRUE
max()	// 获得序列中的元素最大值
min()	// 获得序列中的元素最小值


流程控制
if expression : 
	statements
elif expression :	// elif = else if 
	statements
else :
	statements
	
if 1<2 : 
	print 'ok'
	
if 1+1 : 	// 非0，非空都等同于TRUE
	print 'ok'


逻辑判断
and  or  not


循环
FOR循环
for x in "abcd" : 
	print "Hello world " + x
else :	// for循环正常结束时 
	statements
	pass	// 代码桩	仅仅是占位用的
	
range(i,j[,步进值])	// 快速生成一个序列
range(3)	// [0, 1, 2]	
range(1,10, 2)	// [1,3,5,7,9]

xrange()	// 返回迭代对象

for x in range(10) :
	print 'abc'		// 循环10次

// 遍历字典
dic = {1:111, 2:222, 5:555, 3:333}
for x in d:		// x 为字典中的key
	print x + ":" + d[x]
/////////////////
1:111
2:222
5:555
3:333

// 拆分(元组、字典)
a,b,c in ('a', 'b', 'c')	// 元组的拆分
k,v in dic.items() 	// 字典


while循环
while expression:
	statements
else:	// 成立的条件同For，正常结束时调用
	statements


函数
定义 & 调用
def add(a="默认A", b="默认值"):
	if a == b :
		print a, " = ", b
	else : 
		print a, " != ", b
	
	return "one"	// 函数的返回

add(b="hello")	// 默认A != hello

在函数里面可以通过global 将变量声明为全局变量
global y // y就变成全局变量了



函数的多类型传参
元组作为参数 
fun(*t)	// t为元组，t中的值与fun()中为一一对应的

def f(name="name", age=0)
	print "name: %s" % name
	print "age: %s" % age

f(age=30, name='hevo')	
// name: hevo
// age: 30
	
t=(30, 'hevo')
f(*t)
// name: 30
// age: hevo

dic = {'age': 30, 'name': 'Hevo'}
f(**dic)		// 传递字典，字典中的形参一定要和形参一致
// name: hevo
// age: 30


处理多余的冗余参数

def f(x, *args):	// 一个*表示元组
	print x
	print args

def f(x, *args, **kwargs):	// **表示字典
	print x
	print args
	print kwargs

f(1,2,3,4,5,6)
// 1
// (2,3,4,5,6)
// {}

f(x=1, y=2)
// 1
// ()
// {'y':2}



Lambda函数： 快速定义单行的最小函数

def f(x,y):
	return x * y
f(2, 3)		// 6
	
g = lambda x,y : x*y	// 与上面的函数等价
g(2, 3)		// 6


lambda 冒号前是参数，冒号后面是返回值


switch // python 没有switch关键字，使用字典实现switch功能 

def add(x, y):
	return x + y
	
def sub(x, y):
	return x - y
	
operator = {"+": add, "-": sub}

print operator["+"](2,3) 	// 等同于 add(2,3) = 5
// 5
print operator.get("+")(2, 3)	// get()方法保证调用时不出异常




内置函数
abs()	// 绝对值
max()	// 最大值
min()	// 最小值
len()	// 列表，序列的长度
divmod()	// 分别获得两个数的商和模
pow()	// 求幂 pow(x,y[,z]) = x ** y % z
round()	// 切换为浮点数
callable()	// 测试某个函数是否可以调用
isinstance()	// 判断对象是否是某种类型
cmp()	// 
range()		// 
xrange()	// 返回一个对象-生成器，大数据时效率高

// 类型及其转化
type()
int()
long()
float()
complex()
str()
list()
tuple()
hex()
oct()
ord()

String函数
str = "hello world"
str.capitalize()	// 字符串首字母大写
// Hello world

str.replace("hello", "good")	// 替换
// good world

ip="192.168.1.123"
str.split(".")		// 切割
// ['192', '168', '1', '123']


序列处理函数
filter(function, list)	
zip()
map()
reduce()





reduce(function, list) 	

l = range(1, 6)		// [1, 2, 3, 4, 5]
def f(x, y)
	return x * y
	
reduce(f, l)		// 120


print "%s : %s" % x, y   // 最后一个%表示，前面的%s为格式符，而不是单纯的%




正则表达式
python正则模块 Re
import re

r1 = r"\d{3,4}-?\d{8}"

p_tel = re.compile(r1)

p_tel.findall('010-12345678')
['010-12345678']

p_case_1 = re.compile(r'csvt', re.I)	// ingore case 不区分大小写

x = re.match('csvt hello')	// 匹配数据，如果字符串开头匹配则返回MatchObject对象，反之返回空

re.search('sdfsdf')	// 匹配数据，不论目标字符在什么位置，只要匹配成功就返回对象，反之返回空

re.finditer()	// 返回所有符合条件的值，返回一个迭代器

x.group(0)	// 输出匹配结果

=====
sub()	// 匹配并替换

subn()	// 匹配并替换，返回替换次数

split()
	

































































