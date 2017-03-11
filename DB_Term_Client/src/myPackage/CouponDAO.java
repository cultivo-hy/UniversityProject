package myPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CouponDAO {
	private static Connection conn;
	private static Statement st;
	private static ResultSet rs;
	public static void insertCoupon(CouponBean coupon) {
		try {
		    conn = ConnectionProvider.getConnection();	
			st = conn.createStatement();
			PreparedStatement pst2 = conn.prepareStatement (
					"SELECT * FROM COUPON WHERE owner = (?) and type = (?) and number = (?) and count = (?);");
			pst2.setString(1,coupon.getOwner());
			pst2.setString(2,coupon.getType());
			pst2.setString(3,coupon.getCouponNumber());
			pst2.setInt(4,coupon.getCouponCount());
			rs = pst2.executeQuery();
			PreparedStatement pst; 
			if(!rs.next()){
				pst = conn.prepareStatement ("INSERT INTO COUPON VALUES(?, ?, ?, ?, ?)");
				pst.setString(1, coupon.getOwner());
				pst.setString(2, coupon.getType());
				pst.setInt(3, coupon.getCouponCount());
				pst.setString(4, coupon.getCouponNumber());
				pst.setString(5, coupon.getCouponName());
			}
			else{
				pst = conn.prepareStatement("UPDATE Coupon SET count = (?) WHERE owner = (?) and type = (?) and number = (?)");
				int count = rs.getInt("count") + coupon.getCouponCount();
				pst.setInt(1,count);
				pst.setString(2, coupon.getOwner());
				pst.setString(3, coupon.getType());
				pst.setString(4, coupon.getCouponNumber());
			}
			pst.executeUpdate();
			pst.close();
			pst2.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static List<String> getCoupon(String email){
		List<String> couponList = new ArrayList<String>();
		String coupon = "";
        try {
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM coupon where owner = (?);");
            pst.setString(1,email);
            rs = pst.executeQuery();
            while(rs.next()) {
            	coupon = rs.getString("type");
            	if(coupon != null){
            		couponList.add(coupon);
            	}
            	else{
            		couponList.add("NoCoupon");
            	}
            }
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
		return couponList;
	}
	public static void deleteCoupon(String couponType , String email){
		try {
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement pst = conn.prepareStatement("DELETE FROM coupon where owner = (?) and type = (?);");
            pst.setString(1,email);
            pst.setString(2,couponType);
            pst.execute();
            pst.close();
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
	}
}
