package org.ocean.spider.silk.list;

import org.ocean.spider.silk.BasicCollector;


public class EstateNameCollector extends BasicCollector{

	protected int[] getXPath() {
		return new int[]{1,0,0,1};
	}

	@Override
	protected String getAttrName() {
		return "name";
	}
}
