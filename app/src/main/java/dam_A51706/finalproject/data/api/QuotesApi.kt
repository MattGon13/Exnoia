package dam_A51706.finalproject.data.api

import dam_A51706.finalproject.data.model.Quote
import retrofit2.http.GET

interface ZenQuotesApi {
    @GET("api/random")
    suspend fun getRandomQuote(): List<Quote>
}