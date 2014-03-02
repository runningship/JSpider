package org.ocean.spider.silk.home.more;

import org.ocean.spider.silk.BasicCollector;

public class InterestBelongsCollector extends BasicCollector{

	@Override
	protected String getMatchString() {
		return "产证归属";
	}

	@Override
	protected String getAttrName() {
		return "interestBelongs";
	}
}
