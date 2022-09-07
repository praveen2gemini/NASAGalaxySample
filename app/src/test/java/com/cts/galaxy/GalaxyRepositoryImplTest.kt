package com.cts.galaxy

import com.cts.galaxy.api.ApiHelper
import com.cts.galaxy.api.ApiService
import com.cts.galaxy.api.models.GalaxyRequestDTO
import com.cts.galaxy.api.repository.GalaxyRepositoryImpl
import com.cts.galaxy.common.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

/**
 * @author Praveen Kumar Sugumaran
 */
@OptIn(ExperimentalCoroutinesApi::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GalaxyRepositoryImplTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val apiService: ApiService = MockApiService()

    private val apiHelper: ApiHelper = ApiHelper(apiService)

    private val subject: GalaxyRepositoryImpl = GalaxyRepositoryImpl(apiHelper)

    @Test
    fun `Test fetchContextualArrangements onSuccess `(): Unit = runBlocking {
        val mockRequest = GalaxyRequestDTO(searchKey, mediaType, yearStart, yearEnd)
        subject.fetchGalaxyCollection(mockRequest)
    }

    companion object {
        private const val searchKey = "milky way"
        private const val mediaType = "image"
        private const val yearStart = "2017"
        private const val yearEnd = "2022"
    }
}