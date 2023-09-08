import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CalendartDAO extends JFrame{
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:orcl";
	String user="sqlid";
	String pw="sqlpw";
	Connection conn;
	public CalendartDAO() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pw); 
			System.out.println("Driver 로드성공/접속 성공");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int JoinCal(String txtid, String txtpw, String txtname, String txtbirth) {
		String sql= "insert into join values(?,  ? , ?, ?)";
		PreparedStatement ps =null;
		int cnt =0;
		try {
			ps =conn.prepareStatement(sql);
			ps.setString(1, txtid);
			ps.setString(2, txtpw);
			ps.setString(3, txtname);
			ps.setString(4, txtbirth);
			cnt =ps.executeUpdate();
			JOptionPane.showMessageDialog(this, "회원가입을 축하합니다",
					"알림",JOptionPane.INFORMATION_MESSAGE);
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "회원가입에 실패했습니다","알림",JOptionPane.INFORMATION_MESSAGE);
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
			}
		}
				return cnt;
	}
	public int login(String id, String pw) {
		String sql= "select * from join  where  id = ? and pw = ? ";
		PreparedStatement ps =null;
		ResultSet rs=null;
		try {
			ps= conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			rs = ps.executeQuery();
			while(rs.next())
			{	
				NoahCal nc = new NoahCal("캘린더",id);
				return 0;
				
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "로그인에 실패했습니다","알림",JOptionPane.INFORMATION_MESSAGE);
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 1;
	}
	public void CreateT(String ID) {
		String sql="create table "+ID+"1234( "
				+ "num number primary key, title varchar2(100),"
				+ "memo varchar2(100),  year number ,month number , day number)";
		PreparedStatement ps =null;
		
		
		try {
			ps= conn.prepareStatement(sql);
			ps.execute(sql);
			
			
		} catch (SQLException e) {
			System.out.println("테이블 실패");
		}finally {
			try {
				ps.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public ArrayList<CalendarBean> getAllCal(String ID, int year, int month , int day) {
		System.out.println(ID);
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<CalendarBean> lists = new ArrayList<CalendarBean>();
		//3
		String sql = "select * from "+ID+"1234 where year= ? and month = ? and day= ?" ;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, year);
			ps.setInt(2, month);
			ps.setInt(3, day);
			rs = ps.executeQuery();
			while(rs.next()) {
			
				String Title = rs.getString("Title");
				String Memo = rs.getString("Memo");
				int num = rs.getInt("num");
				CalendarBean pb = new CalendarBean();
				pb.setTitle(Title);
				pb.setMemo(Memo);
				pb.setNum(num);
				lists.add(pb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				}
		}//finally
		return lists;
	}
	public void InsertMemo(String ID, String Title, String Memo,int year,int month , int day) {
		String sql = "insert into "+ID+"1234 values(memoseq.nextval,?,?,?,?,?)";
		PreparedStatement ps = null;  
		int cnt=-1;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, Title);
			ps.setString(2, Memo);
			ps.setInt(3, year);
			ps.setInt(4, month);
			ps.setInt(5, day);
			cnt=ps.executeUpdate();
			System.out.println(cnt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
	}
	public void UpdateMemo(String ID, String Title, String Memo,int year,int month , int day, int num) {
		System.out.println(ID);
		String sql = "update "+ID+"1234 set Title=?,Memo=? where num=?";
		
		PreparedStatement ps = null;  
		int cnt=-1;
		try {
			ps=conn.prepareStatement(sql);
	
			ps.setString(1, Title);
			ps.setString(2, Memo);
			ps.setInt(3, num);
	
			cnt=ps.executeUpdate();
			System.out.println(cnt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}


	}
	public void DeleteData(String ID, int num) {
		String sql = "delete from "+ID+"1234 where num=?";
		PreparedStatement ps = null;  
		int cnt=-1;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, num);
			cnt=ps.executeUpdate();
			System.out.println(cnt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	public int Numfind( String ID ,String Title, String Memo) {
		System.out.println(ID);
		PreparedStatement ps = null;
		ResultSet rs = null;
		int number=0;
		ArrayList<CalendarBean> lists = new ArrayList<CalendarBean>();
		//3
		String sql = "select num from "+ID+"1234 where Title= ? and Memo = ? " ;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Title);
			ps.setString(2, Memo);
			rs = ps.executeQuery();
			while(rs.next()) {
				number = rs.getInt("num");
			
			}
		
	}catch (SQLException e) {
		e.printStackTrace();
	} finally {
		if(ps != null)
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
	}//finally
	return number;
}
	public ArrayList<CalendarBean> DayMemo(String ID, int year, int month, int day) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<CalendarBean> lists = new ArrayList<CalendarBean>();
		//3
		String sql = "select * from "+ID+"1234 where year= ? and month = ? and day= ?" ;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, year);
			ps.setInt(2, month);
			ps.setInt(3, day);
			rs = ps.executeQuery();
			while(rs.next()) {
				String Title = rs.getString("Title");
				String Memo = rs.getString("Memo");
				int num = rs.getInt("num");
				CalendarBean pb = new CalendarBean();
				pb.setTitle(Title);
				pb.setMemo(Memo);
				pb.setNum(num);
				lists.add(pb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				}
		}//finally
		return lists;
		
	}
}