package network;

import java.io.ObjectInputStream;
import java.util.*;

public class ClientNetInputThread extends Thread 
{
	private ObjectInputStream oin;
	static ChatMsg chatMsg;
	
	public ClientNetInputThread(ObjectInputStream oin) {
		this.oin = oin;
	}

	@Override
	public void run() 
	{
		try {
			while(true) { 
				ChatMsg cm = (ChatMsg)oin.readObject();
				System.out.println("[" + cm.uid + "] " + cm.msg);
				if(cm.fdata!=null && cm.fdata.length>0) {
					chatMsg = cm;
					System.out.println("첨부파일("+cm.fname+")다운로드(y/n):");
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.err.println("ClientNetInputThread dead");
	}
}
