package org.ocean.spider.silk.list;

import org.jsoup.nodes.Element;
import org.ocean.spider.silk.BasicCollector;

public class PhoneCollector extends BasicCollector{

	public PhoneCollector(String name) {
		super(name);
	}

	@Override
	public int[] getXPath() {
		return new int[]{1,1};
	}
	
	@Override
	public String getValue(Element elem) {
		//电 话：400-887-1216转5075
		String text = elem.text();
		text = text.split("：")[1];
		return text;
	}
}
