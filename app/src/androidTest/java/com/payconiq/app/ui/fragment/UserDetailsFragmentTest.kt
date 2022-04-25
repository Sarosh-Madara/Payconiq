package com.payconiq.app.ui.fragment

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.MediumTest
import com.payconiq.app.R
import com.payconiq.app.models.GithubSearchModel
import com.payconiq.app.util.factory.DataFactoryImpl
import com.payconiq.app.util.launchFragmentInHiltContainer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

@MediumTest
@ExperimentalCoroutinesApi
class UserDetailsFragmentTest {

    private lateinit var githubSearchModel : GithubSearchModel
    private lateinit var fragmentArgsBundle: Bundle

    @Before
    fun setUp() {
        githubSearchModel = DataFactoryImpl.getGithubSearchModel()
        fragmentArgsBundle = bundleOf("user" to githubSearchModel)
    }


    @Test
    fun isFragmentDisplayed() {
        launchFragmentInHiltContainer<UserDetailsFragment>(fragmentArgs = fragmentArgsBundle) {  }
        Espresso.onView(withId(R.id.photoScrollView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


    @Test
    fun assertID() {
        launchFragmentInHiltContainer<UserDetailsFragment>(fragmentArgs = fragmentArgsBundle)
        Espresso.onView(withId(R.id.txt_id))
            .check(ViewAssertions.matches(withText(DataFactoryImpl.getGithubSearchModel().id.toString())))
    }


    @Test
    fun assertNodeID() {
        launchFragmentInHiltContainer<UserDetailsFragment>(fragmentArgs = fragmentArgsBundle)
        Espresso.onView(withId(R.id.txt_node_id))
            .check(ViewAssertions.matches(withText(DataFactoryImpl.getGithubSearchModel().nodeId.toString())))
    }


    @Test
    fun assertAvatarURL() {
        launchFragmentInHiltContainer<UserDetailsFragment>(fragmentArgs = fragmentArgsBundle)
        Espresso.onView(withId(R.id.txt_avatar_url))
            .check(ViewAssertions.matches(withText(DataFactoryImpl.getGithubSearchModel().avatarUrl.toString())))
    }


    @Test
    fun assertURL() {
        launchFragmentInHiltContainer<UserDetailsFragment>(fragmentArgs = fragmentArgsBundle)
        Espresso.onView(withId(R.id.txt_url))
            .check(ViewAssertions.matches(withText(DataFactoryImpl.getGithubSearchModel().url.toString())))
    }

    @Test
    fun assertHTMLID() {
        launchFragmentInHiltContainer<UserDetailsFragment>(fragmentArgs = fragmentArgsBundle)
        Espresso.onView(withId(R.id.txt_html_url))
            .check(matches(withText(DataFactoryImpl.getGithubSearchModel().htmlUrl.toString())))
    }


    @Test
    fun assertSubscriptionURL() {
        launchFragmentInHiltContainer<UserDetailsFragment>(fragmentArgs = fragmentArgsBundle)
        Espresso.onView(withId(R.id.txt_subscriptions_url))
            .check(matches(withText(DataFactoryImpl.getGithubSearchModel().subscriptionsUrl.toString())))
    }


    @Test
    fun assertFollowerURL() {
        launchFragmentInHiltContainer<UserDetailsFragment>(fragmentArgs = fragmentArgsBundle)
        Espresso.onView(withId(R.id.txt_follower_url))
            .check(matches(withText(DataFactoryImpl.getGithubSearchModel().followersUrl.toString())))
    }

    @Test
    fun assertFollowingURL() {
        launchFragmentInHiltContainer<UserDetailsFragment>(fragmentArgs = fragmentArgsBundle)
        Espresso.onView(withId(R.id.txt_following_url))
            .check(matches(withText(DataFactoryImpl.getGithubSearchModel().followingUrl.toString())))
    }
}