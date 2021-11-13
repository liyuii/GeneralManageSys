package com.web.util.calculate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DecimalUtil {

    public static BigDecimal add(BigDecimal arg1,BigDecimal arg2){

        List<BigDecimal> decimalsList = judgeNull(arg1, arg2);
        BigDecimal a1 = decimalsList.get(0);
        BigDecimal a2 = decimalsList.get(1);
        return a1.add(a2);
    }

    public static BigDecimal subtract(BigDecimal arg1,BigDecimal arg2){
        List<BigDecimal> decimalsList = judgeNull(arg1, arg2);
        BigDecimal a1 = decimalsList.get(0);
        BigDecimal a2 = decimalsList.get(1);
        return a1.subtract(a2);
    }

    //判断参数是否为null
    private static List<BigDecimal> judgeNull(BigDecimal arg1, BigDecimal arg2){

        List<BigDecimal> list = new ArrayList<>();

        if(arg1 != null){
            list.add(arg1);
            if(arg2 != null){
                list.add(arg2);
            }else{
                list.add(new BigDecimal(0));
            }
        }else{
            list.add(new BigDecimal(0));
            if(arg2 != null){
                list.add(arg2);
            }else{
                list.add(new BigDecimal(0));
            }
        }
        return list;
    }


}
