package com.jarvis.gmall.manager.controller;

import com.jarvis.gmall.bean.PmsBaseAttrInfo;
import com.jarvis.gmall.bean.PmsBaseAttrValue;
import com.jarvis.gmall.bean.PmsBaseSaleAttr;
import com.jarvis.gmall.service.AttrService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * boot-gmall : com.jarvis.gmall.manager.controller
 *
 * @author jarvis
 * @create 2020-03-22 21:22
 */
@RestController
@CrossOrigin
public class AttrController {

    @Reference
    AttrService attrService;

    //查询销售属性
    @PostMapping("/baseSaleAttrList")
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        return attrService.baseSaleAttrList();
    }

    @GetMapping("/attrInfoList")
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        List<PmsBaseAttrInfo> attrInfoList = attrService.attrInfoList(catalog3Id);
        return attrInfoList;
    }

    @PostMapping("/getAttrValueList")
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
        List<PmsBaseAttrValue> pmsBaseAttrValues = attrService.getAttrValueList(attrId);
        return pmsBaseAttrValues;
    }

    @PostMapping("/saveAttrInfo")
    public String saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo) {
        attrService.saveOrUpdateAttrInfo(pmsBaseAttrInfo);
        return "success";
    }

}
