package org.ocean.spider.jobs;

import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.ocean.spider.entity.LouPan;

public class GetPictureJob {

	public void collect(LouPan loupan){
		try{
			String urlStr = "http://newhouse.hfhouse.com/HouseAlbum/listHouseAlbum/childId/15369";
			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			String result = IOUtils.toString(conn.getInputStream(),"utf-8");
			Document doc = Jsoup.parse(result);
			Element img  = doc.getElementById("bigPhoto");
			String src = img.attr("src");
			String estateName = loupan.name;
			String group = "";
			String alt = img.attr("alt");
			if(alt!=null){
				group = alt.split("-")[0];
			}
			
			//get next url
			Element div = doc.getElementsByAttributeValue("class", "big_img").first();
			Element link = div.child(0).child(0).child(0).child(2).child(0);
			String next = "http://newhouse.hfhouse.com/" + link.attr("href");
			//save next
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void listHouseAlbum(){
		
	}
}
