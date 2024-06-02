package practice.omdb.junseokseo.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import practice.omdb.junseokseo.Constants
import practice.omdb.junseokseo.R
import practice.omdb.junseokseo.repository.SearchRepository
import practice.omdb.junseokseo.ui.model.MovieDetailUiModel
import practice.omdb.junseokseo.ui.model.MovieUiModel
import practice.omdb.junseokseo.ui.model.UiTransaction
import practice.omdb.junseokseo.ui.model.toMovieDetailUiModel
import practice.omdb.junseokseo.ui.model.toMovieUiModel
import practice.omdb.junseokseo.utils.Event
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var userKeyWord = Constants.DEFAULT_KEY_WORD
    private var pageNum = Constants.START_PAGE_NUMBER

    val transactionEvent: MutableLiveData<Event<UiTransaction>> = MutableLiveData()

    val errorText: LiveData<Int>
        get() = _errorText
    private val _errorText: MutableLiveData<Int> = MutableLiveData()

    val movieList: LiveData<List<MovieUiModel>>
        get() = _movieList
    private val _movieList: MutableLiveData<List<MovieUiModel>> = MutableLiveData()

    val detailUiModel: LiveData<MovieDetailUiModel>
        get() = _detailUiModel
    private val _detailUiModel: MutableLiveData<MovieDetailUiModel> = MutableLiveData()

    companion object {
        private const val USER_KEYWORD_SAVED = "user_keyword"
    }

    init {
        userKeyWord = savedStateHandle.get<String>(USER_KEYWORD_SAVED) ?: Constants.DEFAULT_KEY_WORD
        getMovieList(userKeyWord, Constants.START_PAGE_NUMBER)
    }

    fun changeKeyword(keyword: String?) {
        if (keyword.isNullOrEmpty()) {
            return
        }
        userKeyWord = keyword
        Log.d("SearchViewModelxx", "userKeyword: $userKeyWord")
    }

    fun getSearch() {
        getMovieList(userKeyWord, pageNum)
    }

    fun getDetailInfo(id: String) {
        viewModelScope.launch {
            val repositorySearch = searchRepository.getSearchResult()
            val detailResult = repositorySearch.getMovieDetail(Constants.REST_API_KEY, id)
            detailResult.onSuccess { detailDTO ->
                val detailUiModel = detailDTO.toMovieDetailUiModel()
                _detailUiModel.value = detailUiModel
            }
        }
    }

    private fun getMovieList(keyword: String, pageNum: Int) {
        savedStateHandle[USER_KEYWORD_SAVED] = keyword

        viewModelScope.launch {
            val repositorySearch = searchRepository.getSearchResult()
            val searchResult = repositorySearch.getMovieList(
                Constants.REST_API_KEY,
                keyword,
                pageNum,
                Constants.SEARCH_TYPE
            )
            searchResult.onSuccess { searchDto ->
                if (searchDto.Response == "True") {
                    val movieListDto = searchDto.Search
                    val uiMovieList = MutableList(movieListDto.size) { index ->
                        movieListDto[index].toMovieUiModel()
                    }
                    _movieList.value = uiMovieList
                } else {
                    _errorText.value = R.string.search_not_found_text
                }
            }
            searchResult.onFailure {
                _errorText.value = R.string.error_text
            }
        }
    }
}