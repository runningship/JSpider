package org.ocean.spider.silk.home.more;

import org.ocean.spider.silk.BasicCollector;

public class PropertyCompanyCollector extends BasicCollector{

	@Override
	protected String getMatchString() {
		return "物业公司";
	}

	@Override
	protected String getAttrName() {
		return "propertyCompany";
	}
}
