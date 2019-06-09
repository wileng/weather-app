/*
	The driver class that uses the WeatherApp class.

	Created By William Eng for Professor Dimpsey's CSS490 Cloud Computing
	class for Winter 2019, as part of a submission for Program 2.
*/

import java.util.Scanner;

public class main {
        //Runs a while loop that accepts a user-inputed city
        //If the city is valid, the problem will print out weather information,
        //and then prompt the user for another city
        //If the city is invalid, it will prompt the user for another city
        public static void main(String args[]) throws Exception{
                String city;
                Scanner sc = new Scanner(System.in);
                while(true){
                        System.out.print("Please enter a city: ");
                        city = sc.nextLine();
                        WeatherApp weather = new WeatherApp(city);
                        //Tries 3 times to connect to the server
                        for(int i = 0; i < 3; i++) {
	                        if(!weather.isServerReachable()) {
	                        	Thread.sleep(i * 2000);
	                        } else {
	                        	break;
	                        }
                        }
                        //If the server is unreachable a 4th time, an error message
                        //is printed, and the program is terminated.
                        if(!weather.isServerReachable()) {
                        	System.out.println("Tried 4 times to connect. OpenWeatherMap API is currently unreachable.");
				System.out.println("Terminating program. Please try agian in a few minutes");
                        	System.exit(-1); 
                        }

                        if(weather.isValidCity()){
                                String json_string = weather.getJson();
                                weather.printInfo(json_string);
                        }
                        System.out.println();
                }
        }
}
