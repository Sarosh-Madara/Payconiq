package com.payconiq.app.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.payconiq.app.data.repository.DataRepository
import com.payconiq.app.util.LiveDataUtils.getOrAwaitValueTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class SearchViewModelTest{
    @Mock
    private lateinit var dataRepository: DataRepository
    private lateinit var viewModel: SearchViewModel
    private val testDispatcher = TestCoroutineDispatcher()
    private val query = "jakewharton"

    @get:Rule
    var instantTaskExecutorRule : InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = SearchViewModel(dataRepository)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun searchUsers() = runBlockingTest {
        viewModel.searchUsers(query)
        Truth.assertThat(viewModel.currentQuery.value).isEqualTo(query)
    }

    @Test
    fun searchUsers_canGetResponse() = runBlockingTest {
        viewModel.searchUsers(query)
        val liveData = viewModel.users.getOrAwaitValueTest()
        Truth.assertThat(liveData).isNotNull()

    }

}