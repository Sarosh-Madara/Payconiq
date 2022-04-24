package com.payconiq.app.ui.fragment

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.payconiq.app.R
import com.payconiq.app.models.GithubSearchModel
import com.payconiq.app.ui.adapter.SearchAdapter
import com.payconiq.app.util.clickChildViewWithId
import com.payconiq.app.util.launchFragmentInHiltContainer
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock


@MediumTest
@ExperimentalCoroutinesApi
class SearchFragmentTest {
    private val mockNavController = mock(NavController::class.java)
    private lateinit var githubSearchModel: GithubSearchModel

    @Before
    fun setup(){
        githubSearchModel = GithubSearchModel()
    }

    @Test
    fun isFragmentDisplayed(){
        launchFragmentInHiltContainer<SearchFragment>{}
        onView(withId(R.id.searchLayout))
            .check(matches(isDisplayed()))
    }

    @Test
    fun isRecyclerViewRendered(){
        launchFragmentInHiltContainer<SearchFragment> {
            Thread.sleep(3000)
        }
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun clickFirstItemInList(){
        launchFragmentInHiltContainer<SearchFragment>{
            Navigation.setViewNavController(this.requireView(), mockNavController)
        }
        Thread.sleep(3000)
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<SearchAdapter.UserViewHolder>(3, clickChildViewWithId(R.id.userItems)))
    }


    @Test
    fun matchItemCount() {
        var itemCount :Int? = 0
        launchFragmentInHiltContainer<SearchFragment> {
            Thread.sleep(2000)
            itemCount = this.recyclerView.adapter?.itemCount
        }
        onView(withId(R.id.recyclerView))
            .perform(itemCount?.let { count ->
                RecyclerViewActions.scrollToPosition<SearchAdapter.UserViewHolder>(count-1)
            })
    }
}