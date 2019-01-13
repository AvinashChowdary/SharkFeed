package com.example.avinashravilla.sharkfeed.services;

import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.avinashravilla.sharkfeed.R;
import com.example.avinashravilla.sharkfeed.activities.App;
import com.example.avinashravilla.sharkfeed.helpers.Constants;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class DownloadImageTask extends AsyncTask<Void, Void, Integer> {

    private final String url;

    private final String id;

    public DownloadImageTask(String url, String id) {
        this.url = url;
        this.id = id;
    }
    @Override
    protected Integer doInBackground(Void... voids) {
        try {

            String dir;

            // getting download directory SD card
            if(!TextUtils.isEmpty(Environment.getExternalStorageDirectory().getAbsolutePath())) {
                dir = Environment.getExternalStorageDirectory().getAbsolutePath();
            } else {
                return 121;
            }

            String path = dir + Constants.DOWNLOAD_PATH;
            File directory = new File(path);

            if(!directory.exists())
                directory.mkdirs();

            File outputFile = new File(path + id + ".png");
            if(!outputFile.exists()) {
                outputFile.createNewFile();
            } else {
                return 122;
            }

            // downloading and writing to file
            URL u = new URL(url);
            URLConnection conn = u.openConnection();
            int contentLength = conn.getContentLength();

            DataInputStream stream = new DataInputStream(u.openStream());

            byte[] buffer = new byte[contentLength];
            stream.readFully(buffer);
            stream.close();

            DataOutputStream fos = new DataOutputStream(new FileOutputStream(outputFile));
            fos.write(buffer);
            fos.flush();
            fos.close();
            return 123;
        } catch(FileNotFoundException e) {
            return 124;
        } catch (IOException e) {
            return 124;
        }
    }

    @Override
    protected void onPostExecute(Integer status) {
        super.onPostExecute(status);
        Log.e("Status", status+"");
        // handling various types of errors
        String msg = App.getContext().getResources().getString(R.string.download_failed);;
        switch(status) {
            case 121:
                msg = App.getContext().getResources().getString(R.string.no_storage);
                break;
            case 122:
                msg = App.getContext().getResources().getString(R.string.image_exists);
                break;
            case 123:
                msg = App.getContext().getResources().getString(R.string.download_success);
                break;
            case 124:
                msg = App.getContext().getResources().getString(R.string.download_failed);
                break;
        }
        Toast.makeText(App.getContext(), msg, Toast.LENGTH_LONG).show();
    }
}
