Created by William Eng

The program works by running a while-loop that prompts the user for a valid city name. It will try to connect to the OWM API 4 times. If it unable to connect, an error message will be printed, and the program will be terminated. If the city is not recognizable by the OWM API, it will print an error and re-prompt the user for another city. Once the program receives a valid city, it will print out weather information for that city, which includes the description of weather conditions (clear, cloudy, rainy, etc.), temperature, humidity, and the times of sunset and sunrise.

It utilizes the OpenWeatherMap (OWM) API to get weather information, and the GSON library to deserialize JSON strings/files.

To build, first make sure that all files are in the same folder. Then, execute the following commands:
--->javac WeatherApp.java
--->javac main.java

To run, execute the following command:
--->java main
Enter a city, and weather information should appear.
