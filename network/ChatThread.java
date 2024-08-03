package network;

import java.io.*;
import java.util.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ChatThread extends Thread 
{
	private String userid;
	private Socket s;
	private ObjectInputStream oin;
	private ObjectOutputStream oos;
	
	static Map<String, ObjectOutputStream> user = new HashMap<>();
	
	public ChatThread(String uid, Socket s, ObjectInputStream oin, ObjectOutputStream oos) {
		this.userid = uid;
		this.s = s;
		this.oin = oin;
		this.oos = oos;
		
		ChatMsg cm = new ChatMsg("서버","클라이언트", "로그인 성공");
		try {
			oos.writeObject(cm);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run()         
	{
		try {
			while(true) {
				ChatMsg cm = (ChatMsg)oin.readObject(); // 클라이언트가 종료하면 SocketException 발생
				if(cm.isSecret) {  // 특정 클라이언트에게만 메시지 전달
					user.get(cm.to).writeObject(cm);
					user.get(cm.to).flush();
					continue;
				}
				//접속한 모든 이용자에게 메시지를 전달한다
				Set<String> idSet = ChatThread.user.keySet();
				Iterator <String> idIter = idSet.iterator();
				ObjectOutputStream userOut = null;
				String userid = null;
				while(idIter.hasNext()) {
					userid = idIter.next();
					userOut = user.get(userid);
					userOut.writeObject(cm);
					userOut.flush();
				}
			}
		} catch (Exception e) {
			InetAddress ia = s.getInetAddress();
			System.err.println(ia + " 이용자 퇴장");
			//user 맵에서 퇴장한 이용자의 정보를 삭제한다
			user.remove(userid);
		}
		System.err.print("ChatThread dead");
	}
}
