package org.ocean.spider.silk.list;

import org.ocean.spider.silk.BasicCollector;

public class PriceCollector extends BasicCollector{

	public PriceCollector(String name) {
		super(name);
	}

	@Override
	public int[] getXPath() {
		return new int[]{2,0};
	}
}
