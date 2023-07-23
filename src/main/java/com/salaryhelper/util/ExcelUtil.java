package com.salaryhelper.util;

import com.salaryhelper.model.EmployeeSalaryResult;
import com.salaryhelper.model.EmployeeSalaryRule;

import com.salaryhelper.model.EmployeeWorkHours;
import com.salaryhelper.util.coordinatesMapper.HourTbl;
import com.salaryhelper.util.coordinatesMapper.RsltTbl;
import com.salaryhelper.util.coordinatesMapper.RuleTbl;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {
    static Row row;
    static Cell cell;
    static Sheet sheet;
    static Workbook workbook;
    static int firstRow,lastRow;
    static String templateFile="src/main/resources/com/salaryhelper/excels/template/result_template.xlsx";
    static String currentEmployeeName;  //记录读取中数据的所属员工姓名，当数据有异常时输出名字便于检查
    static void initialize(String filePath)
    {
        try{
            File file = new File(filePath);
            FileInputStream fs = FileUtils.openInputStream(file);
            workbook = new XSSFWorkbook(fs);
            sheet = workbook.getSheetAt(0);
            firstRow = sheet.getFirstRowNum() + 1; //数据从 列1 开始
            lastRow = sheet.getLastRowNum();
        }catch (Exception e)
        {
            AlertUtil.warning("初始化文件异常,请重试");
            throw new RuntimeException();
        }

    }
    static void saveToFile(String filePath)
    {
        try {
            FileOutputStream outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
        }catch (Exception e)
        {
            AlertUtil.warning("保存文件异常,请重试");
            throw new RuntimeException();
        }
    }
    static String getStringFromCell(int cellIdx)
    {
        Cell cell=row.getCell(cellIdx);
        if(cell!=null)
        {
            cell.setCellType(CellType.STRING);
            return cell.getStringCellValue();
        }
        return "";
    }
    static float getFloatFromCell(int cellIdx)
    {
        Cell cell=row.getCell(cellIdx);
        if(cell!=null)
        {   //获取String,然后转float保留两位小数
            cell.setCellType(CellType.STRING);
            String value=cell.getStringCellValue();
            try {
                return value.isEmpty()?0:Float.parseFloat(value);
            }catch (NumberFormatException e) {
                AlertUtil.warning(currentEmployeeName+"的信息异常，请检查并重试");
                throw new RuntimeException();
            }

        }
        return 0;
    }
    static void writeToCellAt(int colNum,int rowNum, String value)
    {
        row=sheet.getRow(rowNum);
        cell=row.getCell(colNum);
        cell.setCellValue(value);
    }
    static void writeToCellAt(int colNum,int rowNum, float value)
    {
        row=sheet.getRow(rowNum);
        cell=row.getCell(colNum);
        cell.setCellValue(String.format("%.2f",value));
    }






    //从表格获取所有员工的薪资构成规则
    public static List<EmployeeSalaryRule> getAllEmployeesRulesFromXlsx(String filePath)
    {
        initialize(filePath);

        List<EmployeeSalaryRule> ruleList=new ArrayList<>();
        for (int i = firstRow; i <= lastRow; i++)
        {
            EmployeeSalaryRule rule=new EmployeeSalaryRule();
            row=sheet.getRow(i);

            currentEmployeeName=getStringFromCell(RuleTbl.employeeName);
            rule.setEmployeeName(currentEmployeeName);
            rule.setHourlyRate(getFloatFromCell(RuleTbl.hourlyRate));
            rule.setDailySubsidy(getFloatFromCell(RuleTbl.dailySubsidy));
            rule.setOutstandingReward(getFloatFromCell(RuleTbl.outStandingReward));
            rule.setFullAttendanceReward(getFloatFromCell(RuleTbl.fullAttendanceReward));
            rule.setNormalSubsidy(getFloatFromCell(RuleTbl.normalSubsidy));
            rule.setSpecialJobSubsidy(getFloatFromCell(RuleTbl.specialJobSubsidy));
            rule.setTechJobSubsidy(getFloatFromCell(RuleTbl.techJobSubsidy));
            rule.setNightSubsidy(getFloatFromCell(RuleTbl.nightSubsidy));
            rule.setOtherSubsidy(getFloatFromCell(RuleTbl.otherSubsidy));
            rule.setLunchSubsidyStandard(getFloatFromCell(RuleTbl.lunchSubsidy));
            rule.setLastMonthDiff(getFloatFromCell(RuleTbl.lastMonthDiff));
            rule.setLunchFee(getFloatFromCell(RuleTbl.lunchFee));

            ruleList.add(rule);
        }

        return ruleList;
    }


    public static List<EmployeeWorkHours> getAllEmployeesWorkHoursFromXlsx(String filePath)
    {
        initialize(filePath);

        List<EmployeeWorkHours> hoursList=new ArrayList<>();
        for (int i = firstRow; i <=lastRow; i++)
        {
            EmployeeWorkHours hours=new EmployeeWorkHours();
            row=sheet.getRow(i);

            currentEmployeeName=getStringFromCell(HourTbl.employeeName);
            hours.setEmployeeName(currentEmployeeName);
            hours.setDays3to5H((int)getFloatFromCell(HourTbl.days3to5H));
            hours.setDaysGT5H((int)getFloatFromCell(HourTbl.daysGT5H));
            hours.setDaysGT11H((int)getFloatFromCell(HourTbl.daysGT11H));
            hours.setWeekdaysHours(getFloatFromCell(HourTbl.weekdaysHours));
            hours.setWeekdaysOvertimeHours(getFloatFromCell(HourTbl.weekdaysOvertimeHours));
            hours.setWeekendOvertimeHours(getFloatFromCell(HourTbl.weekendOvertimeHours));
            hours.setHolidayHours(getFloatFromCell(HourTbl.holidayHours));
            hours.setHolidayOvertimeHours(getFloatFromCell(HourTbl.holidayOvertimeHours));
            hours.setPaidLeaveHours(getFloatFromCell(HourTbl.paidLeaveHours));
            hours.setSickLeaveHours(getFloatFromCell(HourTbl.sickLeaveHours));
            hours.setAfter20Subsidy(getFloatFromCell(HourTbl.after20Subsidy));
            hours.setAbsences(getFloatFromCell(HourTbl.absences));
            hours.setMeals5(getFloatFromCell(HourTbl.meals5));
            hours.setMeals11(getFloatFromCell(HourTbl.meals11));
            hours.setMeas12(getFloatFromCell(HourTbl.meals12));
            hours.setOvertimeSubsidy(getFloatFromCell(HourTbl.overtimeSubsidy));
            hours.setCleanerSubsidy(getFloatFromCell(HourTbl.cleanerSubsidy));
            hours.setUnionFee(getFloatFromCell(HourTbl.unionFee));

            hoursList.add(hours);
        }

        return hoursList;
    }



    public static void writeResultToFile(EmployeeSalaryResult result, String outputFile)
    {
        initialize(templateFile);

        writeToCellAt(RsltTbl.XemployeeName,RsltTbl.YemplyeeName,result.employeeName);//姓名
        writeToCellAt(RsltTbl.XhourlyRate,RsltTbl.YhourlyRate,result.hourlyRate);//时薪
        writeToCellAt(RsltTbl.XdailySubsidy,RsltTbl.YdailySubsidy,result.dailySubsidy);//日津贴
        writeToCellAt(RsltTbl.Xdays3to5H,RsltTbl.Ydays3to5H,result.days3to5H);//3h-5h天数
        writeToCellAt(RsltTbl.XdaysGT5H,RsltTbl.YdaysGT5H,result.daysGT5H);//5h以上天数
        writeToCellAt(RsltTbl.XdaysGT11H,RsltTbl.YdaysGT11H,result.daysGT11H);//11h以上天数
        writeToCellAt(RsltTbl.XmonthlySubsidy,RsltTbl.YmonthlySubsidy,result.monthlySubsidy);//月津贴工资
        writeToCellAt(RsltTbl.XweekdaysHours,RsltTbl.YweekdaysHours,result.weekdaysHours);//工作日工时
        writeToCellAt(RsltTbl.XweekdaysSalary,RsltTbl.YweekdaysSalary,result.weekdaysSalary);//工作日工资
        writeToCellAt(RsltTbl.XweekdaysOvertimeHours,RsltTbl.YweekdaysOvertimeHour,result.weekdaysOvertimeHours);//工作日加班工时
        writeToCellAt(RsltTbl.XweekdaysOvertimeSalary,RsltTbl.YweekdaysOvertimeSalary,result.weekdaysOvertimeSalary);//工作日加班工资
        writeToCellAt(RsltTbl.XweekendOvertimeHours,RsltTbl.YweekendOvertimeHours,result.weekendOvertimeHours);//休息日加班工时
        writeToCellAt(RsltTbl.XweekendOvertimeSalary,RsltTbl.YweekendOvertimeSalary,result.weekendOvertimeSalary);//休息日加班工资
        writeToCellAt(RsltTbl.XholidayHours,RsltTbl.YhoulidayHours,result.holidayHours);//法定休息日正常工时
        writeToCellAt(RsltTbl.XholidayOvertimeHours,RsltTbl.YholidayOvertimeHours,result.holidayOvertimeHours);//法定休息日加班工时
        writeToCellAt(RsltTbl.XholidaySalary,RsltTbl.YholidaySalary,result.holidaySalary);//法定休息日工资
        writeToCellAt(RsltTbl.XpaidLeaveHours,RsltTbl.YpaidLeaveHours,result.paidLeaveHours);//带薪假工时
        writeToCellAt(RsltTbl.XpaidLeaveSalary,RsltTbl.YpaidLeaveSalary,result.paidLeaveSalary);//带薪假工资
        writeToCellAt(RsltTbl.XlunchSubsidy,RsltTbl.YlunchSubsidy,result.lunchSubsidy);//午餐补贴
        writeToCellAt(RsltTbl.XdinnerSubsidy,RsltTbl.YdinnerSubsidy,result.dinnerSubsidy);//晚餐补贴
        writeToCellAt(RsltTbl.XfullAttendanceReward,RsltTbl.YfullAttendancceReward,result.fullAttendanceReward);//全勤奖
        writeToCellAt(RsltTbl.XoutStandingLevel,RsltTbl.YoutStandingLevel,result.outStandingLevel);//优秀员工级别
        writeToCellAt(RsltTbl.XoutStandingReward,RsltTbl.YoutStandingReward,result.outStandingReward);//优秀员工奖金
        writeToCellAt(RsltTbl.XspecialJobSubsidyDays,RsltTbl.YspecialJobSubsidyDays,result.specialJobSubsidy);//特殊岗位津贴(天数)
        writeToCellAt(RsltTbl.XspecialJobSubsidys,RsltTbl.YspecialJobSubsidy,result.specialJobSubsidy);//特殊岗位津贴
        writeToCellAt(RsltTbl.XtechJobLevel,RsltTbl.YtechJobLevel,result.techJobLevel);//技能岗位等级
        writeToCellAt(RsltTbl.XtechJobSubsidy,RsltTbl.YtechJobSubsidy,result.techJobSubsidy);//技能岗位奖励
        writeToCellAt(RsltTbl.XnightSubsidy,RsltTbl.YnightSubsidy,result.nightSubsidy);//夜班补贴
        writeToCellAt(RsltTbl.XnormalSubsidy,RsltTbl.YnormalSubsidy,result.normalSubsidy);//常规绩效
        writeToCellAt(RsltTbl.XotherSubsidy,RsltTbl.YotherSubsidy,result.otherSubsidy);//其他补贴
        writeToCellAt(RsltTbl.XlunchFee,RsltTbl.YlunchFee,result.lunchFee);//代扣个人午餐费
        writeToCellAt(RsltTbl.XlastMonthDiff,RsltTbl.YlastMonthDiff,result.lastMonthDiff);//调整上月误差
        writeToCellAt(RsltTbl.XtotalSalary,RsltTbl.YtotalSalary,result.totalSalary);//应发工资总额

        saveToFile(outputFile);
    }


    public static void main(String[]args)
    {
//        List<EmployeeWorkHours> hoursList=getAllEmployeesWorkHoursFromXlsx("C:\\Users\\admin\\Documents" +
//                "\\WeChat Files\\wxid_97ch7mz2v4bz12\\FileStorage\\File\\2023-07\\wkhr_template.xlsx");
//        System.out.println(hoursList);

        EmployeeSalaryResult result = new EmployeeSalaryResult();
        result.employeeName="李四";
        result.hourlyRate=(float) 10.5;
        result.days3to5H=10;
        result.normalSubsidy=20;
        result.totalSalary=1500;
        initialize(templateFile);
//        writeToCellAt(RsltTbl.XemployeeName,RsltTbl.YemplyeeName,result.employeeName);
//        saveToFile("output.xlsx");
        writeResultToFile(result,"output.xlsx");
    }
}