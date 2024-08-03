package inheritance;

public class Hamster extends Pet 
{
	float size;

	public Hamster() {}
	
	@Override
	public String toString() {
		return super.toString() +"\t\t\t"+ size;
	}
	
	public Hamster(int price, String species, float size) {
		super(price, species);
		setSize(size);
	}
	
	public Hamster(String[] token) { 
		super(Integer.parseInt(token[0]), token[1]);
        float size = Float.parseFloat(token[2]);  
        setSize(size);
	}
	
	public Hamster(String string, int i, float f) {
		// TODO Auto-generated constructor stub
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}
}
