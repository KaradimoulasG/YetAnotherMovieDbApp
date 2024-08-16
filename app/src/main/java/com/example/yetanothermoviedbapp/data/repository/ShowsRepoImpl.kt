package com.example.yetanothermoviedbapp.data.repository

import com.example.yetanothermoviedbapp.data.mappers.ShowsListMapper
import com.example.yetanothermoviedbapp.data.persistence.ShowsDao
import com.example.yetanothermoviedbapp.data.remote.TvMazeApi
import com.example.yetanothermoviedbapp.domain.repository.ShowsRepo
import org.koin.core.component.KoinComponent

class ShowsRepoImpl(
    private val api: TvMazeApi,
    private val dao: ShowsDao
) : ShowsRepo, KoinComponent {

//    private val dao: ShowsDao by inject()

//        override suspend fun getShowsList(): Flow<List<ShowsDtoItem>?> =
//        networkBoundResource(
//            query = {
//                dao.getSavedShows()
//            },
//            fetch = {
//                api.getShows()
//            },
//            saveFetchResult = {
//                dao.saveShowsList(it.toShowsList())
//            }
//        ) {
//            it?.isEmpty() ?: true
//        }

    override suspend fun getShowsList() =
        dao.getSavedShows().ifEmpty {
            val showsList = api.getShows().let {
                ShowsListMapper.modelToDomain(it)
            }
            dao.saveShowsList(showsList)
            showsList
        }


    override suspend fun getShowDetails(showId: Int) =
        api.getShowDetails(showId)
}