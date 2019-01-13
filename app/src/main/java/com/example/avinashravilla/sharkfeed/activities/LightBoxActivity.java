package com.example.avinashravilla.sharkfeed.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.avinashravilla.sharkfeed.R;
import com.example.avinashravilla.sharkfeed.helpers.Constants;
import com.example.avinashravilla.sharkfeed.model.imagedetails.ImageDetailsResponse;
import com.example.avinashravilla.sharkfeed.model.search.Photo;
import com.example.avinashravilla.sharkfeed.network.NetworkUtil;
import com.example.avinashravilla.sharkfeed.services.DownloadImageTask;
import com.example.avinashravilla.sharkfeed.services.Services;
import com.example.avinashravilla.sharkfeed.services.rest.RestClient;
import com.jsibbold.zoomage.ZoomageView;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LightBoxActivity extends BaseActivity {

    @BindView(R.id.img_shark)
    ZoomageView imgShark;

    @BindView(R.id.txt_title)
    TextView title;

    @BindView(R.id.txt_desc)
    TextView desc;

    @BindView(R.id.txt_owner)
    TextView owner;

    @BindView(R.id.txt_views)
    TextView views;

    @BindView(R.id.no_internet)
    TextView noInternet;

    private String imgLink;

    private String id;

    private String flickrLink;

    @Override
    public int getContentViewId() {
        return R.layout.activity_lightbox;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick({R.id.btn_download, R.id.txt_download})
    public void download() {
        // dynamically checking permissions
        // if not granted, request permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            this.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,}, Constants.WRITE_STORAGE);
            return;
        } else {
            // has permissions
            // being downloading image in background
            new DownloadImageTask(imgLink, id).execute();
        }

    }

    @OnClick({R.id.btn_flickr, R.id.txt_open_in_flickr})
    public void openInFlickr() {
        // trying to load flickr app with image link
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.yahoo.mobile.client.android.flickr");
        if(!isEmpty(flickrLink)) {
            if (launchIntent != null) {
                launchIntent.setAction(Intent.ACTION_SEND);
                launchIntent.putExtra(Intent.EXTRA_STREAM, imgLink);
                startActivity(launchIntent);
            } else {
                // opening image in flickr website
                Intent openWebPageIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(flickrLink));
                startActivity(openWebPageIntent);
            }
        } else {
            Toast.makeText(this, getStr(R.string.link_not_found), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent != null && intent.getSerializableExtra(Constants.SHARK) != null) {
            Photo photo = (Photo) intent.getSerializableExtra(Constants.SHARK);
            id = photo.getId();
            // detecting best available image quality
            if (!TextUtils.isEmpty(photo.getUrl_o())) {
                imgLink = photo.getUrl_o();
            } else if (!TextUtils.isEmpty(photo.getUrl_l())) {
                imgLink = photo.getUrl_l();
            } else if (!TextUtils.isEmpty(photo.getUrl_c())) {
                imgLink = photo.getUrl_c();
            } else if (!TextUtils.isEmpty(photo.getUrl_t())) {
                imgLink = photo.getUrl_t();
            }
            Glide.with(this)
                    .load(imgLink)
                    .thumbnail(Glide.with(this)
                            .load(photo.getUrl_t())) // loading thumbnail
                    .into(imgShark);

            getImageInfo(id);

        }
    }

    /**
     * obtain image info based on ID
     * @param id
     */
    private void getImageInfo(String id) {
        if (NetworkUtil.isOnline(this)) {
            imgShark.setVisibility(View.VISIBLE);
            noInternet.setVisibility(View.GONE);
            // getting adapter for retrofit
            Services services = RestClient.getInstance().getServices();
            // calling api with params
            services.getImageDetails(Constants.PHOTO_INFO_METHOD, Constants.API_KEY, id, Constants.FORMAT, Constants.NO_JSON_CALLBACK, new Callback<ImageDetailsResponse>() {
                @Override
                public void success(ImageDetailsResponse imageDetailsResponse, Response response) {
                    if(imageDetailsResponse != null) {
                        // show image info
                        setImageInfo(imageDetailsResponse);
                    } else {
                        showDialogWithMessage(getResources().getString(R.string.failed_to_load_image));
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    showDialogWithMessage(getResources().getString(R.string.failed_to_load_image));
                }
            });
        } else {
            // show no internet
            imgShark.setVisibility(View.GONE);
            noInternet.setVisibility(View.VISIBLE);
        }
    }

    private void setImageInfo(ImageDetailsResponse imageDetailsResponse) {
        // displaying details based on availability
        if(!isEmpty(imageDetailsResponse.getPhoto().getOwner().getUsername().trim())) {
            owner.setText(getStr(R.string.owner) + imageDetailsResponse.getPhoto().getOwner().getUsername());
        } else {
            owner.setVisibility(View.GONE);
        }

        if(!isEmpty(imageDetailsResponse.getPhoto().getTitle().get_content().trim())) {
            title.setText(getStr(R.string.title) + imageDetailsResponse.getPhoto().getTitle().get_content());
        } else {
            title.setVisibility(View.GONE);
        }

        if(!isEmpty(imageDetailsResponse.getPhoto().getDescription().get_content().trim())) {
            desc.setText(getStr(R.string.description) + imageDetailsResponse.getPhoto().getDescription().get_content());
        } else {
            desc.setVisibility(View.GONE);
        }

        if(!isEmpty(imageDetailsResponse.getPhoto().getViews().trim())) {
            views.setText(getStr(R.string.views) + imageDetailsResponse.getPhoto().getViews());
        } else {
            views.setVisibility(View.GONE);
        }

        flickrLink = imageDetailsResponse.getPhoto().getUrls().getUrl()[0].get_content();
    }

    /**
     * helper to check if a string is empty
     * @param content
     * @return
     */
    private boolean isEmpty(String content) {
        return TextUtils.isEmpty(content);
    }

    private String getStr(int id) {
        return getResources().getString(id);
    }

    /**
     * checking result of permissions request
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Constants.WRITE_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    download();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
