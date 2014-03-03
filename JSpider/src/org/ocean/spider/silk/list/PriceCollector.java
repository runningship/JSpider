package org.ocean.spider.silk.list;

import org.jsoup.nodes.Element;
import org.ocean.spider.silk.BasicCollector;

public class PriceCollector extends BasicCollector{

	@Override
	public int[] getXPath() {
		return new int[]{2,0};
	}
	
	@Override
	protected String getAttrName() {
		return "price";
	}
	
	protected String getValue(Element elem) {
		return elem.ownText();
	}
}
