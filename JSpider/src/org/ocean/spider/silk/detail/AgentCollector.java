package org.ocean.spider.silk.detail;

import org.ocean.spider.silk.BasicCollector;

public class AgentCollector extends BasicCollector{

	@Override
	protected String getAttrName() {
		return "agent";
	}

	@Override
	protected String getMatchString() {
		return "代理公司";
	}
}
