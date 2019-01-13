package com.example.avinashravilla.sharkfeed.services;

import com.example.avinashravilla.sharkfeed.model.imagedetails.ImageDetailsResponse;
import com.example.avinashravilla.sharkfeed.model.search.SearchResponse;


import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * interface with list of services
 * implemented in activities
 */
public interface Services {

    // both the services can be differentiated with extensive
    // items in the URL, but for more customization left it with
    // highest degree of customization

    /**
     *
     * @param apiMethod
     * function to call, search photos in this case
     * @param apiKey
     * auth key
     * @param searchQuery
     * Query string to be searched
     * @param format
     * response format, json in this case
     * @param page
     * getting response in pagination, retrieving 100 at a time
     * @param extras
     * different image qualities low, high, original
     * @param callBack
     * response converted to SearchResponse object
     */
    @GET("/")
    void searchImagesFor(@Query("method") String apiMethod, @Query("api_key") String apiKey, @Query("text") String searchQuery,
                @Query("format") String format, @Query("nojsoncallback") int noJsonCallBack, @Query("page") int page, @Query("extras") String extras, Callback<SearchResponse> callBack);

    @GET("/")
    void getImageDetails(@Query("method") String apiMethod, @Query("api_key") String apiKey, @Query("photo_id") String photoID,
                         @Query("format") String format, @Query("nojsoncallback") int noJsonCallBack, Callback<ImageDetailsResponse> callback);

}
