package org.ocean.spider.silk.support;

import org.jsoup.nodes.Element;
import org.ocean.spider.silk.BasicCollector;

public class SchoolsCollector extends BasicCollector{

	@Override
	protected String getAttrName() {
		return "schools";
	}

	@Override
	protected int[] getXPath() {
		return new int[]{0,0,0,1};
	}

	protected String getValue(Element elem) {
		return elem.text();
	}
}
