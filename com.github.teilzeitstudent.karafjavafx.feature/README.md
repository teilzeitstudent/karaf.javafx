# Karaf & Java FX :: Karaf feature
Easily install all example bundles in one go.

## Manual install
As usual, build via

    mvn clean install

Once on the Karaf console, add the repository:

	karaf@root()> feature:repo-add mvn:com.github.teilzeitstudent/karafjavafx.feature/LATEST/xml/features
	Adding feature url mvn:com.github.teilzeitstudent/karafjavafx.feature/LATEST/xml/features

And then install the feature:

    feature:install karafjavafx.feature

The UI will be started automatically.