package practice.omdb.junseokseo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import practice.omdb.junseokseo.Constants
import practice.omdb.junseokseo.repository.SearchRepository
import practice.omdb.junseokseo.ui.model.MovieDetailUiModel
import practice.omdb.junseokseo.ui.model.MovieUiModel
import practice.omdb.junseokseo.ui.model.UiTransaction
import practice.omdb.junseokseo.ui.model.toMovieDetailUiModel
import practice.omdb.junseokseo.utils.Event
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private var userKeyWord = "star"
    private var pageNum = Constants.START_PAGE_NUMBER

    val transactionEvent: MutableLiveData<Event<UiTransaction>> = MutableLiveData()

    val errorText: LiveData<String>
        get() = _errorText
    private val _errorText: MutableLiveData<String> = MutableLiveData()

    val movieList: LiveData<List<MovieUiModel>>
        get() = _movieList
    private val _movieList: MutableLiveData<List<MovieUiModel>> = MutableLiveData()

    val detailUiModel: LiveData<MovieDetailUiModel>
        get() = _detailUiModel
    private val _detailUiModel: MutableLiveData<MovieDetailUiModel> = MutableLiveData()

    init {
        getMovieList(userKeyWord, Constants.START_PAGE_NUMBER)
    }

    fun getSearch(keyword: String?) {
        if (keyword.isNullOrEmpty()) {
            return
        }
        userKeyWord = keyword
        pageNum = 1
        getMovieList(keyword, pageNum)
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
        viewModelScope.launch {
//            val repositorySearch = searchRepository.getSearchResult()
//            val searchResult = repositorySearch.getMovieList(
//                Constants.REST_API_KEY,
//                keyword,
//                pageNum,
//                Constants.SEARCH_TYPE
//            )
//            searchResult.onSuccess {
//                Log.d("ViewModelxx", "it: ${it.toString()}")
//                val movieListDto = it.Search
//                val uiMovieList = MutableList(movieListDto.size) { index ->
//                    movieListDto[index].toMovieUiModel()
//                }
//                _movieList.value = uiMovieList
//            }
            //TODO RESPONSE IS COMING TO 200 ERROR KEYWORD
//            searchResult.onFailure {
//                _errorText.value = "에러가 발생했습니다. 다시 시도해 주세요"
//            }

            val list = mutableListOf(
                MovieUiModel(
                    title = "Star Trek Into Darkness",
                    year = 2013,
                    id = "tt1408101",
                    posterUrl = "https://m.media-amazon.com/images/M/MV5BMTk2NzczOTgxNF5BMl5BanBnXkFtZTcwODQ5ODczOQ@@._V1_SX300.jpg"
                ),
                MovieUiModel(
                    title = "Star Wars: Episode IX - The Rise of Skywalker",
                    year = 2019,
                    id = "tt2527338",
                    posterUrl = "https://m.media-amazon.com/images/M/MV5BMDljNTQ5ODItZmQwMy00M2ExLTljOTQtZTVjNGE2NTg0NGIxXkEyXkFqcGdeQXVyODkzNTgxMDg@._V1_SX300.jpg"
                ),
                MovieUiModel(
                    title = "Star Trek Into Darkness",
                    year = 2013,
                    id = "tt1408101",
                    posterUrl = "https://m.media-amazon.com/images/M/MV5BMTk2NzczOTgxNF5BMl5BanBnXkFtZTcwODQ5ODczOQ@@._V1_SX300.jpg"
                ),
                MovieUiModel(
                    title = "Star Trek Into Darkness",
                    year = 2013,
                    id = "tt1408101",
                    posterUrl = "https://m.media-amazon.com/images/M/MV5BMTk2NzczOTgxNF5BMl5BanBnXkFtZTcwODQ5ODczOQ@@._V1_SX300.jpg"
                ),
                MovieUiModel(
                    title = "Star Wars: Episode IX - The Rise of Skywalker",
                    year = 2019,
                    id = "tt2527338",
                    posterUrl = "https://m.media-amazon.com/images/M/MV5BMDljNTQ5ODItZmQwMy00M2ExLTljOTQtZTVjNGE2NTg0NGIxXkEyXkFqcGdeQXVyODkzNTgxMDg@._V1_SX300.jpg"
                ),
                MovieUiModel(
                    title = "Star Trek Into Darkness",
                    year = 2013,
                    id = "tt1408101",
                    posterUrl = "https://m.media-amazon.com/images/M/MV5BMTk2NzczOTgxNF5BMl5BanBnXkFtZTcwODQ5ODczOQ@@._V1_SX300.jpg"
                ),
                MovieUiModel(
                    title = "Star Trek Into Darkness",
                    year = 2013,
                    id = "tt1408101",
                    posterUrl = "https://m.media-amazon.com/images/M/MV5BMTk2NzczOTgxNF5BMl5BanBnXkFtZTcwODQ5ODczOQ@@._V1_SX300.jpg"
                ),
                MovieUiModel(
                    title = "Star Wars: Episode IX - The Rise of Skywalker",
                    year = 2019,
                    id = "tt2527338",
                    posterUrl = "https://m.media-amazon.com/images/M/MV5BMDljNTQ5ODItZmQwMy00M2ExLTljOTQtZTVjNGE2NTg0NGIxXkEyXkFqcGdeQXVyODkzNTgxMDg@._V1_SX300.jpg"
                ),
                MovieUiModel(
                    title = "Star Trek Into Darkness",
                    year = 2013,
                    id = "tt1408101",
                    posterUrl = "https://m.media-amazon.com/images/M/MV5BMTk2NzczOTgxNF5BMl5BanBnXkFtZTcwODQ5ODczOQ@@._V1_SX300.jpg"
                ),
                MovieUiModel(
                    title = "Star Trek Into Darkness",
                    year = 2013,
                    id = "tt1408101",
                    posterUrl = "https://m.media-amazon.com/images/M/MV5BMTk2NzczOTgxNF5BMl5BanBnXkFtZTcwODQ5ODczOQ@@._V1_SX300.jpg"
                )
            )
            _movieList.value = list
        }
    }
}