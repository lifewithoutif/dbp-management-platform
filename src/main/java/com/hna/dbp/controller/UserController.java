package com.hna.dbp.controller;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hna.dbp.vo.response.RequestResult;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * swagger api 用法
 * 
 * @author jlagn
 *
 */
@RestController
public class UserController extends AbsController {

	// 创建线程安全的Map
	static Map<Integer, User> users = Collections.synchronizedMap(new HashMap<Integer, User>());

	/**
	 * 
	 * @Api：修饰整个类，描述Controller的作用
	 * @ApiOperation：描述一个类的一个方法，或者说一个接口
	 * @ApiParam：单个参数描述
	 * @ApiModel：用对象来接收参数
	 * @ApiProperty：用对象接收参数时，描述对象的一个字段
	 * @ApiResponse：HTTP响应其中1个描述
	 * @ApiResponses：HTTP响应整体描述
	 * @ApiIgnore：使用该注解忽略这个API
	 * @ApiError ：发生错误返回的信息
	 * @ApiImplicitParam：一个请求参数
	 * @ApiImplicitParams：多个请求参数
	 */

	/**
	 * 根据ID查询用户
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
	@RequestMapping(value = "user/{id}", method = RequestMethod.GET)
	public ModelAndView getUserById(@PathVariable(value = "id") Integer id) {
		RequestResult requestResult = null;
		try {
			User user = users.get(id);
			requestResult = new RequestResult(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return createModelAndView(requestResult);
	}
}

class User {

	private int id;
	private String username;
	private int age;
	private Date ctm;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getCtm() {
		return ctm;
	}

	public void setCtm(Date ctm) {
		this.ctm = ctm;
	}

}