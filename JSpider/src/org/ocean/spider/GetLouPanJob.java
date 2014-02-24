package org.ocean.spider;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.ocean.spider.entity.LouPan;

public class GetLouPanJob {

	public static void main(String[] args) throws IOException{
//		Map<String,String> settings = new HashMap<String , String>();
//		settings.put(AvailableSettings.URL, "jdbc:mysql://localhost:3306/ihouse?characterEncoding=utf-8");
//		settings.put(AvailableSettings.USER, "root");
//		settings.put(AvailableSettings.PASS, "");
//		settings.put(AvailableSettings.SHOW_SQL, "true");
//		settings.put(AvailableSettings.DRIVER, "com.mysql.jdbc.Driver");
//		settings.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
//		settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//		settings.put(AvailableSettings.HBM2DDL_AUTO, "update");
//		settings.put("annotated.packages", "org.ocean.spider.entity");
//		SessionFactoryBuilder.applySettings(settings);
//		CommonDaoService service  =TransactionalServiceHelper.getTransactionalService(CommonDaoService.class);
		String nextPage = "http://newhouse.hf.house365.com/house/p-28/";
		do {
			nextPage = collectPage(nextPage);
		}while(nextPage!=null);
		
	}
	
	private static String collectPage(String urlStr) throws IOException{
		System.out.println("start to collect page "+ urlStr);
		URL url = new URL(urlStr);
		URLConnection conn = url.openConnection();
		String result = IOUtils.toString(conn.getInputStream(),"gbk");
		Document doc = Jsoup.parse(result);
		Elements loupanList = doc.getElementsByAttributeValue("class", "fList clearfix");
		for(Element loupan : loupanList){
			LouPan lp = new LouPan();
			String name = loupan.child(1).child(0).child(0).text();
			if(loupan.child(1).child(0).children().size()>=2){
				String state = loupan.child(1).child(0).child(1).attr("alt");
				lp.state = state;
			}
			String type = loupan.child(1).child(1).text();
			type = getValue(type);
			String address = loupan.child(1).child(2).text();
			address = getValue(address);
			String area = loupan.child(1).child(3).ownText();
			area = getValue(area);
			
			String phone = loupan.child(2).child(0).child(0).text();
			if(!loupan.child(2).child(1).children().isEmpty()){
				String price = loupan.child(2).child(1).child(0).text();
				if(price!=null){
					if(price.contains("-")){
						lp.price = Integer.valueOf(price.split("-")[0]);
					}else{
						lp.price = Integer.valueOf(price);
					}
				}
			}
			lp.uid = UUID.randomUUID().toString();
			lp.name = name;
			lp.type = type;
			lp.address = address;
			lp.area = area;
			lp.phoneNumber = phone;
			System.out.println(lp);
		}
		
		//find next page
		Elements pageBar = doc.getElementsByAttributeValue("class", "liebiao_manu");
		
		String nextPage = null;
		for(Element elem : pageBar.get(0).children()){
			if("下一页".equals(elem.text())){
				nextPage = elem.attr("href");
				break;
			}
		}
		return nextPage;
	}
	private static String getValue(String kvStr){
		String value = "";
		if(kvStr!=null && kvStr.contains("：")){
			String[] arr = kvStr.split("：");
			if(arr.length==2){
				value = arr[1];
			}
		}
		return value;
	}
}
