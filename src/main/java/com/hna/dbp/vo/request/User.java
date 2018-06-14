package com.hna.dbp.vo.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.hna.dbp.vo.group.Insert;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "名称不能为空", groups = { Insert.class })
	private String name;
	// @Size(max = 100, min = 1, message = "年龄超出范围", groups = { Insert.class })
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
