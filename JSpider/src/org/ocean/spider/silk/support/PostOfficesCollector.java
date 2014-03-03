package org.ocean.spider.silk.support;

import org.jsoup.nodes.Element;
import org.ocean.spider.silk.BasicCollector;

public class PostOfficesCollector extends BasicCollector{

	@Override
	protected String getAttrName() {
		return "postOffices";
	}

	@Override
	protected int[] getXPath() {
		return new int[]{0,0,2,1};
	}

	protected String getValue(Element elem) {
		return elem.text();
	}
}
