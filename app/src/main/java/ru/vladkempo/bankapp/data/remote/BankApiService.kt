package ru.vladkempo.bankapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface BankApiService {

    @GET("operations")
    suspend fun getOperations(@Query("page") page: Int): List<OperationDTO> // Оставим пока так для простоты, или используй BaseResponse
}