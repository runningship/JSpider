package org.ocean.spider.silk.home;

import org.ocean.spider.silk.BasicCollector;

public class DeveloperCollector extends BasicCollector{

	@Override
	protected String getAttrName() {
		return "developer";
	}

	@Override
	protected String getMatchString() {
		return "开 发 商";
	}
}
