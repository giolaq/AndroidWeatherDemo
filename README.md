Android Weather App
=========

Android Weather is a case study Android client for [World Weather Online] Api

Building
----

 Builded with Android Studio 0.8.6 and gradle 1.12

Design and Development
-----------

Android Weather App uses a number of open source library to work properly:

* [Dagger] - awesome library from Square: A dependency injector for Android and Java "Dagger is a replacement for FactoryFactory classes. It allows you to focus on the interesting classes. Declare dependencies, specify how to satisfy them, and ship your app."
* [Retrofit] - A type-safe REST client for Android and Java. Retrofit turns your REST API into a Java interface.
* [Picasso] - A powerful image downloading and caching library for Android, used to load forecast weather icons.
* [FloatingActionButton] - An open source implementation of Material Design FAB waiting for new Android Support Library officializing this new cool component.
* [ListViewAnimations] - An framework to create animations on Lists, used for swipe card animations.
* [ActionBar-PullToRefresh] - A deprecated library to implement PulltoRefresh but still valid IMHO.

Using
--------------
By Default Android Weather App load weather forecast for to Dublin, London, New York and Barcelona.
You can add a new city to the list tapping the Floating Action Button and inserting the city name you want in the Input Dialog and tap OK.
You can swipe left or right on a Card to Dismiss it and to reload forecast you have to pull down

Screenshots
-------------
![alt][screenshot1]
![alt][screenshot2]
![alt][screenshot3]

ToDo
--------------
* Tap on Card to launch a new Fragment to visualize detailed forecast.
* Testing.
* Error checking and graceful handling. "No connection..."
* Inserting a place not just by name but for Latitude and Longitude from LocationService.


[World Weather Online]:http://www.worldweatheronline.com/
[Dagger]:http://square.github.io/dagger/
[Retrofit]:http://square.github.io/retrofit/
[ActionBar-PullToRefresh]:https://github.com/chrisbanes/ActionBar-PullToRefresh
[Picasso]:http://square.github.io/picasso/
[FloatingActionButton]:https://github.com/makovkastar/FloatingActionButton
[ListViewAnimations]:https://github.com/nhaarman/ListViewAnimations
[screenshot1]:https://raw.githubusercontent.com/joaobiriba/AndroidWeatherDemo/master/screenshots/screen1.png
[screenshot2]:https://raw.githubusercontent.com/joaobiriba/AndroidWeatherDemo/master/screenshots/screen2.png
[screenshot3]:https://raw.githubusercontent.com/joaobiriba/AndroidWeatherDemo/master/screenshots/screen3.png
