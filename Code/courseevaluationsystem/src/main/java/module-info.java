module qau.ces {
    
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires transitive javafx.base;
    requires transitive javafx.graphics;
    requires fontawesomefx; // Add missing fontawesomefx module

    opens qau.ces to javafx.fxml;
    
    
    exports qau.ces;
}
