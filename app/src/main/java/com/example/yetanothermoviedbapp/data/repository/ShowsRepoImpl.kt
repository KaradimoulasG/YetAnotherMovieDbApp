package com.example.yetanothermoviedbapp.data.repository

import com.example.yetanothermoviedbapp.data.persistence.ShowsDao
import com.example.yetanothermoviedbapp.data.remote.TvMazeApi
import com.example.yetanothermoviedbapp.domain.repository.ShowsRepo
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ShowsRepoImpl(
    private val api: TvMazeApi,
    private val dao: ShowsDao
) : ShowsRepo, KoinComponent {

//    private val dao: ShowsDao by inject()

    //    override suspend fun getShowsList(): Flow<Resource<out List<ShowsList>?>> =
//        networkBoundResource(
//            query = {
//                dao.getSavedShows()
//            },
//            fetch = {
//                api.getShows()
//            },
//            saveFetchResult = {
//                dao.saveShowsList(it.toShowsList())
//            },
//            shouldFetch = {
//                it?.isEmpty() ?: true
//            }
//        )
    override suspend fun getShowsList() =
        api.getShows()

    override suspend fun getShowDetails(showId: Int) =
        api.getShowDetails(showId)
}