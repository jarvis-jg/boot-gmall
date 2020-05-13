package com.jarvis.gmall.user.mapper;


import com.jarvis.gmall.bean.UmsMember;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * boot-gmall : com.jarvis.gmall.user.mapper
 *
 * @author jarvis
 * @create 2020-03-18 21:17
 */
public interface UserMapper extends Mapper<UmsMember> {


    List<UmsMember> selectUmsMemberList();
}
