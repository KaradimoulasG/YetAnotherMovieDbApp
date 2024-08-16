package com.example.yetanothermoviedbapp.data.repository

import com.example.yetanothermoviedbapp.data.mappers.ShowsListMapper
import com.example.yetanothermoviedbapp.data.persistence.ShowsDao
import com.example.yetanothermoviedbapp.data.remote.TvMazeApi
import com.example.yetanothermoviedbapp.domain.models.ShowsListItem
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

    override suspend fun getShowsList(shouldRefresh: Boolean): List<ShowsListItem> {

        return when (shouldRefresh) {
            true ->
                api.getShows().let {
                    ShowsListMapper.modelToDomain(it)
                }

            false -> {
                if(dao.getSavedShows().isEmpty()) {
                    val showsList = api.getShows().let {
                        ShowsListMapper.modelToDomain(it)
                    }
                    dao.saveShowsList(showsList)
                    return showsList
                } else {
                    return dao.getSavedShows()
                }
            }
        }
    }


    override suspend fun getShowDetails(showId: Int) =
        api.getShowDetails(showId)
}