package com.github.teilzeitstudent.karafjavafx.main;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class JavaxExample extends Application implements BundleActivator {
	public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
	}

	@Override
	public void start(BundleContext context) throws Exception {
		// Hack the classloader. Otherwise will run into
		// java.lang.ClassNotFoundException: com.github.teilzeitstudent.karafjavafx.main.JavaxExample
		// with origin in javafx.application.Application.launch(Application.java:248)
		// see http://paulonjava.blogspot.co.uk/2014/11/making-javafx-better-with-osgi.html
		Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
		launch();
		System.out.println("Start");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stop");
	}

}
