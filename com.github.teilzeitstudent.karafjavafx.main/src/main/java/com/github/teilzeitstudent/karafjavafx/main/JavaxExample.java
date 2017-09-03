package com.github.teilzeitstudent.karafjavafx.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.teilzeitstudent.karafjavafx.greetings.spi.GreetingsService;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

@Component
public class JavaxExample extends Application {
	private static final Logger LOGGER = LoggerFactory.getLogger(JavaxExample.class);
	private ListView<GreetingsService> listView;
	private ObservableList<GreetingsService> listViewContent;
	
	private List<GreetingsService> greetings;

	@Override
	public void start(Stage primaryStage) throws Exception {
		LOGGER.trace(">> start");
		findServicesInRegistry();
		primaryStage.setTitle("Hello World!");
		
		if (greetings == null) {
			greetings = new ArrayList<GreetingsService>();
			// add some fake services for manual testing
			// They can not be set from caller, because JavaFX will create a new instance
			// Have to use comments to create this during manual tests...
			//addFakeServices();
		}
		
		listView = new ListView<GreetingsService>();
		listViewContent = FXCollections.observableArrayList (greetings);
		listView.setItems(listViewContent);
		Button btn = new Button();
		btn.setText("Say 'Hello World'");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				LOGGER.trace("Pressed button...");
				if (listView.getSelectionModel().getSelectedItem() != null) {
					listView.getSelectionModel().getSelectedItem().printGreeting();
				}
			}
		});

		StackPane root = new StackPane();
		root.getChildren().add(listView);
		root.getChildren().add(btn);
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.setOnCloseRequest(onClose);
		primaryStage.show();
		LOGGER.trace("<< start");
	}
	
	private void findServicesInRegistry() throws InvalidSyntaxException {
		LOGGER.trace(">> findServicesInRegistry");
		
		final BundleContext bundleContext = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		Collection<ServiceReference<GreetingsService>> services = bundleContext.getServiceReferences(GreetingsService.class, null);
		LOGGER.debug("Found {} greeting services", services.size());
		
		if (greetings == null) {
			greetings = new ArrayList<GreetingsService>();
		}
		
		for (Iterator<ServiceReference<GreetingsService>> iterator = services.iterator(); iterator.hasNext();) {
			ServiceReference<GreetingsService> serviceReference = iterator.next();
			LOGGER.debug("Adding service from bundle {}", serviceReference.getBundle().getBundleId());
			GreetingsService service = bundleContext.getService(serviceReference);
			greetings.add(service);
		}
		
		LOGGER.trace("<< findServicesInRegistry");
	}

	@Activate
	public void activate(BundleContext context) throws Exception {
		LOGGER.trace(">> activate");
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
		LOGGER.trace("<< activate");
	}

	@Deactivate
	public void stop(BundleContext context) throws Exception {
		LOGGER.trace(">> stop");
		LOGGER.trace("<< stop");
	}
	
	private final EventHandler<WindowEvent> onClose = (WindowEvent event) -> {
		if (event.getEventType().equals(WindowEvent.WINDOW_CLOSE_REQUEST)) {
			LOGGER.info("Recieved close event from UI");
			CompletableFuture.runAsync(() -> {
				try {
					BundleContext bc = FrameworkUtil.getBundle(JavaxExample.class).getBundleContext();
					Bundle bundle = bc.getBundle(0);
					bundle.stop();
				} catch (Exception e) {
					System.err.println("Error when shutting down Apache Karaf");
				}
			});
		}
	};
	
	protected void addFakeServices() {
		greetings.add(new GreetingsService() {
			
			@Override
			public void printGreeting() {
				LOGGER.info("Hi");
				
			}

			@Override
			public String toString() {
				return "Service A";
			}
			
			
		});
		
		greetings.add(new GreetingsService() {
			
			@Override
			public void printGreeting() {
				LOGGER.info("Hello");
				
			}
			
			@Override
			public String toString() {
				return "Service B";
			}
		});
	}

}
