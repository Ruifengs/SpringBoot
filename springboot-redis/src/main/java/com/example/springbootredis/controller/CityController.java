package com.example.springbootredis.controller;

import com.example.springbootredis.domain.City;
import com.example.springbootredis.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CityController {

    @Autowired
    private ICityService cityService;

    @RequestMapping(value = "/city",method = RequestMethod.GET)
    public List<City> findAllCity(){
        return cityService.findAllCity();
    }


    /**
     * 根据id查找城市
     */
    @RequestMapping(value = "/city/{id}",method = RequestMethod.GET)
    public City findCityById(@PathVariable("id") Long id){
        return cityService.findCityById(id);
    }

    /**
     * 保存城市
     */
    @RequestMapping(value = "/city/add",method = RequestMethod.POST)
    public void saveCity(@RequestBody City city){
        cityService.saveCity(city);
    }

    /**
     * 删除城市
     */
    @RequestMapping(value = "/city/{id}",method =RequestMethod.DELETE)
    public void deleteCity(@PathVariable("id") Long id){
        cityService.deleteCity(id);
    }

    @RequestMapping(value = "/city/{id}",method = RequestMethod.PUT)
    public void updateCity(@RequestBody City city){
        cityService.updateCity(city);
    }


}
