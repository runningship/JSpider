package org.ocean.spider.silk.home;

import org.ocean.spider.silk.BasicCollector;

public class AddressCollector extends BasicCollector{


	@Override
	protected String getMatchString() {
		return "项目位置";
	}

	@Override
	protected String getAttrName() {
		return "address";
	}
}
