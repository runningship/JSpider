package org.ocean.spider;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class GetDetailInfoJob {

	public static void main(String[] args) throws IOException{
		URL url = new URL("");
		URLConnection conn = url.openConnection();
		String result = IOUtils.toString(conn.getInputStream(),"gbk");
		Document doc = Jsoup.parse(result);
		Elements loupanList = doc.getElementsByAttributeValue("class", "fList clearfix");
	}
}
