package org.ocean.spider.silk.home.more;

import org.ocean.spider.silk.BasicCollector;

public class FloorHeightCollector extends BasicCollector{

	@Override
	protected String getMatchString() {
		return "单户层高";
	}

	@Override
	protected String getAttrName() {
		return "floorHeight";
	}
}
