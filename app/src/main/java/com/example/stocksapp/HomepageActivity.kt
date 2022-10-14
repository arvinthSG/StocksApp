package com.example.stocksapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stocksapp.Adapter.PortfolioListAdapter
import com.example.stocksapp.Data.Stock

class HomepageActivity : AppCompatActivity(), HomepageContract.View {
    private lateinit var tvWelcomeUser: TextView
    private lateinit var rvPortfolioList: RecyclerView
    private lateinit var presenter: HomepagePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvWelcomeUser = findViewById(R.id.tv_welcome_user)
        rvPortfolioList = findViewById(R.id.rv_portfolio_list)
        presenter = HomepagePresenter(this, HomepageModel())
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewLoaded()
    }

    override fun loadView() {
        TODO("Not yet implemented")
    }

    override fun updateWelcomeMessage(userName: String) {
        tvWelcomeUser.text = getString(R.string.welcome_user, userName)
    }

    override fun updatePortfolio(listOfStocks: List<Stock>) {
        rvPortfolioList.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context,2)
            adapter = PortfolioListAdapter(listOfStocks)
        }
    }
}