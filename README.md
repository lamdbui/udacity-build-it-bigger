# Build-It-Bigger Joke-telling application

This is a joke-telling app that displays a random joke with a click of a button. 

The application is built on two libraries - A Java library that 
provides jokes, a Google Cloud Endpoints (GCE) project that serves those jokes, 
an Android Library containing an activity for displaying jokes, and an Android 
app that fetches jokes from the GCE module and passes them to the Android Library 
for display.

##Features

There are two configurations for this project - 'free' and 'paid'. The 'free' version
contains ads(banner and interstitial) to be displayed to the user while the 'paid' is ad-free.

###JokeFactory
* Java library that randomly returns a joke from a set list of jokes

###JokeDisplay
* Android library housing an Activity that is passed a joke via an Intent and displays the joke

###Loading Indicator
* Progress spinner shown when a joke is being fetched from the GCE and cleared when finished

### Unit Tests
* Gradle task that launches a GCE local development server and runs basic tests (UI, libraries, and main app)
* Available in: app:other:runTests
