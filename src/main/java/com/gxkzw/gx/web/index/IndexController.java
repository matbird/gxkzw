package com.gxkzw.gx.web.index;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.core.Controller;

public class IndexController extends Controller{

	public void index() {
		List<News> newsList = new ArrayList<>();
		newsList.add(new News("中国制冷学会科普教育基地（广西）落户我院", "05-02"));
		newsList.add(new News("“中国制冷学会高职院校科普基地运行服务研讨", "05-02"));
		newsList.add(new News("我院组织开展“全国青少年税收法治教育大课堂", "05-02"));
		newsList.add(new News("我院党委书记冯国忠在全区加强和改进高校思想", "05-02"));
		newsList.add(new News("学院举办“健康人生 绿色无毒”禁毒演讲比赛", "05-02"));
		newsList.add(new News("生态机电，绿色前行——机电师生共建校友林", "05-02"));
		newsList.add(new News("传承民族传统文化，领略机电壮乡风情", "05-02"));
		newsList.add(new News("自治区工信委直属院校组学习宣传贯彻党的十九", "05-02"));
		
		setAttr("newsList", newsList);
		render("index.html");
	}
	
	public void flex() {
		render("flex.html");
	}
	
	public class News {
		private String title;
		private String date;
		
		public News(String title, String date) {
			super();
			this.title = title;
			this.date = date;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		
	}
}
