package practice.omdb.junseokseo.api

import practice.omdb.junseokseo.api.model.DetailDTO
import practice.omdb.junseokseo.api.model.SearchDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDbApi {
    @GET("/")
    suspend fun getMovieList(
        @Query("apikey") apiKey: String,
        @Query("s") keyword: String,
        @Query("page") pageNumber: Int,
        @Query("type") type: String
    ): Result<SearchDTO>

    @GET("/")
    suspend fun getMovieDetail(
        @Query("apikey") apiKey: String,
        @Query("i") imdbId: String,
    ): Result<DetailDTO>
}