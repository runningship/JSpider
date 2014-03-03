package org.ocean.spider.entity;

import java.lang.reflect.Field;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LouPan {
	
	@Id
	public String uid;
	
	public String name;
	
	public String pinyin;
	
	//详细信息连接，数据抓取用
	public String linkId;
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
	public float longitude;
	
	//纬度
	public float latitude;
	
	//--------------------------
	public String salesDeptAddress;
	
	//层高
	public String floorHeight;
	
	//开发商
	public String developer;
	
	//建筑类别，如高层，小高层，超高层
	public String buildingType;
	
	//房屋属性
	public String estateProp;
	
	//学区
	public String schoolDistrict;
	
	//产权
	public String interest;
	
	public String decoration;
	
	//预售许可证
	public String preSalePermit;
	
	//产证归属
	public String interestBelongs;
	
	//占地面积
	public String floorArea;
	
	//付款方式
	public String payment;
	
	//物业公司
	public String propertyCompany;
	
	//物业费
	public String propertyFee;
	
	//车位
	public String carport;
	
	//交通状况
	public String traffic;
	
	//-------------------------
	//投资商
	public String investor;
	
	//开工时间
	public String startTime;
	
	//代理公司
	public String agent;
	
	//总建筑面积
	public String totalArea;
	
	//公摊
	public String pool;
	
	//施工单位
	public String builder;
	
	//绿化率
	public String greeningRate;
	
	//容积率
	public float volumeFraction;
	
	//建筑风格
	public String buildingStyle;
	
	//结构
	public String fabric;
	
	public String wall;
	
	public String door;
	
	public String window;
	
	//------------------------
	//物业信息
//	public String heatSupply;
//	
//	public String gasSupply;
//	
//	public String waterSupply;
//	
//	public String elevator;
//	
//	public String deails;
	
	//---------------
	
	//学校
	public String schools;
	
	//商场
	public String markets;
	
	//邮局
	public String postOffices;
	
	public String banks;
	
	//幼儿园
	public String preSchools;
	
	public String hospitals;
	
	//其他配套
	public String otherSupports;

	public String updateTime;
	
	public String lastError;
	
	public boolean forceUpdate;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Field f : this.getClass().getDeclaredFields()){
			try {
				Object value = f.get(this);
				if(value==null){
					continue;
				}
				sb.append(f.getName()+"="+value+",");
			} catch (IllegalArgumentException e) {
				//will never happen
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	
}
