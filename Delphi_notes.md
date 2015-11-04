#整形、实形、字符串、字符型、布尔型
整形: Integer
实形: Real
字符型: Char
字符串: String


#字符串：用''引起来

StrToInt()	
IntToStr()

#F1 帮助文档

#变量 
temp：Integer;

#不区分大小写

#大括号： begin end

#常量：
const
直接常量、'a', 123, etc
符号常量、 const TEMP = 111;		注意： 此处是=，而不是:=
类型常量、 const  TEMP:Integer = 111; 

#变量： 
var temp:Integer ;
var 
	n1,n2,n3:Real;
	str1,str2:String;
	
在implementation部分声明的变量是单元级的变量，单元内的所有过程和函数均可调用
在interface部分中定义的变量是全局变量，其他单元均可引用
在procedure中声明的变量（过程变量），只能在对应begin和end中有效


#表达式04
+ 取正运算符
/ 除法运算符	运算的结果总是实型数据
div 整除运算符
mod 取余数运算符

+-* 其结果为参加运算的数据中，精度高的类型

UpperCase
LowerCase
CompareStr() == 0
Pos(s1, s2): Integer
e.g. x:=Pos('bc', 'abcd');		// x=2

#合并字符串
AppendStr(var s1, const s2); // 效率比 字符串相加 高

#截取字符串
Copy(str, m, n) 	// 从m个字符开始，截取n的长度， 若 m > s, 则返回空串
// 对于中文，一个汉字占两个字节

#删除子字符串
Delete(str, m ,n)

#插入字符
Insert(str1, str, k)	// 将str1插入到str中的第k个字符处

#数字 字符串转换
IntToStr(m):String

#字符串 数字转换
StrToInt(s):Integer

#浮点 字符串转换
FloatToStr(f):String

#字符串 浮点转换
StrToFloat(s):Extended


===================================================

#Timer工具 在system tab中

#Delphi的关系运算符：
=	等于
<>	不等于
>	大于
<	小于
>=	大于等于
<=	小于等于
in	属于
>=	包含于
<=	包含


#Case语句
case (表达式) of 
'e' : 语句1;
'f' : 语句1;
'g' : 语句1;
'f' : 语句1;
else	// 相当于default 
	语句
end;	


#if
If(xxx) then
Else xxxxx ;

# MessageBox(handle, 'msg', 'title', MB_OK )

桌面 -- additional img































