package me.gmur.bingImageDownloader;

import me.gmur.bingImageDownloader.util.Display;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import static me.gmur.bingImageDownloader.util.Flags.DEBUG_IGNORE_DISPLAY_RESOLUTION;

//TODO: Development imports. To be removed!

public class BingImageDownloader implements ImageDownloader {
    private final String imageJsonUrlBase = "http://www.bing.com/HPImageArchive.aspx?format=js&n=1";    // n specifies the number of img's
    private String jsonUrlDaysAgo = "0";   // up to 20
    private String jsonUrlRegion = "en-US";
    private URL jsonUrl;

    private String filename = "bingimage.jpg";


    private String getImageAddress() {
        try {
            jsonUrl = new URL(imageJsonUrlBase
                    + "&idx=" + jsonUrlDaysAgo
                    + "&mkt=" + jsonUrlRegion
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        String jsonContents = readJson(jsonUrl);
        JSONObject jsonMainObject = new JSONObject(jsonContents);
        JSONObject jsonImagesObject = jsonMainObject.getJSONArray("images").getJSONObject(0);
        final String imageUrl = "http://www.bing.com" + jsonImagesObject.getString("urlbase")
                + "_" + (DEBUG_IGNORE_DISPLAY_RESOLUTION ? "1920x1080" : Display.getDisplayWidth() + "x" + Display.getDisplayHeight())
                + ".jpg";

        return imageUrl;
    }

    private String readJson(final URL _url) {
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(_url.openStream()));

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }


    public final void downloadImage() {
        URL imageAddress;
        ImageSaver imageSaver;
        try {
            imageAddress = new URL(getImageAddress());

            imageSaver = new ImageSaverImpl(imageAddress, filename);

            imageSaver.saveImage();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

}
