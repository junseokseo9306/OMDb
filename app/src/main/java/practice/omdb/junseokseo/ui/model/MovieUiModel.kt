package practice.omdb.junseokseo.ui.model

import practice.omdb.junseokseo.api.model.MovieDTO

data class MovieUiModel(
    val title: String,
    val year: Int,
    val id: String,
    val posterUrl: String
)

fun MovieDTO.toMovieUiModel(): MovieUiModel =
    MovieUiModel(
        title = Title,
        year = Year.toInt(),
        id = imdbID,
        posterUrl = Poster
    )