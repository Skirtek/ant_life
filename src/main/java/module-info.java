module com.codecool.ant_life {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens com.codecool.ant_life to javafx.fxml;
    exports com.codecool.ant_life;
}