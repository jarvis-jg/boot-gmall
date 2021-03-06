package com.jarvis.gmall.manager.mapper;

import com.jarvis.gmall.bean.PmsProductSaleAttr;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * boot-gmall : com.jarvis.gmall.manager.mapper
 *
 * @author jarvis
 * @create 2020-03-28 22:33
 */
public interface PmsProductSaleAttrMapper extends Mapper<PmsProductSaleAttr> {

    List<PmsProductSaleAttr> selectSpuSaleAttrListCheckBySku(HashMap<Object, Object> map);

}
