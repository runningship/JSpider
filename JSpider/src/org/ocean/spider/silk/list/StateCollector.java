package org.ocean.spider.silk.list;

import org.jsoup.nodes.Element;
import org.ocean.spider.silk.BasicCollector;

public class StateCollector extends BasicCollector{

	@Override
	public int[] getXPath() {
		return new int[]{1,0,0,1,0};
	}
	
	@Override
	public String getValue(Element elem) {
		String text = elem.text();
		text = text.replace("(", "");
		text = text.replace(")", "");
		return text;
	}
	
	@Override
	protected String getAttrName() {
		return "state";
	}
}
