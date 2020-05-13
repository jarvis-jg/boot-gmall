package com.jarvis.gmall.item.controller;

import com.alibaba.fastjson.JSON;
import com.jarvis.gmall.bean.PmsProductSaleAttr;
import com.jarvis.gmall.bean.PmsSkuInfo;
import com.jarvis.gmall.bean.PmsSkuSaleAttrValue;
import com.jarvis.gmall.service.SkuService;
import com.jarvis.gmall.service.SpuService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * boot-gmall : com.jarvis.gmall.item.controller
 *
 * @author jarvis
 * @create 2020-03-30 16:33
 */
@Controller
public class ItemController {

    @Reference
    SkuService skuService;

    @Reference
    SpuService spuService;

    @GetMapping("{sku_id}.html")
    public String getSkuInfo(@PathVariable(name = "sku_id") String sku_id, ModelMap modelMap) {

        System.out.println("进入controller");

        PmsSkuInfo pmsSkuInfo = skuService.getSkuInfo(sku_id);

        //销售属性列表
        List<PmsProductSaleAttr> spuSaleAttrList = spuService.spuSaleAttrListCheckBySku(sku_id,pmsSkuInfo.getProductId());

        //制作hash表
        List<PmsSkuInfo> pmsSkuInfos = skuService.getSkuSaleAttrValueListBySpu(pmsSkuInfo.getProductId());

        HashMap<String, String> skuSaleAttrHash = new HashMap<>();
        for (PmsSkuInfo skuInfo : pmsSkuInfos) {
            String key = "";
            String skuId = skuInfo.getId();

            List<PmsSkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
            for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
                String saleAttrValueId = pmsSkuSaleAttrValue.getSaleAttrValueId();
                key += saleAttrValueId + "|";
            }
            skuSaleAttrHash.put(key, skuId);
        }

        String skuSaleAttrHashJsonStr = JSON.toJSONString(skuSaleAttrHash);

        modelMap.put("skuInfo", pmsSkuInfo);
        modelMap.put("spuSaleAttrListCheckBySku", spuSaleAttrList);
        modelMap.put("skuSaleAttrHashJsonStr", skuSaleAttrHashJsonStr);
        return "item";
    }


    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return spuService.test();
    }
}
