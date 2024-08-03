package stream;

import java.io.*;
import java.util.*;

public class StreamMain 
{
	// Java의 Stream API 다루기
	/* Stream:데이터의 흐름
	 * System.out.println(name); //메모리에 있는 데이터를 모니터에 표시
	 * new Scanner(System.in);   //키보드에서 들어오는 데이터를 메모리에 복사
	 * 채팅 : com1, com2
	 * 문자 데이터(텍스트):Character Stream, 바이너리 데이터(비문자):Byte Stream
	 * Reader,Writer		     |      InputStream, OutputStream
	 */
	
	/* 스트림은 파이프처럼 연결하여 사용할 수 있다
	 * Node Stream, Filter Stream
	 * Node : 데이터가 흐르기 시작하는 위치, 데이터가 흐름을 마치는 끝에 위치하는 스트림
	 * Filter: Node의 위치에 올 수 없다
	 */
	
	/* 입력스트림, 출력스트림
	 * 입력스트림(Reader, InputStream):데이터를 가져오는 스트림
	 * 출력스트림(Writer, OutputStream): 데이터를 특정 장소로 보내는 스트림
	 */
	
	static Scanner kbd = new Scanner(System.in);
	
	public static void main(String[] args) 
	{
		textInput();
		textInput2();
		textInput3();
		textInput4();
		textInput5();
		memList();
		login();
		textWrite();
		addUser();
		textUpdate();
		textDelete();
	}
	
	/* 텍스트파일을 읽어서 그 내용을 화면에 표시한다
	 * textInput() ~ textInput5()
	 * 텍스트 파일(File)을 읽을(Reader) 때는 FileReader --> data
	 * 화면에 표시 : System.out.println(data);
	 */
	
