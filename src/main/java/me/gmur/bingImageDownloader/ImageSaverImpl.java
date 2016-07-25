package me.gmur.bingImageDownloader;

import java.io.*;
import java.net.URL;

public class ImageSaverImpl implements ImageSaver {
    private URL imageURL;
    private String filename;

    private String imageContents;

    public ImageSaverImpl(URL _url, String _filename) {
        this.imageURL = _url;
        this.filename = _filename;
    }

    public void saveImage() {
        streamFile(imageURL);
        writeFile(filename);
    }

    private void streamFile(final URL _url) {
        try {
            System.out.println("Reading image");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(_url.openStream()));

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                imageContents += line;
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFile(final String _filename) {
        Writer writer;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(_filename), "utf-8"));

            writer.write(imageContents);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
