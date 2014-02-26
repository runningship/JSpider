package org.ocean.spider.silk.list;

import org.ocean.spider.silk.BasicCollector;

public class EstateTypeCollector extends BasicCollector{

	public EstateTypeCollector(String name) {
		super(name);
	}

	@Override
	public int[] getXPath() {
		return new int[]{1,0,1};
	}
}
