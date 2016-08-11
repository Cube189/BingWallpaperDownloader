package me.gmur.bingImageDownloader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

class ImageSaver {
    public static void saveImageDataTo(byte[] _imageContents, String _filename) {
        FileOutputStream writer;

        try {
            System.err.println("INFO: Writing image content to \'" + _filename + "\'...");

            writer = new FileOutputStream(_filename);
            writer.write(_imageContents);

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
