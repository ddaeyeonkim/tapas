package com.improve777.tapas.ui.series

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.google.android.material.appbar.AppBarLayout
import com.improve777.tapas.base.BaseActivity
import com.improve777.tapas.databinding.ActivitySeriesBinding
import kotlin.math.abs
import kotlin.math.pow

class SeriesActivity : BaseActivity<ActivitySeriesBinding>(ActivitySeriesBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clearStatusBarColor()
        fetchHeaderWindowInsets()
        initView()
    }

    private fun initView() {
        binding.ablSeries.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            val totalScrollRange = binding.ablSeries.totalScrollRange
            val scrolledRange = abs(verticalOffset + totalScrollRange)
            val percent = (scrolledRange / totalScrollRange.toFloat()).pow(1.2f)
            binding.layoutHeader.clRoot.alpha = percent
            binding.tvToolbarTitle.visibility = if (percent <= 0.2f) View.VISIBLE else View.INVISIBLE
        })
    }

    private fun clearStatusBarColor() {
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }

    private fun fetchHeaderWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.ctlSeries) { _, insets ->
            val statusBarHeight = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            binding.ablSeries.updatePadding(top = statusBarHeight)
            insets
        }
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, SeriesActivity::class.java))
        }
    }
}