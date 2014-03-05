package org.ocean.spider.jobs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.ocean.shibernate.CommonDaoService;
import org.ocean.shibernate.TransactionalServiceHelper;
import org.ocean.spider.Cn2SpellUtil;
import org.ocean.spider.DBUtil;
import org.ocean.spider.entity.LouPan;

public class GetPictureJob {

	CommonDaoService service = TransactionalServiceHelper.getTransactionalService(CommonDaoService.class);
	
	private List<String> pictures = new ArrayList<String>();
	
	public void collect(LouPan loupan){
		try{
			String urlStr = "http://newhouse.hfhouse.com/HouseAlbumShow/listHouseAlbumShow/childId/"+loupan.linkId+"/picId/";
//			String urlStr = "http://newhouse.hfhouse.com//HouseAlbumShow/listHouseAlbumShow/childId/16115/picId/5218#go";
			collectImage(urlStr , loupan.name);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private void collectImage(String urlStr , String estateName) throws IOException{
		if(pictures.contains(urlStr)){
			return;
		}
		URL url = new URL(urlStr);
		URLConnection conn = url.openConnection();
		conn.setConnectTimeout(10000);
		conn.setReadTimeout(30000);
		String result = "";
		try{
			result = IOUtils.toString(conn.getInputStream(),"utf-8");
		}catch(SocketException ex){
			ex.printStackTrace();
			return;
		}
		Document doc = Jsoup.parse(result);
		Element img  = doc.getElementById("bigPhoto");
		if(img==null){
			System.out.println("image not found at " + urlStr);
			return;
		}
		String src = img.attr("src");
		String group = "";
		String alt = img.attr("alt");
		if(alt!=null){
			group = alt.split("-")[0];
		}
		Element link = doc.getElementsByAttributeValue("title", "点击跳到下一张").first().parent();
		String next = "http://newhouse.hfhouse.com/" + link.attr("href");
		System.out.println("next is "+next);
		//save next
		try{
			saveImage(src,estateName,group);
		}catch(FileNotFoundException ex){
			System.err.println("no picture found at "+  urlStr);
		}
		pictures.add(urlStr);
		collectImage(next , estateName);
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
		FileUtils.openOutputStream(new File("/Users/xzye/tmp/picture/"+estateName + "/" +group+"/"+fileName)).write(data);
	}

	public static void main(String[] afef){
		DBUtil.init();
		GetPictureJob job = new GetPictureJob();
		List<LouPan> list = job.service.listByParams(LouPan.class, "from LouPan", null, null);
		for(int i=0;i<list.size();i++){
			if(i<755){
				continue;
			}
			System.out.println("-->"+i+","+list.get(i).name+","+list.get(i).linkId);
			job.collect(list.get(i));
			job.pictures.clear();
		}
	}
}
