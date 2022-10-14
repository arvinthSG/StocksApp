package com.example.stocksapp.Data

import com.google.gson.annotations.SerializedName

data class Stocks(
    @SerializedName("stocks")
    val stocks: ArrayList<Stock>
)
