package com.example.stocksapp

import com.example.stocksapp.Data.Stock

class PortfolioPagePresenter(
    private val portfolioPageView: PortfolioPageContract.View,
    private val model: PortfolioPageContract.Model
) : PortfolioPageContract.Presenter, PortfolioPageContract.Model.OnModelResponseListener {

    override fun onViewLoaded() {
        model.init(this)
        updateUserName()
        model.getPortfolio()
    }

    override fun reloadPortfolio() {
        model.getPortfolio()
    }

    //@TODO - rename
    override fun onPortfolioResponse(listOfStocks: List<Stock>) {
        portfolioPageView.updatePortfolio(listOfStocks)
    }

    //@TODO - update error view
    override fun onPortfolioResponseError() {
        portfolioPageView.updateWelcomeMessage("error")
    }


    private fun updateUserName() {
        val userName = model.getUserName()
        portfolioPageView.updateWelcomeMessage(userName)
    }

}