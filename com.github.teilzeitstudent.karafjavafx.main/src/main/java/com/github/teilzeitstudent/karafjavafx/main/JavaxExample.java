package com.github.teilzeitstudent.karafjavafx.main;

import java.util.concurrent.Executors;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavaxExample extends Application implements BundleActivator {
	private static final Logger LOGGER = LoggerFactory.getLogger(JavaxExample.class);

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Hello World!");
		Button btn = new Button();
		btn.setText("Say 'Hello World'");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				LOGGER.info("Hello World!");
			}
		});

		StackPane root = new StackPane();
		root.getChildren().add(btn);
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}

	@Override
	public void start(BundleContext context) throws Exception {
		LOGGER.trace(">> start");
		Executors.defaultThreadFactory().newThread(() -> {
			// Hack the classloader. Otherwise will run into
			// java.lang.ClassNotFoundException:
			// com.github.teilzeitstudent.karafjavafx.main.JavaxExample
			// with origin in javafx.application.Application.launch(Application.java:248)
			// see
			// http://paulonjava.blogspot.co.uk/2014/11/making-javafx-better-with-osgi.html
			Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
			LOGGER.debug("Launching application...");
			launch();
		}).start();
		LOGGER.trace("<< start");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		LOGGER.trace(">> stop");
		LOGGER.trace("<< stop");
	}

}
