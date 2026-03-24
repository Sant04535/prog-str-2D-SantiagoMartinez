module org.example.practicaexamen {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.practicaexamen to javafx.fxml;
    exports org.example.practicaexamen;
}
