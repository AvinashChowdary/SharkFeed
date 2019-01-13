package com.example.avinashravilla.sharkfeed.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.avinashravilla.sharkfeed.R;
import com.example.avinashravilla.sharkfeed.adapters.SharkFeedAdapter;
import com.example.avinashravilla.sharkfeed.helpers.Constants;
import com.example.avinashravilla.sharkfeed.model.search.SearchResponse;
import com.example.avinashravilla.sharkfeed.network.NetworkUtil;
import com.example.avinashravilla.sharkfeed.services.Services;
import com.example.avinashravilla.sharkfeed.services.rest.RestClient;

import java.util.Arrays;

import butterknife.BindView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FeedActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.list_sharks)
    RecyclerView listSharks;

    @BindView(R.id.no_internet)
    TextView noInternet;

    @BindView(R.id.pull_ro_refresh)
    LinearLayout pullToRefresh;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private final int GRID_COLUMNS = 3;

    private int calls = 1;

    private int pastVisibleItems, visibleItemCount, totalItemCount;

    private boolean isLoading = false;

    private GridLayoutManager mLayoutManager;

    private SharkFeedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSwipeRefreshLayout.setNestedScrollingEnabled(true);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        searchForSharks(calls);
        addEndLessScrolling();
    }


    /**
     *  load sharks after reaching bottom
     */
    private void addEndLessScrolling() {
        listSharks.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    // scrolled down
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (!isLoading) {
                        // reached end
                        // time to load new sharks
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            isLoading = true;
                            // handling pagination
                            // incrementing page number
                            calls++;
                            Log.e("...", "Last Item Wow ! page : " + calls);
                            // calling sharks search api
                            // with incremented page
                            searchForSharks(calls);
                        }
                    }
                }
            }
        });
    }

    /**
     * search for sharks
     * search with page number
     * @param page
     */
    private void searchForSharks(int page) {
        if (NetworkUtil.isOnline(this)) {
            listSharks.setVisibility(View.VISIBLE);
            pullToRefresh.setVisibility(View.VISIBLE);
            noInternet.setVisibility(View.GONE);
            showProgress();
            isLoading = true;
            // getting adapter for retrofit
            Services services = RestClient.getInstance().getServices();
            // calling api with params
            services.searchImagesFor(Constants.SEARCH_METHOD, Constants.API_KEY, Constants.SHARK,
                    Constants.FORMAT, Constants.NO_JSON_CALLBACK, page, Constants.EXTRAS, new Callback<SearchResponse>() {
                        @Override
                        public void success(SearchResponse searchResponse, Response response) {
                            if (searchResponse != null) {
                                // response has content
                                if (!TextUtils.isEmpty(searchResponse.getPhotos().getPage())) {
                                    // updating page number
                                    calls = Integer.parseInt(searchResponse.getPhotos().getPage());
                                }
                                // load images to recycler view
                                setImagesToGrid(searchResponse);
                            } else {
                                showDialogWithMessage(getResources().getString(R.string.failed_to_load_sharks));
                            }
                            dismissProgress();
                            isLoading = false;
                        }


                        @Override
                        public void failure(RetrofitError error) {
                            // failed to load sharks
                            dismissProgress();
                            showDialogWithMessage(getResources().getString(R.string.failed_to_load_sharks));
                            isLoading = false;
                        }
                    });

        } else {
            listSharks.setVisibility(View.GONE);
            noInternet.setVisibility(View.VISIBLE);
            pullToRefresh.setVisibility(View.GONE);
        }
    }

    /**
     * from response, load images in recycler view
     * @param searchResponse
     */
    private void setImagesToGrid(SearchResponse searchResponse) {
        if(adapter != null) {
            if(calls == 1) {
                // first page, make list as response
                adapter.setItems(Arrays.asList(searchResponse.getPhotos().getPhoto()));
            } else {
                // already list has items
                // add newly fetched items to adapter
                adapter.addAllItems(Arrays.asList(searchResponse.getPhotos().getPhoto()));
            }

        } else {
            // making recycler view to look like grid
            mLayoutManager = new GridLayoutManager(FeedActivity.this, getNumOfColumns());
            listSharks.setLayoutManager(mLayoutManager);
            adapter = new SharkFeedAdapter(FeedActivity.this, Arrays.asList(searchResponse.getPhotos().getPhoto()));
            listSharks.setHasFixedSize(false);
            listSharks.setAdapter(adapter);
        }
    }

    /**
     * setting number of columns
     * based on screen dimensions
     * @return number of columns to fit in screen
     *
     */
    private int getNumOfColumns() {
        if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
            return (int) (dpWidth / 140);
        }
        return GRID_COLUMNS;
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_feed;
    }

    /**
     * refresh item for lower api levels
     * showing it as an action item
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_refresh:
                // signal SwipeRefreshLayout to start the progress indicator
                mSwipeRefreshLayout.setRefreshing(true);
                bringLatestSharks();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * pull down
     * get sharks from page 1
     */
    private void bringLatestSharks() {
        mSwipeRefreshLayout.setRefreshing(false);
        searchForSharks(1);
    }

    @Override
    public void onRefresh() {
        bringLatestSharks();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // animations between activity transitions
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }
}
