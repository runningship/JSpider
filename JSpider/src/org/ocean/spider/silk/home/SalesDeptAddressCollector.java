package org.ocean.spider.silk.home;

import org.ocean.spider.silk.BasicCollector;

public class SalesDeptAddressCollector extends BasicCollector{

	@Override
	protected String getMatchString() {
		return "售 楼 部";
	}

	@Override
	protected String getAttrName() {
		return "salesDeptAddress";
	}
}
