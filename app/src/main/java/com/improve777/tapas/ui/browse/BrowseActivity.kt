package com.improve777.tapas.ui.browse

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.improve777.tapas.R
import com.improve777.tapas.base.BaseActivity
import com.improve777.tapas.databinding.ActivityBrowseBinding
import com.improve777.tapas.domain.model.State
import com.improve777.tapas.ui.models.Error
import com.improve777.tapas.ui.series.SeriesActivity
import com.improve777.tapas.ui.utils.fetchError
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
        binding.rvBrowse.addItemDecoration(SeriesItemDecoration())

        binding.srlBrowse.setOnRefreshListener {
            viewModel.loadBrowse()
            scrollListener.resetState()
            hideError()
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
                    showError()
                    fetchErrorLayout(Error.Empty)
                }
                else -> {
                    showError()
                    fetchErrorLayout(Error.Network)
                }
            }
        }

        viewModel.openRetrySeriesInfoEvent.observe(this, EventObserver {
            openSeriesInfoRetryDialog(it.seriesId)
        })

        viewModel.loading.observe(this) {
            binding.pbBrowse.isVisible = it
        }

        viewModel.goToSeriesEvent.observe(this, EventObserver { seriesInfo ->
            SeriesActivity.startActivity(this, seriesInfo)
        })
    }

    private fun openSeriesInfoRetryDialog(seriesId: Int) {
        AlertDialog.Builder(this)
            .setTitle(R.string.check_your_network)
            .setMessage(R.string.please_try_again)
            .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                dialog.dismiss()
                viewModel.goToSeriesView(seriesId)
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun fetchErrorLayout(error: Error) {
        binding.layoutError.fetchError(error)
    }

    private fun showError() {
        binding.rvBrowse.isVisible = false
        binding.layoutError.content.isVisible = true
    }

    private fun hideError() {
        binding.rvBrowse.isVisible = true
        binding.layoutError.content.isVisible = false
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