package org.ocean.spider.silk.detail;

import org.ocean.spider.silk.BasicCollector;

public class WallCollector extends BasicCollector{

	@Override
	protected String getAttrName() {
		return "wall";
	}

	@Override
	protected String getMatchString() {
		return "外墙";
	}
}
