package com.virginmoney.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Retrofit

class VmRetrofitTest : NetworkModuleTestSetup() {
    @Test
    fun `provideVmBaseUrl() - should provide correct base URL`() {
        val provideBaseUrl = NetworkModule.provideVmBaseUrl()

        assertEquals("https://61e947967bc0550017bc61bf.mockapi.io/api/v1/", provideBaseUrl)
    }

    // TODO: Fix fail unit test
    @Test
    fun `provideVmRetrofit() - should return correct instance`() {
        val successResponse = MockResponse().setBody("{\"name\":\"test\"}")

        val api = provideVmRetrofit().create(TestApi::class.java)
        mockWebServer.enqueue(successResponse)
        runBlocking(Dispatchers.IO) {
            api.test().execute()
        }

        val request = mockWebServer.takeRequest()
        assertEquals("https://${getMockWebServerHost()}/api/v1/test", request.requestUrl?.toString())
    }

    private fun provideVmRetrofit(): Retrofit =
        NetworkModule.provideVmRetrofit(
            moshi = provideMoshiMock(),
            okHttpClient =
                NetworkModule.provideCommonOkHttpClient(
                    loggingInterceptor = NetworkModule.provideLoggingInterceptor(),
                ),
            baseUrl = "https://${getMockWebServerHost()}/api/v1/",
        )
}
