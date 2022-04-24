package com.payconiq.app.data.repository

import com.payconiq.app.models.GithubApiResponse


interface DataRepository {

    suspend fun getSearchResults(query: String?,
                                 sort:String?,
                                   page: Int?,
                                 perPage: Int
    ): GithubApiResponse
}