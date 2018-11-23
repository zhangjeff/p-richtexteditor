package com.jeff.template.userservice;

import com.jeff.template.BaseTest;
import com.jeff.template.api.IUserService;
import com.jeff.template.model.base.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.Date;

public class UserServiceTest extends BaseTest {

	@Autowired
	private IUserService userService;
	
	@Test
	public void userInsertTest() {
		User user = new User();
		user.setUserId(1111);
		user.setUserName("张三");
		user.setCreateDate(new Date());
		userService.insert(user);
	}
}
