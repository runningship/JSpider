package org.ocean.spider.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LouPan {
	
	@Id
	public String uid;
	
	public String name;
	
	public int price;
	
	public String priceUpdateTiem;
	
	public String area;
	
	public String totalPrice;
	
	public String type;
	
	public String label;
	
	public String region;
	
	public String address;
	
	public String longitude;
	
	public String latitude;
	
	public String state;

	public String developers;
	
	public String openTime;
	
	public String deliveryTime;
	
	public String decoration;
	
	public float pooled;
	
	public float floorAreaRatio;
	
	public int totalHouse;
	
	//RMB/year
	public float propertyFee;
	
	public int carParkCount;
	
	public String schoolDistrict;
	
	public String phoneNumber;

	@Override
	public String toString() {
		return "LouPan [uid=" + uid + ", name=" + name + ", price=" + price
				+ ", priceUpdateTiem=" + priceUpdateTiem + ", area=" + area
				+ ", totalPrice=" + totalPrice + ", type=" + type + ", label="
				+ label + ", region=" + region + ", address=" + address
				+ ", longitude=" + longitude + ", latitude=" + latitude
				+ ", state=" + state + ", developers=" + developers
				+ ", openTime=" + openTime + ", deliveryTime=" + deliveryTime
				+ ", decoration=" + decoration + ", pooled=" + pooled
				+ ", floorAreaRatio=" + floorAreaRatio + ", totalHouse="
				+ totalHouse + ", propertyFee=" + propertyFee
				+ ", carParkCount=" + carParkCount + ", schoolDistrict="
				+ schoolDistrict + ", phoneNumber=" + phoneNumber + "]";
	}
	
}
