package org.ocean.spider.jobs;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.ocean.spider.entity.LouPan;
import org.ocean.spider.silk.home.HomePartCollectorManager;
import org.ocean.spider.silk.home.more.HouseInfoManager;

public class GetHomePartInfoJob {

	public static void main(String[] args) throws IOException{
//		collect("http://newhouse.hfhouse.com/16809.html");
		LouPan loupan = new LouPan();
		loupan.linkId = "16809";
		collect(loupan);
	}
	
	public static void collect(LouPan loupan) throws IOException{
		String urlStr = "http://newhouse.hfhouse.com/" + loupan.linkId + ".html";
		System.out.println("start to collect page "+ urlStr);
		URL url = new URL(urlStr);
		URLConnection conn = url.openConnection();
		String result = IOUtils.toString(conn.getInputStream(),"utf-8");
		Document doc = Jsoup.parse(result);
		Elements list = doc.getElementsByAttributeValue("class", "right");
		HomePartCollectorManager manager = new HomePartCollectorManager();
		manager.addAllDefaultCollectors();
		manager.collect(list.get(1), loupan);
		
		HouseInfoManager infoMgr = new HouseInfoManager();
		infoMgr.collect(doc.getElementsByAttributeValue("class", "house_information").first(), loupan);
		System.out.println(loupan);
	}
}
