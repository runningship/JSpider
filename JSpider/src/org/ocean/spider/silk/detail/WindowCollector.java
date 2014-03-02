package org.ocean.spider.silk.detail;

import org.ocean.spider.silk.BasicCollector;

public class WindowCollector extends BasicCollector{

	@Override
	protected String getAttrName() {
		return "window";
	}

	@Override
	protected String getMatchString() {
		return "窗";
	}
}
