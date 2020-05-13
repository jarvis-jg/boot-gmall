package com.jarvis.gmall.manager.service.impl;

import com.alibaba.fastjson.JSON;
import com.jarvis.gmall.bean.PmsSkuAttrValue;
import com.jarvis.gmall.bean.PmsSkuImage;
import com.jarvis.gmall.bean.PmsSkuInfo;
import com.jarvis.gmall.bean.PmsSkuSaleAttrValue;
import com.jarvis.gmall.manager.mapper.*;
import com.jarvis.gmall.service.SkuService;
import com.jarvis.gmall.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.List;
import java.util.UUID;

/**
 * boot-gmall : com.jarvis.gmall.manager.service.impl
 *
 * @author jarvis
 * @create 2020-03-28 23:28
 */
@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    PmsSkuInfoMapper pmsSkuInfoMapper;

    @Autowired
    PmsSkuImageMapper pmsSkuImageMapper;

    @Autowired
    PmsSkuAttrValueMapper pmsSkuAttrValueMapper;

    @Autowired
    PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;

    @Autowired
    PmsProductSaleAttrMapper pmsProductSaleAttrMapper;

    @Autowired
    RedisUtil redisUtil;


    @Override
    public void saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
        pmsSkuInfoMapper.insertSelective(pmsSkuInfo);

        for (PmsSkuImage pmsSkuImage : pmsSkuInfo.getSkuImageList()) {
            pmsSkuImage.setSkuId(pmsSkuInfo.getId());
            pmsSkuImageMapper.insertSelective(pmsSkuImage);
        }

        for (PmsSkuAttrValue pmsSkuAttrValue : pmsSkuInfo.getSkuAttrValueList()) {
            pmsSkuAttrValue.setSkuId(pmsSkuInfo.getId());
            pmsSkuAttrValueMapper.insertSelective(pmsSkuAttrValue);
        }

        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : pmsSkuInfo.getSkuSaleAttrValueList()) {
            pmsSkuSaleAttrValue.setSkuId(pmsSkuInfo.getId());
            pmsSkuSaleAttrValueMapper.insertSelective(pmsSkuSaleAttrValue);
        }
    }

    public PmsSkuInfo getSkuInfoFromDb(String sku_id) {
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        pmsSkuInfo.setId(sku_id);
        PmsSkuInfo SkuInfo = pmsSkuInfoMapper.selectOne(pmsSkuInfo);

        PmsSkuImage pmsSkuImage = new PmsSkuImage();
        pmsSkuImage.setSkuId(sku_id);
        List<PmsSkuImage> pmsSkuImages = pmsSkuImageMapper.select(pmsSkuImage);
        SkuInfo.setSkuImageList(pmsSkuImages);

        return SkuInfo;
    }


    @Override
    public PmsSkuInfo getSkuInfo(String sku_id) {
        //查询缓存
        Jedis jedis = null;
        PmsSkuInfo pmsSkuInfo = null;
        System.out.println(Thread.currentThread().getName() + "进入getSkuInfo");
        try {
            jedis = redisUtil.getJedis();
            String key = "sku:" + sku_id + ":info";
            String skuInfoStr = jedis.get(key);

            if (StringUtils.isNotBlank(skuInfoStr)) {
                System.out.println(Thread.currentThread().getName() + "查询到缓存中的数据");
                pmsSkuInfo = JSON.parseObject(skuInfoStr, PmsSkuInfo.class);
            }else {
                System.out.println(Thread.currentThread().getName() + "加分布式");
                //解决缓存击穿问题：使用redis自带的分布式锁
                String lockKey = "sku:" + sku_id + ":lock";
//              SetParams setParams = SetParams.setParams().ex(60 * 10).nx(); 高版本的Jedis

                String lockValue = UUID.randomUUID().toString();
                String lockResult = jedis.set(lockKey, lockValue,"nx", "ex",10* 1000);
                if (StringUtils.isNotBlank(lockResult) && "OK".equals(lockResult)){
                    System.out.println(Thread.currentThread().getName() + "加分布式所成功");
                    //加上锁了，才能访问数据库
                    pmsSkuInfo = getSkuInfoFromDb(sku_id);


                    Thread.sleep(5000L);
                    if (pmsSkuInfo == null) {
                        //解决缓存穿透问题,对于不存在的数据设置一个空串
//                        SetParams params = SetParams.setParams().ex(60);

                        jedis.set(key, "","nx","ex",60);
                    } else {
//                        SetParams params = SetParams.setParams().ex(60 * 60);

                        jedis.set(key, JSON.toJSONString(pmsSkuInfo), "nx", "ex", 60 * 60);

                    }
                    System.out.println(Thread.currentThread().getName() + "设置缓存");

                    //删除锁
                    if (lockValue.equals(jedis.get(lockKey))) {
                        jedis.del(lockKey);
                    }
                }else {
                    Thread.sleep(1000L);
                    System.out.println(Thread.currentThread().getName() + "进入自旋");
//                    自旋
                    return getSkuInfo(sku_id);
                }
            }
        } catch (JedisConnectionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return pmsSkuInfo;
    }

    @Override
    public List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId) {
        return pmsSkuInfoMapper.selectSkuSaleAttrValueListBySpu(productId);
    }

}
