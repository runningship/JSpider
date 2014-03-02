package org.ocean.spider.silk.detail;

import org.jsoup.nodes.Element;
import org.ocean.spider.silk.BasicCollector;

public class GreeningRateCollector extends BasicCollector{

	@Override
	protected String getAttrName() {
		return "greeningRate";
	}

	@Override
	protected String getMatchString() {
		return "绿化率";
	}

	@Override
	protected String getValue(Element elem) {
		String text = elem.children().get(0).ownText();
		if(text==null){
			return "";
		}
		return text.replace(" ", "");
	}
	
}
