package com.wang.novelweb.Entity;



import java.io.Serializable;
import java.util.Date;
import lombok.Data;


@Data
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */

	private String id;
	/**
	 * 用户名
	 */
	private String name;
	/**
	 * 用户密码
	 */
	private String password;

}
