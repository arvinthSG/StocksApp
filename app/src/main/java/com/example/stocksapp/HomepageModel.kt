package com.example.stocksapp

import com.example.stocksapp.Data.Stock
import com.example.stocksapp.HomepageContract.Model.OnModelResponseListener

class HomepageModel : HomepageContract.Model {
    private lateinit var onModelResponseListener: OnModelResponseListener
    override fun getPortfolio() {
        val stock = Stock("ticker 1", "stockName")
        val list = mutableListOf<Stock>()
        list.add(stock)
        list.add(stock)
        list.add(stock)
        onModelResponseListener.onPortfolioResponse(list)
    }

    override fun getUserName(): String {
        return "Arvinth"
    }

    override fun setListener(onModelResponseListener: OnModelResponseListener) {
        this.onModelResponseListener = onModelResponseListener
    }

    companion object {

    }
}