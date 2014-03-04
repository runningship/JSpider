package org.ocean.spider.jobs;

import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.ocean.shibernate.CommonDaoService;
import org.ocean.shibernate.TransactionalServiceHelper;
import org.ocean.spider.DBUtil;
import org.ocean.spider.entity.LouPan;
import org.ocean.spider.entity.PriceHistory;

public class GetPriceHistoryJob {

	CommonDaoService service = TransactionalServiceHelper.getTransactionalService(CommonDaoService.class);
	
	public GetPriceHistoryJob(){
		DBUtil.init();
	}
	public void collect(LouPan loupan) {
		
		try{
//			String urlStr = "http://newhouse.hfhouse.com/HousePrice/listHousePrice/childId/15369";
			String urlStr = "http://newhouse.hfhouse.com/HousePrice/listHousePrice/childId/"+loupan.linkId;
			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			String result = IOUtils.toString(conn.getInputStream(),"utf-8");
			Document doc = Jsoup.parse(result);
			Elements elements = doc.getElementsByAttributeValue("bgcolor", "#FFFFFF");
			for(Element elem : elements){
				if(elem.children().size()==0 && "108".equals(elem.attr("width"))){
					PriceHistory price = new PriceHistory();
					price.estateName = loupan.name;
					price.estateType = loupan.estateType;
					price.price = elem.nextElementSibling().ownText();
					price.updateTime = elem.ownText()+" 00:00:00";
					PriceHistory oldPrice = getOldPrice(price.estateName,price.estateType,price.updateTime);
					if(oldPrice!=null){
						oldPrice.price = price.price;
						service.saveOrUpdate(oldPrice);
						System.out.println("update " + oldPrice);
					}else{
						price.uid = UUID.randomUUID().toString();
						service.saveOrUpdate(price);
						System.out.println("new " + price);
					}
					
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			loupan.lastError = ex.getMessage();
		}
	}
	
	private PriceHistory getOldPrice(String estateName,String estateType,String updateTime){
		String hql = "from PriceHistory where estateName=:estateName and estateType=:estateType and updateTime=:updateTime";
		return service.getUniqueByParams(PriceHistory.class, hql, new String[]{"estateName","estateType","updateTime"}
			, new String[]{estateName,estateType,updateTime});
		
	}
	public static void main(String[] args){
		GetPriceHistoryJob job = new GetPriceHistoryJob();
		List<LouPan> list = job.service.listByParams(LouPan.class, "from LouPan", null, null);
		for(int i=0;i<list.size();i++){
			System.out.println("-->"+i);
			job.collect(list.get(i));	
		}
	}
}
