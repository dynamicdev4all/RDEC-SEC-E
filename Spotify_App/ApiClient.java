import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

class ApiClient {
    public static void main(String[] args) {
        String musicUrl = "https://itunes.apple.com/search?term=arijit+singh&limit=25";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(musicUrl)).GET().build();

        try {
            HttpResponse<String> res = client.send(request, HttpResponse.BodyHandlers.ofString());

            String completeData = res.body();

            JSONObject dataObject = new JSONObject(completeData);
            JSONArray dataArray = dataObject.getJSONArray("results");
            ArrayList<SongModel> songList = new ArrayList<>();
            for (int i = 0; i < dataArray.length(); i++) {
                SongModel song;
                JSONObject singleSong = dataArray.getJSONObject(i);

                song = new SongModel(
                        singleSong.getString("trackName"),
                        singleSong.getString("artistName"),
                        singleSong.getString("artworkUrl100"),
                        singleSong.has("previewUrl") ? singleSong.getString("previewUrl") : "No Url Found");
                songList.add(song);
            }
            // System.out.println(songList.get(0).songName);

            for (int i = 0; i < songList.size(); i++) {
                System.out.println(songList.get(i).songUrl);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}