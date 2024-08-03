package oop;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Array
{
	static Scanner kbd = new Scanner(System.in);
	
	public static void main(String[] args) 
	{
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
			System.out.printf("%d|%s|%s %n", 
								barr[i].getNum(), 
								barr[i].getTitle(), 
								barr[i].getAuthor());
		}
	}
}