package com.jarvis.gmall.user.controller;

import com.jarvis.gmall.bean.UmsMember;
import com.jarvis.gmall.bean.UmsMemberReceiveAddress;
import com.jarvis.gmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * boot-gmall : com.jarvis.gmall.user.controller
 *
 * @author jarvis
 * @create 2020-03-18 21:16
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/index")
    public String index() {
        return "hello boot gmall!";
    }


    @GetMapping("/getUmsMember")
    public List<UmsMember> getUmsMember() {
        List<UmsMember> umsMemberList = userService.getUmsMemberList();
        return umsMemberList;
    }

    @GetMapping("/getAdresses")
    public List<UmsMemberReceiveAddress> getAdresses(String memberId) {
        List<UmsMemberReceiveAddress> addresses = userService.getAddressByMemberId(memberId);
        return addresses;
    }
}
