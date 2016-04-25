package me.gmur.bingImageDownloader;

import com.oracle.tools.packager.Log;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageDownloader {
    private final String imageJsonUrlBase = "http://www.bing.com/HPImageArchive.aspx?format=js&n=1";    // n specifies the number of img's
    private String jsonUrlDaysAgo = "0";   // up to 20
    private String jsonUrlRegion = "en-US";
    private URL jsonUrl;


    public void getImageAddress() {
        try {
            jsonUrl = new URL(imageJsonUrlBase
                    + "&idx=" + jsonUrlDaysAgo
                    + "&mkt=" + jsonUrlRegion
            );

            String jsonContents = readFile(jsonUrl);
            JSONObject jsonMainObject = new JSONObject(jsonContents);
            JSONObject jsonImagesObject = jsonMainObject.getJSONArray("images").getJSONObject(0);
            String imageUrl = "http://www.bing.com" + jsonImagesObject.getString("urlbase")
                    + "_" + Display.getDisplayWidth() + "x" + Display.getDisplayHeight()
                    + ".jpg";

            System.out.println(imageUrl);

        } catch (MalformedURLException e) {
            Log.info(e.getStackTrace().toString());
        } catch (IOException e) {
            Log.info(e.getStackTrace().toString());
        }
    }

    private String readFile(URL _url) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(_url.openStream()));
        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        bufferedReader.close();

        return sb.toString();
    }
}
