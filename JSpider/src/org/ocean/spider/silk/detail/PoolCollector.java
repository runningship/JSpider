package org.ocean.spider.silk.detail;

import org.jsoup.nodes.Element;
import org.ocean.spider.AException;
import org.ocean.spider.ExceptionType;
import org.ocean.spider.silk.BasicCollector;

public class PoolCollector extends BasicCollector{

	@Override
	protected String getAttrName() {
		return "pool";
	}
	
	protected String getValue(Element elem) {
		return elem.ownText();
	}

	@Override
	protected String getMatchString() {
		return "公摊";
	}
}
