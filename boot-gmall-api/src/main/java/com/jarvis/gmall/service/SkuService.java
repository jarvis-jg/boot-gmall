package com.jarvis.gmall.service;

import com.jarvis.gmall.bean.PmsSkuInfo;

import java.util.List;

/**
 * boot-gmall : com.jarvis.gmall.service
 *
 * @author jarvis
 * @create 2020-03-28 23:27
 */
public interface SkuService {

    void saveSkuInfo(PmsSkuInfo pmsSkuInfo);

    PmsSkuInfo getSkuInfo(String sku_id);

    List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId);
}
