package com.example.yetanothermoviedbapp.presentation.showDetails

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.text.HtmlCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.yetanothermoviedbapp.common.Constants.SHOW_ID
import com.example.yetanothermoviedbapp.common.components.ShimmerShowDetails
import com.example.yetanothermoviedbapp.common.hide
import com.example.yetanothermoviedbapp.common.show
import com.example.yetanothermoviedbapp.databinding.FragmentShowDetailsBinding
import com.example.yetanothermoviedbapp.domain.models.ShowDetails
import com.example.yetanothermoviedbapp.presentation.BindingFragment
import com.example.yetanothermoviedbapp.presentation.showDetails.showDetailsViewModel.ShowDetailsViewModelEvents
import com.example.yetanothermoviedbapp.presentation.showDetails.showDetailsViewModel.ShowDetailsViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import kotlin.math.abs

class ShowDetailsFragment : BindingFragment<FragmentShowDetailsBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentShowDetailsBinding::inflate

    private val viewModel: ShowDetailsViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        setUpViewModel()
    }

    private fun setUpUi() {
        viewModel.getShowDetails(requireArguments().getInt(SHOW_ID))
        binding.apply {
            scrollView.hide()
            shimmerView.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent { ShimmerShowDetails() }
            }
            backBtn.setOnClickListener { findNavController().popBackStack() }
        }
    }

    private fun setUpViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.onEach { state ->
                when (state.eventName) {
                    ShowDetailsViewModelEvents.Loading -> { binding.shimmerView.show() }
                    ShowDetailsViewModelEvents.Success -> { updateViews(viewModel.state.value.showDetails) }
                    ShowDetailsViewModelEvents.Error -> {}
                    else -> {}
                }
            }.launchIn(this)
        }
    }

    private fun updateViews(showDetails: ShowDetails?) {
        binding.apply {
            shimmerView.hide()
            scrollView.show()
            appBar.show()
            offlineUiTv.hide()
            showTitleTv.text = showDetails?.name


            appBar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
                if (abs(verticalOffset) >= appBarLayout.totalScrollRange) {
                    showDetailsLayout.hide()
                    collapsingToolbar.title = showDetails?.name
                } else {
                    showDetailsLayout.show()
                    collapsingToolbar.title = " "
                }
            }

            Glide.with(requireContext()).load(showDetails?.image?.original).into(showPosterIv)
            descriptionDetailsTv.text = Html.fromHtml(showDetails?.summary, HtmlCompat.FROM_HTML_MODE_COMPACT)
            ratingBar.rating = (showDetails?.rating?.average?.toFloat()?.div(2) ?: 0) as Float
        }
    }
}