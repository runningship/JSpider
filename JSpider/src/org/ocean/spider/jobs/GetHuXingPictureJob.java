package org.ocean.spider.jobs;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.ocean.shibernate.CommonDaoService;
import org.ocean.shibernate.TransactionalServiceHelper;
import org.ocean.spider.Cn2SpellUtil;
import org.ocean.spider.DBUtil;
import org.ocean.spider.entity.HuXing;

public class GetHuXingPictureJob {

	CommonDaoService service = TransactionalServiceHelper.getTransactionalService(CommonDaoService.class);
	
	public static void main(String[] args){
		DBUtil.init();
		GetHuXingPictureJob job = new GetHuXingPictureJob();
		List<HuXing> list = job.service.listByParams(HuXing.class, "from HuXing", null, null);
		for(int i=0;i<list.size();i++){
			System.out.println("-->"+i+","+list.get(i).estateName);
			job.collect(list.get(i));
		}
	}

	private void collect(HuXing huXing) {
		try {
			saveImage(huXing.outterImgLink,huXing.estateName,String.valueOf(huXing.rooms));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void saveImage(String src,String estateName,String group) throws IOException{
		if(estateName==null){
			estateName="";
		}
		URL url = new URL(src);
		URLConnection conn = url.openConnection();
		conn.setConnectTimeout(10000);
		conn.setReadTimeout(10000);
		String[] arr = src.split("/");
		String fileName = arr[arr.length-1];
		byte[] data = IOUtils.toByteArray(conn.getInputStream());
		group = Cn2SpellUtil.converterToSpell(group);
		estateName = Cn2SpellUtil.converterToSpell(estateName);
		FileUtils.openOutputStream(new File("/Users/xzye/tmp/huxing/"+estateName + "/" +group+"/"+fileName)).write(data);
	}
}
