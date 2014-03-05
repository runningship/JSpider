package org.ocean.spider.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class HuXing {

	@Id
	public String uid;
	
	public String estateName;
	
	//一室，二室
	public int rooms;
	//户型
	public String style;
	
	//描述
	public String comment;
	
	public String outterImgLink;

	@Override
	public String toString() {
		return "HuXing [uid=" + uid + ", estateName=" + estateName + ", rooms="
				+ rooms + ", style=" + style + ", comment=" + comment
				+ ", outterImgLink=" + outterImgLink + "]";
	}
	
}
