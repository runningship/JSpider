package org.ocean.spider.silk.list;

import org.ocean.spider.silk.BasicCollector;

public class EstateTypeCollector extends BasicCollector{

	@Override
	public int[] getXPath() {
		return new int[]{1,0,1};
	}
	
	@Override
	protected String getAttrName() {
		return "estateType";
	}
}
