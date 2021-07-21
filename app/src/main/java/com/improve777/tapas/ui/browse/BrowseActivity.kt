package com.improve777.tapas.ui.browse

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.improve777.tapas.base.BaseActivity
import com.improve777.tapas.databinding.ActivityBrowseBinding
import com.improve777.tapas.ui.series.SeriesActivity
import com.improve777.tapas.ui.utils.EndlessRecyclerViewScrollListener
import com.improve777.tapas.ui.utils.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BrowseActivity : BaseActivity<ActivityBrowseBinding>(ActivityBrowseBinding::inflate) {

    private val viewModel: BrowseViewModel by viewModels()

    private val browseAdapter = BrowseAdapter {
        viewModel.goToSeriesView()
    }

    private val scrollListener: EndlessRecyclerViewScrollListener by lazy {
        object : EndlessRecyclerViewScrollListener(binding.rvBrowse.layoutManager!!) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                viewModel.getBrowseList(true)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clearStatusBarColor()
        fetchHeaderWindowInsets()
        initView()
        observeViewModel()

        viewModel.getBrowseList()
    }

    private fun initView() {
        binding.rvBrowse.adapter = browseAdapter
        binding.rvBrowse.addOnScrollListener(scrollListener)

        binding.srlBrowse.setOnRefreshListener {
            viewModel.getBrowseList()
            scrollListener.resetState()
            binding.srlBrowse.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.browseList.observe(this) {
            browseAdapter.submitList(it)
        }

        viewModel.error.observe(this) {
            binding.rvBrowse.isVisible = false
            binding.layoutError.content.isVisible = true
        }

        viewModel.loading.observe(this) {
            binding.pbBrowse.isVisible = it
        }

        viewModel.goToSeriesEvent.observe(this, EventObserver { seriesInfo ->
            SeriesActivity.startActivity(this, seriesInfo)
        })
    }

    private fun clearStatusBarColor() {
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }

    private fun fetchHeaderWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.appbarLayout) { _, insets ->
            val statusBarHeight = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            binding.appbarLayout.updateLayoutParams { height += statusBarHeight }
            binding.toolbar.ctBrowse.minimumHeight += statusBarHeight
            insets
        }
    }
}