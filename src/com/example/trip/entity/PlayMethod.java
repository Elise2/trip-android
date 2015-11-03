package com.example.trip.entity;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import android.R.integer;

@DatabaseTable(tableName = "collectionarticle")
public class PlayMethod implements Serializable {
	@DatabaseField(id = true)
	int id;
	@DatabaseField
	String title;
	@DatabaseField
	String time;
	@DatabaseField
	int img;
	@DatabaseField
	String url;
	@DatabaseField
	String contnt;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getImg() {
		return img;
	}

	public void setImg(int img) {
		this.img = img;
	}

	public String getContnt() {
		return contnt;
	}

	public void setContnt(String contnt) {
		this.contnt = contnt;
	}

	public PlayMethod(String url, String title, int img, String contnt, int id) {
		super();
		this.url = url;
		this.title = title;
		this.img = img;
		this.contnt = contnt;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PlayMethod(String url, String title, int img, String contnt) {
		super();
		this.url = url;
		this.title = title;
		this.img = img;
		this.contnt = contnt;
	}

	public PlayMethod() {
		super();
	}

}
