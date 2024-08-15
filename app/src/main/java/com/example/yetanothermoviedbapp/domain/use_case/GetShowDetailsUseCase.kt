package com.example.yetanothermoviedbapp.domain.use_case

import com.example.yetanothermoviedbapp.common.ErrorMessages
import com.example.yetanothermoviedbapp.common.Resource
import com.example.yetanothermoviedbapp.data.mappers.ShowDetailsMapper
import com.example.yetanothermoviedbapp.domain.models.ShowDetails
import com.example.yetanothermoviedbapp.domain.repository.ShowsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetShowDetailsUseCase(
    private val repo: ShowsRepo
) {

    operator fun invoke(showId: Int) : Flow<Resource<ShowDetails>> = flow {
        try {
            val showDetails = repo.getShowDetails(showId).let { result ->
                ShowDetailsMapper.modelToDomain(result)
            }
            emit(Resource.Success(showDetails))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: ErrorMessages.UNEXPECTED_ERROR))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: ErrorMessages.INTERNET_ERROR))
        }
    }
}