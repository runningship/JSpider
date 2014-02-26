package org.ocean.spider.silk;

import org.jsoup.nodes.Element;

public class BasicCollector{

	protected String name;
	
	public BasicCollector(String name){
		this.name = name;
	}
	
	protected String getAttrName() {
		return name;
	}

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
