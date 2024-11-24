
open module vet.care.api {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.aop;
    requires spring.core;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
    requires spring.context.support;
    requires lombok;
    requires spring.jdbc;
    requires java.sql;
    requires spring.boot.starter.mail;
    requires jakarta.mail;

    exports vetcare.api;
    exports vetcare.gui;
}