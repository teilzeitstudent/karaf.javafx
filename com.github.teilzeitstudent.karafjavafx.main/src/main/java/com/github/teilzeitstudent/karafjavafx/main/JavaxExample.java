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
		System.out.println("Start");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stop");
	}

}
