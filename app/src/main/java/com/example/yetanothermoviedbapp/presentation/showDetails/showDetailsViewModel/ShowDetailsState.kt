package com.example.yetanothermoviedbapp.presentation.showDetails.showDetailsViewModel

import com.example.yetanothermoviedbapp.domain.models.ShowDetails

data class ShowDetailsState(
    val eventName: ShowDetailsViewModelEvents = ShowDetailsViewModelEvents.None,
    val showDetails: ShowDetails? = null
)

sealed class ShowDetailsViewModelEvents {

    object None: ShowDetailsViewModelEvents()
    object Loading: ShowDetailsViewModelEvents()
    object Success: ShowDetailsViewModelEvents()
    object Error: ShowDetailsViewModelEvents()
}