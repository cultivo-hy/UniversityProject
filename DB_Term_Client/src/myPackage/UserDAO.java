package myPackage;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserDAO {
	private static Connection conn;
	private static Statement st;
	private static ResultSet rs, rs2;
	private static int number=1;
	
	public static void insertUser(UserBean user) {
		try {
		    conn = ConnectionProvider.getConnection();	
			st = conn.createStatement();
			PreparedStatement pst2 = conn.prepareStatement (
					"SELECT * FROM USERLIST WHERE email = (?);");
			pst2.setString(1,user.getEmail());
			rs = pst2.executeQuery();
			PreparedStatement pst = conn.prepareStatement (
					"INSERT INTO UserList VALUES(?, ?, ?, ?, ?);");
			if(!rs.next()){
				String birth = user.getBirth();
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date parsed = transFormat.parse(birth);
				java.sql.Date date = new java.sql.Date(parsed.getTime());
				pst.setString(1, user.getEmail());
				pst.setString(2, user.getPw());
				pst.setString(3, user.getLastName());
				pst.setString(4, user.getFamilyName());
				pst.setDate(5, date);
				pst.executeUpdate();
			}
			pst.close();
			pst2.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static UserBean selectUser(String email) {
	    UserBean user = null;
	    try {
            conn = ConnectionProvider.getConnection();
            st = conn.createStatement(); 
			PreparedStatement pst2 = conn.prepareStatement (
					"SELECT * FROM USERLIST WHERE email = (?);");
			pst2.setString(1,email);
			rs = pst2.executeQuery();
            while(rs.next()) {
                user = new UserBean();       
                user.setEmail((rs.getString("email")).replaceAll(" ", ""));
                user.setPw((rs.getString("passwd")).replaceAll(" ", ""));
                user.setLastName((rs.getString("lastName")).replaceAll(" ", ""));
                user.setFamilyName((rs.getString("familyName")).replaceAll(" ", ""));
    		    user.setBirth((rs.getString("birth")).replaceAll(" ", ""));
            }
            
            st.close();
            conn.close();
            
        } catch(Exception e) {
                e.printStackTrace();
        }
	    return user;
	}
	
	public static List<UserBean> selectAllStudent() {
        List<UserBean> userList = new ArrayList<UserBean>();
	    UserBean user = null;
        try {
            Connection conn = ConnectionProvider.getConnection();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM UserList");
            while(rs.next()) {
                user = new UserBean();       
                user.setEmail(rs.getString("email"));
                user.setPw(rs.getString("passwd"));
                user.setLastName(rs.getString("lastName"));
                user.setFamilyName(rs.getString("familyName"));
    		    user.setBirth(rs.getString("birth"));
                userList.add(user);
            }
            
            st.close();
            conn.close();
        } catch(Exception e) {
                e.printStackTrace();
        }
        return userList;
    }
}
