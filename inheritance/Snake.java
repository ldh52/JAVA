package inheritance;

public class Snake extends Pet 
{
	String pattern;
	float size;
	
	public Snake() {}
	
	@Override
	public String toString() {
		return super.toString() +"\t"+ pattern +"\t\t"+ size;
	}
	
	public Snake(int price, String species, String pattern, float size) {
		super(price, species);
		setPattern(pattern);
		setSize(size);
	}
	
	public Snake(String[] token) { 
		super(Integer.parseInt(token[0]), token[1]);
		String pattern = token[2];
        float size = Float.parseFloat(token[3]);  
        setPattern(pattern);
        setSize(size);
	}
	
	public Snake(String string, int i, String string2, float f) {
		// TODO Auto-generated constructor stub
	}

	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public float getSize() {
		return size;
	}
	public void setSize(float size) {
		this.size = size;
	}	
}