	// 한 글자 읽어오기 : FileReader
	private static void textInput()
	{
		try {
			FileReader fr = new FileReader("C:/test/data/sample.txt");
			int ch = fr.read(); //글자 1개
			System.out.println("읽어온 데이터:" + (char)ch);
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 배열[]로 읽어올 글의 수를 정하기 : FileReader
	private static void textInput2()
	{
		try {
			FileReader fr = new FileReader("C:/test/data/sample.txt");
			char[] cbuf = new char[12];
			int cnt = fr.read(cbuf);
			String sdata = new String(cbuf);
			System.out.println("읽어온 데이터: " + sdata + "갯수:" + cnt);
			fr.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 배열[]로 한줄씩 읽어 모든 글 읽어오기 : FileReader
	private static void textInput3()
	{
		try {
			FileReader fr = new FileReader("C:/test/data/sample.txt");
			char[] cbuf = new char[12];
			int cnt = 0;
			do
			{
				cnt = fr.read(cbuf);
				if(cnt != -1) {
					String sdata = new String(cbuf, 0, cnt);
					System.out.print(sdata);
				}
			}
			while(cnt!=-1);
			System.out.println("읽기 완료");
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 한줄씩 읽어 모든 글 읽어오기 : BufferedReader
	private static void textInput4()
	{
		try {
			FileReader fr = new FileReader("C:/test/data/sample.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while((line=br.readLine())!=null) {
				System.out.println(line);
			}
			System.out.println("읽기 완료");
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 바이트 데이터로 한번에 읽어오기 : FileInputStream
	private static void textInput5()
	{
		File textFile = new File("C:/test/data/sample.txt");
		int len = (int)textFile.length();
		try {
			FileInputStream fin = new FileInputStream(textFile);
			byte[] buf = new byte[len];  // 파일 크기만큼의 메모리공간 준비
			fin.read(buf);
			String str = new String(buf);
			System.out.println(str);
			fin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* C:/test/data/members.txt
	 * 한 행에 한 회원의 정보가 기록되어 있다
	 * 번호|이름|전화|이메일
	 * String[] token = line.split("\\|");
	 * List<Member> list;
	 * 텍스트파일(member.txt)에서 목록을 가져와서 화면에 표시한다
	 * memList() ~ listFromFile()
	 */
	private static void memList()
	{
		List<Member> list = listFromFile();
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
	}
	
	private static List<Member> listFromFile()
	{
		File f = new File("C:/test/data/members.txt");
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = null;
			List<Member> list = new ArrayList<>();
			while((line=br.readLine())!=null) {
				list.add(new Member(line));
			}
			br.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/* 파일을 사용한 로그인 기능
	 * c:/test/data/users.txt
	 * smith:1234 형식으로 5개 행 생성
	 * 이용자가 키보드에서 로그인하면 User 클래스의 인스턴스를 사용하여 id, pwd를 저장하고
	 * 파일에서 로드된 List<User> 자료구조와 비교하여 회원정보에 존재하는지 확인
	 * 로그인 성공/실패 메시지가 표시되도록 한다
	 * login() ~ getUsers()
	 */
	private static void login()
	{
		System.out.print("아이디 암호:");
		String id = kbd.next().trim();
		String pwd = kbd.next().trim();
		kbd.nextLine();
		
		User inUser = new User(id, pwd);
		List<User> list = getUsers();
		boolean ok = list.contains(inUser);
		String result = ok ? "로그인 성공": "로그인 실패";
		System.out.println(result);
	}
	
	private static List<User> getUsers()
	{
		String fpath = "C:/test/data/users.txt";
		List<User> list = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fpath));
			String line = null;
			while((line=br.readLine())!=null) {
				list.add(new User(line));
			}
			br.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 메모리에 문자열 --> 파일에 기록 (메모리에서 디스크로 데이터 이동)
	// 문자열을 파일(File)에 기록(Write) : FileWriter (노드스트림)
	// System.out.println();  // PrintStream.println()
	private static void textWrite()
	{
		try {
			String fname = "C:/test/data/mytext";
			PrintWriter pw = new PrintWriter(new FileWriter(fname, true));
			pw.println("smith:1234");
			pw.println("scott:4321");
			pw.close();
			System.out.println("파일에 한행 쓰기 성공");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* 키보드에서 아이디, 암호를 입력받아서 users.txt 파일에 한행으로 추가하는 기능
	 * 아이디:암호
	 * 회원정보 추가 성공, users.txt 파일의 목록을 화면에 표시해서 확인
	 * addUser() ~ appendUser(User user)
	 */
	private static void addUser()
	{
		System.out.println("아이디와 암호를 추가하겠습니다.");
		System.out.print("아이디:");
		String uid = kbd.next();	kbd.nextLine();
		System.out.print("암호:");
		String pwd = kbd.next();	kbd.nextLine();
		
		User user = new User(uid, pwd);
		boolean added = appendUser(user);
		
		System.out.println(added ? "추가 성공":"실패");
		
		List<User> list = getUsers();
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
	}
	
	private static boolean appendUser(User user)
	{
		try {
			String fname = "C:/test/data/users.txt";
			PrintWriter pw = new PrintWriter(new FileWriter(fname, true));
			pw.printf("%s:%s%n", user.getId(), user.getPwd());
			pw.close();
			System.out.println("사용자 정보추가 성공");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 텍스트를 로드 > 메모리에서 수정 > 메모리에서 수정된 내용을 기존 파일에 덮어쓰기 한다
	private static void textUpdate()
	{
		File f = new File("C:/test/data/members.txt");
		if(!f.exists()) {
			System.err.println("지정된 파일이 없습니다");
			return;
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = null;
			List<Member> list = new ArrayList<>();
			while((line=br.readLine())!=null) {
				list.add(new Member(line));
			}
			br.close();
			
			// 13번 회원의 전화번호를 010-3333-7777
			Member key = new Member(13);
			if(list.contains(key)) {
				list.get(list.indexOf(key)).setPhone("010-3333-7777");
			}
			//메모리에서 변경된 데이터를 파일에 덮어쓰기한다
			PrintWriter out = new PrintWriter(new FileWriter(f));  //덮어쓰기
			for(int i=0;i<list.size();i++)
			{
				Member m = list.get(i);
				out.printf("%d|%s|%s|%s%n", 
					m.getNo(), m.getName(), m.getPhone(), m.getEmail());
				out.flush();
			}
			out.close();
			System.out.println("전화번호 변경 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void textDelete()
	{
		// 텍스트를 로드 > 메모리에서 삭제 > 메모리에서 수정된 내용을 기존 파일에 덮어쓰기 한다
		File f = new File("C:/test/data/members.txt");
		if(!f.exists()) {
			System.err.println("지정된 파일이 없습니다");
			return;
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = null;
			List<Member> list = new ArrayList<>();
			while((line=br.readLine())!=null) {
				list.add(new Member(line));
			}
			br.close();
			
			// 14번 회원정보 삭제
			Member key = new Member(14);
			if(list.contains(key)) {
				list.remove(key);
			}
			// 메모리에서 변경된 데이터를 파일에 덮어쓰기한다
			PrintWriter out = new PrintWriter(new FileWriter(f));  //덮어쓰기
			for(int i=0;i<list.size();i++) {
				Member m = list.get(i);
				out.printf("%d|%s|%s|%s%n", 
					m.getNo(), m.getName(), m.getPhone(), m.getEmail());
				out.flush();
			}
			out.close();
			System.out.println("지정된 회원정보 삭제 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
