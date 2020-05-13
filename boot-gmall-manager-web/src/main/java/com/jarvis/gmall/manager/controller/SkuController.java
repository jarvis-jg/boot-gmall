package com.jarvis.gmall.manager.controller;

import com.jarvis.gmall.bean.PmsSkuImage;
import com.jarvis.gmall.bean.PmsSkuInfo;
import com.jarvis.gmall.service.SkuService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * boot-gmall : com.jarvis.gmall.manager.controller
 *
 * @author jarvis
 * @create 2020-03-28 23:12
 */
@RestController
@CrossOrigin
public class SkuController {

    @Reference
    SkuService skuService;

    @PostMapping("/saveSkuInfo")
    public String saveSkuInfo(@RequestBody PmsSkuInfo pmsSkuInfo) {
        pmsSkuInfo.setProductId(pmsSkuInfo.getSpuId());
        for (PmsSkuImage pmsSkuImage : pmsSkuInfo.getSkuImageList()) {
            pmsSkuImage.setProductImgId(pmsSkuImage.getSpuImgId());
        }

        skuService.saveSkuInfo(pmsSkuInfo);
        return "success";
    }
}
