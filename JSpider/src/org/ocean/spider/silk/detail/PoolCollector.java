package org.ocean.spider.silk.detail;

import org.ocean.spider.silk.BasicCollector;

public class PoolCollector extends BasicCollector{

	@Override
	protected String getAttrName() {
		return "pool";
	}

	@Override
	protected String getMatchString() {
		return "公摊";
	}
}
