package com.example.stocksapp

import com.example.stocksapp.Data.Stock

class HomepagePresenter(
    private val homepageActivity: HomepageContract.View,
    private val model: HomepageContract.Model
) : HomepageContract.Presenter, HomepageContract.Model.OnModelResponseListener {
    override fun onViewLoaded() {
        model.setListener(this)
        updateUserName()
        model.getPortfolio()
    }

    //@TODO - rename
    override fun onPortfolioResponse(listOfStocks: List<Stock>) {
        homepageActivity.updatePortfolio(listOfStocks)
    }


    private fun updateUserName() {
        val userName = model.getUserName()
        homepageActivity.updateWelcomeMessage(userName)
    }

}