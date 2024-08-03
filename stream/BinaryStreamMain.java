package stream;

import java.io.*;

public class BinaryStreamMain 
{
	public static void main(String[] args) 
	{
		// Text, None-Text
		/* Character Stream : 문자를 다루는 스트림
		 *   + Reader, Writer
		 *   
		 * Binary Stream(Byte Stream)
		 *   + InputStream, OutputStream
		 *   
		 * 변환 스트림
		 *   + InputStreamReader: 바이너리 데이터를 문자 데이터로 변환
		 *   + OutputStreamWriter: 문자 데이터를 바이너리 데이터로 변환
		 *   
		 * 네트워크 통신
		 *   + 문자 메시지 --> 바이트 데이터 --> 문자 데이터
		 */

		binaryTest01();
		binaryTest02();
		binaryTest03();
		binaryTest04();
		binaryTest05();
		conversionTest01();
	}
	
	// 이미지 파일의 정보를 byte로 읽어 int로 표시하기 : FileInputStream
	private static void binaryTest01()
	{
		String imgPath = "c:/test/data/img/sample.jpg";
		// byte(1), short(2), int(4), long(8)
		try {
			FileInputStream fin = new FileInputStream(imgPath);
			while(true) {
				int data = fin.read();
				if(data==-1) break;
				System.out.print(data+",");
			}
			System.out.println();
			System.out.println("이미지 읽기 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 이미지를 배열[] 단위로 읽고, 이미지 파일 크기 표시하기 : readAllBytes()
	private static void binaryTest02()
	{
		String imgPath = "c:/test/data/img/sample.jpg";
		try {
			FileInputStream fin = new FileInputStream(imgPath);
			byte[] imgData = fin.readAllBytes();
			System.out.println("이미지 읽기 완료, 이미지 파일크기: " + imgData.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 이미지를 배열[] 단위로 읽고, 파일에 쓰기(복사하기) : FileOutputStream
	private static void binaryTest03()
	{
		String imgPath = "c:/test/data/img/sample.jpg";
		try {
			FileInputStream fin = new FileInputStream(imgPath);
			byte[] imgData = fin.readAllBytes();
			fin.close();
			
			String imgDest = "c:/test/data/img/sample_cpy.jpg";
			FileOutputStream fout = new FileOutputStream(imgDest);
			fout.write(imgData);
			fout.close();
			System.out.println("이미지 복사 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// byte[256] 단위로 읽어서 바로 다른 파일에 쓰기(복사하기)
	private static void binaryTest04()
	{
		String imgPath = "c:/test/data/img/sample.jpg";
		String imgDest = "c:/test/data/img/sample_cpy2.jpg";
		
		try {
			FileInputStream fin = new FileInputStream(imgPath);
			FileOutputStream fout = new FileOutputStream(imgDest);
			byte[] buf = new byte[256];
			while(true) {
				int cnt = fin.read(buf);
				if(cnt==-1) break;
				fout.write(buf,0,cnt);
			}
			
			fin.close();
			fout.close();
			
			System.out.println("이미지 복사 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 이미지를 반복하여 읽어서 메모리에 데이터를 누적한다
	// ByteArray
	private static void binaryTest05()
	{
		String imgPath = "c:/test/data/img/sample.jpg";
		String imgDest = "c:/test/data/img/sample_cpy3.jpg";
		try {
			FileInputStream fin = new FileInputStream(imgPath);
			FileOutputStream fout = new FileOutputStream(imgDest);
			//ByteArrayInputStream bin = new ByteArrayInputStream()
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			
			byte[] buf = new byte[256];
			while(true) {
				int cnt = fin.read(buf);
				if(cnt==-1) break;
				bout.write(buf, 0, cnt);
			}
			
			byte[] img = bout.toByteArray();
			fout.write(img);
			
			bout.close();
			fin.close();
			fout.close();
			
			System.out.println("이미지 복사 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void conversionTest01()
	{
		// 키보드에서 문자열을 받아서 파일에 저장할 때 문자 스트림이 아닌 바이트 스트림을 사용한다
		try {
			// PrintWriter pw = new PrintWriter(new FileWriter("c:/test/data/conv.txt"));
			PrintWriter pw = new PrintWriter(new FileOutputStream("c:/test/data/conv.txt"));
			/ *Creates a new PrintWriter, without automatic line flushing, from anexisting OutputStream. 
			 * This convenience constructor creates thenecessary intermediate OutputStreamWriter
			 */
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
