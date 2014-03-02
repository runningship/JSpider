package org.ocean.spider.silk.detail;

import org.ocean.spider.silk.BasicCollector;

public class BuildingStyleCollector extends BasicCollector{

	@Override
	protected String getAttrName() {
		return "buildingStyle";
	}

	@Override
	protected String getMatchString() {
		return "景观规划";
	}
}
