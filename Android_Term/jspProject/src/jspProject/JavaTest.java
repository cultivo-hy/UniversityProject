package jspProject;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements; 

public class JavaTest {	
	String URLGS = "http://gs25.gsretail.com/gscvs/ko/products/event-goods-search?CSRFToken=eb2aa1bd-538c-49e6-8fd3-bf251af16306";
	String URLmini = "http://minihomepage.cloudapp.net/MiniStopHomePage/page/event/plus1.do";
	String URLCu = "http://cu.bgfretail.com/event/plusAjax.do";
	String URLwithme = "http://www.with-me.co.kr/service/eventProduct.asp";
	ArrayList<ArrayList<String>> returnValue = null;

	public ArrayList<ArrayList<String>> getGS(){
		
		ArrayList<String> nameList = null;
		ArrayList<String> costList = null;
		ArrayList<String> srcList = null;
		returnValue = null;
		returnValue = new ArrayList<ArrayList<String>>();
		String[] search = {"ONE_TO_ONE","TWO_TO_ONE","GIFT"};
		String[] index = {"10","46","8"};
		Pattern url = Pattern.compile("((http)\\:\\/\\/||www)[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(\\/\\S*)[jp][g]");	
		Pattern url2 = Pattern.compile("((http)\\:\\/\\/||www)[a-zA-Z0-9\\-\\.]*\\.[a-zA-Z]{2,3}(\\/\\S*)[jpg]*[\\s\\\"¤¡-¤¾a-zA-Z\\:\\/\\_\\.°¡-ÆR0-9$\\*\\,]+");
		Pattern name = Pattern.compile("(goods)Nm(\\|\"|:)*.{5}[a-zA-z¤¡-¤¾°¡-ÆR()0-9.%/*?]*[\\\"]?");
		Pattern cost = Pattern.compile("((price\\\")(\\|\"|:)*[0-9.m]*)");

		for(int i=0; i<3; i++){
			System.out.println("---------------------------"+i+"+"+i+"-----------------------------");
			for(int j=1; j<=Integer.parseInt(index[i]); j++){
				Document doc = null;
				String tmp = j +"";
				try {
				      doc = Jsoup.connect(URLGS).data("pageNum",tmp).data("parameterList",search[i]).postDataCharset("UTF-8").get();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
		
		
				String[] temp = doc.text().replace("{", "").replaceFirst("^,:$", "").split("\"attFileId\"");
				//((?:http\:\/\/)|)?([a-zA-Z0-9\-\.]+\.[a-zA-Z]{2,3}(?:\??)[a-zA-Z0-9\-\._\?\,\'\/\\\+&%\$#\=~]+)
				//System.out.println(doc.html().toString());
				for(int k=0; k<temp.length; k++){
					String myData = temp[k];
					Matcher urlMatch;
					if(i == 2){
						urlMatch = url2.matcher(myData);
						
					}
					else{
						urlMatch = url.matcher(myData);
					}
					Matcher nameMatch = name.matcher(myData);
					Matcher costMatch = cost.matcher(myData);
					if(nameMatch.find()){
						System.out.println(nameMatch.group(0));
					}
					if(costMatch.find()){
						System.out.println(costMatch.group(0));
					}
					if (urlMatch.find())
					{
						if(i == 2){
							String[] tmplist = urlMatch.group(0).replace("\"", "").replace("\\", "").split(",");
							System.out.println(tmplist[8]);
							System.out.println(tmplist[6]); 
							System.out.println(tmplist[9]);            
							System.out.println(tmplist[0]);
							
						}
						else{
							System.out.println(urlMatch.group(0));
						}
					}

				}
				tmp="";
			}
			System.out.println("---------------------------"+i+"+"+i+"-----------------------------");
		}
		
		//System.out.println(doc.html().toString());
		return returnValue;
	}

	/*
	public ArrayList<ArrayList<String>> getSeven(){  // ¾å½Ã·Î ÇÏ¸é µÊ
		
		StringBuilder contentBuilder = null;
		String URL11 = "C:\\Users\\Administrator\\workspace\\jspProject\\WebContent\\parsingInfo\\seven_11.html";
		String URL21 = "C:\\Users\\Administrator\\workspace\\jspProject\\WebContent\\parsingInfo\\seven_21.html";
		String URLPR = "C:\\Users\\Administrator\\workspace\\jspProject\\WebContent\\parsingInfo\\seven_pre.html";
		
		ArrayList<String> nameList = null;
		ArrayList<String> costList = null;
		ArrayList<String> srcList = null;
		returnValue = null;
		returnValue = new ArrayList<ArrayList<String>>();
		
		String[] list = {URL11, URL21, URLPR};
		for(int i=0; i<3; i++){
			nameList = new ArrayList<String>();
			costList = new ArrayList<String>();
			srcList = new ArrayList<String>();
			try {
				contentBuilder = new StringBuilder();
			    BufferedReader in = new BufferedReader(new FileReader(list[i]));
			    String str;
			    while ((str = in.readLine()) != null) {
			        contentBuilder.append(str);
			    }
			    in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String content = contentBuilder.toString();
			Document doc = Jsoup.parse(content);
			
			System.out.println("seven");
			Elements proName=doc.select("div.pic_product div.infowrap div.name");
			Elements proCost=doc.select("div.pic_product div.infowrap span");
			Elements proSrc=doc.select("div.pic_product img");
	         
			for(Element e: proName){
				nameList.add(e.text());
			}
			for(Element e: proCost){	
				System.out.println("ASdasdasdasdas");
				costList.add(e.text().replace(",", ""));
			}
			for(Element e: proSrc){
				srcList.add("https://www.7-eleven.co.kr" + e.attr("src"));
			}
			returnValue.add(nameList);
			returnValue.add(costList);
			returnValue.add(srcList);
			nameList = null;
			costList = null;
			srcList = null;
		}
		return returnValue;
	}
	*/
	public ArrayList<ArrayList<String>> getCu(){	// ³¡
		
		String[] search = {"23","24","http://cu.bgfretail.com/event/presentAjax.do"};
		String[] index = {"2","9"};
		
		ArrayList<String> nameList = null;
		ArrayList<String> costList = null;
		ArrayList<String> srcList = null;
		returnValue = null;
		returnValue = new ArrayList<ArrayList<String>>();
		
		Document doc = null;
		for(int i=0; i<2; i++){
			nameList = new ArrayList<String>();
			costList = new ArrayList<String>();
			srcList = new ArrayList<String>();
			for(int j=1; j<=Integer.parseInt(index[i]);j++){
				String num = j + "";
				try {
					  doc = Jsoup.connect(URLCu).data("searchCondition",search[i]).data("pageIndex",num).get();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			

				Elements proName=doc.select("ul li p.prodName");
				Elements proCost=doc.select("ul li p.prodPrice");
				Elements proSrc=doc.select("ul li div.photo img");

				for(Element e: proName){
					nameList.add(e.text());
				}

				for(Element e: proCost){
					costList.add(e.text().replace(",", "").replace("¿ø", ""));
				}

				for(Element e: proSrc){
					srcList.add(e.attr("src"));		
				}
				num ="";
			}
			returnValue.add(nameList);
			returnValue.add(costList);
			returnValue.add(srcList);
			nameList = null;
			costList = null;
			srcList = null;
		}
		String present = "http://cu.bgfretail.com/event/presentAjax.do";
		nameList = new ArrayList<String>();
		costList = new ArrayList<String>();
		srcList = new ArrayList<String>();
		try {
			doc = Jsoup.connect(present).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Elements proName=doc.select("div.presentListBox p.prodName");
		Elements proCost=doc.select("div.presentListBox p.prodPrice");
		Elements proSrc=doc.select("div.presentListBox div.photo img");
		for(Element e: proName){
			nameList.add(e.text());	
		}
		for(Element e: proCost){
			costList.add(e.text().replace(",", "").replace("¿ø", ""));	
		}
		for(Element e: proSrc){

			srcList.add(e.attr("src"));			
		}
		returnValue.add(nameList);
		returnValue.add(costList);
		returnValue.add(srcList);
		return returnValue;
	}
	
	public ArrayList<ArrayList<String>> getMini(){	// ³¡	
		
		StringBuilder contentBuilder = null;
		String URL11 = "C:\\Users\\Administrator\\workspace\\jspProject\\WebContent\\parsingInfo\\mini11.html";
		String URL21 = "C:\\Users\\Administrator\\workspace\\jspProject\\WebContent\\parsingInfo\\mini21.html";
		String URLPR = "C:\\Users\\Administrator\\workspace\\jspProject\\WebContent\\parsingInfo\\miniPre.html";
		
		String[] list = {URL11, URL21, URLPR};
		
		ArrayList<String> nameList = null;
		ArrayList<String> costList = null;
		ArrayList<String> srcList = null;
		returnValue = null;
		returnValue = new ArrayList<ArrayList<String>>();
		
		//File file = new File("C:\\Users\\Administrator\\workspace\\jspProject\\src\\resultMini.txt");
		for(int i=0; i<3; i++){
			nameList = new ArrayList<String>();
			costList = new ArrayList<String>();
			srcList = new ArrayList<String>();
			try {
				contentBuilder = new StringBuilder();
			    BufferedReader in = new BufferedReader(new FileReader(list[i]));
			    String str;
			    while ((str = in.readLine()) != null) {
			        contentBuilder.append(str);
			    }
			    in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String content = contentBuilder.toString();
			
			Document doc = Jsoup.parse(content);

			
			System.out.println("mini");
			Elements proName=doc.select("ul li img");
			Elements proCost=doc.select("strong");
			Elements proSrc=doc.select("img");
            // true ÁöÁ¤½Ã ÆÄÀÏÀÇ ±âÁ¸ ³»¿ë¿¡ ÀÌ¾î¼­ ÀÛ¼º
			for(Element e: proName){
				nameList.add(e.attr("alt"));
			}
			for(Element e: proCost){
				costList.add(e.text().replace(",", ""));
			}
			for(Element e: proSrc){
				srcList.add("http://minihomepage.cloudapp.net/MiniStopHomePage/page" + e.attr("src").substring(2));
			}
			returnValue.add(nameList);
			returnValue.add(costList);
			returnValue.add(srcList);
			nameList = null;
			costList = null;
			srcList = null;
			/*
            FileWriter fw=null;
			try {
				fw = new FileWriter(file, true);
		
				for(Element e: proName){
					fw.write(e.attr("alt")+"\r\n");
			        fw.flush();	
				}
				for(Element e: proCost){	
					fw.write(e.text()+"\r\n");
			        fw.flush();	
				}
				for(Element e: proSrc){
					fw.write("http://minihomepage.cloudapp.net/MiniStopHomePage/page" + e.attr("src").substring(2)+"\r\n");
			        fw.flush();	
				}
				fw.close();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			*/		
		}
		return returnValue;
	}


	public ArrayList<ArrayList<String>> getWithme(){	//³¡
		String[] text = {"1n1","2n1","X2"};
		String[] index = {"4","20","1"};
		ArrayList<String> nameList = null;
		ArrayList<String> costList = null;
		ArrayList<String> srcList = null;
		Document doc =  null;
		returnValue = null;
		returnValue = new ArrayList<ArrayList<String>>();
		for(int j=0; j<3; j++){
			nameList = new ArrayList<String>();
			costList = new ArrayList<String>();
			srcList = new ArrayList<String>();
			for(int i=1; i<=Integer.parseInt(index[j]); i++){
				String tmp = i +"";
				try {
				      doc = Jsoup.connect(URLwithme).data("productCategory",text[j]).data("cpage",tmp).get();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	

				Elements titles1 = doc.select("strong.productDiv");
				Elements titles2 = doc.select("p.price");
				Elements titles3 = doc.select("p.productImg img");
				
				for(Element e: titles1){
					nameList.add(e.text());
					System.out.println(e.text());
				}
				for(Element e: titles2){
					String temp =null;
					if(j==2){
						temp = e.text().replace("\u00a0","").replace(",", "").split(" ")[1];
					}
					else{
						temp= e.text().replace("\u00a0","").replace(",", "");
					}

					int size = temp.length();
					costList.add(temp.substring(0,size-1));
					System.out.println(temp);
				}
				for(Element e: titles3){
					srcList.add("http://with-me.co.kr" + e.attr("src"));
					System.out.println("http://with-me.co.kr" + e.attr("src"));
				}
				tmp="";
			}
			returnValue.add(nameList);
			returnValue.add(costList);
			returnValue.add(srcList);
			nameList = null;
			costList = null;
			srcList = null;
		}
		return returnValue;
	}
}