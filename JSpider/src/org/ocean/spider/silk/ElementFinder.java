package org.ocean.spider.silk;

import org.jsoup.nodes.Element;

public class ElementFinder {

	public static Element findElementByMatchContent(Element root ,String text){
		if(text==null || "".equals(text)){
			return null;
		}
		Element target = root;
		if(target.ownText()!=null && target.ownText().startsWith(text)){
			return target;
		}
		for(Element elem : target.children()){
			Element result = findElementByMatchContent(elem,text);
			if(result!=null){
				return result;
			}
		}
		return null;
	}
}
