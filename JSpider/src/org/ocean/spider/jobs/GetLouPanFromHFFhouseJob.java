package org.ocean.spider.jobs;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.ocean.shibernate.CommonDaoService;
import org.ocean.shibernate.TransactionalServiceHelper;
import org.ocean.spider.DBUtil;
import org.ocean.spider.DateUtil;
import org.ocean.spider.entity.LouPan;
import org.ocean.spider.silk.list.ListCollectorManager;

public class GetLouPanFromHFFhouseJob {

	private static CommonDaoService service = null;
	private static ListCollectorManager manager = new ListCollectorManager();
	
	private static GetSupportInfoJob sInfoJob = new GetSupportInfoJob();
	
	private static GetHomePartInfoJob hInfoJob = new GetHomePartInfoJob();
	
	private static GetDetailPartInfoJob dInfoJob = new GetDetailPartInfoJob();
	public static void main(String[] args) throws IOException{
		DBUtil.init();
		service  =TransactionalServiceHelper.getTransactionalService(CommonDaoService.class);
		String hfshouse = "http://newhouse.hfhouse.com";
		String nextPage = "/HouseList/index/?&p=112";
		manager.addAllDefaultCollectors();
		do {
			nextPage = collectPage(hfshouse+nextPage);
		}while(nextPage!=null);
		
//		List<LouPan> list = service.listByParams(LouPan.class, "from LouPan", null, null);
//		if(list.size()==0){
//			System.out.println("没有楼盘数据，请先运行GetLouPanFromHFFhouseJob");
//			return;
//		}
//		
//		for(int i=0;i<list.size();i++){
//			collect(list.get(i));
//		}
	}
	
	private static String collectPage(String urlStr) throws IOException{
		System.out.println("start to collect page "+ urlStr);
		URL url = new URL(urlStr);
		URLConnection conn = url.openConnection();
		String result = IOUtils.toString(conn.getInputStream(),"utf-8");
		Document doc = Jsoup.parse(result);
		Elements loupanList = doc.getElementsByAttributeValue("class", "loupan_list_none");
		for(Element root : loupanList){
			LouPan loupan = new LouPan();
			loupan.uid = UUID.randomUUID().toString();
			manager.collect(root, loupan);
			collect(loupan);
			System.out.println(loupan);
			LouPan oldLoupan = service.getUniqueByKeyValue(LouPan.class, "name", loupan.name);
			if(oldLoupan==null){
				service.saveOrUpdate(loupan);
			}
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
	
	private static void collect(LouPan loupan){
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
		sInfoJob.collect(loupan);
		hInfoJob.collect(loupan);
		dInfoJob.collect(loupan);
	}
}
