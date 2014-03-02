package org.ocean.spider.silk;

import org.jsoup.nodes.Element;
import org.ocean.spider.AException;
import org.ocean.spider.ExceptionType;

public abstract class BasicCollector{

	protected abstract String getAttrName();

	protected int[] getXPath() {
		return new int[]{};
	}

	protected String getValue(Element elem) {
		String text = elem.text();
		String[] arr = text.split("：");
		if(arr.length<2){
			throw new AException(ExceptionType.ValueNotFound,"");
		}
		text = text.split("：")[1];
		return text;
	}

	protected String getMatchString(){
		return "";
	}
	public String getAttrValue(Element root) {
//		int[] arr = getXPath();
//		Element target = root;
//		for(int i=0;i<arr.length;i++){
//			target = target.child(arr[i]);
//		}
//		if(StringUtil.isBlank(getMatchString())){
//			throw new AException(ExceptionType.ParameterMiss,"match string not setted for "+ this.getClass().getSimpleName());
//		}
		Element target = ElementFinder.findElementByMatchContent(root, getMatchString());
		if(target == null){
			int[] arr = getXPath();
			if(arr!=null && arr.length>0){
				target = root;
				for(int i=0;i<arr.length;i++){
					target = target.child(arr[i]);
				}
			}
		}
		if(target == null){
			throw new AException(ExceptionType.ValueNotFound,"");
		}
		return getValue(target);
	}

}
