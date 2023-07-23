package com.salaryhelper.service;

import com.salaryhelper.model.EmployeeSalaryRule;
import com.salaryhelper.model.EmployeeWorkHours;
import com.salaryhelper.util.AlertUtil;
import com.salaryhelper.util.ExcelUtil;

import java.io.IOException;
import java.util.List;

public class SalaryRuleService {
    public List<EmployeeSalaryRule> getRuleListFromFile(String filePath)
    {
        //从文件读取薪资构成表
        List<EmployeeSalaryRule> ruleList=ExcelUtil.getAllEmployeesRulesFromXlsx(filePath);
        return ruleList;
    }

    public EmployeeSalaryRule getSalaryRuleByEmployeeName(String employeeName,List<EmployeeSalaryRule> ruleLst)
    {
        for(EmployeeSalaryRule rule:ruleLst)
        {
            if(employeeName.equals(rule.getEmployeeName()))
            {
                return rule;
            }
        }
        return null;
    }
}
