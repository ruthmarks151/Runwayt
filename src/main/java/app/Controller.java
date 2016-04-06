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

	// options, get, head: idempotent and safe
	public static void main(String[] args) {
		System.out.println("Setting up server");
		DataAccess.prepare();

		post("/calculation", (req, res) -> {
			String origin = req.queryParams("origin");
			String destination = req.queryParams("destination");
			String airline = req.queryParams("airline");
			DelayProfile profile = Estimation.findDelay(origin, destination, airline);
			return View.chartPage(profile);
		});

		get("/", (req, res) -> "<!DOCTYPE html>" + "<html>" + "<head>" + "<title>Runwayt</title>"
				+ "<link rel='stylesheet' + href='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css'>"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
				+ "</head>" 
				+ "<body>"
				+ "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js\"></script>"
				+ "<link rel=\"stylesheet\" href=\"https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css\">\n" 
				+ "<script src=\"https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js\"></script>"
				+ "<div class=\"container\">\n" 
				+ "			<h1 class=\"text-primary text-center\">\n"
				+ "				Runwayt\n" 
				+ "			</h1>\n" 
				+ "			<form role=\"form\" method=\"POST\" action=\"calculation\" >\n"
				+ "				<div class=\"form-group\">\n"  
				+ "  					<label for=\"origin\">Origin Airport</label>\n" 
				+ "  					<select name=\"origin\" class=\"form-control\" id=\"origin\">\n" 
				+ 							View.optionString(DataRead.getAirportNames())
				+ "  					</select>\n" 
				+ "				</div>\n"
				+ "				<div class=\"form-group\">\n"  
				+ "  					<label for=\"dest\">Destination Airport</label>\n" 
				+ "  					<select name=\"destination\" class=\"form-control\" id=\"dest\">\n" 
				+ 							View.optionString(DataRead.getAirportNames())
				+ "  					</select>\n" 
				+ "				</div>\n"
				+ "				<div class=\"form-group\">\n"  
				+ "  					<label for=\"airline\">Airline</label>\n" 
				+ "  					<select name=\"airline\" class=\"form-control\" id=\"airline\">\n" 
				+ 							View.optionString(DataRead.getAirline())
				+ "  					</select>\n" 
				+ "				</div>\n"
				+ "				<button type=\"submit\" class=\"btn btn-default\">\n" 
				+ "					Submit\n"
				+ "				</button>\n" 
				+ "			</form>\n"  
				+ "</div>"
				+ "</body>" + "</html>");
		options("/", (req, res) -> {
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
