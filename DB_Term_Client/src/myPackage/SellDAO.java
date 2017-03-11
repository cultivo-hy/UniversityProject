package myPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SellDAO {
	private static Connection conn;
	private static Statement st;
	private static ResultSet rs;
	private static PreparedStatement pst ;
	
	public static String insertSellWithCoupon(List<String[]> infoList)
	{	
		String text="";
		try {
			conn = ConnectionProvider.getConnection();
			String[] emailList = infoList.get(0);
			String email = emailList[0]; 
			String[] itemCodeList = infoList.get(1);
			String[] sellerCodeList = infoList.get(2);
			String[] allPriceList = infoList.get(3);
			String[] countlist = infoList.get(4);
			String[] percentTen = infoList.get(5);
			if(percentTen == null){
				percentTen = new String[1];
			}
			String[] percentThirty = infoList.get(6);
			if(percentThirty == null){
				percentThirty = new String[1];
			}
			int size=(percentTen.length + percentThirty.length);
			String[] percentCheck = new String[size];
			System.arraycopy(percentTen, 0, percentCheck,0, percentTen.length);
			System.arraycopy(percentThirty, 0, percentCheck, percentTen.length , percentThirty.length);
			int index=0,tmpIndex=0;

			for(int i=0; i<size; i++){
				if(percentCheck[i] != null){
					tmpIndex = Integer.parseInt(percentCheck[i]);
					index = (tmpIndex/2);
					int sum = Integer.parseInt(allPriceList[index]);
					int couponPrice = 0;
					if(tmpIndex%2 == 0) {
						text = "10";
						couponPrice = (sum * (90)) / 100;
					}
					else {
						text = "30";
						couponPrice = (sum * (70)) / 100;
					}
					pst= conn.prepareStatement("INSERT INTO Sell VALUES(?, ?, ?, ?, ?, ? ,?);");
					pst.setString(1, itemCodeList[index]);
					pst.setString(2, email);
					pst.setString(3, sellerCodeList[index]);
					pst.setString(4, text);
					pst.setInt(5, couponPrice);
					pst.setInt(6, Integer.parseInt(countlist[index]));
					pst.setTimestamp(7,new Timestamp(System.currentTimeMillis()));
					pst.execute();
				}	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return text;
	}
	public static void insertSell(List<String[]> infoList) throws Exception{
		conn = ConnectionProvider.getConnection();	
		String[] emailList = infoList.get(0);
		String email = emailList[0]; 
		String[] itemCodeList = infoList.get(1);
		String[] sellerCodeList = infoList.get(2);
		String[] allPriceList = infoList.get(3);
		String[] countlist = infoList.get(4);
		String[] buyChecklist = infoList.get(5);
		for(int i=0; i<buyChecklist.length; i++){
			int index = Integer.parseInt(buyChecklist[i]);
			pst= conn.prepareStatement("INSERT INTO Sell VALUES(?, ?, ?, ?, ?, ? ,?);");
			pst.setString(1, itemCodeList[index]);
			pst.setString(2, email);
			pst.setString(3, sellerCodeList[index]);
			pst.setString(4, "0");
			pst.setInt(5, Integer.parseInt(allPriceList[index]));
			pst.setInt(6, Integer.parseInt(countlist[index]));
			pst.setTimestamp(7,new Timestamp(System.currentTimeMillis()));
			pst.execute();
			// 재고 뺴주고, Rank 체크해보고, 
		}
	}
	public static List<SellBean> selectAllSell() {
        List<SellBean> sellList = new ArrayList<SellBean>();
        SellBean sell = null;
        try {
            Connection conn = ConnectionProvider.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM Sell Order by Date asc");
            while(rs.next()) {
            	sell = new SellBean();       
            	sell.setItemCode(rs.getString("itemCode"));
            	sell.setBuyer(rs.getString("buyer"));
            	sell.setSeller(rs.getString("seller"));
            	sell.setCouponUse(rs.getString("couponUse")); //  바꾸기
            	sell.setAllPrice(Integer.parseInt(rs.getString("allPrice")));
            	sell.setCount(Integer.parseInt(rs.getString("count")));
            	sell.setDate(rs.getString("date"));
            	sellList.add(sell);
            }
            st.close();
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return sellList;
    }
	public static void insertSellToDB(SellBean sell){
		try {
			conn = ConnectionProvider.getConnection();
			 st = conn.createStatement();
				pst = conn.prepareStatement("INSERT INTO Sell VALUES(?, ?, ?, ?, ?, ? , ?);");
				pst.setString(1, sell.getItemCode());
				pst.setString(2, sell.getBuyer());
				pst.setString(3, sell.getSeller());
				pst.setString(4, sell.getCouponUse());
				pst.setInt(5, sell.getAllPrice());
				pst.setInt(6, sell.getCount());
				Timestamp time = Timestamp.valueOf(sell.getDate());
				pst.setTimestamp(7,new Timestamp(time.getTime()));
				pst.execute();
				pst.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	}
	
	public static List<SellBean> selectUserSell(String email) {
        List<SellBean> sellList = new ArrayList<SellBean>();
        SellBean sell = null;
        try {
            Connection conn = ConnectionProvider.getConnection();
            st = conn.createStatement();
            PreparedStatement pst = conn.prepareStatement
            		("SELECT name, buyer, sellerName, couponUse , allPrice , count , date"
            				+ " FROM (Sell join Item on Sell.itemCode = Item.itemCode and Sell.seller = Item.sellercode) "
            				+ " where buyer = (?) Order by date asc;");
            pst.setString(1,email);
            rs = pst.executeQuery();
            while(rs.next()) {
            	sell = new SellBean();       
            	sell.setItemCode(rs.getString("name"));
            	sell.setBuyer(rs.getString("buyer"));
            	sell.setSeller(rs.getString("sellerName"));
            	sell.setCouponUse(rs.getString("couponUse"));
            	sell.setAllPrice(Integer.parseInt(rs.getString("allPrice")));
            	sell.setCount(Integer.parseInt(rs.getString("count")));
            	sell.setDate(rs.getString("date"));
            	sellList.add(sell);
            }
            st.close();
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return sellList;
    }
}
