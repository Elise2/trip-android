package com.example.trip.entity;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "hunter")
public class Hunter implements Serializable {
	private int hunter_id;
	@DatabaseField
	private String hunter_img;
	@DatabaseField
	private String hunter_descript;
	@DatabaseField
	private int like_num;
	@DatabaseField
	private int hunter_price;
	@DatabaseField
	private int hunterdetail_id;
	@DatabaseField
	private int huntermain_id;
	@DatabaseField
	private String hunterdetail_content;
	@DatabaseField
	private String hunterdetail_img;
	@DatabaseField
	private String hunter_sex;
	@DatabaseField
	private String hunter_tel;
	@DatabaseField
	private String hunter_detatils_info;// 个人介绍
	@DatabaseField
	private String hunter_identity_card;
	@DatabaseField
	private String hunter_title_img;
	private String hunter_label;
	private String hunter_location;
	private String hunter_name;
	private String hunter_time;
	public String getHunter_time() {
		return hunter_time;
	}

	public void setHunter_time(String hunter_time) {
		this.hunter_time = hunter_time;
	}

	public String getHunter_name() {
		return hunter_name;
	}

	public void setHunter_name(String hunter_name) {
		this.hunter_name = hunter_name;
	}

	public String getHunter_label() {
		return hunter_label;
	}

	public void setHunter_label(String hunter_label) {
		this.hunter_label = hunter_label;
	}

	public String getHunter_location() {
		return hunter_location;
	}

	public void setHunter_location(String hunter_location) {
		this.hunter_location = hunter_location;
	}

	public String getHunter_title_img() {
		return hunter_title_img;
	}

	public void setHunter_title_img(String hunter_title_img) {
		this.hunter_title_img = hunter_title_img;
	}

	public int getHunterdetail_id() {
		return hunterdetail_id;
	}

	public void setHunterdetail_id(int hunterdetail_id) {
		this.hunterdetail_id = hunterdetail_id;
	}

	public int getHuntermain_id() {
		return huntermain_id;
	}

	public void setHuntermain_id(int huntermain_id) {
		this.huntermain_id = huntermain_id;
	}

	public String getHunterdetail_content() {
		return hunterdetail_content;
	}

	public void setHunterdetail_content(String hunterdetail_content) {
		this.hunterdetail_content = hunterdetail_content;
	}

	public String getHunterdetail_img() {
		return hunterdetail_img;
	}

	public void setHunterdetail_img(String hunterdetail_img) {
		this.hunterdetail_img = hunterdetail_img;
	}

	public String getHunter_sex() {
		return hunter_sex;
	}

	public void setHunter_sex(String hunter_sex) {
		this.hunter_sex = hunter_sex;
	}

	public String getHunter_tel() {
		return hunter_tel;
	}

	public void setHunter_tel(String hunter_tel) {
		this.hunter_tel = hunter_tel;
	}

	public String getHunter_detatils_info() {
		return hunter_detatils_info;
	}

	public void setHunter_detatils_info(String hunter_detatils_info) {
		this.hunter_detatils_info = hunter_detatils_info;
	}

	public String getHunter_identity_card() {
		return hunter_identity_card;
	}

	public void setHunter_identity_card(String hunter_identity_card) {
		this.hunter_identity_card = hunter_identity_card;
	}

	public int getHunter_price() {
		return hunter_price;
	}

	public void setHunter_price(int hunter_price) {
		this.hunter_price = hunter_price;
	}

	public int getHunter_id() {
		return hunter_id;
	}

	public void setHunter_id(int hunter_id) {
		this.hunter_id = hunter_id;
	}

	public String getHunter_img() {
		return hunter_img;
	}

	public void setHunter_img(String hunter_img) {
		this.hunter_img = hunter_img;
	}

	public String getHunter_descript() {
		return hunter_descript;
	}

	public void setHunter_descript(String hunter_descript) {
		this.hunter_descript = hunter_descript;
	}

	public int getLike_num() {
		return like_num;
	}

	public void setLike_num(int like_num) {
		this.like_num = like_num;
	}

}
