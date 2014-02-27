package org.ocean.spider.silk;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.ocean.shibernate.utils.ClassUtil;
import org.ocean.spider.AException;
import org.ocean.spider.ExceptionType;
import org.ocean.spider.entity.LouPan;

public class CollectorManager {

	private List<BasicCollector> collectors = new ArrayList<BasicCollector>();
	
	public void addCollector(BasicCollector collector){
		collectors.add(collector);
	}
	
	public void addAllDefaultCollectors(){
		List<Class<?>> clazzList = ClassUtil.getClasssFromPackage("org.ocean.spider.silk.list");
		if(clazzList == null || clazzList.size()==0){
			System.out.println("no collector found in org.ocean.spider.silk.list");
			return;
		}
		for(Class<?> clazz : clazzList){
			try {
				BasicCollector collector = (BasicCollector) clazz.newInstance();
				collectors.add(collector);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void collect(Element root ,LouPan loupan){
		for(BasicCollector collector : collectors){
			String value = "";
			try{
				value = collector.getAttrValue(root);
			}catch(AException ex){
				if(ex.getType() == ExceptionType.ValueNotFound){
					continue;
				}
				throw ex;
			}catch(Exception ex){
				throw new RuntimeException("collector "+collector.getAttrName()+" faild on root="+root);
			}
			try {
				Field field = loupan.getClass().getDeclaredField(collector.getAttrName());
				if(field.getType()==Float.class){
					field.set(loupan, Float.valueOf(value));
					continue;
				}
				if(field.getType()==Long.class){
					field.set(loupan, Long.valueOf(value));
					continue;
				}
				field.set(loupan, value);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
