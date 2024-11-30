module projects.rupizzeria.rupizzeria {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires junit;


    opens projects.rupizzeria.rupizzeria to javafx.fxml;
    exports projects.rupizzeria.rupizzeria;
    opens projects.rupizzeria.rupizzeria.controllers to javafx.fxml;
    exports projects.rupizzeria.rupizzeria.controllers;
    exports projects.rupizzeria.rupizzeria.pizza;
    opens projects.rupizzeria.rupizzeria.pizza to javafx.fxml;
    exports projects.rupizzeria.rupizzeria.util;
    opens projects.rupizzeria.rupizzeria.util to javafx.fxml;
    exports projects.rupizzeria.rupizzeria.pizza.impl;
    opens projects.rupizzeria.rupizzeria.pizza.impl to javafx.fxml;
}