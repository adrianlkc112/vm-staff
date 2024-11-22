package com.virginmoney.network

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Call
import retrofit2.http.GET

abstract class NetworkModuleTestSetup {
    protected val mockWebServer = MockWebServer()

    @Before
    open fun setup() {
        clearAllMocks()

        MockKAnnotations.init(this)
    }

    @After
    open fun tearDown() {
        mockWebServer.shutdown()
    }

    protected interface TestApi {
        @GET("test")
        fun test(): Call<TestData>
    }

    @JsonClass(generateAdapter = true)
    data class TestData(
        val name: String,
    )

    protected fun getMockWebServerHost(): String = "${mockWebServer.url("")}"

    protected fun provideMoshiMock(): Moshi = NetworkModule.provideMoshi()
}
