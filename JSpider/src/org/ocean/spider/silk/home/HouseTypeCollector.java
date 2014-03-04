package org.ocean.spider.silk.home;

import org.ocean.spider.silk.BasicCollector;

public class HouseTypeCollector extends BasicCollector{

	@Override
	protected String getMatchString() {
		return "主力户型";
	}
	
	@Override
	protected String getAttrName() {
		return "houseType";
	}
}
