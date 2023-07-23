package com.salaryhelper.model;

//员工薪酬构成规则
public class EmployeeSalaryRule {
    String employeeName;
    float hourlyRate; //工时工资
    float dailySubsidy; //日津贴
    String outStandingLevel;  //优秀员工等级
    float outstandingReward; //优秀员工
    String techJobLevel;  //技术岗位等级
    float techJobSubsidy; //技能岗位补贴
    float specialJobSubsidy; //特殊岗位补贴
    float lunchSubsidyStandard; //午餐补贴标准
    float nightSubsidy;     //夜班补贴
    float normalSubsidy;     //常规绩效
    float otherSubsidy;     //其他补贴
    float lastMonthDiff;    //调整上月误差

    public EmployeeSalaryRule() {
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setHourlyRate(float hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void setDailySubsidy(float dailySubsidy) {
        this.dailySubsidy = dailySubsidy;
    }

    public void setOutStandingLevel(String outStandingLevel) {
        this.outStandingLevel = outStandingLevel;
    }

    public void setOutstandingReward(float outstandingReward) {
        this.outstandingReward = outstandingReward;
    }


    public void setTechJobLevel(String techJobLevel) {
        this.techJobLevel = techJobLevel;
    }

    public void setTechJobSubsidy(float techJobSubsidy) {
        this.techJobSubsidy = techJobSubsidy;
    }

    public void setSpecialJobSubsidy(float specialJobSubsidy) {
        this.specialJobSubsidy = specialJobSubsidy;
    }

    public void setLunchSubsidyStandard(float lunchSubsidyStandard) {
        this.lunchSubsidyStandard = lunchSubsidyStandard;
    }

    public void setNightSubsidy(float nightSubsidy) {
        this.nightSubsidy = nightSubsidy;
    }

    public void setNormalSubsidy(float normalSubsidy) {
        this.normalSubsidy = normalSubsidy;
    }

    public void setOtherSubsidy(float otherSubsidy) {
        this.otherSubsidy = otherSubsidy;
    }


    public void setLastMonthDiff(float lastMonthDiff) {
        this.lastMonthDiff = lastMonthDiff;
    }


}
