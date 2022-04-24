package com.payconiq.app.ui.viewmodels

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.payconiq.app.data.SearchPagingSource
import com.payconiq.app.data.repository.DataRepository
import com.payconiq.app.data.repository.GithubRepository
import com.payconiq.app.globals.DEFAULT_QUERY_STRING
import com.payconiq.app.globals.DEFAULT_STRING
import com.payconiq.app.globals.PER_PAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    val dataRepository: DataRepository,
) : ViewModel() {
    @VisibleForTesting(otherwise = PRIVATE )
    internal val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val users = currentQuery.switchMap { queryString ->
        Pager(
            config = PagingConfig(
                pageSize = PER_PAGE,
                maxSize = PER_PAGE * 5,
                enablePlaceholders = false
            ), pagingSourceFactory = { SearchPagingSource(dataRepository, queryString) }
        ).liveData.cachedIn(viewModelScope)
    }

    fun searchUsers(query: String) {
        currentQuery.value = query
    }

    companion object {
        private const val CURRENT_QUERY: String = DEFAULT_STRING
        private const val DEFAULT_QUERY: String = DEFAULT_QUERY_STRING
    }
}