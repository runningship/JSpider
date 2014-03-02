package org.ocean.spider.silk.home.more;

import org.ocean.spider.silk.BasicCollector;

public class PaymentCollector extends BasicCollector{

	@Override
	protected String getMatchString() {
		return "付款方式";
	}

	@Override
	protected String getAttrName() {
		return "payment";
	}
}
