package inheritance;

import java.util.ArrayList;
import java.util.List;

public class PetMain
{
	/* Pet Store
	 * Cat, Dog, Snake, Hamster : Pet(String species, int price)
	 * Cat(String pattern, float age, int price, String species)
	 * Dog(float weight, float age, int price, String species)
	 * Snake(String pattern, float size, int price, String species)
	 * Hamster(float size, int price, String species)
	 * 
	 * Pet 클래스를 정의해보세요.
	 * CAt, Dog, Snake, Hamster 파생하여 생성
	 * 자식 클래스의 인스턴스를 생성하고 한개의 Collection에 저장
	 * Collection의 내용을 화면에 표시해보세요.
	 * OOP의 3대 특징이 잘 적용된 코드를 구현해보세요.
	 * 
	 * Pet 관리 시스템
	 * 추가(a): Cat(c), Dog(d), Snake(s), Hamster(h)
	 * 목록(s): 품종 가격 나이 무늬 체중 길이
	 */
	
	public static void main(String[] args) 
	{
		
		showFirstList();
//		addPetSpecies();
		

	}

	private static void addPetSpecies() 
	{
		// TODO Auto-generated method stub
		
	}

	private static void showFirstList() 
	{
		List<Pet> pet = new ArrayList<>();
		Cat cat = new Cat("rackdol", 250000, 1.2f, "singlecoat");
		Dog dog = new Dog("poodle", 300000, 2.8f, 0.2f);
		Snake snake = new Snake("드레곤", 350000, "mixed", 15.5f);
		Hamster hamster = new Hamster("햄스터", 50000, 10.5f);
		
		pet.add(cat);
		pet.add(dog);
		pet.add(snake);
		pet.add(hamster);
		
		System.out.println("\t\t ************  Pet List  ************\n");
		System.out.println("품종\t\t 가격\t\t 나이\t\t 무늬\t\t 체중\t\t 길이");
		for(int i=0;i<pet.size();i++) {
			System.out.println(pet.get(i));
		}
	}
	
}