package myPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO {
	private static Connection conn;
	private static Statement st;
	private static ResultSet rs;
	private static PreparedStatement pst ;
	public static List<String> getSalePeriod(String start, String end){
		List<String> saleList = new ArrayList<String>();
		try {
			conn = ConnectionProvider.getConnection();
			st = conn.createStatement();
			pst = conn.prepareStatement (
					"SELECT name, sellerName, price, brand , SUM(SOS) AS allSell"+
					" FROM (SELECT name , sellerName , date , price, brand , SUM(S) as SOS "+
						  " FROM ("+
					            " (SELECT itemCode,seller, date, SUM(COUNT) AS S"+
						        " FROM SELL GROUP BY itemCode , seller,date) AS A JOIN ITEM on (ITEM.itemCode = A.itemCode and ITEM.sellerCode = A.seller) )"+ 
						  " WHERE date Between to_date(?,'YYYY-MM-DD') and to_date(?,'YYYY-MM-DD')"+
					      " GROUP BY  date , name, price, brand, sellerName) AS B"+
					" GROUP BY name, sellerName, price, brand"+ 
					" ORDER BY allSell desc, price desc" );
			pst.setString(1, start);
			pst.setString(2, end);
			rs = pst.executeQuery();	
			int count =0;
			while(rs.next()){
				if(count < 3){
					String name = rs.getString("name");
					String sellerName = rs.getString("sellerName");
					String price = rs.getString("price");
					String brand = rs.getString("brand");
					String allSell = rs.getString("allSell");
					saleList.add(name);
					saleList.add(sellerName);
					saleList.add(price);
					saleList.add(brand);
					saleList.add(allSell);
				}
				else{
					if(saleList.get(5*(count-1)+4).compareTo(rs.getString("allSell")) == 0){
						String name = rs.getString("name");
						String sellerName = rs.getString("sellerName");
						String price = rs.getString("price");
						String brand = rs.getString("brand");
						String allSell = rs.getString("allSell");
						saleList.add(name);
						saleList.add(sellerName);
						saleList.add(price);
						saleList.add(brand);
						saleList.add(allSell);
					}
					else{
						break;
					}
				}
				count++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return saleList;
	}
	public static List<String> getSaleSeller(String seller){
		List<String> saleList = new ArrayList<String>();
		try {
			conn = ConnectionProvider.getConnection();
			st = conn.createStatement();
			pst = conn.prepareStatement (
					" SELECT itemCode, name , brand "+
					" FROM (( SELECT name "+
					       " FROM ( SELECT name , SUM(allPrice) "+
					            " FROM Sell join Item on ( Sell.itemCode = Item.itemCode and Sell.seller = Item.sellercode) "+ 
					            " GROUP BY name Order by sum desc LIMIT 10 "+
					           " ) AS M "+
					       " ) EXCEPT "+
					       " ( SELECT name FROM ITEM "+
						     " WHERE sellerName = (?) "+
					     " )) AS B NATURAL JOIN ITEM "+
					" GROUP BY itemCode, name ,brand "+
					" ORDER BY itemCode asc"
					  );
			pst.setString(1, seller);
			rs = pst.executeQuery();
			while(rs.next()){
				String itemCode = rs.getString("itemCode");
				String name = rs.getString("name");
				String brand = rs.getString("brand");
				saleList.add(itemCode);
				saleList.add(name);
				saleList.add(brand);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return saleList;
	}
	public static List<String> getSaleHurry(){
		List<String> saleList = new ArrayList<String>();
		try {
			conn = ConnectionProvider.getConnection();
			st = conn.createStatement();
			 pst = conn.prepareStatement (
					 " SELECT name, itemCode, sellerCode, sellerName, sumCart, numberOfStock, price "+
					 " FROM (SELECT name , itemCode, sellerCode, sellerName, Sum(count) AS sumCart, numberOfStock , price "+
					 	  " FROM (ITEM NATURAL JOIN CART)"+
					 	  " GROUP BY name, itemCode, sellerCode, sellerName ,numberOfStock , price ) AS A"+
					 " WHERE sumCart > numberOfStock"
					);
			rs = pst.executeQuery();
			while(rs.next()){
				String itemCode = rs.getString("itemCode");
				String sellerName = rs.getString("sellerName");
				String sumCart = rs.getString("sumCart");
				String numberOfStock = rs.getString("numberOfStock");
				String price = rs.getString("price");
				saleList.add(itemCode);
				saleList.add(sellerName);
				saleList.add(sumCart);
				saleList.add(numberOfStock);
				saleList.add(price);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return saleList;
	}
	public static List<String> getBestSelling(){
		List<String> saleList = new ArrayList<String>();
		try {
			conn = ConnectionProvider.getConnection();
			st = conn.createStatement();
			pst = conn.prepareStatement (
					" SELECT name , brand "+
					" FROM ( (SELECT itemCode "+ 
					      " FROM ( SELECT S.itemCode,  sum(count) as sumOfSelling "+
					   			  " FROM ( ( SELECT itemCode , seller,  buyer , count, allPrice FROM SELL "+
					  					  " ) AS A JOIN USERLIST on (A.buyer = USERLIST.email) "+ 
								        " ) AS S "+
								  " WHERE S.birth between '19780101' and '19871231' "+ 
								  " GROUP BY itemCode"+
								  " ORDER BY sumOfSelling desc"+ 
						  " ) AS B LIMIT 10 ) "+
						  " INTERSECT "+
						  " (SELECT itemCode "+
						  "  FROM ( SELECT S.itemCode,  sum(count) as sumOfSelling "+
					   			  " FROM ( ( SELECT itemCode , seller,  buyer , count, allPrice FROM SELL "+
					  					  " ) AS A JOIN USERLIST on (A.buyer = USERLIST.email) "+ 
								  " ) AS S "+
								  " WHERE S.birth between '19880101' and '19971231' "+ 
								  " GROUP BY itemCode"+
								  " ORDER BY sumOfSelling desc"+ 
						  " ) AS B LIMIT 10	)"+
					"  ) AS C join Item on Item.itemCode = C.itemCode "+
					"  GROUP BY name ,brand"
										);
			rs = pst.executeQuery();
			while(rs.next()){
				String name = rs.getString("name");
				String brand = rs.getString("brand");
				saleList.add(name);
				saleList.add(brand);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return saleList;
	}
	public static List<String> getSellerInfo(String sellerName){
		List<String> saleList = new ArrayList<String>();
		try {
			conn = ConnectionProvider.getConnection();
			st = conn.createStatement();
			 pst = conn.prepareStatement (
					" SELECT name, buyer, sellerName, couponUse , allPrice, price , count , date "+
					" FROM (Sell join Item on Sell.itemCode = Item.itemCode and Sell.seller = Item.sellercode)"+
					" WHERE sellerName = (?) Order by date asc"
					);
			pst.setString(1,sellerName);
			rs = pst.executeQuery();
			while(rs.next()){
				String itemCode = rs.getString("name");
				String buyer = rs.getString("buyer");
				String sellerVName = rs.getString("sellerName");
				String price = rs.getString("price");
				String count = rs.getString("count");
				String allPrice = rs.getString("allPrice");
				String date = rs.getString("date");
				saleList.add(itemCode);
				saleList.add(buyer);
				saleList.add(sellerVName);
				saleList.add(price);
				saleList.add(count);
				saleList.add(allPrice);
				saleList.add(date);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return saleList;
	}
	public static List<String> getSellerByCount(String sellerName){
		List<String> saleList = new ArrayList<String>();
		try {
			conn = ConnectionProvider.getConnection();
			st = conn.createStatement();
			 pst = conn.prepareStatement (
					" SELECT sellerName, name , SUM(count) AS sumOfCount , SUM(allprice) AS sumOfPrice"+
					" FROM (Sell join Item on Sell.itemCode = Item.itemCode and Sell.seller = Item.sellercode)"+
					" WHERE sellerName=(?) "+
					" GROUP BY sellerName , name" 
					 );
			pst.setString(1,sellerName);
			rs = pst.executeQuery();
			while(rs.next()){
				String sellerVName = rs.getString("sellerName");
				String name = rs.getString("name");
				String sumOfCount = rs.getString("sumOfCount");
				String sumOfPrice = rs.getString("sumOfPrice");
				saleList.add(sellerVName);
				saleList.add(name);
				saleList.add(sumOfCount);
				saleList.add(sumOfPrice);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return saleList;
	}
}
