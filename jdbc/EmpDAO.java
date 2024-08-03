package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO 
{
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	private Connection getConn() 
	{
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(
	                "jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			return conn;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<EmpVO> getList() //empno,ename,sal,deptno,job,hiredate,mgr,comm
	{
		conn = getConn();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM emp2");
			List<EmpVO> list = new ArrayList<>();
			while(rs.next()) {
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
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}
	
	public boolean updateSal(EmpVO emp) {
		conn = getConn();
		try {
			stmt = conn.createStatement();
			String sql = "UPDATE emp2 SET sal="+emp.getSal()+" WHERE empno=" + emp.getEmpno();
			int rows = stmt.executeUpdate(sql);
			return rows>0;
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return false;
	}
	
	// 사원정보 추가(add()), 사번으로 검색(findByEmpno()), 
	// 부서번호로 검색(findByDeptno()), 직무명칭으로 검색(findByJob()),
	// 사번으로 삭제(deleteByEmpno())
	public boolean add(EmpVO emp) 
	{
		conn = getConn();
		try {
			stmt = conn.createStatement();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sDate = sdf.format(emp.getHiredate());
			String sql = "INSERT INTO emp2(empno, ename,deptno,sal,hiredate,job) " +
						 "VALUES(" + emp.getEmpno() +", '" + emp.getEname()+ "'," +
						     emp.getDeptno() + "," + emp.getSal() + ", '" + sDate + "', '" +
						     emp.getJob() + "')";
			int rows = stmt.executeUpdate(sql);
			return rows>0;
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return false;
	}
	
	public List<EmpVO> findByJob(String job)
	{
		conn = getConn();
		try {
			String sql = "SELECT * FROM emp2 WHERE job='" + job + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			List<EmpVO> list = new ArrayList<>();
			while(rs.next()) {
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
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return null;
	}
	
	// 삭제, 사번으로 삭제
	public boolean delete(int empno) {
		conn = getConn();
		try {
			stmt = conn.createStatement();
			String sql = "DELETE FROM emp2 WHERE empno=" + empno;
			int rows = stmt.executeUpdate(sql);
			return rows>0;
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return false;
	}
	
	private void closeAll() {
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
