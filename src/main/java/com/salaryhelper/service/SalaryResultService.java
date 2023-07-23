package com.salaryhelper.service;

import com.salaryhelper.model.EmployeeSalaryResult;
import com.salaryhelper.model.EmployeeSalaryRule;
import com.salaryhelper.model.EmployeeWorkHours;
import com.salaryhelper.util.ExcelUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SalaryResultService {
    private WorkHourService wkhrService;
    private SalaryRuleService ruleService;

    public SalaryResultService() {
        wkhrService=new WorkHourService();
        ruleService=new SalaryRuleService();
    }

    public List<EmployeeSalaryResult> calculateSalaryResults(List<EmployeeWorkHours> workHoursList, List<EmployeeSalaryRule> ruleList)
    {
        List<EmployeeSalaryResult> resultList=new ArrayList<>();
        for(EmployeeWorkHours whTable:workHoursList)
        {
            EmployeeSalaryRule ruleTable=ruleService.getSalaryRuleByEmployeeName(whTable.getEmployeeName(), ruleList);
            if(ruleTable!=null)
            {
                resultList.add(new EmployeeSalaryResult(ruleTable,whTable));
            }
        }
        return resultList;
    }

    public void outputResults(String directory, List<EmployeeSalaryResult> resultList)
    {
        for(EmployeeSalaryResult result:resultList)
        {
           ExcelUtil.writeResultToFile(result,directory+"\\"+result.employeeName+"的工资条.xlsx");
        }
    }

    public void generateResults(String hourTablePath,String ruleTablePath,String outputDirectory)
    {
        List<EmployeeWorkHours> wkHrList=wkhrService.getHourListFromFile(hourTablePath);
        List<EmployeeSalaryRule> ruleList=ruleService.getRuleListFromFile(ruleTablePath);

        //计算工资
        List<EmployeeSalaryResult> resultList=calculateSalaryResults(wkHrList,ruleList);

        //输出工资条文件到目录
        outputResults(outputDirectory,resultList);
    }
}
