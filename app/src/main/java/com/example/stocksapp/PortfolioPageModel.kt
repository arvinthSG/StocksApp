package com.example.stocksapp

import android.util.Log
import com.example.stocksapp.PortfolioPageContract.Model.OnModelResponseListener
import com.example.stocksapp.Network.GetStocksService
import com.example.stocksapp.Network.StocksAppNetworkInstance
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class PortfolioPageModel : PortfolioPageContract.Model {

    private val compositeDisposable = CompositeDisposable()
    private lateinit var onModelResponseListener: OnModelResponseListener
    private lateinit var service: Retrofit
    private lateinit var getStocksService: GetStocksService

    override fun init(onModelResponseListener: OnModelResponseListener) {
        Log.d(TAG, "init()")
        service = StocksAppNetworkInstance.provideNetworkInstance()
        getStocksService = service.create(GetStocksService::class.java)
        this.onModelResponseListener = onModelResponseListener
    }

    // Main function that retrieves the portfolio from the backend
    override fun getPortfolio() {
        Log.d(TAG, "getPortfolio()")
        compositeDisposable.add(
            getStocksService.getPortfolio().subscribeOn(Schedulers.io()).subscribe({ it ->
                it.let {
                    onModelResponseListener.onPortfolioResponse(it.stocks)
                }
            }, {
                onModelResponseListener.onPortfolioResponseError()
            }
            )
        )
    }

    // Function that returns a malformed the portfolio response
    override fun getMalformedPortfolio() {
        Log.d(TAG, "getMalformedPortfolio()")
        compositeDisposable.add(
            getStocksService.getMalformedPortfolio()
                .subscribe({
                    it.let {
                        onModelResponseListener.onPortfolioResponse(it.stocks)
                    }
                }, {
                    onModelResponseListener.onPortfolioResponseError()
                }
                )
        )
    }

    // Function that returns a empty portfolio response
    override fun getEmptyPortfolio() {
        Log.d(TAG, "getEmptyPortfolio()")
        compositeDisposable.add(
            getStocksService.getEmptyPortfolio()
                .subscribe({
                    it.let {
                        onModelResponseListener.onPortfolioResponse(it.stocks)
                    }
                }, {
                    onModelResponseListener.onPortfolioResponseError()
                }
                )
        )
    }


    override fun getUserName() = USERNAME

    override fun destroy() {
        compositeDisposable.clear()
    }

    companion object {
        private const val TAG = "HomepageModel"
        private const val USERNAME = "Arvinthan"
    }
}