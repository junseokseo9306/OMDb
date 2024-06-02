package practice.omdb.junseokseo.repository

import practice.omdb.junseokseo.api.OMDbApi

interface SearchRepository {
    fun getSearchResult(): OMDbApi
}