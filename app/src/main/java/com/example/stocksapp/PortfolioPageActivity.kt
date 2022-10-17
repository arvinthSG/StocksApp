package com.example.stocksapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.stocksapp.Adapter.PortfolioListAdapter
import com.example.stocksapp.Data.Stock

class PortfolioPageActivity : AppCompatActivity(), PortfolioPageContract.View {
    private lateinit var tvWelcomeUser: TextView
    private lateinit var rvPortfolioList: RecyclerView
    private lateinit var presenter: PortfolioPagePresenter
    private lateinit var pbLoadingIcon: ProgressBar
    private lateinit var groupPortfolioViews: Group
    private lateinit var tvErrorMessage: TextView
    private lateinit var tvEmptyMessage: TextView
    private lateinit var srlReloadList: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate()")
        setContentView(R.layout.activity_main)
        tvWelcomeUser = findViewById(R.id.tv_welcome_user)
        rvPortfolioList = findViewById(R.id.rv_portfolio_list)
        presenter = PortfolioPagePresenter(this, PortfolioPageModel())
        pbLoadingIcon = findViewById(R.id.pb_loading_icon)
        groupPortfolioViews = findViewById(R.id.group_portfolio)
        tvErrorMessage =
            findViewById<TextView?>(R.id.tv_error_message).apply { visibility = View.GONE }
        tvEmptyMessage =
            findViewById<TextView?>(R.id.tv_empty_message).apply { visibility = View.GONE }
        srlReloadList = findViewById(R.id.srl_reload_list)
        srlReloadList.setOnRefreshListener {
            srlReloadList.isRefreshing = false
            rvPortfolioList.removeAllViewsInLayout()
            presenter.reloadPortfolio()
        }
    }

    override fun onStart() {
        super.onStart()
        pbLoadingIcon.visibility = View.VISIBLE
        presenter.onViewLoaded()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy()")
        presenter.onViewDetached()
        super.onDestroy()
    }

    override fun updateWelcomeMessage(userName: String) {
        Log.d(TAG, "updateWelcomeMessage()")
        tvWelcomeUser.text = getString(R.string.welcome_user, userName)
    }

    override fun updatePortfolio(listOfStocks: List<Stock>) {
        runOnUiThread {
            tvErrorMessage.visibility = View.GONE
            tvEmptyMessage.visibility = View.GONE

            rvPortfolioList.visibility = View.VISIBLE
            pbLoadingIcon.visibility = View.GONE
            Thread.sleep(1000) // Added to show the loading icon
            rvPortfolioList.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(context, 2)
                adapter = PortfolioListAdapter(listOfStocks)
            }
        }
    }

    override fun showErrorMessage() {
        Log.d(TAG, "showErrorMessage()")
        runOnUiThread {
            groupPortfolioViews.visibility = View.GONE
            tvErrorMessage.visibility = View.VISIBLE
        }

    }

    override fun showEmptyMessage() {
        Log.d(TAG, "showEmptyMessage()")
        runOnUiThread {
            rvPortfolioList.visibility = View.GONE
            tvEmptyMessage.visibility = View.VISIBLE
            pbLoadingIcon.visibility = View.GONE
        }

    }

    companion object {
        private const val TAG = "HomepageActivity"
    }
}