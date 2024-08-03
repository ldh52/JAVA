package network;

import java.net.*;
import java.io.*;
import java.util.*;

public class Server 
{
	public static void main(String[] args) 
	{
		try {
			ServerSocket ss = new ServerSocket(1234);
			while(true)
			{
				System.out.println("서버 대기 중...");
				Socket s = ss.accept();
				System.out.println("클라이언트 접속");
				
				new LoginThread(s).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("서버 종료");
	}
}
