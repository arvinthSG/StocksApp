package com.example.stocksapp

import com.example.stocksapp.Data.Stock

interface PortfolioPageContract {
    interface View {
        fun loadView()
        fun updateWelcomeMessage(userName: String)
        fun updatePortfolio(listOfStocks: List<Stock>)
    }

    interface Presenter {
        fun onViewLoaded()
        fun reloadPortfolio()
    }

    interface Model {
        fun init(onModelResponseListener: OnModelResponseListener)
        fun getMalformedPortfolio()
        fun getEmptyPortfolio()
        fun getPortfolio()
        fun getUserName(): String

        interface OnModelResponseListener {
            fun onPortfolioResponse(listOfStocks: List<Stock>)
            fun onPortfolioResponseError()
        }
    }
}