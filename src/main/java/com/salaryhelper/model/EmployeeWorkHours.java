package com.salaryhelper.model;

//工时
public class EmployeeWorkHours {
     String employeeName;
     int days3to5H; // 3-5H天数
     int daysGT5H; // >5H天数
     int daysGT11H; // >11H天数
     float weekdaysHours; //工作日工时
     float weekdaysOvertimeHours; //工作日加班工时
     float weekendOvertimeHours; //休息日加班工时
     float holidayHours;  //法定休息日正常工时
     float holidayOvertimeHours; //法定休息日加班工时
     float paidLeaveHours; //带薪假工时
     float sickLeaveHours; //病假工时
     ////////////////////
     float after20Subsidy; ///20点后夜班补贴
     float absences;  //缺勤次数
     float c2Subsidy;    //C2夜班补贴
     float fullAttendanceReward; //全勤奖
     float meals5;  //5元餐次数
     float meals11; //11元餐次数
     float meas12; //12元餐次数
     float overtimeSubsidy;   //加班补贴
     float cleanerSubsidy;    //清洁补贴
     float unionFee;     //工会费

     public EmployeeWorkHours() {
     }

     public String getEmployeeName() {
          return employeeName;
     }

     public void setEmployeeName(String employeeName) {
          this.employeeName = employeeName;
     }

     public void setDays3to5H(int days3to5H) {
          this.days3to5H = days3to5H;
     }

     public void setDaysGT5H(int daysGT5H) {
          this.daysGT5H = daysGT5H;
     }

     public void setDaysGT11H(int daysGT11H) {
          this.daysGT11H = daysGT11H;
     }

     public void setWeekdaysHours(float weekdaysHours) {
          this.weekdaysHours = weekdaysHours;
     }

     public void setWeekdaysOvertimeHours(float weekdaysOvertimeHours) {
          this.weekdaysOvertimeHours = weekdaysOvertimeHours;
     }

     public void setWeekendOvertimeHours(float weekendOvertimeHours) {
          this.weekendOvertimeHours = weekendOvertimeHours;
     }

     public void setHolidayHours(float holidayHours) {
          this.holidayHours = holidayHours;
     }

     public void setHolidayOvertimeHours(float holidayOvertimeHours) {
          this.holidayOvertimeHours = holidayOvertimeHours;
     }

     public void setPaidLeaveHours(float paidLeaveHours) {
          this.paidLeaveHours = paidLeaveHours;
     }

     public void setSickLeaveHours(float sickLeaveHours) {
          this.sickLeaveHours = sickLeaveHours;
     }

     public void setAfter20Subsidy(float after20Subsidy) {
          this.after20Subsidy = after20Subsidy;
     }

     public void setAbsences(float absences) {
          this.absences = absences;
     }

     public void setC2Subsidy(float c2Subsidy) {
          this.c2Subsidy = c2Subsidy;
     }

     public void setFullAttendanceReward(float fullAttendanceReward) {
          this.fullAttendanceReward = fullAttendanceReward;
     }

     public void setMeals5(float meals5) {
          this.meals5 = meals5;
     }

     public void setMeals11(float meals11) {
          this.meals11 = meals11;
     }

     public void setMeas12(float meas12) {
          this.meas12 = meas12;
     }

     public void setOvertimeSubsidy(float overtimeSubsidy) {
          this.overtimeSubsidy = overtimeSubsidy;
     }

     public void setCleanerSubsidy(float cleanerSubsidy) {
          this.cleanerSubsidy = cleanerSubsidy;
     }

     public void setUnionFee(float unionFee) {
          this.unionFee = unionFee;
     }

     @Override
     public String toString() {
          return "EmployeeWorkHours{" +
                  "employeeName='" + employeeName + '\'' +
                  ", days3to5H=" + days3to5H +
                  ", daysGT5H=" + daysGT5H +
                  ", daysGT11H=" + daysGT11H +
                  ", weekdaysHours=" + weekdaysHours +
                  ", weekdaysOvertimeHours=" + weekdaysOvertimeHours +
                  ", weekendOvertimeHours=" + weekendOvertimeHours +
                  ", holidayHours=" + holidayHours +
                  ", holidayOvertimeHours=" + holidayOvertimeHours +
                  ", paidLeaveHours=" + paidLeaveHours +
                  ", sickLeaveHours=" + sickLeaveHours +
                  ", after20Subsidy=" + after20Subsidy +
                  ", absences=" + absences +
                  ", c2Subsidy=" + c2Subsidy +
                  ", fullAttendanceReward=" + fullAttendanceReward +
                  ", meals5=" + meals5 +
                  ", meals11=" + meals11 +
                  ", meas12=" + meas12 +
                  ", overtimeSubsidy=" + overtimeSubsidy +
                  ", cleanerSubsidy=" + cleanerSubsidy +
                  ", unionFee=" + unionFee +
                  '}';
     }
}
