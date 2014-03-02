package org.ocean.spider.silk.detail;

import org.ocean.spider.silk.BasicCollector;

public class FabricCollector extends BasicCollector{

	@Override
	protected String getAttrName() {
		return "fabric";
	}

	@Override
	protected String getMatchString() {
		return "结构";
	}
}
