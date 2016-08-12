package me.gmur.bingImageDownloader;

import com.sun.istack.internal.NotNull;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

class BingJsonProcessor implements JsonProcessor {
    public URL getImageAddress() {
        final URL jsonAddress = getJsonAddress();
        final JSONObject jsonData = getJsonData(jsonAddress);
        final URL imageAddress = retrieveImageAddressFrom(jsonData);

        return imageAddress;
    }


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

    private JSONObject getJsonData(@NotNull final URL _jsonAddress) {
        BufferedReader bufferedReader;
        StringBuilder jsonData = new StringBuilder();

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(_jsonAddress.openStream()));

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
