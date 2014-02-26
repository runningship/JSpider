package org.ocean.spider.silk.list;

import org.jsoup.nodes.Element;
import org.ocean.spider.silk.BasicCollector;

public class DetailLinkCollector extends BasicCollector{

	public DetailLinkCollector(String name) {
		super(name);
	}

	@Override
	public int[] getXPath() {
		return new int[]{1,0,0,1};
	}

	@Override
	public String getValue(Element elem) {
		return elem.attr("href");
	}

}
