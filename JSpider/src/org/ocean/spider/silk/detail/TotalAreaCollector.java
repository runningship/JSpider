package org.ocean.spider.silk.detail;

import org.ocean.spider.silk.BasicCollector;

public class TotalAreaCollector extends BasicCollector{

	@Override
	protected String getAttrName() {
		return "totalArea";
	}

	@Override
	protected String getMatchString() {
		return "总建筑面积";
	}
}
