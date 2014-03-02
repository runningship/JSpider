package org.ocean.spider.silk.home.more;

import org.ocean.spider.silk.BasicCollector;

public class TrafficCollector extends BasicCollector{

	@Override
	protected String getMatchString() {
		return "交通状况";
	}

	@Override
	protected String getAttrName() {
		return "traffic";
	}
}
