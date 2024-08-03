package err;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class ExceptionMain 
{
	static Scanner kbd = new Scanner(System.in);
	
	public static void main(String[] args) 
	{
		// 에러, 예외
		// Error : 
		// Exception : Mild Error
		// 소프트웨어 에러(실행 중 에러) 
		// 비정상 종료
		
		error01();
//		error02();
//		error03();
//		
//		try {
//			error04();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		try {
//			error05();
//		} catch (PasswordException e) {
//			System.err.println(e.getMessage());
//		}
		
//		try {
//			error06();
//		} catch (UserLoginException e) {
//			System.err.println(e.getMessage());
//		}
	}
	
	private static void error01()
	{
		int a = 5;
		int b = 0;
		int c;
		try {
			c = a/b;
			System.out.println("나눈 결과 = " + c);
		}
		catch(ArithmeticException ae) {
			// 이 부분 실행
			// System.err.println(ae.getMessage());
			System.err.println("0으로 수를 나눌 수는 없습니다");
			return;
		}
		finally	// 예외 발생과 무관하게 실행되는 블럭 
		{
			System.out.println("finally 블럭");
		}
		System.out.println("메소드 종료");
	}
	
	// 키보드에서 정수 a, b를 받아서 나눗셈하고 그 결과식을 화면에 표시한다
	// 결과식의 예시 : "4/3 = 1"
	private static void error02() 
	{
		boolean go =true;
		while(go)
		{
			System.out.print("정수 a b: ");
			try {
				int a = kbd.nextInt();
				int b = kbd.nextInt();	

				int c = a/b;
				String s =String.format("%d / %d = %d", a,b,c);
				System.out.println(s);
				break;
			} catch(Exception ex) {
				if (ex instanceof ArithmeticException) {
					System.err.println("0으로 나눌 수 없습니다.");
				} else if(ex instanceof InputMismatchException) {
					System.err.println("정수만 입력해주세요");
				}
			}
			/*
			catch(ArithmeticException ae)	{
				System.err.println("0으로 나눌 수 없습니다.");
				System.out.println("두 수를 다시 입력해주세요.");
			} catch(InputMismatchException ime)	{
				System.err.println("정수가 아닙니다.");
				System.out.println("두 수를 다시 입력해주세요.");
			} 
			*/
			finally {
				kbd.nextLine();
			}
		}	
		System.out.println("프로그램 종료");
	}

	// abc.txt 파일을 열고 그 안에 저장된 텍스트를 화면에 표시해보세요.
	private static void error03() 
	{
		BufferedReader br = null;
		String fpath = "C:/test/data/abc.txt";
		try {
			br = new BufferedReader(new FileReader(fpath));
			String line = null;
			while((line=br.readLine()) !=null) {
				System.out.println(line);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if(br != null) br.close();
			} catch (IOException e) {
			e.printStackTrace();
			}
		}	
		System.out.println("메소드 종료");
	}
	
	private static void error04() throws Exception
	{
		String fpath = "C:/test/data/abc.txt";
		BufferedReader br = new BufferedReader(new FileReader(fpath));
		String line = null;
		while((line=br.readLine()) !=null) {
			System.out.println(line);
		}
		br.close();

	}
	
	private static void error05() throws PasswordException 
	{
		System.out.println("id pass:");
		String uid = kbd.next();
		String pwd = kbd.nextLine();
		
		if(pwd.length()<5) {
			throw new PasswordException("암호는 5자 이상이어야 합니다");
		}
		System.out.println("메소드 종료");
	}
	
	// user.txt 파일에 아이디, 암호를 5개 저장하고
	// 키보드에서 로그인할 때 로그인에 실패할 경우 UserLoginException이 발생하도록
	// 하고 발생한 예외를 처리하여 이용자에게 적절한 메시지를 표시하여 다시 로그인
	// 할 수 있도록 반복 로그인할 수 있도록 작성해보세요.
	private static void error06() throws UserLoginException
	{
		System.out.println("아이디 비번");
		String id = kbd.next().trim();
		String pwd = kbd.next().trim();
		kbd.nextLine();
		
		String fpath = "C:/test/data/users.txt";
		boolean ok = false;

		try {
			BufferedReader br = new BufferedReader(new FileReader(fpath));
			String line = null; 
			
			while((line=br.readLine()) != null)
			{
				String[] fdata = line.split("\\:");
				if(fdata[0].equals(id) && fdata[1].endsWith(pwd))
				{
					System.out.println("로그인 성공");
					ok =true;
					break;
				}
			}
			br.close();	
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(!ok) {
			throw new UserLoginException("로그인실패");
		}
	}
}