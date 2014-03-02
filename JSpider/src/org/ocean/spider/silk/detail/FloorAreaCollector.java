package org.ocean.spider.silk.detail;

import org.ocean.spider.silk.BasicCollector;

public class FloorAreaCollector extends BasicCollector{

	@Override
	protected String getAttrName() {
		return "floorArea";
	}

	@Override
	protected String getMatchString() {
		return "占地面积";
	}
}
