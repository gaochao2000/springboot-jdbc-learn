package com.yanggaochao.springboot.learn.springbootjdbclearn.web;

import com.yanggaochao.springboot.learn.springbootjdbclearn.domain.bo.RestCollectionResult;
import com.yanggaochao.springboot.learn.springbootjdbclearn.domain.bo.RestItemResult;
import com.yanggaochao.springboot.learn.springbootjdbclearn.domain.dao.UserDO;
import com.yanggaochao.springboot.learn.springbootjdbclearn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户 Http Rest 接口
 *
 * @author 杨高超
 * @since 2018-03-09
 */
@RestController
@RequestMapping("api/v1/user")
public class UserApi {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RestItemResult<UserDO> add(@RequestBody UserDO user) {
        RestItemResult<UserDO> result = new RestItemResult<>();
        user = userService.add(user);
        if (user != null) {
            result.setItem(user);
            result.setResult("success");
        } else {
            result.setMessage("新增用户失败");
            result.setResult("failure");
        }
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RestItemResult<UserDO> update(@RequestBody UserDO user) {
        RestItemResult<UserDO> result = new RestItemResult<>();
        user = userService.update(user);
        if (user != null) {
            result.setItem(user);
            result.setResult("success");
        } else {
            result.setMessage("修改用户失败");
            result.setResult("failure");
        }
        return result;
    }

    @RequestMapping(value = "/delete/{uuid}", method = RequestMethod.GET)
    public RestItemResult<UserDO> delete(@PathVariable Long uuid) {
        RestItemResult<UserDO> result = new RestItemResult<>();
        if (userService.delete(uuid)) {
            result.setResult("success");
        } else {
            result.setMessage("删除用户失败");
            result.setResult("failure");
        }
        return result;
    }

    @RequestMapping(value = "/locate/{uuid}", method = RequestMethod.GET)
    public RestItemResult<UserDO> locate(@PathVariable Long uuid) {
        RestItemResult<UserDO> result = new RestItemResult<>();
        UserDO user = userService.locate(uuid);
        if (user != null) {
            result.setItem(user);
            result.setResult("success");
        } else {
            result.setMessage("查询用户失败");
            result.setResult("failure");
        }
        return result;
    }

    @RequestMapping(value = "/match/{name}", method = RequestMethod.GET)
    public RestCollectionResult<UserDO> match(@PathVariable String name) {
        RestCollectionResult<UserDO> result = new RestCollectionResult<>();
        List<UserDO> users = userService.matchName(name);
        result.setItems(users);
        result.setResult("success");
        return result;
    }
}
