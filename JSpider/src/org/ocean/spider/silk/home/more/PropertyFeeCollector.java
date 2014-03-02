package org.ocean.spider.silk.home.more;

import org.ocean.spider.silk.BasicCollector;

public class PropertyFeeCollector extends BasicCollector{

	@Override
	protected String getMatchString() {
		return "物业费";
	}

	@Override
	protected String getAttrName() {
		return "propertyFee";
	}
}
