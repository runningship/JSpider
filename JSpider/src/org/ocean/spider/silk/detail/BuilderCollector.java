package org.ocean.spider.silk.detail;

import org.ocean.spider.silk.BasicCollector;

public class BuilderCollector extends BasicCollector{

	@Override
	protected String getAttrName() {
		return "builder";
	}

	@Override
	protected String getMatchString() {
		return "施工单位";
	}
}
