import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONObject;

public class BookClient {

    private static final String URL = "https://www.googleapis.com/books/v1/volumes?q=harry+potter+inauthor:rowling" +
            "&key=AIzaSyAdhqZaUyS--kQ3BnAjUOmmfSc70FeCYwg";
    private static final String TEXT = "text"; // key for reading JSON

    private static final String USER_ERROR_MSG = "<html>Sorry, an error happened." +
            "<br>Good thing you are AWESOME at debugging!</html>";

    public static String getHarry() {
        try {
            HttpResponse<JsonNode> response = Unirest.get(URL).header("accept", "application/json").asJson();
            JSONObject jsonObject = response.getBody().getObject();
            return  jsonObject.getString("kind");
        } catch (UnirestException ue){
            System.out.println(ue);
            return USER_ERROR_MSG;
        }
    }
}


/*
public class ComplimentClient {
    public static String getCompliment() {
        try {
            HttpResponse<JsonNode> response = Unirest.get(SERVER_URL + "random")
                    .header("accept", "application/json")
                    .asJson();

            JSONObject jsonObject = response.getBody().getObject();
            return jsonObject.getString(TEXT);
        } catch (UnirestException e){
            System.out.println(e); // for debugging
            return USER_ERROR_MSG;
        }
    }
}

*
* */