package org.ocean.spider.jobs;

import java.io.FileNotFoundException;
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
import org.ocean.spider.entity.HuXing;
import org.ocean.spider.entity.LouPan;

public class GetHouseStyleJob {

	CommonDaoService service = TransactionalServiceHelper.getTransactionalService(CommonDaoService.class);
	
	public void collect(LouPan loupan){
		try{
			//http://newhouse.hfhouse.com/HouseStyleView/listHouseStyleViewShow/childId/17059/id/13069/isCommon/
			//http://newhouse.hfhouse.com/HouseStyleView/listHouseStyleViewShow/childId/17059/id/13069/isCommon/Y#go
//			String urlStr = "http://newhouse.hfhouse.com/HouseStyle/listHouseStyle/childId/17059";
			
			for(int i=1;i<=4;i++){
				String urlStr = "http://newhouse.hfhouse.com/HouseStyle/listHouseStyle/childId/"+loupan.linkId+"/r/"+i+"/d/";
//				String urlStr = "http://newhouse.hfhouse.com/HouseStyle/listHouseStyle/childId/15912/r/2/d/";
				URL url = new URL(urlStr);
				URLConnection conn = url.openConnection();
				conn.setConnectTimeout(10000);
				conn.setReadTimeout(30000);
				String result = "";
				try{
					result = IOUtils.toString(conn.getInputStream(),"utf-8");
				}catch(FileNotFoundException ex){
					ex.printStackTrace();
					continue;
				}
				Document doc = Jsoup.parse(result);
				Element first = doc.getElementsByAttributeValue("class", "lp_album_r").first();
				if(first==null){
					continue;
				}
				Elements houses = first.children();
				for(Element elem : houses){
					if(elem.children().size()==0){
						continue;
					}
					HuXing style = new HuXing();
					style.estateName = loupan.name;
					style.rooms = i;
					Element img = elem.child(1).child(0).child(0);
					style.comment = elem.child(3).ownText();
					style.style = elem.child(2).child(0).ownText();
					style.outterImgLink = img.attr("src");
					String hql = "from HuXing where estateName=:estateName and style=:style";
					HuXing oldData = service.getUniqueByParams(HuXing.class, hql, new String[]{"estateName","style"}, new String[]{style.estateName,style.style});
					if(oldData==null){
						style.uid = UUID.randomUUID().toString();
						service.saveOrUpdate(style);
					}else{
						System.err.println("update "+style);
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		DBUtil.init();
		GetHouseStyleJob job = new GetHouseStyleJob();
		List<LouPan> list = job.service.listByParams(LouPan.class, "from LouPan", null, null);
		for(int i=0;i<list.size();i++){
			if(i<1092){
				continue;
			}
			System.out.println("-->"+i+","+list.get(i).name+","+list.get(i).linkId);
			job.collect(list.get(i));
		}
	}
}
