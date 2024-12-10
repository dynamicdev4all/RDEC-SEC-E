import org.json.JSONArray;
import org.json.JSONObject;

public class DemoParse {
    public static void main(String[] args) {
        String data = """
                [{"name":"Shubham","age":"21"},{"name":"Abhishek","age":"20"},{"name":"Rohan","age":"18"},{"name":"Deepak","age":"22"}]
                """;
        JSONArray arr = new JSONArray(data);
        System.out.println(arr.length());
        for (int i = 0; i < arr.length(); i++) {
            JSONObject object = arr.getJSONObject(i);
            System.out.println(object.getString("age"));
        }

    }
}
