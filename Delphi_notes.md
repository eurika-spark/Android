#���Ρ�ʵ�Ρ��ַ������ַ��͡�������
����: Integer
ʵ��: Real
�ַ���: Char
�ַ���: String


#�ַ�������''������

StrToInt()	
IntToStr()

#F1 �����ĵ�

#���� 
temp��Integer;

#�����ִ�Сд

#�����ţ� begin end

#������
const
ֱ�ӳ�����'a', 123, etc
���ų����� const TEMP = 111;		ע�⣺ �˴���=��������:=
���ͳ����� const  TEMP:Integer = 111; 

#������ 
var temp:Integer ;
var 
	n1,n2,n3:Real;
	str1,str2:String;
	
��implementation���������ı����ǵ�Ԫ���ı�������Ԫ�ڵ����й��̺ͺ������ɵ���
��interface�����ж���ı�����ȫ�ֱ�����������Ԫ��������
��procedure�������ı��������̱�������ֻ���ڶ�Ӧbegin��end����Ч


#���ʽ04
+ ȡ�������
/ ���������	����Ľ������ʵ������
div ���������
mod ȡ���������

+-* ����Ϊ�μ�����������У����ȸߵ�����

UpperCase
LowerCase
CompareStr() == 0
Pos(s1, s2): Integer
e.g. x:=Pos('bc', 'abcd');		// x=2

#�ϲ��ַ���
AppendStr(var s1, const s2); // Ч�ʱ� �ַ������ ��

#��ȡ�ַ���
Copy(str, m, n) 	// ��m���ַ���ʼ����ȡn�ĳ��ȣ� �� m > s, �򷵻ؿմ�
// �������ģ�һ������ռ�����ֽ�

#ɾ�����ַ���
Delete(str, m ,n)

#�����ַ�
Insert(str1, str, k)	// ��str1���뵽str�еĵ�k���ַ���

#���� �ַ���ת��
IntToStr(m):String

#�ַ��� ����ת��
StrToInt(s):Integer

#���� �ַ���ת��
FloatToStr(f):String

#�ַ��� ����ת��
StrToFloat(s):Extended

#���� ����
Copy(S, Index, Count:Integer): String/Array;
	S	--Ҫ����������
	Index	--��ʼ����
	Count	--�����ĳ���

#���顢�ַ�������
Length(S):Integer

===================================================

#Timer���� ��system tab��

#Delphi�Ĺ�ϵ�������
=	����
<>	������
>	����
<	С��
>=	���ڵ���
<=	С�ڵ���
in	����
>=	������
<=	����


#Case���
case (���ʽ) of 
'e' : ���1;
'f' : ���1;
'g' : ���1;
'f' : ���1;
else	// �൱��default 
	���
end;	


#if
If(xxx) then
Else xxxxx ;

# MessageBox(handle, 'msg', 'title', MB_OK )

���� -- additional img


#ѭ���ṹ
##while���
while (codintion) Do 
	ѭ����;
	
##repeat���
Repeat
	ѭ����;
Until (condition);		// condition == true then finish this loop

##for���
For ��ñ���:=��ֵ To (DownTo) ��ֵ  Do			// To ������ DownTo �ݼ���
	ѭ����;


#����
##��̬���� 
###����һλ����
Type 		// ������������
	�������� = Array[�±�����] of ������
���磺
Type
	number = Array[2..10] of Integer	// ���� 2 �� 10
	
�±����ͣ� ���͡��ַ��͡������͡��ӽ��� �� ö����	 
	
var 
	num_one : number;	// �����������
	
���ķ�����
var 	
	num_one : Array[2..9] of Integer ;

###�����λ����
Type 
	���������� = Array[�±�����1, С������2] of ������
	
Type
	���������� = Array[С������1] of Array[�±�����2] of ������

#���α������ķ���
Randomize;

##��̬���� ���ȿ��Զ�̬�趨
����
	���������� = Array of ������

###��ά����

Type
	���������� = Array of Array of Array of ������
�ж��ٸ�Array of ���Ƕ���ά����

###setLength ���ö�̬����ĳ���

��ά�������

var 
	Arrs : Array of Array of Integer;
begin
	setLength(Arrs, 2, 5);		// ���ö�ά����ĳ��ȣ� ��������
