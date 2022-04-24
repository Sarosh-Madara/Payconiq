package com.payconiq.app.network

import com.payconiq.app.api.ApiService
import com.payconiq.app.globals.BASE_URL
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceTest {
    private lateinit var mockWebServer: MockWebServer
    private val mockResponseCharacterInfo = """
           {
             "total_count": 676765,
             "incomplete_results": false,
             "items": [
               {
                 "login": "A",
                 "id": 1410106,
                 "node_id": "MDQ6VXNlcjE0MTAxMDY=",
                 "avatar_url": "https://avatars.githubusercontent.com/u/1410106?v=4",
                 "gravatar_id": "",
                 "url": "https://api.github.com/users/A",
                 "html_url": "https://github.com/A",
                 "followers_url": "https://api.github.com/users/A/followers",
                 "following_url": "https://api.github.com/users/A/following{/other_user}",
                 "gists_url": "https://api.github.com/users/A/gists{/gist_id}",
                 "starred_url": "https://api.github.com/users/A/starred{/owner}{/repo}",
                 "subscriptions_url": "https://api.github.com/users/A/subscriptions",
                 "organizations_url": "https://api.github.com/users/A/orgs",
                 "repos_url": "https://api.github.com/users/A/repos",
                 "events_url": "https://api.github.com/users/A/events{/privacy}",
                 "received_events_url": "https://api.github.com/users/A/received_events",
                 "type": "User",
                 "site_admin": false,
                 "score": 1.0
               },
               {
                 "login": "snyff",
                 "id": 45491,
                 "node_id": "MDQ6VXNlcjQ1NDkx",
                 "avatar_url": "https://avatars.githubusercontent.com/u/45491?v=4",
                 "gravatar_id": "",
                 "url": "https://api.github.com/users/snyff",
                 "html_url": "https://github.com/snyff",
                 "followers_url": "https://api.github.com/users/snyff/followers",
                 "following_url": "https://api.github.com/users/snyff/following{/other_user}",
                 "gists_url": "https://api.github.com/users/snyff/gists{/gist_id}",
                 "starred_url": "https://api.github.com/users/snyff/starred{/owner}{/repo}",
                 "subscriptions_url": "https://api.github.com/users/snyff/subscriptions",
                 "organizations_url": "https://api.github.com/users/snyff/orgs",
                 "repos_url": "https://api.github.com/users/snyff/repos",
                 "events_url": "https://api.github.com/users/snyff/events{/privacy}",
                 "received_events_url": "https://api.github.com/users/snyff/received_events",
                 "type": "User",
                 "site_admin": false,
                 "score": 1.0
               },
               {
                 "login": "Amichai",
                 "id": 313874,
                 "node_id": "MDQ6VXNlcjMxMzg3NA==",
                 "avatar_url": "https://avatars.githubusercontent.com/u/313874?v=4",
                 "gravatar_id": "",
                 "url": "https://api.github.com/users/Amichai",
                 "html_url": "https://github.com/Amichai",
                 "followers_url": "https://api.github.com/users/Amichai/followers",
                 "following_url": "https://api.github.com/users/Amichai/following{/other_user}",
                 "gists_url": "https://api.github.com/users/Amichai/gists{/gist_id}",
                 "starred_url": "https://api.github.com/users/Amichai/starred{/owner}{/repo}",
                 "subscriptions_url": "https://api.github.com/users/Amichai/subscriptions",
                 "organizations_url": "https://api.github.com/users/Amichai/orgs",
                 "repos_url": "https://api.github.com/users/Amichai/repos",
                 "events_url": "https://api.github.com/users/Amichai/events{/privacy}",
                 "received_events_url": "https://api.github.com/users/Amichai/received_events",
                 "type": "User",
                 "site_admin": false,
                 "score": 1.0
               }
             ]
           }
    """.trimIndent()

    private val mockResponseAllCharacters = "[$mockResponseCharacterInfo]"

    private fun getCharacterService(): ApiService {
        System.setProperty("javax.net.ssl.trustStoreType", "JKS")
        val baseUrl = mockWebServer.url(BASE_URL)
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }

    @Test
    fun getSearchUsers_canBeParsed() = runBlocking {
        mockWebServer = MockWebServer()
        mockWebServer.enqueue(MockResponse().setBody(mockResponseAllCharacters))
        mockWebServer.start()

        val service = getCharacterService()
        val characters = service.searchUsers("A","alphabetically",1,3)
        assertFalse(characters.items.isEmpty())
        assertTrue(characters.items[0].id == 1410106)
        assertTrue(characters.items[0].type == "User")
    }

    @Test
    fun getSearchUsers_canNotBeParsed() = runBlocking {
        mockWebServer = MockWebServer()
        mockWebServer.enqueue(MockResponse().setBody(mockResponseAllCharacters))
        mockWebServer.start()

        val service = getCharacterService()
        val characters = service.searchUsers("A","alphabetically",1,3)
        assertFalse(characters.items.isEmpty())
        assertFalse(characters.items[0].id != 1410106)
        assertFalse(characters.items[0].type != "User")
    }
}