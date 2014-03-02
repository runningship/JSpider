package org.ocean.spider.silk.home.more;

import org.ocean.spider.silk.BasicCollector;

public class EstatePropCollector extends BasicCollector{

	@Override
	protected String getMatchString() {
		return "房屋属性";
	}

	@Override
	protected String getAttrName() {
		return "estateProp";
	}
}
