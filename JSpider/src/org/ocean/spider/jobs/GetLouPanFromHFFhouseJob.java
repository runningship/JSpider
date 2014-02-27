package org.ocean.spider.jobs;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.hibernate.cfg.AvailableSettings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.ocean.shibernate.CommonDaoService;
import org.ocean.shibernate.SessionFactoryBuilder;
import org.ocean.shibernate.TransactionalServiceHelper;
import org.ocean.spider.entity.LouPan;
import org.ocean.spider.silk.CollectorManager;

public class GetLouPanFromHFFhouseJob {

	private static CommonDaoService service = null;
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
//		service  =TransactionalServiceHelper.getTransactionalService(CommonDaoService.class);
		String hfshouse = "http://newhouse.hfhouse.com";
		String nextPage = "/HouseList/index/";
		do {
			nextPage = collectPage(hfshouse+nextPage);
		}while(nextPage!=null);
		
	}
	
	private static String collectPage(String urlStr) throws IOException{
		System.out.println("start to collect page "+ urlStr);
		URL url = new URL(urlStr);
		URLConnection conn = url.openConnection();
		String result = IOUtils.toString(conn.getInputStream(),"utf-8");
		Document doc = Jsoup.parse(result);
		Elements loupanList = doc.getElementsByAttributeValue("class", "loupan_list_none");
		CollectorManager manager = new CollectorManager();
		manager.addAllDefaultCollectors();
		for(Element root : loupanList){
			LouPan loupan = new LouPan();
			loupan.uid = UUID.randomUUID().toString();
			manager.collect(root, loupan);
			System.out.println(loupan);
//			LouPan oldLoupan = service.getUniqueByKeyValue(LouPan.class, "name", loupan.name);
//			if(oldLoupan==null){
//				service.saveOrUpdate(loupan);
//			}
		}
		
		//find next page
		Elements pageBar = doc.getElementsByAttributeValue("class", "page");
		
		String nextPage = null;
		for(Element elem : pageBar.get(0).children()){
			if("下一页".equals(elem.text())){
				nextPage = elem.attr("href");
				break;
			}
		}
		return nextPage;
	}
}
