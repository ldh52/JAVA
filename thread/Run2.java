package thread;

import java.util.Date;

public class Run2 implements Runnable 
{
	@Override
	public void run() {
		String dt = Thread.currentThread().getName();
		for(int i=0; i<10; i++) {
			System.out.println(dt + ":" + new Date());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(dt + "Thread Dead");
	}
}
