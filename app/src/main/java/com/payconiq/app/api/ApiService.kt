package com.payconiq.app.api


import com.payconiq.app.models.GithubApiResponse
import com.payconiq.app.globals.DEFAULT_STRING
import com.payconiq.app.globals.GIT_SEARCH_USER_API_ROUTE
import com.payconiq.app.globals.PER_PAGE
import com.payconiq.app.globals.STARTING_PAGE_INDEX
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(GIT_SEARCH_USER_API_ROUTE)
    suspend fun searchUsers(
            @Query("q") query: String? = DEFAULT_STRING,
            @Query("sort")sort:String? = DEFAULT_STRING,
            @Query("page") page: Int? = STARTING_PAGE_INDEX,
            @Query("per_page") perPage: Int? = PER_PAGE
    ): GithubApiResponse
}