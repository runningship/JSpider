package org.ocean.spider.silk.home.more;

import org.ocean.spider.silk.BasicCollector;

public class InterestCollector extends BasicCollector{

	@Override
	protected String getMatchString() {
		return "产 权";
	}

	@Override
	protected String getAttrName() {
		return "interest";
	}
}
