package com.example.yetanothermoviedbapp.presentation.showsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.example.yetanothermoviedbapp.common.components.ShimmerCardView
import com.example.yetanothermoviedbapp.common.hide
import com.example.yetanothermoviedbapp.common.show
import com.example.yetanothermoviedbapp.databinding.FragmentShowsListBinding
import com.example.yetanothermoviedbapp.domain.models.ShowsListItem
import com.example.yetanothermoviedbapp.presentation.BindingFragment
import com.example.yetanothermoviedbapp.presentation.showsList.showsListViewModel.ShowsListViewModel
import com.example.yetanothermoviedbapp.presentation.showsList.showsListViewModel.ShowsListViewModelEvents
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ShowsListFragment : BindingFragment<FragmentShowsListBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentShowsListBinding::inflate

    private val viewModel: ShowsListViewModel by sharedViewModel()
    private val showsListAdapter by lazy { ShowsListAdapter(layoutInflater, ::scrolledToItemsEnd, ::onShowsClick) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getShowsList()
        setUpUi()
        observeViewModel()
    }

    private fun setUpUi() {
        binding.moviesListRv.apply {
            layoutManager = LinearLayoutManager(activity?.applicationContext)
            adapter = showsListAdapter
        }

        binding.shimmerView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent { ShimmerCardView() }
            show()
        }

        binding.movieRefresher.setOnRefreshListener { viewModel.getShowsList() }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.onEach { state ->
                when (state.eventName) {
                    ShowsListViewModelEvents.Loading -> {
                        showsListAdapter.clear()
                        binding.shimmerView.show()
                    } //show Shimmer
                    ShowsListViewModelEvents.Success -> { updateViews(viewModel.state.value.items) } //show Error if there are shows
                    ShowsListViewModelEvents.Error -> { } //show Items
                    else -> {}
                }
            }.launchIn(this)
        }
    }

    private fun updateViews(items: List<ShowsListItem>) {
        binding.apply {
            shimmerView.hide()
            moviesListRv.show()
            movieRefresher.isRefreshing = false
            offlineUiTv.hide()
        }
        timber.log.Timber.i("PAOK here with the items ${items}")
        showsListAdapter.addAll(items)
    }

    private fun scrolledToItemsEnd() {}

    private fun onShowsClick(showsId: Int) {}
}