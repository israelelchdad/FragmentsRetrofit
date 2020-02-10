package com.example.frame2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MooveiServich {

    @GET("movie/popular")
    Call<ImegeSearchResult> searchMobiesByPepuler(@Query("api_key") String key,@Query("page")int numPage);
    @GET("movie/{movie_id}/videos")
    Call<Response> serchTrailerById(@Path("movie_id")String movieId,@Query("api_key") String key);
}
