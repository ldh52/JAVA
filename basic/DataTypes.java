package basic;

import java.util.Scanner;
import java.util.Random;

public class DataTypes
{
	public static void main(String[] args)
	{
		memory();
		typeByteShortLong();
		shortCal();
		scannerIntCal();
		floatDouble();
		floatDoubleMinMax();
		floatCircleArea();
		scannerIdPwd();
		equalsString();
		threeCalculatorLogin();
		charString();
		randomCal();
		equalsReference();
		changeTypes();
		randomAlpabetPrint();
	}
	
	private static void memory()
	{
		int age;		// 메모리에 정수 4바이트(32비트) 공간 확보
	    age = 27;		// 할당연산자(오른편의 데이터를 왼편 메모리 공간에 기억)
	             		// Assignment Operator
	    System.out.println("age 변수에 기억된 정보: " + age);
	    System.out.println("int min: " + Integer.MIN_VALUE);      
	    System.out.println("int max: " + Integer.MAX_VALUE); 
	}
	
	private static void typeByteShortLong()
	{
		// byte, short, long 형으로 표현가능한 최대값 출력하기
	    System.out.println("byte min: " + Byte.MIN_VALUE);   
	    System.out.println("byte max: " + Byte.MAX_VALUE);
	    System.out.println("short max: " + Short.MAX_VALUE);
	    System.out.println("long max: " + Long.MAX_VALUE);
	
	    int a = 110;
	    int b = 25;
	    int c = a + b;
	    float d = a/b;
	    System.out.println(c);
	    System.out.println(d);
	    System.out.println(a%b);
	}
	
	private static void shortCal()
	{
		// short 자료형으로 표현가능한 최소의 수와 최대의 수를 덧셈하고
	    // 그 결과를 수식과 함께 화면에 표시해보세요.
	    int s1 = Short.MIN_VALUE;
	    System.out.println(s1);
	    int s2 = Short.MAX_VALUE;
	    System.out.println(s2);
	    int result = s1 + s2;
	    System.out.println(result);
	
	    int a = Short.MIN_VALUE;
	    int b = Short.MAX_VALUE;
	    int c = a+b;
	    System.out.println(a + " + " + b + " = " + c);
	    String format = "%d + %d = %d%n";
	    System.out.printf(format, a, b, c);
	
	    String fs = String.format(format, a, b, c);    // 서식을 이용한 새 문자열 생성
	    System.out.println(fs);
	}
	
	private static void scannerIntCal()
	{
		//키보드로부터 동적인 데이터를 받아오는 예
	    Scanner kbd = new Scanner(System.in);
	    System.out.print("수1: ");
	    int a = kbd.nextInt();
	    System.out.print("수2: ");
	    int b = kbd.nextInt();
	    System.out.printf("%d + %d = %d %n", a, b, a+b);
	}

	private static void floatDouble()
	{
		float pi = 3.14159201234567F;
	    System.out.println("pi= " + pi);
	
	    double pi2 = 3.141592012345678901234567890;
	    System.out.println("pi2= " + pi2);
	}
	
	private static void floatDoubleMinMax()
	{
		// float, double min / max?
	    float a = -Float.MAX_VALUE;
	    float b = Float.MAX_VALUE;
	    double c = -Double.MAX_VALUE;
	    double d = Double.MAX_VALUE;
	    System.out.printf("min float: %f%n", a);
	    System.out.printf("max float: %f%n", b);
	    System.out.printf("min double: %f%n", c);
	    System.out.printf("max double: %f", d);
	    System.out.println();
	}
	
	private static void floatCircleArea()
	{
		// Scanner를 사용하여 실수 입력받기
	    // kdb.nextFloat(); 
	    // 키보드에서 원의 반지름을 입력받아서 원의 면적 계산,  소수점 이하 4자리까지
	    // r * r * 3.14
	    // 출력예시) 반지름이 2.7인 원의 면적은 =
	    Scanner kbd  = new Scanner(System.in);
	    System.out.print("원의 반지름: ");
	    float r = kbd.nextFloat();
	    float area = r * r * 3.141562F;
	    String areaResult = String.format("반지름이 %f인 원의 면적은 = %.4f", r, area);
	    System.out.println(areaResult);
	}
	
	private static void scannerIdPwd()
	{
		// kbd.next();
	    // 키보드에서 이용자의 아이디, 암호를 입력받아서 아래처럼 화면에 표시하기
	    // 아이디 : 
	    // 암호 : 
	    Scanner kbd = new Scanner(System.in);
	    System.out.print("아이디: ");
	    String a = kbd.next();
	    System.out.print("암호: ");
	    String b = kbd.next();
	    String idPwd = String.format("아이디: %s%n암호: %s", a, b);
	    System.out.println(idPwd);
	}
	
	private static void equalsString()
	{
		// 문자열 비교
	    // a.equals(b)
	    boolean y= 1==2;
	    System.out.println("비교결과: " + y);
	
	    String a = "Hello";
	    String b = "World";
	    boolean same = a.equals(b);
	    System.out.println("문자열 비교결과: " + same);
	}
	
