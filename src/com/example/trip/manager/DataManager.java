package com.example.trip.manager;

import java.util.ArrayList;
import java.util.List;

import com.example.trip.R;
import com.example.trip.entity.Infomation;
import com.example.trip.entity.PlayMethod;
import com.example.trip.entity.Type;

public class DataManager {
	private List<Infomation> infos;

	public DataManager() {
		super();
		infos = new ArrayList<Infomation>();
		infos.add(new Infomation(1, "第一条信息", "这是第一条信息内容", 1));
		infos.add(new Infomation(2, "第二条信息", "这是第二条信息内容", 2));
		infos.add(new Infomation(3, "第三条信息", "这是第三条信息内容", 3));
	}

	public static List<Type> iniTypes() {
		List<Type> radioList = new ArrayList<Type>();
		radioList.add(new Type(1, "通知"));
		radioList.add(new Type(2, "广播"));
		radioList.add(new Type(3, "私信"));
		return radioList;
	}

	public static List<PlayMethod> initMethods() {
		List<PlayMethod> playMethods = new ArrayList<PlayMethod>();
		PlayMethod method = new PlayMethod(
				"http://m.mafengwo.cn/nb/h5/topic.php?id=788",
				"卡瓦伦大桥蹦个级",
				R.drawable.playmethod_beiji,
				"要说蹦极哪都有，但到发源地来纵身一跳是不是更有意义！位于新西兰皇后镇的卡瓦伦大桥可是蹦极运动的诞生地，最特别的是这里还有入水式的蹦极体验等着你",
				1);
		playMethods.add(method);
		method = new PlayMethod(
				"http://m.mafengwo.cn/nb/h5/topic.php?id=836",
				"以山为被，以海为席",
				R.drawable.playmethod_panyan,
				"飞檐走壁，攀登上岩壁之巅，俯瞰碧海，仰望蓝天，坐拥阳光与海滩，那怎么能错过泰国甲米的莱利海滩？这里得天独厚的喀斯特地貌，风景如画的安达曼海滩",
				2);
		playMethods.add(method);
		method = new PlayMethod(
				"http://m.mafengwo.cn/nb/h5/topic.php?id=828",
				"坠入深渊的极限体验",
				R.drawable.playmethod_sea,
				"如果想尝试深度潜水，却没有真正潜入海洋与各类鲨鱼共舞的胆量，“Nemo33”一定是最适合你的绝好去处！这座位于比利时布鲁塞尔的深海游泳池深达33米",
				3);
		playMethods.add(method);
		method = new PlayMethod(
				"http://m.mafengwo.cn/nb/h5/topic.php?id=833",
				"为你写一封寄至天空的情书",
				R.drawable.playmethod_light,
				"还记得电影《那些年》里，柯景腾与沈佳宜一起放飞天灯的画面吗？正是取景与台湾平溪铁路的“十分站”，这里是全台最热门的天灯放飞地点",
				4);
		playMethods.add(method);
		return playMethods;
	}

	public List<Infomation> getNewsById(int newTyId) {
		List<Infomation> tmp = new ArrayList<Infomation>();
		for (Infomation n : infos) {
			if (n.getTypeId() == newTyId) {
				tmp.add(n);
			}
		}
		return tmp;
	}
}
