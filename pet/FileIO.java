package pet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileIO 
{
	static String serPath = "C:/test/data/pet.ser";

	public static boolean addObject(PetVO pet) {
		List<PetVO> list = getList();
		list.add(pet);
		return overwrite(list);
	}
	
	public static List<PetVO> getList()
	{
		try {
			File ser = new File(serPath);
			if(!ser.exists())		// 직렬화 파일이 없는 경우
			{
				List<PetVO> list = new ArrayList<>();
				overwrite(list);
			}
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serPath));
			List<PetVO> list = (List<PetVO>)ois.readObject();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	private static boolean overwrite(List<PetVO> list)
	{
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serPath));
			oos.writeObject(list);
			oos.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static PetVO findByNo(int no) {
		List<PetVO> list = getList();
		PetVO key = new PetVO(no);
		if(list.contains(key)) {
			PetVO pet = list.get(list.indexOf(key));
			return pet;
		}
		return null;
	}

	public static PetVO findBySpecies(String species) {
		List<PetVO> list = getList();
		for(int i=0;i<list.size();i++) {
			PetVO b = list.get(i);
			if(list.get(i).getSpecies().contains(species)) {
				return b;
			}
		}
		return null;
	}

	public static boolean update(PetVO pet) {
		List<PetVO> list = getList();
		if(list.contains(pet)) {
			PetVO found = list.get(list.indexOf(pet));
			found.setSpecies(pet.getSpecies());
			found.setWeight(pet.getWeight());
			found.setPrice(pet.getPrice());

			return overwrite(list);
		}
		return false;
	}	
}
