package practice.omdb.junseokseo.repository

import practice.omdb.junseokseo.api.OMDbApi
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val omDbApi: OMDbApi
) : SearchRepository {
    override fun getSearchResult() = omDbApi
}