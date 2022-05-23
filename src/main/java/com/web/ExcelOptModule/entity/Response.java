package com.web.ExcelOptModule.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Response {

    @Excel(name = "第1列", width = 30)
    private String regStatus;
    @Excel(name = "第2列", width = 30)
    private Integer amount;
    @Excel(name = "第3列", width = 30)
    private Long estiblishTime;
    @Excel(name = "第4列", width = 30)
    private String regCapital;
    @Excel(name = "第5列", width = 30)
    private Integer type;
    @Excel(name = "第6列", width = 30)
    private String amountSuffix;
    @Excel(name = "第7列", width = 30)
    private String percent;
    @Excel(name = "第8列", width = 30)
    private String legalPersonName;
    @Excel(name = "第9列", width = 30)
    private String business_scope;
    @Excel(name = "第10列", width = 30)
    private String orgType;
    @Excel(name = "第11列", width = 30)
    private String creditCode;
    @Excel(name = "第12列", width = 30)
    private String name;
    @Excel(name = "第13列", width = 30)
    private String alias;
    @Excel(name = "第14列", width = 30)
    private Long id;
    @Excel(name = "第15列", width = 30)
    private String category;
    @Excel(name = "第16列", width = 30)
    private Integer personType;
    @Excel(name = "第17列", width = 30)
    private String base;
}
