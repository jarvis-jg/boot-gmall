package com.jarvis.gmall.manager.mapper;

import com.jarvis.gmall.bean.PmsProductInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

/**
 * boot-gmall : com.jarvis.gmall.manager.mapper
 *
 * @author jarvis
 * @create 2020-03-28 11:06
 */
public interface PmsProductInfoMapper extends Mapper<PmsProductInfo> {

    Map<String, Object> selectNull();
}
