package com.example.yetanothermoviedbapp.presentation.showsList.showsListViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yetanothermoviedbapp.common.Resource
import com.example.yetanothermoviedbapp.common.toNotNull
import com.example.yetanothermoviedbapp.domain.use_case.GetShowsListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class ShowsListViewModel(
    private val getShowsListUseCase: GetShowsListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ShowsListState())
    val state = _state.asStateFlow()

    init {
        _state.value = ShowsListState()
    }

    fun getShowsList(shouldRefresh: Boolean) {
        _state.update { it.copy(eventName = ShowsListViewModelEvents.Loading) }

        getShowsListUseCase(shouldRefresh).onEach { result ->
            when (result) {
                is Resource.Error -> _state.update { it.copy(eventName = ShowsListViewModelEvents.Error) }
                is Resource.Loading -> _state.update { it.copy(eventName = ShowsListViewModelEvents.Loading) }
                is Resource.Success -> _state.update {
                    it.copy(
                        eventName = ShowsListViewModelEvents.Success,
                        items = result.data.toNotNull()
                    )
                }
                else -> _state.update { it.copy(eventName = ShowsListViewModelEvents.None) }
            }

        }.launchIn(viewModelScope)
    }

}