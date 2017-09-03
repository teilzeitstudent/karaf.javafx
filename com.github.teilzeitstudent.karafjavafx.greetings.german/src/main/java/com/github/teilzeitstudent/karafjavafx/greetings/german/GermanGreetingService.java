package com.github.teilzeitstudent.karafjavafx.greetings.german;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.teilzeitstudent.karafjavafx.greetings.spi.GreetingsService;

@Component
public class GermanGreetingService implements GreetingsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(GermanGreetingService.class);

	@Override
	public void printGreeting() {
		LOGGER.info("Hallo Welt!");
	}
	
	public String toString() {
		return "German greeting";
	}
}
