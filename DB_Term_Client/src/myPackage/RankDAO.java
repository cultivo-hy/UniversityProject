package myPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RankDAO {
	private static Connection conn;
	private static Statement st;
	private static ResultSet rs;
	private static PreparedStatement pst;
	public static void insert(String email){
		try {
			conn = ConnectionProvider.getConnection();
			st = conn.createStatement();
			pst = conn.prepareStatement ("INSERT INTO Rank VALUES(?,?);");
			pst.setString(1, email);
			pst.setString(2, "REGULAR");
			pst.execute();
			pst.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public static void insert(String email,String rank){
		try {
			conn = ConnectionProvider.getConnection();
			st = conn.createStatement();
			pst = conn.prepareStatement ("INSERT INTO Rank VALUES(?,?);");
			pst.setString(1, email);
			pst.setString(2, rank);
			pst.execute();
			pst.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public static String getRank(String email){
		String rank="";
		try {
			conn = ConnectionProvider.getConnection();
			pst = conn.prepareStatement ("SELECT type FROM Rank WHERE userEmail = (?);");
			pst.setString(1, email);
			rs = pst.executeQuery();
			rs.next();
			rank = rs.getString("type");
			rs.close();
			pst.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rank;
	}
	public static String checkUpdate(String email){
		String flag = "No";
		try {
			conn = ConnectionProvider.getConnection();
			pst = conn.prepareStatement ("SELECT SUM(allPrice) AS sum FROM RANK join SELL on userEmail = buyer "
					+ "where userEmail = (?);");
			pst.setString(1, email);
			rs = pst.executeQuery();
			rs.next();
			int sum = Integer.parseInt(rs.getString("sum"));
			
			pst = conn.prepareStatement ("SELECT type FROM RANK where userEmail = (?);");
			pst.setString(1, email);
			rs = pst.executeQuery();
			rs.next();
			String rank = rs.getString("type");
			
			if(rank.compareTo("REGULAR")==0){
				if(sum >= 200000){
					pst = conn.prepareStatement ("UPDATE RANK SET Type = (?) WHERE userEmail = (?)");
					if(sum >= 500000){
						pst.setString(1, "VVIP");
						flag = "VVIP,VIP";
					}
					else{
						pst.setString(1, "VIP");
						flag = "VIP";
					}
					pst.setString(2, email);
					pst.execute();
					
				}
			}
			else if(rank.compareTo("VIP")==0){
				pst = conn.prepareStatement ("UPDATE RANK SET Type = (?) WHERE userEmail = (?)");
				if(sum >= 500000){
					pst.setString(1, "VVIP");
				}
				flag = "VVIP";
				pst.setString(2, email);
				pst.execute();
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}
