package network;

import java.io.*;
import java.net.Socket;

public class LoginThread extends Thread 
{
	private Socket s;
	private ObjectInputStream oin;
	private ObjectOutputStream oos;
	
	public LoginThread(Socket s) {
		this.s = s;
	}
	
	@Override
	public void run() {
		try {
			OutputStream out = s.getOutputStream();
			this.oos = new ObjectOutputStream(out);
			
			ChatMsg cm = new ChatMsg("서버", "클라이언트", "아이디 암호");
			oos.writeObject(cm);
			oos.flush();
			
			InputStream in = s.getInputStream();
			this.oin = new ObjectInputStream(in);
			
			ChatMsg cm2 = (ChatMsg)this.oin.readObject();
			System.out.printf("%s / %s %n", cm2.uid, cm2.pwd);
			
			if(cm2.uid.length()>3 && cm2.pwd.length()>3) {    // 이용자 인증
				ChatThread.user.put(cm2.uid, oos);
				new ChatThread(cm2.uid, this.s, this.oin, this.oos).start();  // 채팅 시작
			} else {
				ChatMsg cm3 = new ChatMsg("서버","클라이언트", "로그인 실패");
				oos.writeObject(cm3);
				oos.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println("LoginThread dead");
	}
}
