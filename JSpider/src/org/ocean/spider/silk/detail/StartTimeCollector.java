package org.ocean.spider.silk.detail;

import org.ocean.spider.silk.BasicCollector;

public class StartTimeCollector extends BasicCollector{

	@Override
	protected String getAttrName() {
		return "startTime";
	}

	@Override
	protected String getMatchString() {
		return "开工时间";
	}
}
