package com.example.springbootredis.service.impl;


import com.example.springbootredis.dao.CityDao;
import com.example.springbootredis.domain.City;
import com.example.springbootredis.service.ICityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CityServiceImpl implements ICityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CityDao cityDao;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取城市逻辑：
     * 如果缓存存在，从缓存中获取城市信息
     * 如果缓存不存在，从 DB 中获取城市信息，然后插入缓存
     */
    @Override
    @Cacheable(value = "citys")
    public  City findCityById(Long id) {
        System.out.println("无缓存时执行下面代码，获取zhangsan,Time：" + new Date());
        return cityDao.findById(id);
    }

    @Override
    public List<City> findAllCity() {
        String key = "city";
        ValueOperations<String,List<City>> operations = redisTemplate.opsForValue();
        return cityDao.findAllCity();
    }

    @Override
    @CachePut(value = "citycs")
    public Long saveCity(City city) {
        LOGGER.info("我在添加城市");
        return cityDao.saveCity(city);
    }

    /**
     * 更新城市逻辑：
     * 如果缓存存在，删除
     * 如果缓存不存在，不操作
     */
    @Override
    @CachePut(value = "citys",key = "#p0")
    public Long updateCity(City city) {
        return cityDao.updateCity(city);
    }

    @Override
    @CacheEvict(value = "citys")
    //@CacheEvict 从缓存people中删除key为id的数据
    public Long deleteCity(Long id) {
        Long del = cityDao.deleteCity(id);
        System.out.println("删除数据" + id + "，同时清除对应的缓存");
        return del;
    }
}
