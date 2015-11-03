package com.example.trip.entity;

public class Discuss {
	private int id;
	private String content;
	private int userId;
	private String time;
	private int shortCommentId;
	private String level;
	private String img;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getShortCommentId() {
		return shortCommentId;
	}

	public void setShortCommentId(int shortCommentId) {
		this.shortCommentId = shortCommentId;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Discuss(int id, String content, int userId, String time,
			int shortCommentId, String level, String img, String name) {
		super();
		this.id = id;
		this.content = content;
		this.userId = userId;
		this.time = time;
		this.shortCommentId = shortCommentId;
		this.level = level;
		this.img = img;
		this.name = name;
	}

	public Discuss() {
		super();
	}

}
