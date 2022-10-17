package com.example.stocksapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.stocksapp.PortfolioPage.PortfolioPageFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val portfolioFragment = PortfolioPageFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fl_container,
                portfolioFragment,
                PortfolioPageFragment.TAG
            )
            .commit()
    }
}