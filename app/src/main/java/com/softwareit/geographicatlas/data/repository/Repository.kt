package com.softwareit.geographicatlas.data.repository

import com.softwareit.geographicatlas.data.remote.RemoteDataSource
import javax.inject.Inject

class Repository @Inject constructor(
    characterRemoteDataSource: RemoteDataSource,
//    localDataSource: LocalDataSource
) {
    private val TAG: String = "Repository"

    val remote = characterRemoteDataSource
//    val local = localDataSource
}