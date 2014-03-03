package org.ocean.spider.silk.list;

import org.jsoup.nodes.Element;
import org.ocean.spider.AException;
import org.ocean.spider.ExceptionType;
import org.ocean.spider.silk.BasicCollector;


public class EstateNameCollector extends BasicCollector{

	protected int[] getXPath() {
		return new int[]{1,0,0,1};
	}

	@Override
	protected String getAttrName() {
		return "name";
	}
	
	protected String getValue(Element elem) {
		return elem.ownText();
	}
}
