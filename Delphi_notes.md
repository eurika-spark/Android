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































