package org.ocean.spider.silk.home.more;

import org.ocean.spider.silk.BasicCollector;

public class DecorationCollector extends BasicCollector{

	@Override
	protected String getMatchString() {
		return "装修状况";
	}

	@Override
	protected String getAttrName() {
		return "decoration";
	}
}
