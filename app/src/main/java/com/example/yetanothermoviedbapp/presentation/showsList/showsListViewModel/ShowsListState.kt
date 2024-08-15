package com.example.yetanothermoviedbapp.presentation.showsList.showsListViewModel

import com.example.yetanothermoviedbapp.domain.models.ShowsListItem

data class ShowsListState(
    val eventName: ShowsListViewModelEvents = ShowsListViewModelEvents.None,
    val items: List<ShowsListItem> = listOf()
)

sealed class ShowsListViewModelEvents {
    object None : ShowsListViewModelEvents()
    object Loading : ShowsListViewModelEvents()
    object Success : ShowsListViewModelEvents()
    object Error : ShowsListViewModelEvents()
}