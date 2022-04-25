package com.payconiq.app.util.factory

import com.payconiq.app.models.GithubSearchModel
import javax.inject.Singleton

@Singleton
object DataFactoryImpl : DataFactory {

    override fun getGithubSearchModel() = GithubSearchModel(
        "https://www.google.com/",
        "https://www.google.com/",
        "https://www.google.com/",
        "https://www.google.com/",
        "https://www.google.com/",
        "1",
        "https://www.google.com/",
        1,
        "your_name",
        "1",
        "https://www.google.com/",
        "https://www.google.com/",
        "https://www.google.com/",
        100.0,
        true,
        "https://www.google.com/",
        "https://www.google.com/",
        "some type",
        "https://www.google.com/"

    )
}