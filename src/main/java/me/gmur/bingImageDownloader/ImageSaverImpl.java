package me.gmur.bingImageDownloader;

import java.io.*;
import java.net.URL;

public class ImageSaverImpl implements ImageSaver {
    private URL imageURL;
    private String filename;

    private byte[] imageContents;

    public ImageSaverImpl(URL _url, String _filename) {
        this.imageURL = _url;
        this.filename = _filename;
    }

    public void saveImage() {
        streamImageFrom(imageURL);
        writeImageTo(filename);
    }

    private void streamImageFrom(final URL _url) {
        InputStream input;
        ByteArrayOutputStream output;

        byte[] buffer = new byte[1024];

        try {
            System.out.println("Reading image...");

            input = new BufferedInputStream(_url.openStream());
            output = new ByteArrayOutputStream();

            int n;
            while (-1 != (n = input.read(buffer))) {
                output.write(buffer, 0, n);
            }

            output.close();
            input.close();

            imageContents = output.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeImageTo(final String _filename) {
        FileOutputStream writer;

        try {
            System.out.println("Writing to file...");

            writer = new FileOutputStream(_filename);
            writer.write(imageContents);

            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
