package com.improve777.tapas.ui.series

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import com.improve777.tapas.R
import com.improve777.tapas.domain.model.State
import com.improve777.tapas.base.BaseActivity
import com.improve777.tapas.databinding.ActivitySeriesBinding
import com.improve777.tapas.ui.models.Error
import com.improve777.tapas.domain.model.SeriesInfo
import com.improve777.tapas.ui.utils.addOnRangePercentChangedListener
import com.improve777.tapas.ui.utils.loadUrl
import com.improve777.tapas.ui.utils.updateDimensionRatioByThumbnailType
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.pow

@AndroidEntryPoint
class SeriesActivity : BaseActivity<ActivitySeriesBinding>(ActivitySeriesBinding::inflate) {

    private val viewModel: SeriesViewModel by viewModels()

    private val episodeAdapter: EpisodeAdapter by lazy {
        EpisodeAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clearStatusBarColor()
        fetchHeaderWindowInsets()
        initView()
        observeViewModel()
        initData()
    }

    private fun initView() {
        binding.ablSeries.addOnRangePercentChangedListener { rangePercent ->
            val percent = rangePercent.pow(1.2f)
            binding.layoutHeader.clRoot.alpha = percent
            binding.tvToolbarTitle.visibility =
                if (percent <= 0.2f) View.VISIBLE else View.INVISIBLE

        }

        binding.rvSeries.adapter = episodeAdapter
        binding.rvSeries.addItemDecoration(EpisodeItemDecoration())

        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun observeViewModel() {
        viewModel.seriesInfo.observe(this) {
            binding.tvToolbarTitle.text = it.title
            with(binding.layoutHeader) {
                updateDimensionRatioByThumbnailType(ivThumbnail, it.isBookCover)
                ivThumbnail.loadUrl(it.thumbnailUrl)
                tvTitle.text = it.title
                tvCreator.text = it.creator
            }
        }

        viewModel.episodeVoList.observe(this) {
            hideError()
            val episodeListWithTitle = it.toMutableList()
            episodeListWithTitle.add(0,
                EpisodeVo.SectionName(getString(R.string.episode_count_format, it.size)))
            episodeAdapter.submitList(episodeListWithTitle)
        }

        viewModel.loading.observe(this) {
            binding.pbSeries.isVisible = it
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
    }

    private fun initData() {
        val seriesInfo = intent.getParcelableExtra<SeriesInfo>(EXTRA_SERIES_INFO) ?: return
        viewModel.setData(seriesInfo)
        viewModel.loadEpisodeList()
    }

    private fun fetchHeaderWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.ctlSeries) { _, insets ->
            val statusBarHeight = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            binding.ablSeries.updatePadding(top = statusBarHeight)
            insets
        }
    }

    private fun showError() {
        binding.rvSeries.isVisible = false
        binding.layoutError.content.isVisible = true
    }

    private fun hideError() {
        binding.rvSeries.isVisible = true
        binding.layoutError.content.isVisible = false
    }

    private fun fetchErrorLayout(error: Error) {
        binding.layoutError.fetchError(error)
    }

    companion object {
        private const val EXTRA_SERIES_INFO = "EXTRA_SERIES_INFO"

        fun startActivity(context: Context, seriesInfo: SeriesInfo) {
            context.startActivity(Intent(context, SeriesActivity::class.java).apply {
                putExtra(EXTRA_SERIES_INFO, seriesInfo)
            })
        }
    }
}