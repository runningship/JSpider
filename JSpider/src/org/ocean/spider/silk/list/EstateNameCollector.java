package org.ocean.spider.silk.list;

import org.ocean.spider.silk.BasicCollector;


public class EstateNameCollector extends BasicCollector{

	public EstateNameCollector(String name) {
		super(name);
	}

	protected int[] getXPath() {
		return new int[]{1,0,0,1};
	}

}
