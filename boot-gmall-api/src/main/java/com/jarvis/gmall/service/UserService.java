package com.jarvis.gmall.service;

import com.jarvis.gmall.bean.UmsMember;
import com.jarvis.gmall.bean.UmsMemberReceiveAddress;

import java.util.List;

/**
 * boot-gmall : com.jarvis.gmall.user.service
 *
 * @author jarvis
 * @create 2020-03-18 21:17
 */
public interface UserService {

    List<UmsMember> getUmsMemberList();

    List<UmsMemberReceiveAddress> getAddressByMemberId(String memberId);
}
