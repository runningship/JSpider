package org.ocean.spider.silk.list;

import org.jsoup.nodes.Element;
import org.ocean.spider.silk.BasicCollector;

public class RegionCollector extends BasicCollector{

	public RegionCollector(String name) {
		super(name);
	}

	@Override
	public int[] getXPath() {
		return new int[]{1,0,0,0};
	}

	@Override
	public String getValue(Element elem) {
		String text = elem.text();
		text = text.replace("[", "");
		text = text.replace("]", "");
		return text;
	}
}
