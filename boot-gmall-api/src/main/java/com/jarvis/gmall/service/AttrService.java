package com.jarvis.gmall.service;

import com.jarvis.gmall.bean.PmsBaseAttrInfo;
import com.jarvis.gmall.bean.PmsBaseAttrValue;
import com.jarvis.gmall.bean.PmsBaseSaleAttr;

import java.util.List;

/**
 * boot-gmall : com.jarvis.gmall.service
 *
 * @author jarvis
 * @create 2020-03-22 21:25
 */
public interface AttrService {

    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);

    List<PmsBaseAttrValue> getAttrValueList(String attrId);

    void saveOrUpdateAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    List<PmsBaseSaleAttr> baseSaleAttrList();

}
