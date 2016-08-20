package me.gmur.bingImageDownloader.imageDownloader;

import me.gmur.bingImageDownloader.util.Log;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * <code>BingJsonProcessor</code> is responsible for parsing a JSON file
 * in order to get the URL address for the current Bing Image of The Day.
 *
 * @see JsonProcessor
 */
class BingJsonProcessor implements JsonProcessor {
    private static final Logger LOG = Log.getLoggerForClass("BingJsonProcessor");
    private final JSONObject jsonData;

    BingJsonProcessor() {
        jsonData = getJsonData();
    }

    /**
     * Returns a URL to the image.
     *
     * @return The image's address.
     */
    public URL getImageAddress() {
        final String bingUrl = "http://www.bing.com";
        URL imageAddress = null;

        try {
            JSONObject arrayOfImages = jsonData.getJSONArray("images").getJSONObject(0);
            String urlbase = bingUrl + arrayOfImages.getString("urlbase");

            imageAddress = new URL(urlbase + "_" + "1920x1080" + ".jpg");
        } catch (MalformedURLException e) {
            LOG.error("Creating URL object with image's address failed with an error " + Arrays.toString(e.getStackTrace()));
        }

        return imageAddress;
    }

    private JSONObject getJsonData() {
        URL jsonAddress = getJsonAddress();
        BufferedReader bufferedReader;
        StringBuilder jsonDataString = new StringBuilder();

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(jsonAddress.openStream(), "utf-8"));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonDataString.append(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            LOG.error("Getting JSON file failed with an error " + Arrays.toString(e.getStackTrace()));
        }

        return new JSONObject(jsonDataString.toString());
    }

    private URL getJsonAddress() {
        final String baseUrl = "http://www.bing.com/HPImageArchive.aspx?format=js&n=1";
        URL jsonUrl = null;

        try {
            jsonUrl = new URL(baseUrl
                    + "&idx=" + "0"
                    + "&mkt=" + "en-US"
            );
        } catch (MalformedURLException e) {
            LOG.error("Creating URL for JSON file failed with an error " + Arrays.toString(e.getStackTrace()));
        }

        return jsonUrl;
    }
}
