package com.example.stocksapp.PortfolioPage

import android.util.Log
import androidx.annotation.VisibleForTesting
import com.example.stocksapp.Data.Stock

class PortfolioPagePresenter(
    private var portfolioPageView: PortfolioPageContract.View?,
    private val model: PortfolioPageContract.Model
) : PortfolioPageContract.Presenter, PortfolioPageContract.Model.OnModelResponseListener {

    override fun onViewLoaded() {
        Log.d(TAG, "onViewLoaded()")
        model.init(this)
        updateUserName()
        model.getPortfolio()
    }

    override fun onViewDetached() {
        Log.d(TAG, "onViewDetached()")
        model.destroy()
        portfolioPageView = null
    }

    override fun reloadPortfolio() {
        model.getPortfolio()
    }

    override fun onPortfolioResponse(listOfStocks: List<Stock>) {
        Log.d(TAG, "onPortfolioResponse() ${listOfStocks.size}")
        portfolioPageView?.updatePortfolio(listOfStocks)
    }

    override fun onPortfolioResponseError() {
        Log.d(TAG, "onPortfolioResponseError()")
        portfolioPageView?.showErrorMessage()
    }

    override fun onPortfolioEmptyResponse() {
        portfolioPageView?.showEmptyMessage()
    }

    @VisibleForTesting
    fun updateUserName() {
        Log.d(TAG, "updateUserName()")
        val userName = model.getUserName()
        portfolioPageView?.updateWelcomeMessage(userName)
    }

    companion object {
        private const val TAG = "PortfolioPagePresenter"
    }
}