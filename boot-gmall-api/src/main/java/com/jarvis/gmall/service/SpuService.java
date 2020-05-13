package com.jarvis.gmall.service;

import com.jarvis.gmall.bean.PmsProductImage;
import com.jarvis.gmall.bean.PmsProductInfo;
import com.jarvis.gmall.bean.PmsProductSaleAttr;

import java.util.List;

/**
 * boot-gmall : com.jarvis.gmall.service
 *
 * @author jarvis
 * @create 2020-03-28 11:04
 */
public interface SpuService {

    List<PmsProductInfo> spuList(String catalog3Id);

    void saveSpuInfo(PmsProductInfo pmsProductInfo);

    List<PmsProductSaleAttr> spuSaleAttrList(String spuId);

    List<PmsProductImage> spuImageList(String spuId);

    List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(String sku_id, String productId);

    String test();
}
