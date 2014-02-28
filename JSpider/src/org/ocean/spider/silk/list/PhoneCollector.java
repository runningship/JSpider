package org.ocean.spider.silk.list;

import org.ocean.spider.silk.BasicCollector;

public class PhoneCollector extends BasicCollector{

	@Override
	public int[] getXPath() {
		return new int[]{1,1};
	}
	
	@Override
	protected String getMatchString() {
		return "电 话";
	}
	
	@Override
	protected String getAttrName() {
		return "phoneNumber";
	}
}
