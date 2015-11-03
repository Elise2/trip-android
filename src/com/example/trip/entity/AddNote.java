package com.example.trip.entity;

import android.graphics.Bitmap;

public class AddNote {
	private String img;
	private String content;
	private Bitmap bitmap;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public AddNote() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddNote(String img, String content, Bitmap bitmap) {
		super();
		this.img = img;
		this.content = content;
		this.bitmap = bitmap;
	}

}
