package com.example.stocksapp

import com.example.stocksapp.Data.Stock

interface PortfolioPageContract {
    interface View {
        fun updateWelcomeMessage(userName: String)
        fun updatePortfolio(listOfStocks: List<Stock>)
        fun showErrorMessage()
    }

    interface Presenter {
        fun onViewLoaded()
        fun onViewDetached()
    }

    interface Model {
        fun init(onModelResponseListener: OnModelResponseListener)
        fun getMalformedPortfolio()
        fun getEmptyPortfolio()
        fun getPortfolio()
        fun getUserName(): String
        fun destroy()

        interface OnModelResponseListener {
            fun onPortfolioResponse(listOfStocks: List<Stock>)
            fun onPortfolioResponseError()
        }
    }
}