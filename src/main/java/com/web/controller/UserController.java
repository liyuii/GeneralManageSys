package com.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.entity.Result;
import com.web.entity.User;
import com.web.query.UserQuery;
import com.web.service.UserService;
import com.web.util.random.UUIDUtil;
import com.web.util.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserList")
    @ResponseBody
    public Map<String,Object> getUserList(UserQuery queryCmd){
        int num = queryCmd.getPage();
        int row = queryCmd.getRows();
        PageHelper.startPage(num, row);
        List<User> userList = userService.userPage(queryCmd);
        PageInfo<User> page = new PageInfo<>(userList);

        Map<String,Object> map = new HashMap<>();
        map.put("rows",page.getList());
        map.put("total",page.getTotal());
        log.info("获取用户列表信息，参数为：" + queryCmd.toString());
        return map;
    }

    @RequestMapping("/toImportPage")
    public String toImportPage(){
        return "/item/user/user_import";
    }

    @RequestMapping("/toAddPage")
    public String toAddPage(){
        return "/item/user/user_add";
    }

    @RequestMapping("/toUpdatePage")
    public String toUpdatePage(@RequestParam("userId") String userId, ModelMap map){
        User user = userService.getUserById(userId);
        map.put("user",user);
        return "/item/user/user_update";
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public Result addUser(User user){
        Result result = new Result();
        int res = userService.addUser(user);
        if(Integer.toString(res).equals("1")){
            result.setCode("1");
            result.setMessage("添加成功");
        }else{
            result.setCode("0");
            result.setMessage("添加失败");
        }
        return result;
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
//        System.out.println("进来了");
        throw new NullPointerException("抛出一个空指针异常");
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public Result updateUser(User user){
        Result result = new Result();
        int res = userService.updUser(user);
        if(Integer.toString(res).equals("1")){
            result.setCode("1");
            result.setMessage("修改成功");
        }else{
            result.setCode("0");
            result.setMessage("修改失败");
        }
        return result;
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public Result deleteUser(String uids){
        Result result = new Result();
        int res = userService.delUser(uids);
        if(Integer.toString(res).equals("1")){
            result.setCode("1");
            result.setMessage("删除成功");
        }else{
            result.setCode("0");
            result.setMessage("删除失败");
        }
        return result;
    }

    @RequestMapping("/importUser")
    @ResponseBody
    public Result importUser(@RequestParam("Filedata") MultipartFile file, HttpServletRequest request){
        System.out.println("进来了");
        Result result = new Result();
        int res = userService.importUserInfo(file);
        if(Integer.toString(res).equals("1")){
            result.setCode("1");
            result.setMessage("导入成功");
        }else{
            result.setCode("0");
            result.setMessage("导入失败");
        }
        return result;
    }


}
