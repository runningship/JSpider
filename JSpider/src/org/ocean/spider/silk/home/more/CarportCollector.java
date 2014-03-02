package org.ocean.spider.silk.home.more;

import org.ocean.spider.silk.BasicCollector;

public class CarportCollector extends BasicCollector{

	@Override
	protected String getMatchString() {
		return "车位详情";
	}

	@Override
	protected String getAttrName() {
		return "carport";
	}
}
