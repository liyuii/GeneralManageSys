package com.web.LoginModule.serviceImpl;

import com.web.LoginModule.dao.UserDao;
import com.web.LoginModule.entity.User;
import com.web.query.UserQuery;
import com.web.LoginModule.service.UserService;
import com.web.util.constant.EnumUtil;
import com.web.util.poi.PoiUtil;
import com.web.util.random.UUIDUtil;
import com.web.util.string.StringUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> userPage(UserQuery queryCmd) {
        System.out.println("userPage");
        return userDao.userPage(queryCmd);
    }

    @Override
    public int importUserInfo(MultipartFile file) {
        Workbook wb;
        Sheet sheet;
        wb = PoiUtil.getNewWorkbook(".xlsx", file) ;
        sheet = wb.getSheetAt(0);
        int rowNum = sheet.getLastRowNum() ; //获得总行数
        System.out.println(rowNum);

        for(int i=1;i<=rowNum;i++)
        {
            Row rowI = sheet.getRow(i);
            String name = PoiUtil.getCellFormatValue(rowI.getCell(0));
            String pyName = PoiUtil.getCellFormatValue(rowI.getCell(1));
            String local = PoiUtil.getCellFormatValue(rowI.getCell(2));
            String phone = PoiUtil.getCellFormatValue(rowI.getCell(3));
            String money = PoiUtil.getCellFormatValue(rowI.getCell(4));
            String money2021 = PoiUtil.getCellFormatValue(rowI.getCell(5));
            String money2022 = PoiUtil.getCellFormatValue(rowI.getCell(6));
            String money2023 = PoiUtil.getCellFormatValue(rowI.getCell(7));
            String money2024 = PoiUtil.getCellFormatValue(rowI.getCell(8));
            String money2025 = PoiUtil.getCellFormatValue(rowI.getCell(9));
            String type = PoiUtil.getCellFormatValue(rowI.getCell(10));
            User user = new User();
            String uuid = UUIDUtil.generateUuid();
            user.setUserId(uuid.replace("-",""));
            user.setUserName(name);
            user.setUserLocal(local);
            user.setUserPhone(phone);

            if(StringUtil.isNotEmpty(money)){
                user.setUserMoney(new BigDecimal(money));
            }

            if(StringUtil.isNotEmpty(money2021)){
                user.setUserMoney2021(new BigDecimal(money2021));
            }

            if(StringUtil.isNotEmpty(money2022)){
                user.setUserMoney2022(new BigDecimal(money2022));
            }

            if(StringUtil.isNotEmpty(money2023)){
                user.setUserMoney2023(new BigDecimal(money2023));
            }

            if(StringUtil.isNotEmpty(money2024)){
                user.setUserMoney2024(new BigDecimal(money2024));
            }

            if(StringUtil.isNotEmpty(money2025)){
                user.setUserMoney2025(new BigDecimal(money2025));
            }

            user.setUserAccumulate(0);
            user.setUserPyName(pyName);

            if(EnumUtil.SUPPLYSHOW.equals(type)){
                user.setUserType(EnumUtil.SUPPLYSAVE);
            }else if(EnumUtil.CONSUMESHOW.equals(type)){
                user.setUserType(EnumUtil.CONSUMESAVE);
            }
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            userDao.importUserInfo(user);
        }
        return 1;
    }

    @Override
    @Cacheable(value = "valueName", key = "'keyName1'")
    public User getUserById(String userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByName(String userName) {
        return null;
    }

    @Override
    public int addUser(User user) {
        String uuid = UUIDUtil.generateUuid();
        user.setUserId(uuid.replace("-",""));
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        return userDao.addUser(user);
    }

    @Override
    @CachePut(value = "valueName", key = "'keyName1'")
    public int updUser(User user) {
        user.setUpdateTime(new Date());
        return userDao.updUser(user);
    }

    @Override
    @CacheEvict(value = "valueName",allEntries = true)
    public int delUser(String uids) {

        String[] uidStore = uids.split(",");
        int count = 0;
        for(String uid:uidStore){
            userDao.delUser(uid);
            count++;
        }
        if(count>0){
            return 1;
        }
        return 0;
    }

//    @Override
//    public User getUserById(String userId) {
//        return null;
//    }
//
//    @Override
//    public User getUserByName(String userName) {
//        return null;
//    }

}
