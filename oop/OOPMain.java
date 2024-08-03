package oop;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Arrays;

public class OOPMain 
{
	static Scanner kbd = new Scanner(System.in);
	static List<Member> mems = new ArrayList<>();
	
	public static void main(String[] args) 
	{
		/*
		Board b = new Board(1, "게시판 테스트","smith");	// 인스턴스 생성(객체생성), Heap에 로드
		Board b2 = new Board(2, "축하해요", "james");
		Board b3 = new Board(3, "사랑해요", "Romeo");

		// b.title = "다른 제목으로 수정";
		
		Board[] barr;
		barr = new Board[3];
		barr[0] = b;
		barr[1] = b2;
		barr[2] = b3;
		
		for(int i=0; i<barr.length; i++)
		{
			System.out.printf("%d\t %s\t %s %n", 
								barr[i].getNum(), 
								barr[i].getTitle(), 
								barr[i].getAuthor());
		}
		*/
		
		User u = getUser();
		String msg = login(u) ? "succes" : "no";
		System.out.println(msg);
		
//		 키보드에서 회원의 번호, 이름, 전화, 이메일을 입력받아서
//		 Member 인스턴스를 생성하고 배열에 저장한 후에 이용자가 아무것도 입력하지 않고
//		 그냥 엔터를 누르면 지금까지 입력된 회원정보를 화면에 목록으로 표시하는 기능을 작성해보세요.
		
		inputMember();
		memList();
		listTest();
		inputMember();			// 3인 정보 입력
		// 리스트에서 검색하여 번호가 11번인 회원정보를 화면에 표시해보세요.
		listSearch();
		listRemove();
		memList();
		listUpdate();
		initMems();
		memList();
		updatePhoneByNo();
		memList();
	}
	
	/*
	private static Member getMember() {
		System.out.print("no name phone email");
		String input = kbd.nextLine().trim();
		System.out.println("회원번호, 이름, 전화번호, 이메일을 차례대로 입력해주세요.");
		System.out.println("입력을 모두 마치면 엔터를 입력해주세요.");
		String[] data = input.split("\\s+");
		Member m = new Member(no, name, phone, email);
		return m;
	}
	*/
	
	private static void initMems()
	{
		mems.add(new Member(11, "smith", "010-1234-5678", "smith@gmail.com"));
		mems.add(new Member(12, "james", "010-5678-1234", "james@yahoo.com"));
	}
	
	private static void updatePhoneByNo() 
	{
		//전화번호 갱신 대상 회원의 번호, 새 전화번호
		inputMember();	
		
		System.out.print("몇 번 회원의 정보를 바꿀까요? : ");
		int no = kbd.nextInt();
		kbd.nextLine();
		
		System.out.print("새 전화번호 : ");
		String phone = kbd.nextLine();
		
		Member key = new Member(no, phone);
		if(mems.contains(key))
		{
			int idx = mems.indexOf(key);
			mems.get(idx).setPhone(key.getPhone());
			System.out.println("전화번호 변경 성공");
		}
		else
		{
			System.err.println("전화번호 변경 실패");
		}
		memList();
	}
	
	private static void listUpdate() 
	{
		inputMember();
		Member key = new Member(11, "1", "1", "1");
		if(mems.contains(key))
		{
			Member rem = mems.set(mems.indexOf(key), key);
			if(rem !=null) System.out.println("수정성공");
			else System.err.println("수정실패");
		}
	}
	
	private static void listRemove()
	{
		inputMember();
		// 특정 번호를 가진 회원정보를 리스트에서 삭제해보세요.
		memList();
		/*
		System.out.println("삭제할 회원번호: ");
		int delNo = kbd.nextInt();
		mems.remove(delNo-1);
		*/
		Member key = new Member(11);
		boolean removed = mems.remove(key);
		System.out.println("삭제결과: " + removed);
		//memList();
	}
	
	private static void listSearch()
	{
		inputMember();
		Member key = new Member(11);
		if(mems.contains(key))
		{
			int idx = mems.indexOf(key);
			Member found = mems.get(idx);
			System.out.println(found);
		}
	}

	private static void listTest()
	{
		// List 사용하기
		List<String> sList = new ArrayList<>();
		sList.add("hello");				// Create
		sList.add("World");
		sList.add("홍길동");
		sList.add("홍길동");
		
		for(int i=0; i<sList.size(); i++)
		{
			String v = sList.get(i);					// Read
			System.out.println(v);
		}
		
		System.out.println(sList.contains("World"));	// search: true/false
		int idx = sList.indexOf("World");
		String value = sList.get(idx);
		System.out.println("검색 결과: " + value);
		
		sList.set(2, "임꺽정");							// update
		System.out.println(Arrays.toString(sList.toArray()));
		
		sList.remove("홍길동");							// delete
		// sList.remove(2);
		System.out.println(Arrays.toString(sList.toArray()));
	}

