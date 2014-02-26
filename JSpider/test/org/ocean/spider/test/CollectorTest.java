package org.ocean.spider.test;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import junit.framework.Assert;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import org.ocean.spider.silk.list.EstateNameCollector;
import org.ocean.spider.silk.list.EstateTypeCollector;
import org.ocean.spider.silk.list.RegionCollector;
import org.ocean.spider.silk.list.StateCollector;

public class CollectorTest {

	static Element elem = null;
	
	@Before
	public void init() throws IOException{
		if(elem==null){
			URL url = new URL("http://newhouse.hfhouse.com/HouseList/index/keyWord/%20%E4%B8%AD%E6%B5%B7%E6%BB%A8%E6%B9%96%E5%85%AC%E9%A6%86/");
			URLConnection conn = url.openConnection();
			String result = IOUtils.toString(conn.getInputStream(),"utf-8");
			Document doc = Jsoup.parse(result);
			Elements loupanList = doc.getElementsByAttributeValue("class", "loupan_list_none");
			elem = loupanList.first();
		}
	}
	
	@Test
	public void testEstateNameCollector(){
		EstateNameCollector enc = new EstateNameCollector("name");
		Assert.assertEquals("中海滨湖公馆", enc.getAttrValue(elem));
	}
	
	@Test
	public void testEstateTypeCollector(){
		EstateTypeCollector enc = new EstateTypeCollector("estateType");
		Assert.assertEquals("住宅", enc.getAttrValue(elem));
	}
	
	@Test
	public void testRegionCollector(){
		RegionCollector enc = new RegionCollector("region");
		Assert.assertEquals("滨湖新区", enc.getAttrValue(elem));
	}
	
	@Test
	public void testStateCollector(){
		StateCollector coll = new StateCollector("state");
		System.out.println(coll.getAttrValue(elem));
		Assert.assertNotNull(coll.getAttrValue(elem));
	}
}