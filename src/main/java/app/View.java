package app;

//import static spark.Spark.*;

/**
 * Shows the user the screen where the user can make choices and shows the output. 
 * @author Tanya
 *
 */
public class View {
	public static String optionString(String[] list){
		StringBuilder sb = new StringBuilder();
		for (String s:list){
			sb.append("<option value='");
			sb.append(s);
			sb.append("'>");
			sb.append(s);
			sb.append("</option>");
		}
	    return sb.toString();
	}
	
	public static String chartPage(DelayProfile profile){
		return "<!DOCTYPE html>" 
				+ "<html>" 
				+ "<head>" 
				+ "<title>Runwayt</title>"
				+ "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css'>"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
				+ "</head>\n" 
				+ "<body>\n"
				+ "<script src=\"//raw.githubusercontent.com/nnnick/Chart.js/master/Chart.min.js\"></script>\n"
				+ "<div class=\"container\">\n" 
				+ "			<h1 class=\"text-primary text-center\">\n"
				+ "				Delay Projection\n" 
				+ "			</h1>\n" 
				+ chart(profile)
				+ "</div>"
				+ "</body>" 
				+ "</html>";
	}
	
	public static String chart(DelayProfile dp){
		return  "<div style=\"width:100%; height:100%;\"> "+
				"<canvas id=\"myChart\" width=\"600px\" height=\"600px\"></canvas>\n" + 
				"<script>"+
				"var ctx = document.getElementById(\"myChart\").getContext(\"2d\");\n" + 
				"var data = {\n" + 
				"    labels: "+dp.delays()+",\n" + 
				"    datasets: [\n" + 
				"        {\n" + 
				"            label: \"My First dataset\",\n" + 
				"            fillColor: \"rgba(151,187,205,0.2)\",\n" + 
				"            strokeColor: \"rgba(151,187,205,1)\",\n" + 
				"            pointColor: \"rgba(151,187,205,1)\",\n" + 
				"            pointStrokeColor: \"#fff\",\n" + 
				"            pointHighlightFill: \"#fff\",\n" + 
				"            pointHighlightStroke: \"rgba(151,187,205,1)\","+
				"            data: "+ dp.freqs() +"\n" + 
				"        },\n" + 
				"    ]\n" + 
				"};" +
				"var myLineChart = new Chart(ctx).Line(data);\n"+
				"</script>"+
				"</div>";
	}
}
