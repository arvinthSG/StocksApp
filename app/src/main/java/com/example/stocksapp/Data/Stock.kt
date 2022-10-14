package com.example.stocksapp.Data

import com.google.gson.annotations.SerializedName
import java.util.Currency

data class Stock(

    @SerializedName("ticker")
    val ticker: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("current_price_cents")
    val currentPriceCents: Int,
    @SerializedName("quantity")
    val quantity: Int?,
    @SerializedName("current_price_timestamp")
    val currentPriceTimestamp: Int
)


