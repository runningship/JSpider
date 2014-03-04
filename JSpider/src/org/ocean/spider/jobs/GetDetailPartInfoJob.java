package org.ocean.spider.jobs;

import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.ocean.shibernate.CommonDaoService;
import org.ocean.shibernate.TransactionalServiceHelper;
import org.ocean.spider.DateUtil;
import org.ocean.spider.entity.LouPan;
import org.ocean.spider.silk.detail.DetailInfoManager;

public class GetDetailPartInfoJob {

	private CommonDaoService service = null;
	private DetailInfoManager detailMgr = new DetailInfoManager();
	
	public GetDetailPartInfoJob(){
		service  =TransactionalServiceHelper.getTransactionalService(CommonDaoService.class);
		detailMgr = new DetailInfoManager();
		detailMgr.addAllDefaultCollectors();
	}
//	public static void main(String[] args) throws IOException{
//		DBUtil.init();
//		detailMgr.addAllDefaultCollectors();
//		service  =TransactionalServiceHelper.getTransactionalService(CommonDaoService.class);
//		List<LouPan> list = service.listByParams(LouPan.class, "from LouPan", null, null);
//		if(list.size()==0){
//			System.out.println("没有楼盘数据，请先运行GetLouPanFromHFFhouseJob");
//			return;
//		}
//		
//		for(int i=0;i<list.size();i++){
//			System.out.println("-->"+i);
//			collect(list.get(i));
//		}
//	}
	
	public void collect(LouPan loupan){
		try{
			String urlStr = "http://newhouse.hfhouse.com/BaseInfo/index/childId/"+loupan.linkId;
//			System.out.println("start to collect page "+ urlStr);
			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			String result = IOUtils.toString(conn.getInputStream(),"utf-8");
			Document doc = Jsoup.parse(result);
			
			detailMgr.collect(doc.getElementsByAttributeValue("class", "col_l_cont").first(), loupan);
		}catch(Exception ex){
			loupan.lastError = ex.getMessage();
		}
	}
}
