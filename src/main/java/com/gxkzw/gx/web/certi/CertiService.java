package com.gxkzw.gx.web.certi;

import java.util.ArrayList;
import java.util.List;

import com.gxkzw.gx.common.model.Certi;

public class CertiService {

	public static final CertiService me = new CertiService();
	private static Certi dao = new Certi().dao();
	
	public List<Certi> getIndexData(){
		List<Certi> certiList = findAll();
		List<Certi> rsList = new ArrayList<>();
		for(Certi c :certiList) {
			if(c.getPId().equals(-1)) {
				rsList.add(c);
			}
		}
		for(Certi pCerti :rsList) {
			List<Certi> childCertiList = new ArrayList<>();
			for(Certi cCerti :certiList) {
				if(cCerti.getPId() == pCerti.getId()) {
					childCertiList.add(cCerti);
				}
			}
			pCerti.put("childList", childCertiList);
		}
		return rsList;
	}
	
	public Certi findByIdWithExtraData(int id) {
		Certi certi = findById(id);
		List<String> levelList = new ArrayList<>();
		List<String> courseList = new ArrayList<>();
		if("教师资格证".equals(certi.getName())) {
			levelList.add("幼儿园");
			levelList.add("小学");
			levelList.add("初中");
			levelList.add("高中");
			courseList.add("语文");
			courseList.add("数学");
			courseList.add("英语");
			courseList.add("生物");
			courseList.add("化学");
			courseList.add("物理");
			courseList.add("历史");
		}
		certi.put("levelList",levelList);
		certi.put("courseList",courseList);
		return certi;
	}
	
	public List<Certi> findAll() {
		return dao.find("select `id`,`name`,`desc`,`pId` from certi where status=1");
	}
	
	public Certi findById(int id) {
		return dao.findById(id);
	}
}
