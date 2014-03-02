package org.ocean.spider.silk.detail;

import org.jsoup.nodes.Element;
import org.ocean.spider.silk.BasicCollector;

public class VolumeFractionCollector extends BasicCollector{

	@Override
	protected String getAttrName() {
		return "volumeFraction";
	}

	@Override
	protected String getMatchString() {
		return "容积率";
	}
	
	@Override
	protected String getValue(Element elem) {
		String text = elem.children().get(0).ownText();
		return text;
	}
}
