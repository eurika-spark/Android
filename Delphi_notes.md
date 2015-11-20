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

#拷贝 函数
Copy(S, Index, Count:Integer): String/Array;
	S	--要拷贝的内容
	Index	--开始索引
	Count	--拷贝的长度

#数组、字符串长度
Length(S):Integer

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


#循环结构
##while语句
while (codintion) Do 
	循环体;
	
##repeat语句
Repeat
	循环体;
Until (condition);		// condition == true then finish this loop

##for语句
For 虚幻变量:=初值 To (DownTo) 终值  Do			// To 递增； DownTo 递减；
	循环体;


#数组
##静态数组 
###定义一位数组
Type 		// 定义数组类型
	数组名称 = Array[下标类型] of 基类型
例如：
Type
	number = Array[2..10] of Integer	// 索引 2 到 10
	
下表类型： 整型、字符型、布尔型、子界型 和 枚举型	 
	
var 
	num_one : number;	// 定义数组变量
	
简洁的方法：
var 	
	num_one : Array[2..9] of Integer ;

###定义二位数组
Type 
	数组类型名 = Array[下标类型1, 小标类型2] of 基类型
	
Type
	数组类型名 = Array[小标类型1] of Array[下标类型2] of 基类型

#解决伪随机数的方法
Randomize;

##动态数组 长度可以动态设定
定义
	数组类型名 = Array of 基类型

###多维数组

Type
	数组类型名 = Array of Array of Array of 基类型
有多少个Array of 就是多少维数组

###setLength 设置动态数组的长度

二维的情况：

var 
	Arrs : Array of Array of Integer;
begin
	setLength(Arrs, 2, 5);		// 设置二维数组的长度， 两行五列
end;	

动态数组 下标是从0开始的

#inputBox();	弹出框
空格键的 ASCII码 为 #32

showMessage(); // 相当于js中的alert



#过程 与 函数

##过程
procedure 过程名(形参) 
var 
	// 声明变量、常量 或 另一个过程或函数
begin
	语句;
end;

#$$$$
// 形参列表中，如果有多个参数，则用分号分割
// 而在函数或过程调用中，实参用逗号分割

声明
procedure Function1(Sender:TObject) ;		// 传值
procedure Function2(var Str: String) ;		// var 表示传递是地址，传址
procedure Function3(conts Str: String) ;		// var 表示传递是地址，传址

 
#
action 属性 与菜单有关，动作列表编辑器
mainMenu	// 控件
popupMenu	// 控件	右键
close;	关闭窗口

actionList	// 控件	


#事件
onEnter --类似onFocus
onExit	--类似onOut

onKeyPress  = onKeyDown + onKeyUp


lable、 staticText、TabControl、PageControl

StaticText 和 Label很相似，不同之处，staticText是窗口型文本组件，具有窗口句柄

TabControl是一个容器，它本身可以包含其他组件 --所有的tab共享一个空间
	tabPosition	-- 标签的位置
	hotTrack	-- 超链接
	multiLine	-- 多行显示
	multiSelect	-- 多选 与 style配合

PageControl 每个page对应一个不同界面




Button、bitButton、speedButton（没有焦点）
bitButton 有kind属性	--使用系统的图像
space：图片和文字的间距

radioButton & checkbox
checkbox 有state属性， cbUnchecked未选中、cbChecked选中、cbGrayed变灰选中

#编辑型的组件
Edit、MaskEdit、Memo、RichEdit

MaskEdit 带掩码（功能强大） format输入
format分为3个部分，每个部分以；隔开 ：
1.格式
2.用户输入是否被记录
3.没输入时候的默认填充

Memo
Memo1.Lines.Add('adding');
Memo1.Lines.Delete(0);		
Memo1.Lines.Insert(0, 'Inserting..');
Memo1.Lines.Move(0, 4);
Memo1.Lines.count			// 总行数
Memo.Lines.loadFromFile('text.txt')
Memo.lines.saveToFile('text.txt')

#OpenDialog
// 如果执行
if OpenDialog.execute then
begin
end;

OpenDialog.Filter:='给客户看的|给系统的'
OpenDialog.Filter:='文本文件(*.txt)|*.TXT| 网页(*.html, *.htm)|*.HTML;*.htm';

DateTimetoStr(now())


ShellApi
ShellExecute(handle, '', ....)

#ListBox
items, MultiSelect, ItemIndex, selected, stored(是否按字母排列)
List.items.Clear
List.items.Add
List.items.Delete
List.items.Insert

#ComboBox
Items

#滑块型组件
##scrollbar
kind, max, min, largeChange(up/down page), smallChange(左右箭头), position  
kind = sbHorizal/sbvertical

#trackBar
slider

#容器 Panel、 GroupBox、ScrollBox

#有序数据类型

#枚举 （顺序类型，每个元素都对应一个有序的整数）
Type
	类型标识符 = (标识符1, 标识符2,..., 标识符n) ; 

Type 
  week = (Sunday, Monday,..., Saturday);

funciton whatDay(day: week): String;
case day of week
	Sunday:		whatDay:='Sunday';		// whatDay:=xxx 即为返回值
	Monday:		whatDay:='Monday';
end;

#子界类型 （有序类型）
Type 
	类型标识符 = 常量1..常量2		// 单调递增的

常量1 和  常量2 是子界的下界和上界，下界必须小于上界 

Type 
	Num=1..10;
var
	n1,n2: Num;
	
或者

var
	n1,n2:1..10;	
	






















