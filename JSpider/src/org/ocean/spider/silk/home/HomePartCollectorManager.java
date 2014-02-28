package org.ocean.spider.silk.home;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.ocean.shibernate.utils.ClassUtil;
import org.ocean.spider.silk.BasicCollector;
import org.ocean.spider.silk.CollectorManager;

public class HomePartCollectorManager extends CollectorManager{

	@Override
	public void addAllDefaultCollectors() {
		List<Class<?>> clazzList = ClassUtil.getClasssFromPackage(this.getClass().getPackage().getName());
		if(clazzList == null || clazzList.size()==0){
			System.out.println("no collector found in org.ocean.spider.silk.list");
			return;
		}
		for(Class<?> clazz : clazzList){
			try {
				Object collector =  clazz.newInstance();
				if(collector instanceof BasicCollector){
					collectors.add((BasicCollector)collector);
				}
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Element getRootElement(Document doc){
		Elements list = doc.getElementsByAttributeValue("class", "right");
		return list.get(0);
	}

}
