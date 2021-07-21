package com.improve777.tapas.ui.browse

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.improve777.tapas.R
import com.improve777.tapas.State
import com.improve777.tapas.base.BaseActivity
import com.improve777.tapas.databinding.ActivityBrowseBinding
import com.improve777.tapas.domain.model.Error
import com.improve777.tapas.ui.series.SeriesActivity
import com.improve777.tapas.ui.utils.EndlessRecyclerViewScrollListener
import com.improve777.tapas.ui.utils.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BrowseActivity : BaseActivity<ActivityBrowseBinding>(ActivityBrowseBinding::inflate) {

    private val viewModel: BrowseViewModel by viewModels()

    private val browseAdapter = SeriesAdapter {
        viewModel.goToSeriesView(it)
    }

    private val scrollListener: EndlessRecyclerViewScrollListener by lazy {
        object : EndlessRecyclerViewScrollListener(binding.rvBrowse.layoutManager!!) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                viewModel.loadBrowse(true)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clearStatusBarColor()
        fetchHeaderWindowInsets()
        initView()
        observeViewModel()

        viewModel.loadBrowse()
    }

    private fun initView() {
        binding.rvBrowse.adapter = browseAdapter
        binding.rvBrowse.addOnScrollListener(scrollListener)

        binding.srlBrowse.setOnRefreshListener {
            viewModel.loadBrowse()
            scrollListener.resetState()
            binding.rvBrowse.isVisible = true
            binding.layoutError.content.isVisible = false
            binding.srlBrowse.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.seriesList.observe(this) {
            browseAdapter.submitList(it)
        }

        viewModel.error.observe(this) {
            Log.w("error", it.message)

            when (it.status) {
                State.Error.STATUS_JSON_ERROR, State.Error.STATUS_EMPTY -> {
                    binding.rvBrowse.isVisible = false
                    binding.layoutError.content.isVisible = true

                    binding.layoutError.ivError.setImageDrawable(
                        ContextCompat.getDrawable(this, Error.Empty.iconRes))
                    binding.layoutError.tvErrorMessage.text = getString(Error.Empty.message)
                }
                else -> {
                    binding.rvBrowse.isVisible = false
                    binding.layoutError.content.isVisible = true

                    binding.layoutError.ivError.setImageDrawable(
                        ContextCompat.getDrawable(this, Error.Network.iconRes))
                    binding.layoutError.tvErrorMessage.text = getString(Error.Network.message)
                }
            }
        }

        viewModel.openRetrySeriesInfoEvent.observe(this, EventObserver {
            AlertDialog.Builder(this)
                .setTitle(R.string.check_your_network)
                .setMessage(R.string.please_try_again)
                .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                    dialog.dismiss()
                    viewModel.goToSeriesView(it.seriesId)
                }
                .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        })

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