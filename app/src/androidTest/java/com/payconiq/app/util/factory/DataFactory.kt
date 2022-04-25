package com.payconiq.app.util.factory

import com.payconiq.app.models.GithubSearchModel

interface DataFactory {
    fun getGithubSearchModel(): GithubSearchModel
}