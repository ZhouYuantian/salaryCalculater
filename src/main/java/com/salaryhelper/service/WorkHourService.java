package com.salaryhelper.service;

import com.salaryhelper.model.EmployeeSalaryRule;
import com.salaryhelper.model.EmployeeWorkHours;
import com.salaryhelper.util.AlertUtil;
import com.salaryhelper.util.ExcelUtil;

import java.io.IOException;
import java.util.List;


public class WorkHourService {
    public List<EmployeeWorkHours> getHourListFromFile(String filePath)
    {
        //从文件读取工时表
        List<EmployeeWorkHours> wkHrList= ExcelUtil.getAllEmployeesWorkHoursFromXlsx(filePath);
        return wkHrList;
    }

    public EmployeeWorkHours getWorkHourTableByEmployeeName(String employeeName,List<EmployeeWorkHours> ewhLst)
    {
        for(EmployeeWorkHours whTable:ewhLst)
        {
            if(employeeName.equals(whTable.getEmployeeName()))
            {
                return whTable;
            }
        }
        return null;
    }
}
