package network;

import java.net.*;
import java.io.*;
import java.util.*;

public class Client 
{
	static Scanner kbd = new Scanner(System.in);
	
	public static void main(String[] args) 
	{
		try {
			Socket s = new Socket("220.67.113.131", 1234); // 접속 요청
			
			InputStream in = s.getInputStream();
			ObjectInputStream oin = new ObjectInputStream(in);
			ChatMsg cm = (ChatMsg)oin.readObject();
			System.out.println(cm.msg + ":"); // 아이디 암호:
			
			String uid = kbd.next();
			String pwd = kbd.nextLine();
			
			// 출력스트림, uid, pwd를 ChatMsg에 저장하여 서버로 전송한다
			ChatMsg cm2 = new ChatMsg(true, uid, pwd);
			OutputStream out = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(cm2);
			oos.flush();
			
			// 로그인 결과 수신
			cm = (ChatMsg)oin.readObject();
			
			if(cm.msg.equals("로그인 성공")) {
				new ClientNetInputThread(oin).start();
				while(true) {   // 채팅 시작
					System.out.println("귓속말(s) 공개메시지(p) 종료(x):");
					String m = kbd.nextLine().trim();
					
					if(m.equalsIgnoreCase("x")) {
						System.out.println("채팅을 종료합니다");
						break;
					} 
					else if(m.equals("s")) {
						System.out.println("수신자:");
						String rec = kbd.nextLine();
						
						System.out.println("메시지:");
						String msg = kbd.nextLine();
						
						System.out.println("첨부:");
						String fname = kbd.nextLine();
						
						cm = new ChatMsg();
						if(fname!=null && !fname.equals("")) {
							byte[] fdata = new FileIO().load(fname);
							if(fdata!=null) {
								cm.fname = fname;
								cm.fdata = fdata;
							} else {
								System.out.println("첨부파일 없이 메시지만 전송합니다");
							}
						}
						cm.uid = uid;  			// 송신
						cm.isSecret = true;		// 비밀 메시지
						cm.to = rec;			// 수신
						cm.msg = msg;			// 대화
						oos.writeObject(cm);
						oos.flush();
					}
					else if(m.equals("p")) {
						System.out.println("메시지:");  // 공개 메시지 보내는 경우
						String msg = kbd.nextLine().trim();
						
						cm = new ChatMsg();
						cm.uid = uid;
						cm.msg = msg;
						oos.writeObject(cm);
						oos.flush();
					} else if(m.equals("y")) { // 파일 다운로드 승락(y)인 경우
						String fname = ClientNetInputThread.chatMsg.fname;
						byte[] fdata = ClientNetInputThread.chatMsg.fdata;
						boolean saved = new FileIO().download(fname, fdata);
						if(saved) System.out.println("다운로드 성공");
						else System.err.println("다운로드 실패");
					}
				}
			} else {
				System.err.println("로그인 실패");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("클라이언트 종료");
	}
}
