package com.jarvis.gmall.manager.controller;

import com.jarvis.gmall.bean.PmsBaseCatalog1;
import com.jarvis.gmall.bean.PmsBaseCatalog2;
import com.jarvis.gmall.bean.PmsBaseCatalog3;
import com.jarvis.gmall.service.CatalogService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * boot-gmall : com.jarvis.gmall.manager.controller
 *
 * @author jarvis
 * @create 2020-03-22 18:49
 */
@RestController
@CrossOrigin
public class CatalogController {

    @Reference
    CatalogService catalogService;


    @PostMapping("/getCatalog1")
    public List<PmsBaseCatalog1> getCatalog1() {
        List<PmsBaseCatalog1> pmsBaseCatalog1s = catalogService.getCatalog1();
        return pmsBaseCatalog1s;
    }


    @PostMapping("/getCatalog2")
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id) {
        List<PmsBaseCatalog2> pmsBaseCatalog2s = catalogService.getCatalog2(catalog1Id);
        return pmsBaseCatalog2s;
    }


    @PostMapping("/getCatalog3")
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id) {
        List<PmsBaseCatalog3> pmsBaseCatalog3s = catalogService.getCatalog3(catalog2Id);
        return pmsBaseCatalog3s;
    }
}
