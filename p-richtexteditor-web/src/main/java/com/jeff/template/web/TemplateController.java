package com.jeff.template.web;
import com.jeff.template.model.base.User;
import com.jeff.template.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;


@Controller
public class TemplateController {
    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET ,value = "/user/search")
    public List search() {
        List<User> userList = userService.queryUserList();
        return userList;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST ,value = "/insertUser")
    public String createUser() {
        User user = new User();
        user.setUserId(1111);
        user.setUserName("张三");
        user.setCreateDate(new Date());
        int i =  userService.insert(user);
        return "insertUser " + i;
    }


}
