module com.salaryhelper.salarycalculater {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.io;
    requires poi.ooxml;
    requires poi;
    requires java.desktop;


    opens com.salaryhelper to javafx.fxml;
    exports com.salaryhelper;
    exports com.salaryhelper.controller;
    opens com.salaryhelper.controller to javafx.fxml;
}