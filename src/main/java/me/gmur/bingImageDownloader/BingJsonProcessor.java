package me.gmur.bingImageDownloader;

import com.sun.istack.internal.NotNull;
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
    /**
     * Returns a URL to the image.
     *
     * @return The image's address.
     */
    public URL getImageAddress() {
        final URL jsonAddress = getJsonAddress();
        final JSONObject jsonData = getJsonData(jsonAddress);
        final URL imageAddress = retrieveImageAddressFrom(jsonData);

        return imageAddress;
    }

    /**
     * Parses the JSON file's contents looking
     * for the key which holds the image's address.
     *
     * @param _jsonData Contains the JSON file's data.
     * @return The image's URL address.
     */
    private URL retrieveImageAddressFrom(@NotNull final JSONObject _jsonData) {
        final String bingUrl = "http://www.bing.com";
        URL imageAddress = null;

        try {
            JSONObject arrayOfImages = _jsonData.getJSONArray("images").getJSONObject(0);
            String urlbase = bingUrl + arrayOfImages.getString("urlbase");

            imageAddress = new URL(urlbase + "_" + "1920x1080" + ".jpg");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return imageAddress;
    }

    /**
     * Attempts to download the JSON data from bing.com servers.
     *
     * @param _jsonAddress Contains JSON file's URL address.
     * @return JSON file's data.
     */
    private JSONObject getJsonData(@NotNull final URL _jsonAddress) {
        BufferedReader bufferedReader;
        StringBuilder jsonData = new StringBuilder();

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(_jsonAddress.openStream(), "utf-8"));

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

    /**
     * Composes a URL address pointing to the JSON file.
     *
     * @return URL address to the JSON file.
     */
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
