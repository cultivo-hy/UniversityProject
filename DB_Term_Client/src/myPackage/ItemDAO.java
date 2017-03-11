package myPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
	private static Connection conn;
	private static Statement st;
	private static ResultSet rs;
	private static PreparedStatement pst,pst2;
	
	public static int insertItem(ItemBean item) {
		int status = 0;
		try {
		    conn = ConnectionProvider.getConnection();	
			st = conn.createStatement();
			pst2 = conn.prepareStatement ("SELECT * FROM ITEM WHERE itemCode = (?) and SellerCode = (?);");
			pst2.setString(1,item.getItemCode());
			pst2.setString(2, item.getSellerCode());
			rs = pst2.executeQuery();
			pst = conn.prepareStatement ("INSERT INTO ITEM VALUES(?, ?, ?, ?, ? , ?, ?, ?);");
			if(!rs.next()){
				pst.setString(1, item.getItemCode());
				pst.setString(2, item.getName());
				pst.setInt(3, item.getPrice());
				pst.setString(4, item.getBrand());
				pst.setString(5, item.getSellerCode());
				pst.setString(6,  item.getSellerName());
				pst.setInt(7, item.getNumberOfStock());
				pst.setInt(8,item.getNumberOfSale());
				status = pst.executeUpdate();
			}
			pst.close();
			pst2.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static ItemBean selectItem(String itemCode, String sellerCode) {
	    ItemBean item = null;
	    try {
            conn = ConnectionProvider.getConnection();
            st = conn.createStatement();
            
			pst = conn.prepareStatement ("SELECT * FROM ITEM WHERE itemCode = (?) and sellerCode = (?) Order by itemCode;");
			pst.setString(1,itemCode);
			pst.setString(2,sellerCode);
			rs = pst.executeQuery();
            while(rs.next()) {
            	item = new ItemBean();       
                item.setItemCode(rs.getString("itemCode"));
                item.setName(rs.getString("name"));
                item.setPrice(Integer.parseInt(rs.getString("price")));
                item.setBrand((rs.getString("brand")));
                item.setSellerCode(rs.getString("sellerCode"));
                item.setSellerName(rs.getString("sellerName"));
                item.setNumberOfStock(Integer.parseInt(rs.getString("numberOfStock")));
                item.setNumberOfSale(Integer.parseInt(rs.getString("numberOfSale")));
            }
            st.close();
            conn.close();
        } catch(Exception e) {
                e.printStackTrace();
        }
	    return item;
	}
	public static List<ItemBean> searchSelect(String search){
        List<ItemBean> itemList = new ArrayList<ItemBean>();
	    ItemBean item = null;
		try {
            conn = ConnectionProvider.getConnection();
            pst = conn.prepareStatement("SELECT * FROM Item WHERE name LIKE  ('%' || ? || '%') Order by itemCode;");
            pst.setString(1, search);
            rs = pst.executeQuery();
            while(rs.next()) {
	            item = new ItemBean();       
                item.setItemCode(rs.getString("itemCode"));
                item.setName(rs.getString("name"));
                item.setPrice(Integer.parseInt(rs.getString("price")));
                item.setBrand(rs.getString("brand"));
                item.setSellerCode(rs.getString("sellerCode"));
                item.setSellerName(rs.getString("sellerName"));
                item.setNumberOfStock(Integer.parseInt(rs.getString("numberOfStock")));
                item.setNumberOfSale(Integer.parseInt(rs.getString("numberOfSale")));
                itemList.add(item);
            }
            conn.close();
        } catch(Exception e) {
                e.printStackTrace();
        }
        return itemList;
	}
	public static List<ItemBean> selectAllItem() {
        List<ItemBean> itemList = new ArrayList<ItemBean>();
	    ItemBean item = null;
        try {
            Connection conn = ConnectionProvider.getConnection();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Item Order by itemCode");
            while(rs.next()) {
	            item = new ItemBean();       
                item.setItemCode(rs.getString("itemCode"));
                item.setName(rs.getString("name"));
                item.setPrice(Integer.parseInt(rs.getString("price")));
                item.setBrand(rs.getString("brand"));
                item.setSellerCode(rs.getString("sellerCode"));
                item.setSellerName(rs.getString("sellerName"));
                item.setNumberOfStock(Integer.parseInt(rs.getString("numberOfStock")));
                item.setNumberOfSale(Integer.parseInt(rs.getString("numberOfSale")));
                itemList.add(item);
            }
            st.close();
            conn.close();
        } catch(Exception e) {
                e.printStackTrace();
        }
        return itemList;
    }
	
	public static void updateItem(String[] itemList, String[] countList, String[] sellerList ,String email,String[] buyChecklist){
		for(int i=0; i<buyChecklist.length; i++){
			if(buyChecklist[i] != null){
				int index = Integer.parseInt(buyChecklist[i]);
				try{
					conn = ConnectionProvider.getConnection();
					pst = conn.prepareStatement ("UPDATE Item SET numberOfStock = numberOfStock - (?), numberOfSale = numberOfSale + (?)  WHERE itemCode = (?) and sellerCode = (?);");
					int cnt = Integer.parseInt(countList[index]);
					pst.setInt(1, cnt);
					pst.setInt(2, cnt);
					pst.setString(3, itemList[index]);
					pst.setString(4, sellerList[index]);
					pst.execute();
					pst.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
	}
	public static void updateItemCheck(String[] itemList, String[] countList, String[] sellerList ,String email,String[] buyChecklist){
		for(int i=0; i<buyChecklist.length; i++){
			if(buyChecklist[i] != null){
				int index = Integer.parseInt(buyChecklist[i])/2;
				try{
					conn = ConnectionProvider.getConnection();
					pst = conn.prepareStatement ("UPDATE Item SET numberOfStock = numberOfStock - (?), numberOfSale = numberOfSale + (?)  WHERE itemCode = (?) and sellerCode = (?);");
					int cnt = Integer.parseInt(countList[index]);
					pst.setInt(1, cnt);
					pst.setInt(2, cnt);
					pst.setString(3, itemList[index]);
					pst.setString(4, sellerList[index]);
					pst.execute();
					pst.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
	}
}
