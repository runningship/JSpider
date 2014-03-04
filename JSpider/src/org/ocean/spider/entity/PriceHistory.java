package org.ocean.spider.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PriceHistory {

	@Id
	public String uid;
	
	//楼盘uid
	public String estateName;
	
	public String price;
	
	public String updateTime;
	
	//楼盘类型,同一楼盘不同类型的价格不一样
	public String estateType;

	@Override
	public String toString() {
		return "PriceHistory [uid=" + uid + ", estateName=" + estateName
				+ ", price=" + price + ", updateTime=" + updateTime
				+ ", estateType=" + estateType + "]";
	}
	
}
