package com.jarvis.gmall.manager.controller;

import com.jarvis.gmall.bean.PmsProductImage;
import com.jarvis.gmall.bean.PmsProductInfo;
import com.jarvis.gmall.bean.PmsProductSaleAttr;
import com.jarvis.gmall.manager.util.PmsUploadUtil;
import com.jarvis.gmall.service.SpuService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * boot-gmall : com.jarvis.gmall.manager.controller
 *
 * @author jarvis
 * @create 2020-03-28 11:01
 */
@RestController
@CrossOrigin
public class SpuController {

    @Reference
    SpuService spuService;


    @GetMapping("/spuImageList")
    public List<PmsProductImage> spuImageList(String spuId) {
        List<PmsProductImage> pmsProductImages = spuService.spuImageList(spuId);
        return pmsProductImages;
    }

    @GetMapping("/spuSaleAttrList")
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId) {
        List<PmsProductSaleAttr> pmsProductSaleAttrs = spuService.spuSaleAttrList(spuId);
        return pmsProductSaleAttrs;
    }

    @PostMapping("/saveSpuInfo")
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo) {
        // 先把属性设置好【前后端属性名不一致的暂时方案】
        pmsProductInfo.setProductName(pmsProductInfo.getSpuName());
        spuService.saveSpuInfo(pmsProductInfo);
        return "success";
    }

    @PostMapping("/fileUpload")
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile) {

        String imgUrl = PmsUploadUtil.uploadImg(multipartFile);
        System.out.println(imgUrl);
        return imgUrl;
    }

    @GetMapping("/spuList")
    public List<PmsProductInfo> spuList(String catalog3Id) {
        List<PmsProductInfo> pmsProductInfos = spuService.spuList(catalog3Id);
        return pmsProductInfos;
    }
}
