package com.niko.cryptoapp.data.network

import com.niko.cryptoapp.data.network.dto.CoinList
import com.niko.cryptoapp.data.network.dto.CoinPriceInfoRawData
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers("$HEADER_APIKEY: $API_KEY")
    @GET("top/totaltoptiervolfull")
    suspend fun getCoinList(
        @Query(QUERY_LIMIT) limitPerPage: Int = DEFAULT_LIMIT_PER_PAGE,
        @Query(QUERY_TSYM) currency: String = DEFAULT_CURRENCY
    ): CoinList

    @Headers("$HEADER_APIKEY: $API_KEY")
    @GET("pricemultifull")
    suspend fun getFullPriceList(
        @Query(QUERY_FSYMS) cryptovalutes: String,
        @Query(QUERY_TSYMS) currency: String = DEFAULT_CURRENCY
    ): CoinPriceInfoRawData

    companion object {
        private const val QUERY_LIMIT = "limit"
        private const val QUERY_TSYM = "tsym"
        private const val QUERY_TSYMS = "tsyms"
        private const val QUERY_FSYMS = "fsyms"
        private const val HEADER_APIKEY = "Apikey"
        private const val API_KEY =
            "6406373fa4918e2850c36859800bbc626d85126a0ed88dbdc0c323931f9e2ca9"
        private const val DEFAULT_LIMIT_PER_PAGE = 10
        private const val DEFAULT_CURRENCY = "USD"
    }
}
