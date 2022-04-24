package com.payconiq.app.data.repository

import com.payconiq.app.api.ApiService
import com.payconiq.app.data.repository.DataRepository
import com.payconiq.app.models.GithubApiResponse
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(private val apiService: ApiService) : DataRepository {

   override suspend fun getSearchResults(query: String?,
                                         sort:String?,
                                         page: Int?,
                                         perPage: Int
   ): GithubApiResponse = apiService.searchUsers(query,sort,page,perPage)
}