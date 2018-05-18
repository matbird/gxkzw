package com.gxkzw.gx.web.certi;

import java.util.List;

import com.gxkzw.gx.common.model.Price;

public class PriceService {

	public static final PriceService me = new PriceService();
	private static Price dao = new Price().dao();
	
	public List<Price> findByCertiId(int certiId){
		return dao.find("select * from `price` where certiId=?", certiId);
	}
	
	public List<Price> findKzTypeByCertiId(int certiId){
		return dao.find("select kzType from price where certiId=? and kzType!=0 group by kzType", certiId);
	}
	
	public List<Price> findTrainTypeByCertiId(int certiId){
		return dao.find("select trainType from price where certiId=? and trainType!=0 group by trainType", certiId);
	}
	
	public Price findByKzAndTrainType(int certiId,int kzType,int trainType) {
		return dao.findFirst("select * from price where certiId=? and kzType=? and trainType=?", certiId,kzType,trainType);
	}
}
