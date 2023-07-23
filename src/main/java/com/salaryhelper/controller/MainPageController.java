package com.salaryhelper.controller;


import com.salaryhelper.service.SalaryResultService;
import com.salaryhelper.util.AlertUtil;
import com.salaryhelper.util.FileUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class MainPageController {
    @FXML
    private TextField tfHourFilePath;
    @FXML
    private TextField tfRuleFilePath;
    @FXML
    private TextField tfOutputDirectoryPath;
    @FXML
    public void onSelectHourTable(ActionEvent actionEvent) {
        FileChooser chooser=new FileChooser();
        chooser.setTitle("打开文件");
        chooser.setInitialDirectory(new File("c:\\"));
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".xlsx文件","*.xlsx"));
        Stage stage=(Stage)tfHourFilePath.getScene().getWindow();
        File file=chooser.showOpenDialog(stage);
        if(file==null)
        {
            AlertUtil.warning("未选择任何文件");
        }
        else
        {
            tfHourFilePath.setText(file.getAbsolutePath());
        }
    }
    @FXML
    public void onSelectRuleTable(ActionEvent actionEvent) {
        FileChooser chooser=new FileChooser();
        chooser.setTitle("打开文件");
        chooser.setInitialDirectory(new File("c:\\"));
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".xlsx文件","*.xlsx"));
        Stage stage=(Stage)tfRuleFilePath.getScene().getWindow();
        File file=chooser.showOpenDialog(stage);
        if(file==null)
        {
            AlertUtil.warning("未选择任何文件");
        }
        else
        {
            tfRuleFilePath.setText(file.getAbsolutePath());
        }
    }
    @FXML
    public void onSelectOutputDirectory(ActionEvent actionEvent) {
        DirectoryChooser chooser=new DirectoryChooser();
        chooser.setTitle("请选择文件夹");
        chooser.setInitialDirectory(new File("c:\\"));
        Stage stage=(Stage) tfOutputDirectoryPath.getScene().getWindow();
        File file=chooser.showDialog(stage);
        if(file==null){
            AlertUtil.warning("未选择任何文件夹");
        }else{
            tfOutputDirectoryPath.setText(file.getAbsolutePath());
        }
    }
    @FXML
    public void onExport(ActionEvent actionEvent) {
        String hourTablePath=tfHourFilePath.getText();
        String ruleTablePath=tfRuleFilePath.getText();
        String outputDirectory= tfOutputDirectoryPath.getText();
        if(hourTablePath==null ||"".equals(hourTablePath))
        {
            AlertUtil.warning("请先选择工时表文件");
            return;
        }
        if(ruleTablePath==null||"".equals(ruleTablePath))
        {
            AlertUtil.warning("请选择薪资构成表文件");
            return;
        }
        if(outputDirectory==null||"".equals(outputDirectory))
        {
            AlertUtil.warning("请选择输出目录");
            return;
        }

        SalaryResultService resultService=new SalaryResultService();
        resultService.generateResults(hourTablePath,ruleTablePath,outputDirectory);
        AlertUtil.info("导出成功");
    }
    @FXML
    public void onViewRuleTemplate(ActionEvent actionEvent) {
        FileUtil.openRuleTemplate();
    }
    @FXML
    public void onViewWkHrTemplate(ActionEvent actionEvent) {
        FileUtil.openWkhrTemplate();
    }
}
