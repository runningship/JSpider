package org.ocean.spider.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LouPan {
	
	@Id
	public String uid;
	
	public String name;
	
	public String pinyin;
	
	//详细信息连接，数据抓取用
	public String link;
	//区域
	public String region;
	
	//楼盘类型,住宅，商业..
	public String estateType;
	
	//均价
	public String price;
	
	//均价更新时间
	public String priceUpdateTime;
	
	//户型
	public String houseType;
	
	public String address;
	
	public String street;
	
	public String phoneNumber;
	
	//标签,特色
	public String label;
	
	//销售状态
	public String state;
	
	//经度
	public long longitude;
	
	//纬度
	public long latitude;
	
	//--------------------------
	public String salesDeptAddress;
	
	public float floorHeight;
	
	public String developer;
	
	public String mainHouseType;
	
	//建筑类别，如高层，小高层，超高层
	public String buildingType;
	
	//房屋属性
	public String estateProp;
	
	//学区
	public String schoolDistrict;
	
	//产权
	public String interest;
	
	public String decoration;
	
	public String preSalePermit;
	
	//产证归属
	public String interestBelongs;
	
	//占地面积
	public String floorArea;
	
	public String payment;
	
	public String propertyCompany;
	
	public float propertyFee;
	
	//车位
	public String carport;
	
	public String traffic;
	
	//-------------------------
	public String investor;
	
	//开工时间
	public String startTime;
	
	//代理公司
	public String agent;
	
	public String totalArea;
	
	//公摊
	public String pool;
	
	//施工单位
	public String builder;
	
	//绿化率
	public float greeningRate;
	
	//容积率
	public float volumeFraction;
	
	//建筑风格
	public String buildingStyle;
	
	//结构
	public String fabric;
	
	public String wall;
	
	public String door;
	
	public String window;
	
	//public String other
	//------------------------
	public String heatSupply;
	
	public String gasSupply;
	
	public String waterSupply;
	
	public String elevator;
	
	public String deails;
	
	//---------------
	
	public String schools;
	
	public String markets;
	
	public String postOffices;
	
	public String banks;
	
	//幼儿园
	public String preSchools;
	
	public String hospitals;
	
	//其他配套
	public String otherSupports;
}
