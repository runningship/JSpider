package org.ocean.spider.silk.home.more;

import org.ocean.spider.silk.BasicCollector;

public class SchoolDistrictCollector extends BasicCollector{

	@Override
	protected String getMatchString() {
		return "学 区";
	}

	@Override
	protected String getAttrName() {
		return "schoolDistrict";
	}
}
