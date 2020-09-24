package com.example.springbootswagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: xuruifeng
 * @Date: 2020/9/24 17:25
 */
@Api("用户管理")   //注解API说明该类需要生成Api文档
@RestController
@RequestMapping("/users")
public class UserController {

    // 模拟数据
    static Map<Long,User> users = new ConcurrentHashMap<>();

    @ApiOperation("获取用户列表")
    @GetMapping("/")
    public Map<Long,User> findAll(){
        User user = new User();
        user.setId(1L);
        user.setName("小明");
        user.setAge(18);
        users.put(1L,user);
        return users;
    }

    //注解ApiOperation()用于方法,表示一个http请求的操作,value描述方法
    @ApiOperation(value = "根据用户id获取详细信息")
    //ApiImplicitParam表示单独的请求参数,name–参数名,value–参数说明,dataType–数据类型,paramType–参数类型,required-是否必须,example–举例说明
    @ApiImplicitParam(name = "id",value = "用户编号",dataType = "Long",paramType = "path", required = true, example = "1")
    @GetMapping("/{id}")
    public User findOneById(@PathVariable(name = "id") Long id){
        return users.get(id);
    }

    @ApiOperation("添加用户")
    @PostMapping("/")
    public String addUser(@RequestBody User user){
        users.put(user.getId(),user);
        return "success";
    }

    @ApiOperation("更新用户信息")
    @ApiImplicitParam(paramType = "path", dataType = "Long", name = "id", value = "用户编号", required = true, example = "1")
    @PutMapping("/{id}")
    public User updateUser(@PathVariable(name = "id") Long id,@RequestBody User user){
        User oldUser = findOneById(id);
        oldUser.setId(user.getId());
        oldUser.setAge(user.getAge());
        oldUser.setName(user.getName());
        return oldUser;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", dataType = "Long", name = "id", value = "用户编号", required = true, example = "1")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        users.remove(id);
        return "success";
    }

}

