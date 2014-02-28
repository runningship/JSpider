package org.ocean.spider.silk.home;

import org.ocean.spider.silk.BasicCollector;

public class BuildingTypeCollector extends BasicCollector{

	@Override
	protected String getAttrName() {
		return "buildingType";
	}

	@Override
	protected String getMatchString() {
		return "物业类别";
	}
}