end;	

��̬���� �±��Ǵ�0��ʼ��

#inputBox();	������
�ո���� ASCII�� Ϊ #32

showMessage(); // �൱��js�е�alert



#���� �� ����

##����
procedure ������(�β�) 
var 
	// �������������� �� ��һ�����̻���
begin
	���;
end;

#$$$$
// �β��б��У�����ж�����������÷ֺŷָ�
// ���ں�������̵����У�ʵ���ö��ŷָ�

����
procedure Function1(Sender:TObject) ;		// ��ֵ
procedure Function2(var Str: String) ;		// var ��ʾ�����ǵ�ַ����ַ
procedure Function3(conts Str: String) ;		// var ��ʾ�����ǵ�ַ����ַ

 
#
action ���� ��˵��йأ������б�༭��
mainMenu	// �ؼ�
popupMenu	// �ؼ�	�Ҽ�
close;	�رմ���

actionList	// �ؼ�	


#�¼�
onEnter --����onFocus
onExit	--����onOut

onKeyPress  = onKeyDown + onKeyUp


lable�� staticText��TabControl��PageControl

StaticText �� Label�����ƣ���֮ͬ����staticText�Ǵ������ı���������д��ھ��

TabControl��һ����������������԰���������� --���е�tab����һ���ռ�
	tabPosition	-- ��ǩ��λ��
	hotTrack	-- ������
	multiLine	-- ������ʾ
	multiSelect	-- ��ѡ �� style���

PageControl ÿ��page��Ӧһ����ͬ����




Button��bitButton��speedButton��û�н��㣩
bitButton ��kind����	--ʹ��ϵͳ��ͼ��
space��ͼƬ�����ֵļ��

radioButton & checkbox
checkbox ��state���ԣ� cbUncheckedδѡ�С�cbCheckedѡ�С�cbGrayed���ѡ��

#�༭�͵����
Edit��MaskEdit��Memo��RichEdit

MaskEdit �����루����ǿ�� format����
format��Ϊ3�����֣�ÿ�������ԣ����� ��
1.��ʽ
2.�û������Ƿ񱻼�¼
3.û����ʱ���Ĭ�����

Memo
Memo1.Lines.Add('adding');
Memo1.Lines.Delete(0);		
Memo1.Lines.Insert(0, 'Inserting..');
Memo1.Lines.Move(0, 4);
Memo1.Lines.count			// ������
Memo.Lines.loadFromFile('text.txt')
Memo.lines.saveToFile('text.txt')

#OpenDialog
// ���ִ��
if OpenDialog.execute then
begin
end;

OpenDialog.Filter:='���ͻ�����|��ϵͳ��'
OpenDialog.Filter:='�ı��ļ�(*.txt)|*.TXT| ��ҳ(*.html, *.htm)|*.HTML;*.htm';

DateTimetoStr(now())


ShellApi
ShellExecute(handle, '', ....)

#ListBox
items, MultiSelect, ItemIndex, selected, stored(�Ƿ���ĸ����)
List.items.Clear
List.items.Add
List.items.Delete
List.items.Insert

#ComboBox
Items

#���������
##scrollbar
kind, max, min, largeChange(up/down page), smallChange(���Ҽ�ͷ), position  
kind = sbHorizal/sbvertical

#trackBar
slider

#���� Panel�� GroupBox��ScrollBox

#������������

#ö�� ��˳�����ͣ�ÿ��Ԫ�ض���Ӧһ�������������
Type
	���ͱ�ʶ�� = (��ʶ��1, ��ʶ��2,..., ��ʶ��n) ; 

Type 
  week = (Sunday, Monday,..., Saturday);

funciton whatDay(day: week): String;
case day of week
	Sunday:		whatDay:='Sunday';		// whatDay:=xxx ��Ϊ����ֵ
	Monday:		whatDay:='Monday';
end;

#�ӽ����� ���������ͣ�
Type 
	���ͱ�ʶ�� = ����1..����2		// ����������

����1 ��  ����2 ���ӽ���½���Ͻ磬�½����С���Ͻ� 

Type 
	Num=1..10;
var
	n1,n2: Num;
	
����

var
	n1,n2:1..10;	
	






















