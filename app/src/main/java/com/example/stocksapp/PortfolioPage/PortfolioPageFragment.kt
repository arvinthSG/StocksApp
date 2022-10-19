package com.example.stocksapp.PortfolioPage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.stocksapp.Adapter.PortfolioListAdapter
import com.example.stocksapp.Data.Stock
import com.example.stocksapp.R

class PortfolioPageFragment : Fragment(), PortfolioPageContract.View {

    private lateinit var tvWelcomeUser: TextView
    private lateinit var rvPortfolioList: RecyclerView
    private lateinit var presenter: PortfolioPagePresenter
    private lateinit var pbLoadingIcon: ProgressBar
    private lateinit var groupPortfolioViews: Group
    private lateinit var tvErrorMessage: TextView
    private lateinit var tvEmptyMessage: TextView
    private lateinit var srlReloadList: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.portfolio_page_view, container, false)
        tvWelcomeUser = view.findViewById(R.id.tv_welcome_user)
        rvPortfolioList = view.findViewById(R.id.rv_portfolio_list)
        presenter = PortfolioPagePresenter(this, PortfolioPageModel())
        pbLoadingIcon = view.findViewById(R.id.pb_loading_icon)
        groupPortfolioViews = view.findViewById(R.id.group_portfolio)
        tvErrorMessage =
            view.findViewById<TextView?>(R.id.tv_error_message).apply { visibility = View.GONE }
        tvEmptyMessage =
            view.findViewById<TextView?>(R.id.tv_empty_message).apply { visibility = View.GONE }
        srlReloadList = view.findViewById(R.id.srl_reload_list)
        srlReloadList.setOnRefreshListener {
            srlReloadList.isRefreshing = false
            rvPortfolioList.removeAllViewsInLayout()
            presenter.reloadPortfolio()
        }
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate()")
    }

    override fun onStart() {
        super.onStart()
    }


    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
        pbLoadingIcon.visibility = View.VISIBLE
        presenter.onViewLoaded()
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

        tvErrorMessage.visibility = View.GONE
        tvEmptyMessage.visibility = View.GONE

        rvPortfolioList.visibility = View.VISIBLE
        pbLoadingIcon.visibility = View.GONE
        Thread.sleep(SLEEP_TIMER) // Added to show the loading icon
        rvPortfolioList.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = PortfolioListAdapter(listOfStocks)
        }
    }


    override fun showErrorMessage() {
        Log.d(TAG, "showErrorMessage()")
        activity?.runOnUiThread {
            groupPortfolioViews.visibility = View.GONE
            tvErrorMessage.visibility = View.VISIBLE
        }

    }

    override fun showEmptyMessage() {
        Log.d(TAG, "showEmptyMessage()")
            rvPortfolioList.visibility = View.GONE
            tvEmptyMessage.visibility = View.VISIBLE
            pbLoadingIcon.visibility = View.GONE
    }

    companion object {
        /**
         * Better way to init a fragment.
         * If the activity has some arguments to be passed to the fragment we can make
         * use this type of instantiation
         */
        @JvmStatic
        fun newInstance() = PortfolioPageFragment()

        const val TAG = "PortfolioPageFragment"
        private const val SLEEP_TIMER = 1000L
    }
}