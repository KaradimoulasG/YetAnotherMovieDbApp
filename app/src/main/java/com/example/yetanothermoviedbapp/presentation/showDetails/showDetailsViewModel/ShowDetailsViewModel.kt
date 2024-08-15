package com.example.yetanothermoviedbapp.presentation.showDetails.showDetailsViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yetanothermoviedbapp.common.Resource
import com.example.yetanothermoviedbapp.domain.use_case.GetShowDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class ShowDetailsViewModel(
    private val useCase: GetShowDetailsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ShowDetailsState())
    val state = _state.asStateFlow()

    init {
        _state.value = ShowDetailsState()
    }

    fun getShowDetails(showId: Int) {
        _state.update { it.copy(eventName = ShowDetailsViewModelEvents.Loading) }

        useCase(showId).onEach { result ->
            when (result) {
                is Resource.Error -> _state.update { it.copy(eventName = ShowDetailsViewModelEvents.Error) }
                is Resource.Loading -> _state.update { it.copy(eventName = ShowDetailsViewModelEvents.Loading) }
                is Resource.Success -> _state.update {
                    it.copy(
                        eventName = ShowDetailsViewModelEvents.Success,
                        showDetails = result.data
                    )
                }
                else -> _state.update { it.copy(eventName = ShowDetailsViewModelEvents.None) }
            }

        }.launchIn(viewModelScope)
    }
}