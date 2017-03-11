package myPackage;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.opencsv.CSVReader;
 
public class OpenCsvReader {

	private void userReader(String filePath) throws IOException {
		UserBean tmpUser= new UserBean();
		CouponBean tmpCoupon = new CouponBean();
		CSVReader reader = new CSVReader(new FileReader(filePath));
		String[] nextLine;
		nextLine = reader.readNext();
		while ((nextLine = reader.readNext()) != null) {
			
			tmpUser.setEmail(nextLine[0]);
			tmpUser.setPw(nextLine[1]);
			tmpUser.setFamilyName(nextLine[2]);
			tmpUser.setLastName(nextLine[3]);
			tmpUser.setBirth(nextLine[4]);
			UserDAO.insertUser(tmpUser);
			String strCoupon = nextLine[5];
			String list[] = strCoupon.split("\\|");
			String rank = "REGULAR";
			for(int i=0; i<list.length; i++){
				String tCoupon[]= list[i].split(" ");
				if(tCoupon.length == 3){
					tmpCoupon.setOwner(tmpUser.getEmail());
					tmpCoupon.setCouponNumber(tCoupon[0]);
					tmpCoupon.setCouponCount(1);
					tmpCoupon.setCouponName(tCoupon[1]);
					tmpCoupon.setType(tCoupon[2]);
					CouponDAO.insertCoupon(tmpCoupon);
					if( Integer.parseInt(tCoupon[0]) == 0){
						rank = "VVIP";
					}
					else{
						if(rank == "REGULAR")
							rank = "VIP";
					}
				}
			}
			RankDAO.insert(nextLine[0],rank);
		}
	}
	private void itemReader(String filePath) throws IOException {
		ItemBean tmpItem= new ItemBean();	
		CSVReader reader = new CSVReader(new FileReader(filePath));
		String[] nextLine;
		nextLine = reader.readNext();
		while ((nextLine = reader.readNext()) != null) {
			tmpItem.setItemCode(nextLine[0]);
			tmpItem.setName(nextLine[1]);
			tmpItem.setBrand(nextLine[2]);
			tmpItem.setSellerCode(nextLine[3]);
			tmpItem.setSellerName(nextLine[4]);
			tmpItem.setPrice(Integer.parseInt(nextLine[5]));
			tmpItem.setNumberOfStock(Integer.parseInt(nextLine[6]));
			tmpItem.setNumberOfSale(Integer.parseInt(nextLine[7]));
			ItemDAO.insertItem(tmpItem);
			
			String cart = nextLine[8];
			if(cart.compareTo("")!=0){
				CartBean myCart = new CartBean();
				String cartList[] = cart.split("\\|");
				for(int i=0; i<cartList.length; i++){
					String tCart[]= cartList[i].split(" ");
					myCart.setOwner(tCart[0]);
					myCart.setCount(Integer.parseInt(tCart[1]));
					myCart.setItemCode(nextLine[0]);
					myCart.setItemName(nextLine[1]);
					myCart.setSellerCode(nextLine[3]);
					myCart.setPrice(Integer.parseInt(nextLine[5]));	
					CartDAO.insertItemToCart(myCart);
				}
			}
			String purchase = nextLine[9];
			if(purchase.compareTo("")!=0){
				SellBean sell = new SellBean();
				String purchaseList[] = purchase.split("\\|");
				for(int i=0; i<purchaseList.length; i++){
					String tSell[]= purchaseList[i].split(" ");
					sell.setBuyer(tSell[0]);
					int count = Integer.parseInt(tSell[1]);
					sell.setCouponUse(tSell[2]);
					int percent = Integer.parseInt(tSell[2]);
					sell.setCount(count);
					sell.setDate(tSell[3]+ " " + tSell[4]);
					sell.setItemCode(nextLine[0]);
					if(percent == 0){
						sell.setAllPrice(count * Integer.parseInt(nextLine[5]));
					}
					else{
						sell.setAllPrice((count * Integer.parseInt(nextLine[5]) * (100-percent))/100);
					}
					sell.setSeller(nextLine[3]);			
					SellDAO.insertSellToDB(sell);
					
				}
			}
		}
	}
	public static void main(String[] args)  {

		OpenCsvReader csvRead = new OpenCsvReader();
		try {
			csvRead.userReader("C:\\Users\\Administrator\\workspace\\postsql-example\\src\\myPackage\\user.csv");
			csvRead.itemReader("C:\\Users\\Administrator\\workspace\\postsql-example\\src\\myPackage\\item.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
 
	}
}