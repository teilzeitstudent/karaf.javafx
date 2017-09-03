# Karaf & Java FX :: German Greeting
Implement the `GreetingService` to print "Hello world" in German.

## Overview
Not much to it. The service interface is implemented & exported via

	@Component
	public class GermanGreetingService implements GreetingsService {
	
and prints to the log like this:

	@Override
	public void printGreeting() {
		LOGGER.info("Hallo Welt!");
	}
	
The standard `toString` method is overridden to return the name of this service. 
This is required to display the item correctly in the JavaFX UI:

	public String toString() {
		return "German greeting";
	}
	
## Manual deployment
First, build the bundle via

	mvn clean install
	
Start karaf, e.g. via

	./start-shell
	
Once running, install the bundle:

	bundle:install --start mvn:com.github.teilzeitstudent/karafjavafx.greetings.german/LATEST
	Bundle ID: 59
	
Obviously, the `karafjavafx.greetings.spi` bundle needs to be installed.

If all goes well, you should see the exported service:
	
	Provide-Capability = 
	osgi.service;objectClass:List<String>=com.github.teilzeitstudent.karafjavafx.greetings.spi.GreetingsService
