package org.ocean.spider.silk.detail;

import org.ocean.spider.silk.BasicCollector;

public class InvestorCollector extends BasicCollector{

	@Override
	protected String getAttrName() {
		return "investor";
	}

	@Override
	protected String getMatchString() {
		return "投资商";
	}
}
