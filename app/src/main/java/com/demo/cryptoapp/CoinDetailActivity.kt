package com.demo.cryptoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)

        val tvPrice = findViewById<TextView>(R.id.tvPrice)
        val tvMinPrice = findViewById<TextView>(R.id.tvMinPrice)
        val tvMaxPrice = findViewById<TextView>(R.id.tvMaxPrice)
        val tvLastMarket = findViewById<TextView>(R.id.tvLastMarket)
        val tvLastUpdate = findViewById<TextView>(R.id.tvLastUpdate)
        val tvFromSym = findViewById<TextView>(R.id.tvFromSym)
        val tvToSymbol = findViewById<TextView>(R.id.tvToSymbol)
        val ivLogo = findViewById<ImageView>(R.id.ivLogo)

        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)){
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)?: ""
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailInfo(fromSymbol).observe(this) {
            tvPrice.text = it.price.toString()
            tvMinPrice.text = it.lowDay.toString()
            tvMaxPrice.text = it.highDay.toString()
            tvLastMarket.text = it.lastMarket
            tvLastUpdate.text = it.getFormattedTime()
            tvFromSym.text = it.fromSymbol
            tvToSymbol.text = it.toSymbol
            Picasso.get().load(it.getFullImageUrl()).into(ivLogo)
        }
    }

    companion object{
        private const val EXTRA_FROM_SYMBOL = "fSym"
        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}