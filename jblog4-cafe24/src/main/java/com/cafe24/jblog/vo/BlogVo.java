package com.cafe24.jblog.vo;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class BlogVo {
	
	private String id;
	
	@NotEmpty
	private String title;
	private String logo;
	
	private MultipartFile multipartFile;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

	@Override
	public String toString() {
		return "BlogVo [id=" + id + ", title=" + title + ", logo=" + logo + ", multipartFile=" + multipartFile + "]";
	}
	
	
	
}
