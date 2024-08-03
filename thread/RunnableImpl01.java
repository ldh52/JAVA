package thread;

public class RunnableImpl01 implements Runnable 
{
	@Override
	public void run() {
		String it = Thread.currentThread().getName();
		for(int i=0; i<10; i++) {
			System.out.println(it + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(it + "Thread Dead");
	}
}
