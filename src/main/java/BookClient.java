import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONArray;
import org.json.JSONObject;

public class BookClient {

    private static final String URL_START = "https://www.googleapis.com/books/v1/volumes?q=";
    private static final String URL_END = "&key=AIzaSyAdhqZaUyS--kQ3BnAjUOmmfSc70FeCYwg";

    private static final String HARRY_URL = "https://www.googleapis.com/books/v1/volumes?q=harry+potter+inauthor:rowling" +
            "&key=AIzaSyAdhqZaUyS--kQ3BnAjUOmmfSc70FeCYwg";
    private static final String LILAC_URL = "https://www.googleapis.com/books/v1/volumes?q=lilac+girls" +
            "&key=AIzaSyAdhqZaUyS--kQ3BnAjUOmmfSc70FeCYwg";
    private static final String TEXT = "text"; // key for reading JSON
    private static final String USER_ERROR_MSG = "<html>Sorry, an error happened." +
            "<br>Good thing you are AWESOME at debugging!</html>";

    public static String buildURL(String title){
        String title_words [] = title.split(" ");
        String build_url_title = "";

        for (int i = 0 ; i < title_words.length ; i++ ){
            if (i == title_words.length - 1){
                build_url_title += title_words[i];
            }
            else {
                build_url_title += title_words[i];
                build_url_title += "+";
            }
        }

        String full_URL = URL_START + build_url_title + URL_END;
        return full_URL;
    }

    public static String getDesc(String URL){
        try {
            HttpResponse<JsonNode> response = Unirest.get(URL).header("accept", "application/json").asJson();
            JSONObject jsonObject = response.getBody().getObject();
            JSONArray items = jsonObject.getJSONArray("items");
            JSONObject itemsObj = items.getJSONObject(0);
            JSONObject volInfoObj = itemsObj.getJSONObject("volumeInfo");
            return  volInfoObj.getString("description");
        } catch (UnirestException ue){
            System.out.println(ue);
            return USER_ERROR_MSG;
        }
    }

    /* These are some testing methods and shouldn't be used in the final project */
    public static String getHarry() {
        try {
            HttpResponse<JsonNode> response = Unirest.get(HARRY_URL).header("accept", "application/json").asJson();
            JSONObject jsonObject = response.getBody().getObject();
            return  jsonObject.getString("kind");
        } catch (UnirestException ue){
            System.out.println(ue);
            return USER_ERROR_MSG;
        }
    }
    /*public static String getHarryDesc() {
        try {
            HttpResponse<JsonNode> response = Unirest.get(HARRY_URL).header("accept", "application/json").asJson();
            JSONObject jsonObject = response.getBody().getObject();
            JSONArray items = jsonObject.getJSONArray("items");
            JSONObject itemsObj = items.getJSONObject(0);
            JSONObject volInfoObj = itemsObj.getJSONObject("volumeInfo");
            return  volInfoObj.getString("description");
        } catch (UnirestException ue){
            System.out.println(ue);
            return USER_ERROR_MSG;
        }
    }*/
}