	private static void threeCalculatorLogin()
	{
		// 3항연산자
	    // a ? b : c
	    boolean result1 = 2<1 ? true:false;
	    int result2 = 2<1 ? 1:0;
	    System.out.println(result1);
	    System.out.println(result2);
	
	    // 로그인 성공, 실패 3항 연산자
	    // 로그인(아이디(smith), 암호(1234))
	    Scanner kbd = new Scanner(System.in);
	    System.out.print("아이디: ");
	    String uid = kbd.next();
	    System.out.print("암호: ");
	    String pwd = kbd.next();
	    // System.out.printf("입력하신 아이디는 %s이고, 암호는 %d입니다.", uid, pwd);
	
	    boolean result = uid.equals("smith") && pwd.equals("1234");
	    String success = result ? "로그인 성공" : "로그인 실패";
	    System.out.println(success);
	}
	
	private static void charString()
	{
		// 문자, 문자열(문자의 집합)
	    String s = "smith";
	    char  ch = 'a';		// 97
	    String typeChar = String.format("ch=%c, %d%n", ch, (int)ch);
	    System.out.printf(typeChar); 
	
	    ch = (char)(ch + 1);
	    ch = 99;       		 // c
	    ch = 0x63;     		 // c
	    System.out.println("0x63 -> " + "알파벳 " + ch);
	}
	
	private static void randomCal()
	{
		// rd.nextInt(10);    // 0~9
		// 두 정수를 추출하여 두 수의 곱셈식을 생성하여 화면에 표시
		// 출력예시) 3 * 5 = 15
		Random rd = new Random();
	    System.out.println("두 식을 추출하여 곱셈을 하겠습니다");
	    int a = rd.nextInt(10)+1;
	    int b = rd.nextInt(10)+1;
	    int c = a*b;
	    String calResult = String.format("%d * %d = %d", a,b,c);
	    System.out.println(calResult);
	
	    // 10~20 포함
	    System.out.println("10부터 20사이에 있는 임의의 두 식 추출 / 곱셈 결과");
	    int a2 = rd.nextInt(11)+10;
	    int b2 = rd.nextInt(11)+10; 
	    int c2 = a2*b2;
	    String calResult2 = String.format("%d * %d = %d", a2,b2,c2);
	    System.out.println(calResult2);
	}
	
	private static void equalsReference()
	{
		// 참조형의 비교는 왜 equals()를 사용하는가?
	    String s = "smith";                  // Literal Pool
	    String s2 = new String("smith");   	 // Heap
	    // Literal Pool 에서는 동일한 문자열을 저장하지 않도록 되어 있다.
	    // 현재 메모리에는 "smith"가 몇 개 기억되어 있을까요?
	    System.out.println(s==s2);           // 참조비교
	    System.out.println(s.equals(s2));    // 내용비교
	}
	
	private static void changeTypes()
	{
		short sh = (short)(Short.MAX_VALUE);
	    System.out.println(Short.MAX_VALUE+1 + " , "+sh);
	
	    short s = 4500;
	    int a = s;
	
	    char ch = (char)100;
	    String afterTypeChange = String.format("형변환 후: " + (int)ch);
	    System.out.println(afterTypeChange);
	}
	
	private static void randomAlpabetPrint()
	{
		// 알파벳 소문자 5개 무작위 추출, 화면에 표시
	    // Random 클래스 사용
	    // 알파벳 소문자의 코드범위 확인 후 해당 구간에 대한 무작위 추출
		System.out.println("랜덤 숫자 5개 추출 -> 알파벳/숫자로 표현하겠습니다");
	    Random rd = new Random();
	    char c1 = (char)(rd.nextInt(26) + 97);
	    char c2 = (char)(rd.nextInt(26) + 97);
	    char c3 = (char)(rd.nextInt(26) + 97);
	    char c4 = (char)(rd.nextInt(26) + 97);
	    char c5 = (char)(rd.nextInt(26) + 97);
	    String rdStr = String.format("숫자 -> 알파벳: %c, %c, %c, %c, %c", c1,c2,c3,c4,c5);
	    System.out.println(rdStr);
	
	    int a = rd.nextInt(26) + 97;
	    char a1 = (char)a;
	    int b = rd.nextInt(26) + 97;
	    int c = rd.nextInt(26) + 97;
	    int d = rd.nextInt(26) + 97;
	    int e = rd.nextInt(26) + 97;
	    String rdStrToInt = String.format("알파벳 -> 숫자: %d, %d, %d, %d, %d", a,b,c,d,e);
	    System.out.println(rdStrToInt);
	
	    char a2 = (char)a;
	    char b2 = (char)b;
	    char c21 = (char)c;
	    char d2 = (char)d;
	    char e2 = (char)e;
	    String rdIntToStr = String.format("숫자 -> 알파벳: %c,%c,%c,%c,%c,", a2,b2,c21,d2,e2);
	    System.out.println(rdStr);
	}
}