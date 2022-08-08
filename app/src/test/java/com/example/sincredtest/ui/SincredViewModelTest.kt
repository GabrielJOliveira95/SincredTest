package com.example.sincredtest.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.sincredtest.TestCoroutineRule
import com.example.sincredtest.data.datasource.SincredDataSource
import com.example.sincredtest.data.repository.SincredRepository
import com.example.sincredtest.data.repository.SincredRepositoryImpl
import com.example.sincredtest.data.request.SincredCheckInRequest
import com.example.sincredtest.data.response.EventsResponse
import com.example.sincredtest.ui.main.SincredViewModel
import com.example.sincredtest.ui.sealedClass.SincredSealedClassResponse
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

@OptIn(ExperimentalCoroutinesApi::class)
class SincredViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @MockK
    lateinit var observerResponse: Observer<SincredSealedClassResponse>

    private lateinit var viewModel: SincredViewModel

    private lateinit var repository: SincredRepository

    private var dataSourceImpl: SincredDataSource = mockk(relaxed = true)

    var eventsResponse: EventsResponse = mockk(relaxed = true)

    private var request: SincredCheckInRequest = mockk(relaxed = true)


    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = SincredRepositoryImpl(dataSourceImpl)
        viewModel = SincredViewModel(repository)
        viewModel.sealedClassResponse.observeForever(observerResponse)
    }

    @Test
    fun `when the getEvents api return success, must show success screen`() {

        coEvery { repository.getEvents() } returns eventsResponse

        viewModel.loadEvents()

        coVerify {
            observerResponse.onChanged(SincredSealedClassResponse.OnSuccess(eventsResponse))
        }
    }


    @Test
    fun `when the getEvents api return error, must show error screen`() {
        val exception = Exception()

        coEvery { dataSourceImpl.getEvents() }
        coEvery { repository.getEvents() } throws exception

        viewModel.loadEvents()

        coVerify {
            observerResponse.onChanged(SincredSealedClassResponse.OnFailure(exception.message.orEmpty()))
        }
    }

    @Test
    fun `when the check in api return error, must show success screen`() {
        val exception = Exception()

        coEvery { repository.checkIn(request) } throws exception

        viewModel.checkIn(request)

        coVerify {
            observerResponse.onChanged(SincredSealedClassResponse.OnFailure(exception.message.orEmpty()))
        }
    }
}