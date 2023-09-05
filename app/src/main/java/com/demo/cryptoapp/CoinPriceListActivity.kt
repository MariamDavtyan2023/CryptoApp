package com.demo.cryptoapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.demo.cryptoapp.adapters.CoinInfoAdapter
import com.demo.cryptoapp.pojo.CoinPriceInfo


class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        val rvCoinPriceList = findViewById<RecyclerView>(R.id.rvCoinPriceList)
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener{
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                val intent = CoinDetailActivity.newIntent(this@CoinPriceListActivity, coinPriceInfo.fromSymbol)
                startActivity(intent)
            }
        }
        rvCoinPriceList.adapter = adapter
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.priceList.observe(this) {
            adapter.coinInfoList = it
        }
    }

}