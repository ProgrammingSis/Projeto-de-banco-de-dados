
open module vet.care.api {
	requires java.sql;
	requires jakarta.mail;
	requires javafx.controls;
	requires javafx.fxml;
	requires lombok;
	requires spring.aop;
	requires spring.core;
	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.context;
	requires spring.beans;
	requires spring.context.support;
	requires spring.jdbc;

	exports vetcare.api;
	exports vetcare.gui;
	exports vetcare;
}