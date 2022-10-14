package com.example.stocksapp.Network

import com.example.stocksapp.Data.Stocks
import io.reactivex.Observable
import retrofit2.http.GET

interface GetStocksService {

    @GET("portfolio.json")
    fun getPortfolio(): Observable<Stocks>

    @GET("portfolio_malformed.json")
    fun getMalformedPortfolio(): Observable<Stocks>

    @GET("portfolio_empty.json")
    fun getEmptyPortfolio(): Observable<Stocks>
}