package com.salaryhelper.model;

//工资单
public class EmployeeSalaryResult {
    public String employeeName;
    public float hourlyRate;//工时工资
    public float dailySubsidy;//日津贴
    public int days3to5H;//3-5H天数
    public int daysGT5H; //>5H天数
    public int daysGT11H; //>11H天数
    public float monthlySubsidy;//  月津贴
    public float weekdaysHours; //工作日工时
    public float weekdaysSalary; //工作日工资
    public float weekdaysOvertimeHours; //工作日加班工时
    public float weekdaysOvertimeSalary; //工作日加班工资
    public float weekendOvertimeHours; //休息日加班工时
    public float weekendOvertimeSalary; //休息日加班工资
    public float holidayHours;     //节假日工时
    public float holidayOvertimeHours; //节假日加班工时
    public float holidaySalary;    //节假日工资
    public float paidLeaveHours; //带薪假工时
    public float paidLeaveSalary; //带薪假工资
    public float lunchSubsidy; //午餐补贴
    public float dinnerSubsidy;  //晚餐补贴
    public float fullAttendanceReward;  //全勤奖
    public String outStandingLevel;  //优秀员工等级
    public float outStandingReward;  //优秀员工奖励
    public float specialJobSubsidyDays; //特殊岗位补贴(天数)
    public float specialJobSubsidy;  //特殊岗位补贴
    public String techJobLevel;  //技术岗位等级
    public float techJobSubsidy;   //技术岗位补贴
    public float  nightSubsidy;    //夜班补贴
    public float normalSubsidy;     //常规绩效
    public float otherSubsidy;     //其他补贴
    public float lunchFee;        //代扣个人午餐费
    public float lastMonthDiff;    //调整上月误差
    public float totalSalary;      //应发工资总额
    public float socialSecu;       //代扣个人社保
    public float PCF;              //代扣个人公积金
    public float incomeTax;        //代扣个人所得税
    public float unionFee;         //工会费
    public float finalTotalSalary; //实发工资

    public EmployeeSalaryResult() {
    }

    public EmployeeSalaryResult(EmployeeSalaryRule rule, EmployeeWorkHours hours)
    {
        employeeName=rule.employeeName;
        hourlyRate=rule.hourlyRate;
        dailySubsidy=rule.dailySubsidy;
        days3to5H=hours.days3to5H;
        daysGT5H=hours.daysGT5H;
        daysGT11H=hours.daysGT11H;
        //月津贴工资 = 3~5小时天数*日津贴*50%+5小时天数*日津贴
        monthlySubsidy=rule.dailySubsidy *(days3to5H * (1/2) + daysGT5H);
        weekdaysHours=hours.weekdaysHours;
        //工作日工资=工作日工时*时薪
        weekdaysSalary=weekdaysHours*hourlyRate;
        weekdaysOvertimeHours=hours.weekdaysOvertimeHours;
        //工作日加班工资=工作日加班工时*时薪*1.5
        weekdaysOvertimeSalary=weekdaysOvertimeHours*hourlyRate*(3/2);
        weekendOvertimeHours=hours.weekendOvertimeHours;
        //休息日加班工资=休息日加班工时*时薪*2
        weekendOvertimeSalary=weekendOvertimeHours*hourlyRate*2;
        holidayHours=hours.holidayHours;
        holidayOvertimeHours=hours.holidayOvertimeHours;
        //节假日工资=节假日工时*时薪*3
        holidaySalary=(holidayHours+holidayOvertimeHours)*hourlyRate*3;
        paidLeaveHours=hours.paidLeaveHours;
        //带薪假工资=带薪假工时*时薪
        paidLeaveSalary=paidLeaveHours*hourlyRate;
        //午餐餐补=5小时天数*餐补标准
        lunchSubsidy=daysGT5H * rule.lunchSubsidyStandard;
        //晚餐餐补=11小时天数*餐补标准
        dinnerSubsidy=daysGT11H * rule.lunchSubsidyStandard;
        //全勤奖=工时表上标注的金额。如没有标注，从缺勤次数判断金额。
        fullAttendanceReward=hours.fullAttendanceReward>1e-6? hours.fullAttendanceReward:(hours.absences>4?0:200);
        outStandingLevel=rule.outStandingLevel;
        outStandingReward=rule.outstandingReward;
        //specialJobSubsidyDays=rule.specialJobSubsidyDays;
        specialJobSubsidy=rule.specialJobSubsidy;
        techJobLevel=rule.techJobLevel;
        techJobSubsidy=rule.techJobSubsidy;
        //夜班补贴= 20点后夜班补贴 + C2夜班补贴
        nightSubsidy=hours.after20Subsidy+hours.c2Subsidy;
        normalSubsidy= rule.normalSubsidy;
        //其他补贴 = 加班补贴 + 清洁补贴
        otherSubsidy=hours.cleanerSubsidy+hours.overtimeSubsidy;
        //代扣个人午餐费= 5 * 5元餐次数 + 11 * 11元餐次数 + 12 * 12元餐次数
        lunchFee= 5*hours.meals5+ 11*hours.meals11+ 12*hours.meas12;
        lastMonthDiff=rule.lastMonthDiff;
        //应发工资总额=月津贴+工作日正常工资+工作日加班工资+休息日工资+法定休息日工资+带薪假工资+午餐补贴+晚餐补贴+全勤奖+优秀员工奖金+
        //特殊岗位津贴+技能岗位津贴+夜班补贴+常规绩效+其他补贴-代扣午餐费+调整上月误差
        totalSalary=monthlySubsidy+weekdaysSalary+weekdaysOvertimeSalary+weekendOvertimeSalary+holidaySalary
                +paidLeaveSalary+lunchSubsidy+dinnerSubsidy+fullAttendanceReward+outStandingReward+specialJobSubsidy
                +techJobSubsidy+nightSubsidy+normalSubsidy+otherSubsidy-lunchFee+lastMonthDiff;
    }

}
