package com.salaryhelper.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.awt.Desktop;



public class FileUtil {
    static String ruleSourcePath="src/main/resources/com/salaryhelper/excels/template/rule_template.xlsx";
    static String ruleTempPath="src/main/resources/com/salaryhelper/excels/temporary/rule_template.xlsx";
    static String wkhrSourcePath="src/main/resources/com/salaryhelper/excels/template/wkhr_template.xlsx";
    static String wkhrTempPath="src/main/resources/com/salaryhelper/excels/temporary/wkhr_template.xlsx";
    static void openFile(String filePath)
    {
        try {
            File file = new File(filePath); // 创建文件对象，路径为filePath
            Desktop.getDesktop().open(file); // 启动已在本机桌面上注册的关联应用程序，打开文件对象file。
        } catch (IOException | NullPointerException e) { // 异常处理
            AlertUtil.warning("无法打开文件，请重试");
        }
    }

    public static void openRuleTemplate()
    {
        //复制源文件到临时文件再打开，可保证源文件不被修改
        try {
            FileUtils.copyFile(new File(ruleSourcePath),new File(ruleTempPath));
        } catch (IOException e) {
            AlertUtil.warning("无法打开文件，请重试");
        }

        openFile(ruleTempPath);
    }
    public static void openWkhrTemplate()
    {//复制源文件到临时文件再打开，可保证源文件不被修改
        try {
            FileUtils.copyFile(new File(wkhrSourcePath),new File(wkhrTempPath));
        } catch (IOException e) {
            AlertUtil.warning("无法打开文件，请重试");
        }

        openFile(wkhrTempPath);
    }

}
