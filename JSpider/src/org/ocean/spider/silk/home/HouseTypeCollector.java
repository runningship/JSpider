package org.ocean.spider.silk.home;

import org.jsoup.nodes.Element;
import org.ocean.spider.AException;
import org.ocean.spider.ExceptionType;
import org.ocean.spider.silk.BasicCollector;

public class HouseTypeCollector extends BasicCollector{

	@Override
	public int[] getXPath() {
		return new int[]{1,3};
	}
	
	@Override
	public String getValue(Element elem) {
		String text = elem.text();
		String[] arr = text.split("：");
		if(arr.length<2){
			throw new AException(ExceptionType.ValueNotFound,"");
		}
		text = text.split("：")[1];
		return text;
	}
	
	@Override
	protected String getAttrName() {
		return "houseType";
	}
}