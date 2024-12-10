import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SongClient {
    public static void main(String[] args) {
        String url = "https://itunes.apple.com/search?term=karan+aujla&limit=25";
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();

        try {
           HttpResponse<String> response= client.send(request, HttpResponse.BodyHandlers.ofString());


        //    System.out.println(response.body());
        System.out.println(response.statusCode());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        // System.out.println(URI.create(url).getQuery());
    }
}