package pet;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Objects;

public class PetVO implements Comparable<PetVO>, Serializable
{
	// no, species, weight, price
	private int no;
	private String species;
	private Float weight;
	private int price;

	public PetVO() {}

	public PetVO(String line) {
		String[] token = line.split("\\|");
		setNo(Integer.parseInt(token[0]));
		setSpecies(token[1]);
		setWeight(Float.parseFloat(token[2]));
		setPrice(Integer.parseInt(token[3]));
	}

	public PetVO(int no) {
		setNo(no);
	}

	@Override
	// boolean same = pet1.equals(pet2);
	public boolean equals(Object obj) {
		PetVO other = (PetVO)obj;
		return this.getNo() == other.getNo(); 
	}

	@Override
	public String toString() {
		// no, species, weight, price
		NumberFormat nf = NumberFormat.getNumberInstance();
		String s = String.format("%d\t%s\t%.2f\t%s", no, species, weight, nf.format(price));
		return s;
	}
	
	@Override
	public int compareTo(PetVO other) {
		// 왼쪽의 객체가 크면 양수, 동일하면 0, 작으면 음수
		if(this.getNo() > other.getNo()) return 1;
		else if(this.getNo() == other.getNo()) return 0;
		else return -1;
	}

	public int getNo() {
		return no;
	}

	public String getSpecies() {
		return species;
	}

	public Float getWeight() {
		return weight;
	}

	public int getPrice() {
		return price;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
