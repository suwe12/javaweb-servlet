package com.suwe.demoservlet.entity;

import java.util.Date;

public class Movie {
	private Long id;
	private String title;       // 电影标题
	private Double price;       // 票价
	private Date releaseDate;   // 上映日期
	private String imageUrl;    // 海报图片URL
	private String description; // 电影描述
	private Integer duration;   // 时长（分钟）
	private String type;        // 电影类型
	private String director;    // 导演
	private String actors;      // 主要演员
	private Integer status;     // 状态：0-即将上映，1-正在热映，2-下架

	// 构造函数
	public Movie() {}

	// getter和setter方法
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }

	public Double getPrice() { return price; }
	public void setPrice(Double price) { this.price = price; }

	public Date getReleaseDate() { return releaseDate; }
	public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }

	public String getImageUrl() { return imageUrl; }
	public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	public Integer getDuration() { return duration; }
	public void setDuration(Integer duration) { this.duration = duration; }

	public String getType() { return type; }
	public void setType(String type) { this.type = type; }

	public String getDirector() { return director; }
	public void setDirector(String director) { this.director = director; }

	public String getActors() { return actors; }
	public void setActors(String actors) { this.actors = actors; }

	public Integer getStatus() { return status; }
	public void setStatus(Integer status) { this.status = status; }
}