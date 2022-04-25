package com.payconiq.app.models

import android.os.Parcelable

import com.google.gson.annotations.SerializedName
import com.payconiq.app.globals.DEFAULT_BOOLEAN_VALUE
import com.payconiq.app.globals.DEFAULT_INT_VALUE
import com.payconiq.app.globals.DEFAULT_STRING
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubSearchModel(
        @SerializedName("avatar_url")
        val avatarUrl: String? = DEFAULT_STRING,
        @SerializedName("events_url")
        val eventsUrl: String? = DEFAULT_STRING,
        @SerializedName("followers_url")
        val followersUrl: String? = DEFAULT_STRING,
        @SerializedName("following_url")
        val followingUrl: String? = DEFAULT_STRING,
        @SerializedName("gists_url")
        val gistsUrl: String? = DEFAULT_STRING,
        @SerializedName("gravatar_id")
        val gravatarId: String? = DEFAULT_STRING,
        @SerializedName("html_url")
        val htmlUrl: String? = DEFAULT_STRING,
        @SerializedName("id")
        val id: Int? = DEFAULT_INT_VALUE,
        @SerializedName("login")
        val login: String? = DEFAULT_STRING,
        @SerializedName("node_id")
        val nodeId: String? = DEFAULT_STRING,
        @SerializedName("organizations_url")
        val organizationsUrl: String? = DEFAULT_STRING,
        @SerializedName("received_events_url")
        val receivedEventsUrl: String? = DEFAULT_STRING,
        @SerializedName("repos_url")
        val reposUrl: String? = DEFAULT_STRING,
        @SerializedName("score")
        val score: Double? = 0.0,
        @SerializedName("site_admin")
        val siteAdmin: Boolean? = DEFAULT_BOOLEAN_VALUE,
        @SerializedName("starred_url")
        val starredUrl: String? = DEFAULT_STRING,
        @SerializedName("subscriptions_url")
        val subscriptionsUrl: String? = DEFAULT_STRING,
        @SerializedName("type")
        val type: String? = DEFAULT_STRING,
        @SerializedName("url")
        val url: String? = DEFAULT_STRING
) : Parcelable