package utilities.dataManagers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JasonFileReader {


    public static JSONObject getJSONObject() {
        JSONParser jParser = new JSONParser();
        Object obj = null;
        try {
            obj = jParser.parse(new FileReader("src//main//resources//Data//config.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        JSONObject jObj = (JSONObject)obj;
        return jObj;
    }

    public static String getJSONObjectContent(String contentKey)  {
        JSONObject jObj= null;
        try {
            jObj = getJSONObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String value =(String)jObj.get(contentKey);
        return value;

    }
    public static JSONArray getJSONObjects(String contentKey) {

        JSONObject jObj= null;
        try {
            jObj = getJSONObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        JSONArray jObjects=(JSONArray)jObj.get(contentKey);
        return jObjects;
    }

    public static String getJsonMatchingObjectContent(String environment,String keys, String key) {
        JSONArray list=getJSONObjects(keys);
        for(int i =0;i<list.size();i++)
        {
            JSONObject Jobj1 = (JSONObject)list.get(i);
            if(environment.toLowerCase().equals(Jobj1.get("enviorment").toString().toLowerCase()))
            {
                return (String)Jobj1.get(key);
            }

        }
        return null;
    }
}
