module com.ludo.ludo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ludo.ludo to javafx.fxml;
    exports com.ludo.ludo;
}