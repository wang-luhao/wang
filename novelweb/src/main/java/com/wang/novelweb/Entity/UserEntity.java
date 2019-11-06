package com.wang.novelweb.Entity;




import lombok.Data;


@Data
public class UserEntity  {

	/**
	 * 用户id
	 */

	private int id;
	/**
	 * 用户名
	 */
	private String name;
	/**
	 * 用户密码
	 */
	private String password;

	/**
	 * 授权
	 */

}
