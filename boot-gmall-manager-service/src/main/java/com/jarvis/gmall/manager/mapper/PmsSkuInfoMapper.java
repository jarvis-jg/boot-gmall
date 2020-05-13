package com.jarvis.gmall.manager.mapper;

import com.jarvis.gmall.bean.PmsSkuInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * boot-gmall : com.jarvis.gmall.manager.mapper
 *
 * @author jarvis
 * @create 2020-03-28 23:28
 */
public interface PmsSkuInfoMapper extends Mapper<PmsSkuInfo> {

    List<PmsSkuInfo> selectSkuSaleAttrValueListBySpu(String productId);

}
