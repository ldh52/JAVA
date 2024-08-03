package stream;

public class User 
{
	private String id;
	private String pwd;
	
	public User() {}
	public User(String id, String pwd) {
		setId(id);
		setPwd(pwd);
	}
	
	public User(String line) {
		String[] info = line.split("\\:");
		setId(info[0]);
		setPwd(info[1]);
	}
	
	@Override
	public boolean equals(Object obj) {
		User other = (User) obj;
		return this.getId().equals(other.getId()) &&
				this.getPwd().equals(other.getPwd());
	}
	
	@Override
	public String toString() {
		return String.format("%s\t%s", getId(), getPwd());
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
