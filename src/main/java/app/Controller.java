package app;

import static spark.Spark.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * 
 * @author Tanya
 *
 */
public class Controller {
		
	//options, get, head: idempotent and safe
	public static void main(String[] args) {
		System.out.println("Setting up server");
		DataAccess.prepare();
		
		get("/hello/:name", (request, response) -> {
		    return "Hello: " + request.params(":name");
		});
		
		get("/latest", (res,req) -> {
			Date today = new Date();
			return DataAccess.getTakeOff("Chicago, IL: Chicago O'Hare International", "Delta Air Lines Inc.", today);
		});
		
		get("/calculation", (req,res) -> {
			return "Hello: Here is the calculation";
		});
		
		get("/",(req,res) -> "<!DOCTYPE html>" +
		         "<html>" +
		         "<head>" +
		           "<title>Runwayt</title>" +
		           "<link rel='stylesheet' + href='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css'>" +
		         "</head>" +
		        "<body>" +
		           "<h1>Runwayt</h1>" +
		           "<p>Pick the take-off airport for your trip:</p>" +
		           "<select>" +
		           	"<option value='lax'>LAX - Los Angeles International Airport</option>" + 
		           	"<option value='mmh'>MMH - Mammoth Yosemite Airport</option>" +
		           	"<option value='other'>Other airports coming soon...</option>" +
		           "</select>" +
		           "<p>Pick the landing airport for your trip:</p>" +
		           "<select>" +
		           	"<option value='lax'>LAX - Los Angeles International Airport</option>" + 
		           	"<option value='mmh'>MMH - Mammoth Yosemite Airport</option>" +
		           	"<option value='other'>Other airports coming soon...</option>" +
		           "</select>" +
		           "<li><a href='/calculation'>Calculate!</a></li>" +

		           "<p></p>" +
		         "</body>" +
		       "</html>");
		options("/",(req,res) -> {
			return "Request: " + req;
		});
	}
	
	
	
	
	private String renderContent(String htmlFile) {
	    try {
	        // If you are using maven then your files
	        // will be in a folder called resources.
	        // getResource() gets that folder
	        // and any files you specify.
	        URL url = getClass().getResource(htmlFile);

	        // Return a String which has all
	        // the contents of the file.
	        Path path = Paths.get(url.toURI());
	        return new String(Files.readAllBytes(path), Charset.defaultCharset());
	    } catch (IOException | URISyntaxException e) {
	        // Add your own exception handlers here.
	    }
	    return null;
	}
}
