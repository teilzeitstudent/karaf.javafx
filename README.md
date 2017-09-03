# Karaf & JavaFX UI Prototype

Exlore how to use JavaFX UIs when running in a Karaf- based OSGI environment.

## Overview
`start.sh` start the Karaf instance for normal operations
`start-dev,sh` start the Karaf instance for development: Enables debugging & clears the `data/` directory of previously installed bundles

## Requirements
### JavaFX
Obviously, needs JavaFX installed on the machine. This is included in the Oracle JDK. On linux:

    dnf install java-1.8.0-openjdk-openjfx
    
Note that Eclipse will complain about usage of the JavaFX classes. See https://stackoverflow.com/a/22815472 
For eclipse, it's probably best to install E(fx)clipse. This will get rid of the "Access Restriction" problems etc. See https://stackoverflow.com/a/22815472 and this guide: https://www.eclipse.org/efxclipse/install.html
