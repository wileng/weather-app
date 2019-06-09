/*
	The class WeatherApp uses the OpenWeatherMap API to print out weather
	information about a particular city.

	Created By William Eng for Professor Dimpsey's CSS490 Cloud Computing
	class for Winter 2019, as part of a submission for Program 2.
*/


import java.net.*;
import java.io.*;
import com.google.gson.*;

public class WeatherApp {
        public static final String APIURL = "https://api.openweathermap.org/data/2.5/weather?q=";
        public static final String API_KEY = "&appid=1334d0f19f9b5601d8d0eb815590b172";
        public static String city;
        public static URL url;

        public WeatherApp(String c) {
                try {
                        url = new URL(APIURL + c + API_KEY);
                        city = c;
                } catch (MalformedURLException e) {
                        System.out.println("Malformed URL, exiting");
                        System.exit(-1);
                }
        }

        //Checks if the city is recognizable by the OpenWeatherMap API
        //If it is, returns true. Else, return false;
        public boolean isValidCity() throws Exception {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                if(conn.getResponseCode() >= 400) {
                        System.out.println(city + " is not a valid city");
                        return false;
                }
                return true;
        }
        
        //Tries to connect the OpenWeatherMap API
        //If a connection is made without error, returns true
        //Else, if an exception is raised, return true
        public boolean isServerReachable(){
        	try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		} catch (Exception e) { 
			return false;
		}
                return true;
        }

        //Retrives the JSON in the form of a string from the formed URl
        public String getJson() throws Exception{
                StringBuilder json_sb = new StringBuilder();
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String input;
                while((input = read.readLine()) != null){
                        json_sb.append(input);
                }
                read.close();
                return json_sb.toString();
        }

        //Prints weather information based on the given JSON string
        public void printInfo(String json_string) {
                //Creating objects based on the JSON string to get weather information
                JsonObject jobj = new Gson().fromJson(json_string.toString(), JsonObject.class);
                JsonArray jarr_weather = jobj.get("weather").getAsJsonArray();
                JsonObject jobj_main = jobj.get("main").getAsJsonObject();
                JsonObject jobj_sys = jobj.get("sys").getAsJsonObject();
                JsonObject temp = jarr_weather.get(0).getAsJsonObject();

                System.out.println("-------------------------------------------------------------");
		System.out.println(city.toUpperCase());
                System.out.println("Currently: " + temp.get("main").getAsString());
                System.out.println("Description: " + temp.get("description").getAsString());
                System.out.printf("Temperature: %.3f degrees Celcius\n", (jobj_main.get("temp").getAsInt() - 273.15));
                System.out.println("Humidity: " + jobj_main.get("humidity").getAsString() + "%");

                long sunrise_epoch = jobj_sys.get("sunrise").getAsLong();
                long sunset_epoch = jobj_sys.get("sunset").getAsLong();

                //Convert epoch time for sunrise and sunset to readable format
                String sunrise = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (sunrise_epoch*1000));
                String sunset = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (sunset_epoch*1000));

                System.out.println("Sunrise at: " + sunrise + " UTC");
                System.out.println("Sunset at: " + sunset + " UTC");         
                System.out.println("-------------------------------------------------------------");


        }

}


