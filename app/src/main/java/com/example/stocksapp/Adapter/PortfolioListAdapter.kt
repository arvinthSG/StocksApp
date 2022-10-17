package com.example.stocksapp.Adapter

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stocksapp.Data.Stock
import com.example.stocksapp.R
import java.util.Currency

class PortfolioListAdapter(private val data: List<Stock>) :
    RecyclerView.Adapter<PortfolioListAdapter.ViewHolder>() {

    private lateinit var context: Context

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvStockTicker: TextView = itemView.findViewById(R.id.tv_stock_ticker)
        val tvStockName: TextView = itemView.findViewById(R.id.tv_stock_name)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_currency_price)
        val tvQuantity: TextView = itemView.findViewById(R.id.tv_quantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        this.context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.portfolio_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val stockItem = data[position]
        viewHolder.tvStockTicker.text = stockItem.ticker
        viewHolder.tvStockName.text = stockItem.name
        var currencySymbol = ""
        if (stockItem.currency.isNotEmpty()) {
            currencySymbol = Currency.getInstance(stockItem.currency).symbol
        }
        "$currencySymbol${(stockItem.currentPriceCents / 100.00)}".also {
            viewHolder.tvPrice.text = it
        }
        if (stockItem.quantity == null) {
            viewHolder.tvQuantity.visibility = GONE
        } else {
            viewHolder.tvQuantity.text =
                context.getString(R.string.quantity, stockItem.quantity.toString())
        }
    }

    override fun getItemCount(): Int = data.size
}