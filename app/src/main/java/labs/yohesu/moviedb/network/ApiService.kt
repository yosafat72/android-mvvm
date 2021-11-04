package labs.yohesu.newapps.network

import labs.yohesu.moviedb.model.MovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") key : String,
        @Query("language") language : String,
        @Query("page") page : String
    ) : Response<MovieModel>

    @GET("movie/top_rated")
    suspend fun getTopRate(
        @Query("api_key") key : String,
        @Query("language") language : String,
        @Query("page") page: String
    ) : Response<MovieModel>

    @GET("movie/upcoming")
    suspend fun getUpcomming(
        @Query("api_key") key : String,
        @Query("language") language : String,
        @Query("page") page: String
    ) : Response<MovieModel>
}