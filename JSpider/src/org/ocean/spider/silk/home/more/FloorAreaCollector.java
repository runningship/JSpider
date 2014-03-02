package org.ocean.spider.silk.home.more;

import org.ocean.spider.silk.BasicCollector;

public class FloorAreaCollector extends BasicCollector{

	@Override
	protected String getMatchString() {
		return "占地面积";
	}

	@Override
	protected String getAttrName() {
		return "floorArea";
	}
}