	// 3인 정보 입력
	// 리스트에서 검색하여 번호가 11번인 회원정보를 화면에 표시해보세요.
	private static void inputMember()
	{
		while(true)
		{
			System.out.print("no name phone email: ");
			String input = kbd.nextLine().trim();
			if(input.equals("")) break;
			String[] token = input.split("\\s+");
			Member m = new Member(token);	// Constructor Overload
			// mems[cnt++] = m;
			mems.add(m);
		}
	}
	
	// 11 smith 010-1234-5678 smith@gmail.com
	// 12 james 010-5678-1234 james@yahoo.com
	private static void memList()
	{
		for(int i=0; i<mems.size(); i++)
		{
			System.out.println(mems.get(i));	// toString() 메소드의 오버라이드(Override)요구됨
		}
	}
	
	private static User getUser()
	{
		System.out.print("id pwd");
		String input = kbd.nextLine().trim();
		System.out.println("id, pwd를 차례대로 입력해주세요.");
		String[] data = input.split("\\s+");
		User u = new User(data[0], data[1]);
		return u;
	}
	
	private static boolean login(User u)
	{
		return u.getId().equals("smith") && u.getPwd().equals("1234");
	}
}

class Board			// Inheritance, Polymorphism, Encapsulation
{
	// 번호, 제목, 작성자, 본문, 작성일, 히트수
	private int num;		// Access Modifier(public, protected, private, default)
	private String title;
	private String author;
	private String contents;
	private Date rDate;
	int hits;
	//-------------------------------------------------------------------
	// 개발자가 생성자를 정의하지 않으면 컴파일러는 자동으로 기본 생성자를 선언한다
	public Board() {}
	
	// 생성된 인스턴스 멤버 변수를 초기화하는 생성자
	// 인스턴스가 생성된 직후에 자동으로 호출됨
	public Board(int num, String title, String author)
	{
		setNum(num);
		setTitle(title);
		setAuthor(author);
		// 기본생성자(Constructor)
		//this.num = num;
		//this.title = title;
		//this.author = author;
	}

	public int getNum() {
		return num;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public String getContents() {
		return contents;
	}
	public Date getrDate() {
		return rDate;
	}
	public int getHits() {
		return hits;
	}
	public void setNum(int num) {
		if(num<=0)
		{
			System.err.println("num 초기화 실패(글번호는 0보다 커야합니다)");
			return;
		}
		this.num = num;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public void setrDate(Date rDate) {
		this.rDate = rDate;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
}
	// *****************************************************************************
	// 키보드에서 입력된 아이디, 암호를 사용하여 user 클래스의 인스턴스를 초기화하고
	// boolean login(User u) 메소드를 작성하여 로그인 성공여부를 확인하는 기능을 작성해보세요.
	// Encapsulation 적용
	// *****************************************************************************

class User
{
	private String id;
	private String pwd;
	
	public User() {}
	public User(String id, String pwd)
	{
		setId(id);
		setPwd(pwd);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
class Member extends Object		// Inheritance(상속)
{
	private int no;
	private String name;
	private String phone;
	private String email;
	
	public Member() {}
	public Member(int no, String name, String phone, String email)
	{
		setNo(no);
		setName(name);
		setPhone(phone);
		setEmail(email);
	}
	public Member(String[] token) {		//생성자 오버로드(constructor Overload)
		int no = Integer.parseInt(token[0]);
		String name = token[1];
		String phone = token[2];
		String email = token[3];
		
		setNo(no);
		setName(name);
		setPhone(phone);
		setEmail(email);
	}

	public Member(int no)
	{
		setNo(no);
	}
	
	public Member(int no, String phone)
	{
		setNo(no);
		setPhone(phone);
	}
	
	@Override
	public boolean equals(Object obj) {
		Member other = (Member)obj;
		return this.getNo()==other.getNo();
	}
	@Override
	public String toString() 
	{
		String str = String.format("%d\t%s\t%s\t%s", 
				this.getNo(), 
				this.getName(), 
				this.getPhone(),
				this.getEmail());
		return str;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}

/* Collection API
 * 
 * 다수의 객체를 저장 및 관리하는 자료구조
 * List, Set, Map
 * List : 저장할 때의 순서가 유지됨, 동일 객체의 중복저장 가능 
 * Set : 순서유지 안됨, 중복저장 안됨
 * Map : 값만 저장하지 않고 값에 꼬리표(key)를 붙여서 함께 저장(key, value 쌍으로 저장)
 * CRUD 중심으로 학습
 */