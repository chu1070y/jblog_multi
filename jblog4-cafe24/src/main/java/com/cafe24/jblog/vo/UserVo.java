package com.cafe24.jblog.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserVo {
	
	@NotEmpty
	@Length(min=2, max=8)
	private String name;
	
	@NotEmpty
	@Length(min=2, max=15)
	private String id;
	
	@NotEmpty
	@Length(min=2, max=10)
	private String password;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserVo [name=" + name + ", id=" + id + ", password=" + password + "]";
	}
	
}
