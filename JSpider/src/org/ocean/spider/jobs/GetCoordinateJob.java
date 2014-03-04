package org.ocean.spider.jobs;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.ocean.shibernate.CommonDaoService;
import org.ocean.shibernate.TransactionalServiceHelper;
import org.ocean.spider.DBUtil;
import org.ocean.spider.entity.LouPan;

public class GetCoordinateJob {

	public void collect(LouPan loupan) throws IOException{
		//http://api.map.baidu.com/geocoder/v2/?address=百度大厦&output=json&ak=DF51a574e50cf2f860b91b27261629ef&callback=showLocation
		URL url = new URL("http://ditu.google.cn/maps?output=json&hl=zh-CN&q="+loupan.name);
//		URL url = new URL("http://ditu.google.cn/maps?f=q&source=s_q&output=json&hl=zh-CN&geocode=&abauth=531521a3V3zPIvjw9Q3M4noKccFe6Ygylk0&q=%E9%87%91%E6%B1%9F%E8%8A%B1%E5%9B%AD&btnG=&aq=&vps=3&ei=xiEVU4SVOdCciAfgrID4DA&jsv=476a&sll=31.887411%2C117.242784&sspn=0.01115%2C0.054288&vpsrc=0&brcurrent=h3%2C0x35cb638977f24e3f%3A0xebeedf4a8b1203bc%2C%2C0x35cb640ef207cf9d%3A0xdc151173f2c33299%3Bh5%2C0");
//		System.out.println(URLDecoder.decode("http://ditu.google.cn/maps?f=q&source=s_q&output=json&hl=zh-CN&geocode=&abauth=531521a3V3zPIvjw9Q3M4noKccFe6Ygylk0&q=%E9%87%91%E6%B1%9F%E8%8A%B1%E5%9B%AD&btnG=&aq=&vps=3&ei=xiEVU4SVOdCciAfgrID4DA&jsv=476a&sll=31.887411%2C117.242784&sspn=0.01115%2C0.054288&vpsrc=0&brcurrent=h3%2C0x35cb638977f24e3f%3A0xebeedf4a8b1203bc%2C%2C0x35cb640ef207cf9d%3A0xdc151173f2c33299%3Bh5%2C0"));
		try{
		URLConnection conn = url.openConnection();
		conn.setConnectTimeout(10000);
		conn.setReadTimeout(10000);
		String result = IOUtils.toString(conn.getInputStream(),"gbk");
		System.out.println(result);
		result = result.replace("while(1);", "");
//		Jsoup.parse(result).getElementsByTag("a");
		JSONObject json = JSONObject.fromObject(result);
//		System.out.println(json.getString("url"));
		
		String panel = json.getString("panel");
		panel = URLDecoder.decode(panel);
		Document doc = Jsoup.parse(panel);
		Element aLabel = doc.getElementsByAttributeValue("class", "wpt").first();
		String href = aLabel.child(0).attr("href");
		
		String[] arr = href.split("&");
		String sll = "";
		for(int i=0;i<arr.length;i++){
			if(arr[i].startsWith("sll")){
				sll = arr[i];
				break;
			}
		}
		String[] coordinates = sll.split("=");
		String[] values = coordinates[1].split(",");
		loupan.latitude = Float.valueOf(values[0]);
		loupan.longitude = Float.valueOf(values[1]);
		System.out.println(loupan);
		}catch(Exception ex){
			System.out.println(url);
			throw ex;
		}
	}
	
	public static void main(String[] args) throws IOException{
		GetCoordinateJob job = new GetCoordinateJob();
//		job.collect(new LouPan());
		DBUtil.init();
		CommonDaoService service = TransactionalServiceHelper.getTransactionalService(CommonDaoService.class);
		List<LouPan> list = service.listByParams(LouPan.class, "from LouPan", null, null);
		if(list.size()==0){
			System.out.println("没有楼盘数据，请先运行GetLouPanFromHFFhouseJob");
			return;
		}
		
		for(int i=0;i<list.size();i++){
			System.out.println("-->"+i);
			try{
				job.collect(list.get(i));
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
}
