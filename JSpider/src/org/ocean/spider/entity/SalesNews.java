package org.ocean.spider.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SalesNews {

	@Id
	public String uid;
	
	public String estateUid;
	
	public String text;
	
	public String updateTime;
}
