package com.github.teilzeitstudent.karafjavafx.main;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.teilzeitstudent.karafjavafx.greetings.spi.GreetingsService;

/**
 * Manually launch the {@link JavaxExample} application
 */
public class Launcher {
	private static final Logger LOGGER = LoggerFactory.getLogger(Launcher.class);
	public static void main(String[] args) throws Exception {
		List<GreetingsService> services = new ArrayList<GreetingsService>();
		services.add(new GreetingsService() {
			
			@Override
			public void printGreeting() {
				LOGGER.info("Hi");
				
			}
		});
		services.add(new GreetingsService() {
			
			@Override
			public void printGreeting() {
				LOGGER.info("Hello");
				
			}
		});
		
		JavaxExample example = new JavaxExample();
		example.setGreetings(services);
		
		example.start((BundleContext)null);
	}
}
