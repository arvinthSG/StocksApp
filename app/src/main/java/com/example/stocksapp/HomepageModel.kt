package com.example.stocksapp

import com.example.stocksapp.HomepageContract.Model.OnModelResponseListener
import com.example.stocksapp.Network.GetStocksService
import com.example.stocksapp.Network.StocksAppNetworkInstance
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class HomepageModel : HomepageContract.Model {
    private lateinit var onModelResponseListener: OnModelResponseListener
    private lateinit var service: Retrofit
    private lateinit var getStocksService: GetStocksService

    override fun getPortfolio() {
        getStocksService.getPortfolio().subscribeOn(Schedulers.io()).subscribe({ it ->
            it.let {
                onModelResponseListener.onPortfolioResponse(it.stocks)
            }
        }, {
            onModelResponseListener.onPortfolioResponseError()
        }
        )
    }

    override fun init(onModelResponseListener: OnModelResponseListener) {
        service = StocksAppNetworkInstance.provideNetworkInstance()
        getStocksService = service.create(GetStocksService::class.java)
        this.onModelResponseListener = onModelResponseListener
    }

    override fun getMalformedPortfolio() {
        getStocksService.getMalformedPortfolio()
            .subscribe({
                it.let {
                    onModelResponseListener.onPortfolioResponse(it.stocks)
                }
            }, {
                onModelResponseListener.onPortfolioResponseError()
            }
            )
    }

    override fun getEmptyPortfolio() {
        getStocksService.getEmptyPortfolio()
            .subscribe({
                it.let {
                    onModelResponseListener.onPortfolioResponse(it.stocks)
                }
            }, {
                onModelResponseListener.onPortfolioResponseError()
            }
            )
    }

    override fun getUserName(): String {
        return "Arvinth"
    }

    companion object {

    }
}