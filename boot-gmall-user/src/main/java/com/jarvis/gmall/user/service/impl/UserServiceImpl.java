package com.jarvis.gmall.user.service.impl;

import com.jarvis.gmall.bean.UmsMember;
import com.jarvis.gmall.bean.UmsMemberReceiveAddress;
import com.jarvis.gmall.service.UserService;
import com.jarvis.gmall.user.mapper.AddressMapper;
import com.jarvis.gmall.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * boot-gmall : com.jarvis.gmall.user.service.impl
 *
 * @author jarvis
 * @create 2020-03-18 21:17
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AddressMapper addressMapper;

    @Override
    public List<UmsMember> getUmsMemberList() {
        return userMapper.selectUmsMemberList();
    }

    @Override
    public List<UmsMemberReceiveAddress> getAddressByMemberId(String memberId) {

        UmsMemberReceiveAddress umsMemberReceiveAddress = new UmsMemberReceiveAddress();
        umsMemberReceiveAddress.setMemberId(memberId);

        return addressMapper.select(umsMemberReceiveAddress);
    }
}
