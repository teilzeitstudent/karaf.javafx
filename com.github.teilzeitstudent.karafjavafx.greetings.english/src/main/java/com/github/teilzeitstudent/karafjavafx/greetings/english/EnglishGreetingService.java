package com.github.teilzeitstudent.karafjavafx.greetings.english;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.teilzeitstudent.karafjavafx.greetings.spi.GreetingsService;

@Component
public class EnglishGreetingService implements GreetingsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EnglishGreetingService.class);

	@Override
	public void printGreeting() {
		LOGGER.info("Hello world!");
	}
	
	public String toString() {
		return "English greeting";
	}
}
