package myPackage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
	private static Connection conn;
	private static Statement st;
	private static ResultSet rs;
	private static PreparedStatement pst, pst2;
	
	public static void insertCart(List<String[]> infoList) throws Exception{
		String[] emailList = infoList.get(0);
		String email = emailList[0]; 
		String[] itemCodeList = infoList.get(1);
		String[] productNameList = infoList.get(2);
		String[] priceList = infoList.get(3);
		String[] sellerCodeList = infoList.get(4);
		String[] chooseList = infoList.get(5);
		String[] stockList = infoList.get(6);
		conn = ConnectionProvider.getConnection();	
		ResultSet rs;
		List<Integer> checkIndex = new ArrayList<Integer>();
		for(int i=0; i<chooseList.length; i++){
			if(chooseList[i]!="" && chooseList[i]!="0" ){
				checkIndex.add(i);
			}
		}
		for(int i=0; i<checkIndex.size(); i++){
			if(Integer.parseInt(stockList[checkIndex.get(i)]) <= 0) continue;
			pst2 = conn.prepareStatement("SELECT * FROM Cart WHERE itemCode = (?) and sellerCode = (?) and userCode = (?);");
			pst2.setString(1,itemCodeList[checkIndex.get(i)]);
			pst2.setString(2,sellerCodeList[checkIndex.get(i)]);
			pst2.setString(3, email);
			rs = pst2.executeQuery();
			int index = checkIndex.get(i);
			if(rs.next()){
				pst = conn.prepareStatement("UPDATE Cart SET count = (?) WHERE itemCode = (?) and sellerCode = (?) and userCode = (?);");
				pst.setInt(1,Integer.parseInt(chooseList[index]));
				pst.setString(2,itemCodeList[checkIndex.get(i)]);
				pst.setString(3,sellerCodeList[checkIndex.get(i)]);
				pst.setString(4, email);
			}
			else{
				pst = conn.prepareStatement("INSERT INTO Cart VALUES(?, ?, ?, ?, ?, ?);");
				pst.setString(1, itemCodeList[index]);
				pst.setString(2, email);
				pst.setString(3, sellerCodeList[index]);
				pst.setString(4,productNameList[index]);
				pst.setInt(5,Integer.parseInt(priceList[index]));
				pst.setInt(6,Integer.parseInt(chooseList[index]));
			}
			pst.execute();
		}
		pst.close();
		pst2.close();
		conn.close();
	}
	public static void insertItemToCart(CartBean cart){
		try {
			conn = ConnectionProvider.getConnection();
	        st = conn.createStatement();
			pst = conn.prepareStatement("INSERT INTO Cart VALUES(?, ?, ?, ?, ?, ?);");
			pst.setString(1, cart.getItemCode());
			pst.setString(2, cart.getOwner());
			pst.setString(3, cart.getSellerCode());
			pst.setString(4, cart.getItemName());
			pst.setInt(5, cart.getPrice());
			pst.setInt(6, cart.getCount());
			pst.execute();
			pst.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static List<CartBean> selectAllCartItem(String email) {
        List<CartBean> cartList = new ArrayList<CartBean>();
        CartBean cart = null;
        try {
            conn = ConnectionProvider.getConnection();
            st = conn.createStatement();
			pst = conn.prepareStatement ("SELECT * FROM Cart WHERE userCode = (?);");
			pst.setString(1,email);
			rs = pst.executeQuery();
            while(rs.next()) {
            	cart = new CartBean();       
            	cart.setItemCode(rs.getString("itemCode"));
            	cart.setOwner(rs.getString("userCode"));
            	cart.setSellerCode(rs.getString("sellerCode"));
            	cart.setItemName(rs.getString("itemName"));
            	cart.setPrice(Integer.parseInt(rs.getString("price")));
            	cart.setCount(Integer.parseInt(rs.getString("count")));
            	cartList.add(cart);
            }
            st.close();
            conn.close();
        } catch(Exception e) {
                e.printStackTrace();
        }
        return cartList;
    }
	public static void deleteItemByCheck(List<String[]> infoList){

		try {
			String[] emailList = infoList.get(0);
			String email = emailList[0]; 
			String[] itemList = infoList.get(1);
			String[] sellerList = infoList.get(2);
			String[] chooseList = infoList.get(3);

			conn = ConnectionProvider.getConnection();
			List<Integer> checkIndex = new ArrayList<Integer>();
			if(chooseList == null) return;
			for(int i=0; i<chooseList.length; i++){
				if(chooseList[i]!="" && chooseList[i]!="0" ){
					checkIndex.add(i);
				}
			}
			for(int i=0; i<checkIndex.size(); i++){
				pst = conn.prepareStatement("DELETE FROM Cart WHERE itemCode = (?) and sellerCode = (?) and userCode = (?);");
				pst.setString(1, itemList[checkIndex.get(i)]);
				pst.setString(2, sellerList[checkIndex.get(i)]);
				pst.setString(3, email);
				pst.execute();
			}
			pst.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	public static void deleteItemBySell(String[] itemList,String[] sellerList, String email, String[] buyChecklist){
		for(int i=0; i<buyChecklist.length; i++){
			if(buyChecklist[i] != null){
			int index = (Integer.parseInt(buyChecklist[i]));
				try{
					conn = ConnectionProvider.getConnection();
					pst = conn.prepareStatement ("DELETE FROM Cart WHERE itemCode = (?) and sellerCode = (?) and userCode = (?);");
					pst.setString(1, itemList[index]);
					pst.setString(2, sellerList[index]);
					pst.setString(3, email);
					pst.execute();
					pst.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
	}
	public static void deleteItemBySellCheck(String[] itemList,String[] sellerList, String email, String[] buyChecklist){
		for(int i=0; i<buyChecklist.length; i++){
			if(buyChecklist[i] != null){
			int index = (Integer.parseInt(buyChecklist[i]))/2;
				try{
					conn = ConnectionProvider.getConnection();
					pst = conn.prepareStatement ("DELETE FROM Cart WHERE itemCode = (?) and sellerCode = (?) and userCode = (?);");
					pst.setString(1, itemList[index]);
					pst.setString(2, sellerList[index]);
					pst.setString(3, email);
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
