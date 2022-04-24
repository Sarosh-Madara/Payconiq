package com.payconiq.app.data.repository

import com.payconiq.app.api.ApiService
import com.payconiq.app.models.GithubApiResponse
import javax.inject.Inject

class GithubRepository @Inject constructor(val apiService: ApiService) {

    suspend fun getSearchResults(query: String?,
                                          sort:String?,
                                          page: Int?,
                                          perPage: Int
    ): GithubApiResponse = apiService.searchUsers(query,sort,page,perPage)
}