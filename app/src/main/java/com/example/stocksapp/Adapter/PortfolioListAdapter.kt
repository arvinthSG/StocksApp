package com.example.stocksapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stocksapp.Data.Stock
import com.example.stocksapp.R

class PortfolioListAdapter(private val data: List<Stock>): RecyclerView.Adapter<PortfolioListAdapter.ViewHolder>()  {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvStockTicker: TextView = itemView.findViewById(R.id.tv_stock_ticker)
        val tvStockName: TextView = itemView.findViewById(R.id.tv_stock_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.portfolio_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val stockItem = data[position]
        viewHolder.tvStockTicker.text = stockItem.ticker
        viewHolder.tvStockName.text = stockItem.name

    }

    override fun getItemCount(): Int = data.size
}