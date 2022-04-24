package com.payconiq.app.repository

import com.payconiq.app.api.ApiService
import com.payconiq.app.data.repository.DataRepository
import com.payconiq.app.data.repository.DataRepositoryImpl
import com.payconiq.app.models.GithubApiResponse
import com.payconiq.app.models.GithubSearchModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class DataRepositoryTest {
    private lateinit var mockApiService: ApiService
    private lateinit var dataRepository: DataRepository

    private val networkData = GithubApiResponse(676765,false,
    listOf(
        GithubSearchModel(id = 1410106),
        GithubSearchModel(id = 45491),
        GithubSearchModel(id = 313874)
    ))

    private val networkDataForQuerySearchJ = GithubApiResponse(total_count = 2,false,
    listOf(
        GithubSearchModel(id = 589410),
        GithubSearchModel(id = 66577)
    ))

    private val networkDataForQuerySearchJakeWharton = GithubApiResponse(total_count = 2,false,
    listOf(
        GithubSearchModel(id = 66577),
        GithubSearchModel(id = 463941)
    ))


    @Before
    fun createRepository() = runBlocking {
        mockApiService = mock(ApiService::class.java)

        `when`(mockApiService.searchUsers("A","alphabetically",1,3)).thenReturn(networkData)
        `when`(mockApiService.searchUsers("j","alphabetically",1,2)).thenReturn(networkDataForQuerySearchJ)
        `when`(mockApiService.searchUsers("jakewharton","alphabetically",1,2)).thenReturn(networkDataForQuerySearchJakeWharton)

        dataRepository = DataRepositoryImpl(mockApiService)
    }


    @Test
    fun searchUser_returnsExactlyThreeItems() = runBlocking {
        val data = dataRepository.getSearchResults("A","alphabetically",1,3)
        Assert.assertTrue(data.items.size == 3)
    }

    @Test
    fun searchUser_returnsTotalCount676765() = runBlocking {
        val data = dataRepository.getSearchResults("A","alphabetically",1,3)
        Assert.assertEquals(data.total_count,676765)
    }

    @Test
    fun searchUser_querySearchJ() = runBlocking {
        val data = dataRepository.getSearchResults("j","alphabetically",1,2)
        Assert.assertEquals(data.total_count,2)
        Assert.assertTrue(data.items[0].id == 589410)
    }

    @Test
    fun searchUser_querySearchJakeWharton() = runBlocking {
        val data = dataRepository.getSearchResults("jakewharton","alphabetically",1,2)

        Assert.assertEquals(data.total_count,2)
        Assert.assertTrue(data.items[0].id == 66577)
    }


}

//{
//    "total_count": 45489,
//    "incomplete_results": false,
//    "items": [
//    {
//        "login": "j",
//        "id": 589410,
//        "node_id": "MDQ6VXNlcjU4OTQxMA==",
//        "avatar_url": "https://avatars.githubusercontent.com/u/589410?v=4",
//        "gravatar_id": "",
//        "url": "https://api.github.com/users/j",
//        "html_url": "https://github.com/j",
//        "followers_url": "https://api.github.com/users/j/followers",
//        "following_url": "https://api.github.com/users/j/following{/other_user}",
//        "gists_url": "https://api.github.com/users/j/gists{/gist_id}",
//        "starred_url": "https://api.github.com/users/j/starred{/owner}{/repo}",
//        "subscriptions_url": "https://api.github.com/users/j/subscriptions",
//        "organizations_url": "https://api.github.com/users/j/orgs",
//        "repos_url": "https://api.github.com/users/j/repos",
//        "events_url": "https://api.github.com/users/j/events{/privacy}",
//        "received_events_url": "https://api.github.com/users/j/received_events",
//        "type": "User",
//        "site_admin": false,
//        "score": 1.0
//    },
//    {
//        "login": "JakeWharton",
//        "id": 66577,
//        "node_id": "MDQ6VXNlcjY2NTc3",
//        "avatar_url": "https://avatars.githubusercontent.com/u/66577?v=4",
//        "gravatar_id": "",
//        "url": "https://api.github.com/users/JakeWharton",
//        "html_url": "https://github.com/JakeWharton",
//        "followers_url": "https://api.github.com/users/JakeWharton/followers",
//        "following_url": "https://api.github.com/users/JakeWharton/following{/other_user}",
//        "gists_url": "https://api.github.com/users/JakeWharton/gists{/gist_id}",
//        "starred_url": "https://api.github.com/users/JakeWharton/starred{/owner}{/repo}",
//        "subscriptions_url": "https://api.github.com/users/JakeWharton/subscriptions",
//        "organizations_url": "https://api.github.com/users/JakeWharton/orgs",
//        "repos_url": "https://api.github.com/users/JakeWharton/repos",
//        "events_url": "https://api.github.com/users/JakeWharton/events{/privacy}",
//        "received_events_url": "https://api.github.com/users/JakeWharton/received_events",
//        "type": "User",
//        "site_admin": false,
//        "score": 1.0
//    }
//    ]
//}