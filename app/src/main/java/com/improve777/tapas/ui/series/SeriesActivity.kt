package com.improve777.tapas.ui.series

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.google.android.material.appbar.AppBarLayout
import com.improve777.tapas.base.BaseActivity
import com.improve777.tapas.databinding.ActivitySeriesBinding
import com.improve777.tapas.domain.model.SeriesInfo
import com.improve777.tapas.ui.utils.loadUrl
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs
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
        binding.ablSeries.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            val totalScrollRange = binding.ablSeries.totalScrollRange
            val scrolledRange = abs(verticalOffset + totalScrollRange)
            val percent = (scrolledRange / totalScrollRange.toFloat()).pow(1.2f)
            binding.layoutHeader.clRoot.alpha = percent
            binding.tvToolbarTitle.visibility = if (percent <= 0.2f) View.VISIBLE else View.INVISIBLE
        })

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
                val layoutParams = ivThumbnail.layoutParams as? ConstraintLayout.LayoutParams
                layoutParams?.dimensionRatio = if (it.isBookCover) "1:1.5" else "1:1"
                ivThumbnail.layoutParams = layoutParams

                ivThumbnail.loadUrl(it.thumbnailUrl)
                tvTitle.text = it.title
                tvCreator.text = it.creator
            }
        }

        viewModel.episodeVoList.observe(this) {
            episodeAdapter.submitList(it)
        }
    }

    private fun initData() {
        val seriesInfo = intent.getParcelableExtra<SeriesInfo>(EXTRA_SERIES_INFO) ?: return
        viewModel.setData(seriesInfo)
        viewModel.loadEpisodeList()
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
        private const val EXTRA_SERIES_INFO = "EXTRA_SERIES_INFO"

        fun startActivity(context: Context, seriesInfo: SeriesInfo) {
            context.startActivity(Intent(context, SeriesActivity::class.java).apply {
                putExtra(EXTRA_SERIES_INFO, seriesInfo)
            })
        }
    }
}