package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpDAO2 
{
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Connection getConn() 
	{
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(
	                "jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			return conn;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public PageItem getPage(int page, int ipp) {
		String sql = "SELECT * FROM "
			+ "( "
			+ "    SELECT t2.*, TRUNC((RN-1)/?+1) AS page FROM "
			+ "    ( "
			+ "        SELECT t.*, ROWNUM RN FROM "
			+ "        ( "
			+ "            SELECT empno,  "
			+ "            LPAD('　', (LEVEL-1)*3, '　')||ename ename,  "
			+ "            sal, deptno, job, hiredate, total "
			+ "            FROM emp2, "
			+ "            ( "
			+ "                SELECT CEIL(COUNT(*)/?) total FROM emp2 "
			+ "            )  "
			+ "            START WITH mgr IS NULL "
			+ "            CONNECT BY PRIOR empno=mgr "
			+ "        )t "
			+ "    )t2 "
			+ ") "
			+ "WHERE page=?";
		conn = getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ipp);
			pstmt.setInt(2, ipp);
			pstmt.setInt(3, page);
			rs = pstmt.executeQuery();
			List<EmpVO> list = new ArrayList<>();
			PageItem pageItems = new PageItem();
			int i = 0;
			while(rs.next()) {
				if(i==0) {
					int currPage = rs.getInt("PAGE");
					int totalPages = rs.getInt("TOTAL");
					pageItems.setCurrPage(currPage);
					pageItems.setTotalPages(totalPages);
				}
				int empno = rs.getInt("EMPNO");
				String ename = rs.getString("ENAME");
				java.sql.Date hiredate = rs.getDate("HIREDATE");
				int deptno = rs.getInt("DEPTNO");
				int salary = rs.getInt("SAL");
				String job = rs.getString("JOB");
				
				EmpVO emp = new EmpVO();
				emp.setEmpno(empno);
				emp.setEname(ename);
				emp.setDeptno(deptno);
				emp.setHiredate(hiredate);
				emp.setSal(salary);
				emp.setJob(job);
				
				list.add(emp);
				i++;
			}
			pageItems.setList(list);
			return pageItems;
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return null;
	}
	
	
	public List<EmpVO> getList() //empno,ename,sal,deptno,job,hiredate,mgr,comm
	{
		conn = getConn();
		try {
			pstmt = conn.prepareStatement("SELECT * FROM emp2");
			rs = pstmt.executeQuery();
			List<EmpVO> list = new ArrayList<>();
			while(rs.next())
			{
				int empno = rs.getInt("EMPNO");
				String ename = rs.getString("ENAME");
				java.sql.Date hiredate = rs.getDate("HIREDATE");
				int salary = rs.getInt("SAL");
				
				EmpVO emp = new EmpVO();
				emp.setEmpno(empno);
				emp.setEname(ename);
				emp.setHiredate(hiredate);
				emp.setSal(salary);
				list.add(emp);
			}
			return list;
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			closeAll();
		}
		return null;
	}
	
	public boolean updateSal(EmpVO emp) {
		conn = getConn();
		try {
			String sql = "UPDATE emp2 SET sal=? WHERE empno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp.getSal());
			pstmt.setInt(2, emp.getEmpno());
			int rows = pstmt.executeUpdate();
			return rows>0;
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return false;
	}

	public boolean add(EmpVO emp) 
	{
		conn = getConn();
		try {
			String sql = "INSERT INTO emp2(empno, ename,deptno,sal,hiredate,job) " +
										" VALUES(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpno());
			pstmt.setString(2, emp.getEname());
			pstmt.setInt(3, emp.getDeptno());
			pstmt.setInt(4, emp.getSal());
			pstmt.setDate(5, emp.getHiredate());
			pstmt.setString(6, emp.getJob());
			
			int rows = pstmt.executeUpdate();
			return rows>0;
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return false;
	}
	
	public List<EmpVO> findByJob(String job)
	{
		conn = getConn();
		try {
			String sql = "SELECT * FROM emp2 WHERE job=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, job);
			
			rs = pstmt.executeQuery();
			List<EmpVO> list = new ArrayList<>();
			while(rs.next())
			{
				int empno = rs.getInt("EMPNO");
				String ename = rs.getString("ENAME");
				java.sql.Date hiredate = rs.getDate("HIREDATE");
				int salary = rs.getInt("SAL");
				String jb = rs.getString("JOB");
				
				EmpVO emp = new EmpVO();
				emp.setEmpno(empno);
				emp.setEname(ename);
				emp.setHiredate(hiredate);
				emp.setSal(salary);
				emp.setJob(jb);
				list.add(emp);
			}
			return list;
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return null;
	}
	
	//삭제, 사번으로 삭제
	public boolean delete(int empno) {
		conn = getConn();
		try {
			String sql = "DELETE FROM emp2 WHERE empno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			int rows = pstmt.executeUpdate();
			return rows>0;
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return false;
	}
	

	public EmpVO findByEmpno(int eno) {
		conn = getConn();
		try {
			String sql = "SELECT * FROM emp2 WHERE empno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, eno);
			
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				int empno = rs.getInt("EMPNO");
				String ename = rs.getString("ENAME");
				java.sql.Date hiredate = rs.getDate("HIREDATE");
				int deptno = rs.getInt("DEPTNO");
				int salary = rs.getInt("SAL");
				String jb = rs.getString("JOB");
				
				EmpVO emp = new EmpVO();
				emp.setEmpno(empno);
				emp.setEname(ename);
				emp.setHiredate(hiredate);
				emp.setDeptno(deptno);
				emp.setSal(salary);
				emp.setJob(jb);
				return emp;
			}
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return null;
	}
	
	public List<Map<String,String>> getJoinResult(int deptno){
		String sql = "SELECT empno, ename, e.deptno, dname, grade \"호봉\"\r\n"
					+ "FROM emp e JOIN dept d ON e.deptno=d.deptno\r\n"
					+ "JOIN salgrade s ON e.sal BETWEEN s.losal AND s.hisal\r\n"
					+ "WHERE e.deptno=? ORDER BY 호봉 DESC";
		conn = getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deptno);
			rs = pstmt.executeQuery();
			List<Map<String,String>> list = new ArrayList<>();
			Map<String, String> map = null;
			while(rs.next()) {
				map = new HashMap<>();
				int empno = rs.getInt("EMPNO");
				String ename = rs.getString("ENAME");
				int dno = rs.getInt("DEPTNO");
				String dname = rs.getString("DNAME");
				int grade = rs.getInt("호봉");
				
				map.put("EMPNO", ""+empno);
				map.put("ENAME", ename);
				map.put("DEPTNO", ""+dno);
				map.put("DNAME", dname);
				map.put("호봉", ""+grade);
				list.add(map);
			}
			return list;
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return null;
	}
	
	private void closeAll() {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
