package com.example.yetanothermoviedbapp.domain.use_case

import com.example.yetanothermoviedbapp.common.ErrorMessages
import com.example.yetanothermoviedbapp.common.Resource
import com.example.yetanothermoviedbapp.data.mappers.ShowsListMapper
import com.example.yetanothermoviedbapp.domain.models.ShowsListItem
import com.example.yetanothermoviedbapp.domain.repository.ShowsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetShowsListUseCase(
    private val repo: ShowsRepo
) {

    operator fun invoke(): Flow<Resource<List<ShowsListItem>>> = flow {
        try {
            val showsList = repo.getShowsList().let { result ->
                ShowsListMapper.modelToDomain(result)
            }
            emit(Resource.Success(showsList))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: ErrorMessages.UNEXPECTED_ERROR))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: ErrorMessages.INTERNET_ERROR))
        }
    }
}