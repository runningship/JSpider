package org.ocean.spider.silk;

import org.jsoup.nodes.Element;

public abstract class BasicCollector{

	protected abstract String getAttrName();

	protected int[] getXPath() {
		return new int[]{};
	}

	protected String getValue(Element elem) {
		if(elem!=null){
			return elem.ownText();
		}
		return "";
	}

	public String getAttrValue(Element root) {
		int[] arr = getXPath();
		Element target = root;
		for(int i=0;i<arr.length;i++){
			target = target.child(arr[i]);
		}
		return getValue(target);
	}

}
