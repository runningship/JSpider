package org.ocean.spider.silk.list;

import org.jsoup.nodes.Element;
import org.ocean.spider.silk.BasicCollector;

public class AddressCollector extends BasicCollector{

	public AddressCollector(String name) {
		super(name);
	}

	@Override
	public int[] getXPath() {
		return new int[]{1,2};
	}

	@Override
	public String getValue(Element elem) {
		String text = elem.text();
		text = text.split("：")[1];
		return text;
	}
}
