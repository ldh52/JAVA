package stream;

public class Member 
{
	private int no;
	private String name;
	private String phone;
	private String email;
	
	public Member() {}
	public Member(int no)
	{
		this.no = no;
	}
	public Member(int no, String name, String phone, String email)
	{
		setNo(no);
		setName(name);
		setPhone(phone);
		setEmail(email);
	}
	
	/**
	 * 파일에서 읽어온 회원 문자열에 포함된 각 필드 구분자(|)를 사용하여 문자열을 나누고 인스턴스 변수를 초기화한다
	 * @param line 각 필드의 구분자(|)를 포함한 회원정보 문자열
	 */
	public Member(String line)
	{
		String[] info = line.split("\\|");
		setNo(Integer.parseInt(info[0]));
		setName(info[1]);
		setPhone(info[2]);
		setEmail(info[3]);
	}

	@Override
	public boolean equals(Object obj) {
		Member other = (Member) obj;
		return this.getNo()==other.getNo();
	}
	
	@Override
	public String toString() {
		return String.format("%d\t%s\t%s\t%s", 
				getNo(),getName(),getPhone(), getEmail());
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}