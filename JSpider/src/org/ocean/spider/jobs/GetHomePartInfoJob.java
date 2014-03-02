package org.ocean.spider.jobs;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.ocean.shibernate.CommonDaoService;
import org.ocean.shibernate.TransactionalServiceHelper;
import org.ocean.spider.DBUtil;
import org.ocean.spider.DateUtil;
import org.ocean.spider.entity.LouPan;
import org.ocean.spider.silk.home.HomePartCollectorManager;
import org.ocean.spider.silk.home.more.HouseInfoManager;

public class GetHomePartInfoJob {

	private static CommonDaoService service = null;
	
	private static HomePartCollectorManager manager = new HomePartCollectorManager();
	
	private static HouseInfoManager infoMgr = new HouseInfoManager();
	public static void main(String[] args) throws IOException{
//		collect("http://newhouse.hfhouse.com/16809.html");
		DBUtil.init();
		service  =TransactionalServiceHelper.getTransactionalService(CommonDaoService.class);
		manager.addAllDefaultCollectors();
		infoMgr.addAllDefaultCollectors();
		List<LouPan> list = service.listByParams(LouPan.class, "from LouPan", null, null);
		if(list.size()==0){
			System.out.println("没有楼盘数据，请先运行GetLouPanFromHFFhouseJob");
			return;
		}
		
		for(int i=0;i<list.size();i++){
			System.out.println("-->"+i);
			
			collect(list.get(i));
		}
	}
	
	public static void collect(LouPan loupan) throws IOException{
		try {
			if(loupan.updateTime!=null){
				Date updateTime = DateUtil.fromString(loupan.updateTime);
				if(System.currentTimeMillis()-updateTime.getTime()<24*3600*1000){
					System.out.println(loupan.name + "has been updated within a day.");
					return;
				}
			}
		} catch (ParseException e) {
			System.out.println("update time not setting or not right,do update again");
		}
		String urlStr = "http://newhouse.hfhouse.com/" + loupan.linkId + ".html";
		System.out.println("start to collect page "+ urlStr);
		try{
			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			String result = IOUtils.toString(conn.getInputStream(),"utf-8");
			Document doc = Jsoup.parse(result);
			Elements list = doc.getElementsByAttributeValue("class", "right");
			manager.collect(list.get(1), loupan);
			infoMgr.collect(doc.getElementsByAttributeValue("class", "house_information").first(), loupan);
		}catch(Exception ex){
			loupan.lastError = ex.getMessage();
		}
		loupan.updateTime = DateUtil.getCurrentTimeInString();
		service.saveOrUpdate(loupan);
		System.out.println(loupan);
	}
}
