package org.ocean.spider.silk.detail;

import org.ocean.spider.silk.BasicCollector;

public class DoorCollector extends BasicCollector{

	@Override
	protected String getAttrName() {
		return "door";
	}

	@Override
	protected String getMatchString() {
		return "门";
	}
}
