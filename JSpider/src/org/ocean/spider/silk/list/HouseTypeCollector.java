package org.ocean.spider.silk.list;

import org.jsoup.nodes.Element;
import org.ocean.spider.silk.BasicCollector;

public class HouseTypeCollector extends BasicCollector{

	public HouseTypeCollector(String name) {
		super(name);
	}

	@Override
	public int[] getXPath() {
		return new int[]{1,3};
	}
	
	@Override
	public String getValue(Element elem) {
		String text = elem.text();
		text = text.split("：")[1];
		return text;
	}
}
