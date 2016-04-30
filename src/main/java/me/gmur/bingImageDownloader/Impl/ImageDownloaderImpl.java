package me.gmur.bingImageDownloader.Impl;

import me.gmur.bingImageDownloader.ImageDownloader;
import me.gmur.bingImageDownloader.util.Display;
import org.json.JSONObject;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import static me.gmur.bingImageDownloader.util.Flags.DEBUG_IGNORE_DISPLAY_RESOLUTION;

//TODO: Development imports. To be removed!

public class ImageDownloaderImpl implements ImageDownloader {
    private final String imageJsonUrlBase = "http://www.bing.com/HPImageArchive.aspx?format=js&n=1";    // n specifies the number of img's
    private String jsonUrlDaysAgo = "0";   // up to 20
    private String jsonUrlRegion = "en-US";
    private URL jsonUrl;


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

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    private void readFile(final URL _url) {
        try {
            System.out.println("Reading image");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(_url.openStream()));

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeTo(final String _filename) throws IOException {
        FileWriter fileWriter = new FileWriter(_filename);
        BufferedWriter output = new BufferedWriter(fileWriter);

    }

    public final void downloadImage() {       // TODO: Singleton pattern impl?
        URL imageAddress;
        try {
            imageAddress = new URL(getImageAddress());
            readFile(imageAddress);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
