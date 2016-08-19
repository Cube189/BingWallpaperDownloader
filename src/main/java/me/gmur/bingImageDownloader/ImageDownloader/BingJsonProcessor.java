package me.gmur.bingImageDownloader.imageDownloader;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * <code>BingJsonProcessor</code> is responsible for parsing a JSON file
 * in order to get the URL address for the current Bing Image of The Day.
 *
 * @see JsonProcessor
 */
class BingJsonProcessor implements JsonProcessor {
    private JSONObject jsonData;


    public BingJsonProcessor() {
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
            e.printStackTrace();
        }

        return imageAddress;
    }


    public String getImageDescription() {
        // TODO: Get image desc impl
        return "";
    }


    public String getImageLegalInfo() {
        // TODO: Get image copyright impl
        return "";
    }


    private JSONObject getJsonData() {
        URL jsonAddress = getJsonAddress();
        BufferedReader bufferedReader;
        StringBuilder jsonData = new StringBuilder();

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(jsonAddress.openStream(), "utf-8"));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonData.append(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new JSONObject(jsonData.toString());
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
            e.printStackTrace();
        }

        return jsonUrl;
    }
}
