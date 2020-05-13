package com.jarvis.gmall.service;

import com.jarvis.gmall.bean.PmsBaseCatalog1;
import com.jarvis.gmall.bean.PmsBaseCatalog2;
import com.jarvis.gmall.bean.PmsBaseCatalog3;

import java.util.List;

/**
 * boot-gmall : com.jarvis.gmall.service
 *
 * @author jarvis
 * @create 2020-03-22 18:53
 */
public interface CatalogService {

    public List<PmsBaseCatalog1> getCatalog1();

    List<PmsBaseCatalog2> getCatalog2(String catalog1Id);

    List<PmsBaseCatalog3> getCatalog3(String catalog2Id);
}
