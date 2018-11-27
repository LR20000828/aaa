package cc.bw.com.a20181123lirui.fragment;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UtilData {

    public static String getJson(String urlString) {

        try {
            URL url = new URL(urlString);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            int code = urlConnection.getResponseCode();
            if (code == 200) {
                //创建流
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                //创建一个字符串盒子吧
                StringBuilder stringBuffer = new StringBuilder();
                String temp = "";
                while ((temp = reader.readLine()) != null) {
                    stringBuffer.append(temp);
                }
                return stringBuffer.toString();
            } else {
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
