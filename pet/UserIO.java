package pet;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class UserIO 
{
	static Scanner kbd = new Scanner(System.in);
	static String menu = "추가(a), 목록(s), 검색(f), 수정(u), 삭제(d), 종료(x):";

	public static String showmenu() {
		System.out.println();
		System.out.println(menu);
		String m= kbd.nextLine().trim();
		return m;
	}

	public static void add() 
	{
		// no, species, weight, price
		System.out.println("글번호:");
		int no = kbd.nextInt();				kbd.nextLine();
		
		System.out.println("품종:");
		String species = kbd.nextLine();
		
		System.out.println("무게:");
		Float weight = kbd.nextFloat();		kbd.nextLine();
		
		System.out.println("가격:");
		int price = kbd.nextInt();			kbd.nextLine();
		
		PetVO pet = new PetVO();
		pet.setNo(no);
		pet.setSpecies(species);
		pet.setWeight(weight);
		pet.setPrice(price);
		
		boolean added = FileIO.addObject(pet);
		if(added) System.out.println("저장 성공");
		else System.out.println("저장 실패");
	}

	public static void list() 
	{
		List<PetVO> list = FileIO.getList();
		System.out.println();
		System.out.println("\t\t** 게시글 목록 **");
		Collections.sort(list);
		for(int i=0; i<list.size();i++) {
			System.out.println(list.get(i));
		}
	}

	public static void find() 
	{
		System.out.println("검색할 글번호:");
		int no = kbd.nextInt();		kbd.nextLine();
		
		PetVO pet = FileIO.findByNo(no);
		
		System.out.println("\t\t ** 검색결과 **");
		if(pet != null) {
			System.out.println(pet);
		} else {
			System.out.println("검색된 글이 없습니다.");
		}
	}

	public static void update() 
	{
		System.out.print("검색할 품종:");
		String species = kbd.nextLine();
		
		PetVO found = FileIO.findBySpecies(species);
		if(found == null) {
			System.out.println("제목으로 검색된 품종이 없습니다.");
			return;
		}
		
		// no, species, weight, price
		System.out.print("품종:");
		String newSpecies = kbd.nextLine();
		
		System.out.print("무게:");
		Float newWeight = kbd.nextFloat();	kbd.nextLine();
		
		System.out.print("가격:");
		int newPrice = kbd.nextInt();		kbd.nextLine();
		
		PetVO pet = new PetVO();
		pet.setNo(found.getNo());
		pet.setSpecies(newSpecies);
		pet.setWeight(newWeight);
		pet.setPrice(newPrice);
		
		boolean updated = FileIO.update(pet);
		
		if(updated) System.out.println("수정 성공");
		else System.out.println("수정 실패");
	}

	public static void delete() 
	{
		System.out.println("삭제할 글번호");
		int no = kbd.nextInt();			kbd.nextLine();
		
		boolean deleted = FileIO.delete(no);
		if(deleted) System.out.println("삭제 성공");
		else System.out.println("삭제 실패");
	}
}
